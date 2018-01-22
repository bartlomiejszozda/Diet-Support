
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

 
public class LoggedInDB{
   private final String url = "jdbc:postgresql://horton.elephantsql.com:5432/jsiobneg";
   private final String user = "jsiobneg";
   private final String password = "58gEk-EzfB-T-SM14iQzoMy_pKz4GQZg";
 Connection conn = null;
   public Connection connect() {
        
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		  
        return conn;
    }
	
protected void finalize() throws Throwable  
{  
    try { conn.close(); } 
    catch (SQLException e) { 
        e.printStackTrace();
    }
    super.finalize();  
}  

	public ArrayList<TableObject> addProductByName(String productName,Double kcal, Double proteins, Double carbo, Double fat)
	{
		String SQL = "INSERT INTO Produkt (nazwa,kcal,bialko,weglowodany,tluszcze) "
                + "VALUES(?,?,?,?,?)";
		ArrayList<TableObject> productArr=new ArrayList<>();
		try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS)) {
				int id=0;
            pstmt.setString(1, productName);
            pstmt.setDouble(2, kcal);
				pstmt.setDouble(3, proteins);
				pstmt.setDouble(4, carbo);
				pstmt.setDouble(5, fat);
				int affectedRows = pstmt.executeUpdate();
				if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
				
				productArr=new ArrayList<>();
				productArr.add(new Product(id,productName,kcal,proteins,carbo,fat));
				
				return productArr;
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
				return null;
        }
	}
		public ArrayList<TableObject> showAllProducts()
	{
		String SQL = "SELECT * FROM Produkt ";

		ArrayList<TableObject> output = new ArrayList<>();
		try (Connection conn = connect();
            Statement pstmt = conn.createStatement()) {		
            ResultSet rs = pstmt.executeQuery(SQL);
				

				while(rs.next()){
					Product tmp= new Product(rs.getInt("idProdukt"),rs.getString("nazwa"),
							  rs.getDouble("kcal"),rs.getDouble("bialko"),
							  rs.getDouble("weglowodany"),rs.getDouble("tluszcze"),0);
					
					output.add(tmp);
					}
            return output;
			} catch (SQLException ex) {
				 System.out.println(ex.getMessage());
			}
			return output;
	}
	public ArrayList<TableObject> showAllMeals()
	{
		String SQL = "SELECT * FROM PosilekProduktWszystkieKolumny ";

		ArrayList<TableObject> output = new ArrayList<>();
		try (Connection conn = connect();
			Statement pstmt = conn.createStatement()) {		
			ResultSet rs = pstmt.executeQuery(SQL);

			boolean nextFlag = false;
			if(rs.next())
				nextFlag = true;
			while(nextFlag){
				Integer idPosilek = rs.getInt("idposilek");
				String namePosilek = rs.getString("Nazwa Posilku");
				ArrayList<Ingredient> ingrArrTmp=new ArrayList<>();
				while(nextFlag && idPosilek.equals(rs.getInt("idposilek"))){
				Product tmpProduct= new Product(rs.getInt("idprodukt"),rs.getString("nazwa"),
						  rs.getDouble("kcal"),rs.getDouble("bialko"),
						  rs.getDouble("weglowodany"),rs.getDouble("tluszcze"));
					Ingredient ingr=new Ingredient(tmpProduct,rs.getDouble("masaproduktu") );
					ingrArrTmp.add(ingr);
					nextFlag=rs.next();
				}
				Meal mealTmp=new Meal(idPosilek,namePosilek,ingrArrTmp,0);
				output.add(mealTmp);
			}
			return output;
		} catch (SQLException ex) {
			 System.out.println(ex.getMessage());
		}
		return output;

	}
	public ArrayList<TableObject> showMyProducts(Integer id)
	{
		String SQL = "SELECT * FROM MojeProduktyDlaId "
				  +"WHERE idUzytkownik = ?";
		System.out.println(id);
		System.out.println(id);
		System.out.println(id);System.out.println(id);
		
		ArrayList<TableObject> output = new ArrayList<>();
		try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {		
				pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
				

				while(rs.next()){
					Product tmp= new Product(rs.getInt("idProdukt"),rs.getString("nazwa"),
							  rs.getDouble("kcal"),rs.getDouble("bialko"),
							  rs.getDouble("weglowodany"),rs.getDouble("tluszcze"),1);
					
					output.add(tmp);
					}
            return output;
			} catch (SQLException ex) {
				 System.out.println(ex.getMessage());
			}
			return output;

	}
	public ArrayList<TableObject> showMyMeals(Integer id)
	{
		String SQL = "SELECT * FROM MojPosilekProduktWszystkieKolumny "
				  +"WHERE idUzytkownik = ?";

		ArrayList<TableObject> output = new ArrayList<>();
		try (Connection conn = connect();
			   PreparedStatement pstmt = conn.prepareStatement(SQL)) {		
				pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

			boolean nextFlag = false;
			if(rs.next())
				nextFlag = true;
			while(nextFlag){
				Integer idPosilek = rs.getInt("idposilek");
				String namePosilek = rs.getString("Nazwa Posilku");
				ArrayList<Ingredient> ingrArrTmp=new ArrayList<>();
				while(nextFlag && idPosilek.equals(rs.getInt("idposilek"))){
				Product tmpProduct= new Product(rs.getInt("idprodukt"),rs.getString("nazwa"),
						  rs.getDouble("kcal"),rs.getDouble("bialko"),
						  rs.getDouble("weglowodany"),rs.getDouble("tluszcze"),0);
					Ingredient ingr=new Ingredient(tmpProduct,rs.getDouble("masaproduktu") );
					ingrArrTmp.add(ingr);
					nextFlag=rs.next();
				}
				Meal mealTmp=new Meal(idPosilek,namePosilek,ingrArrTmp,1);
				output.add(mealTmp);
			}
			return output;
		} catch (SQLException ex) {
			 System.out.println(ex.getMessage());
		}
		return output;

	}
	public ArrayList<TableObject> setYourGoals(Double weight,Double kcal, Double proteins, Double carbo, Double fat,Integer userId)
	{
		String SQL = "INSERT INTO MojeCele (waga,kcal,bialko,weglowodany,tluszcze,uzytkownik_idUzytkownik) "
                + "VALUES(?,?,?,?,?,?)";
		ArrayList<TableObject> goalArr=new ArrayList<>();
		try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS)) {
				int id=0;
            pstmt.setDouble(1, weight);
            pstmt.setDouble(2, kcal);
				pstmt.setDouble(3, proteins);
				pstmt.setDouble(4, carbo);
				pstmt.setDouble(5, fat);
				pstmt.setDouble(6, userId);
				int affectedRows = pstmt.executeUpdate();
				if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
				
				goalArr=new ArrayList<>();
				goalArr.add(new Goal(id,weight,kcal,proteins,carbo,fat));
				
				return goalArr;
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
				return null;
        }
	}
	public ArrayList<TableObject> addYourWeight(Integer userId,Double weight, java.sql.Date sqlDate)
	{
		String SQL = "INSERT INTO MasaCiala (uzytkownik_idUzytkownik,masa,kiedy) "
                + "VALUES(?,?,?)";
		ArrayList<TableObject> weightArr=new ArrayList<>();
		try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS)) {
				int id=0;
            pstmt.setInt(1, userId);
				pstmt.setDouble(2, weight);
            pstmt.setDate(3,sqlDate );
				int affectedRows = pstmt.executeUpdate();
				if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
				
				weightArr=new ArrayList<>();
				weightArr.add(new Weight(id,weight,sqlDate));
				return weightArr;
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
				return null;
        }
	}
		public ArrayList<TableObject> addMyProduct(Integer userId,String productName,Double kcal, Double proteins, Double carbo, Double fat)
	{
		String SQL = "INSERT INTO Produkt (nazwa,kcal,bialko,weglowodany,tluszcze) "
                + "VALUES(?,?,?,?,?)";
		ArrayList<TableObject> productArr=new ArrayList<>();
		try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS)) {
				int id=0;
            pstmt.setString(1, productName);
            pstmt.setDouble(2, kcal);
				pstmt.setDouble(3, proteins);
				pstmt.setDouble(4, carbo);
				pstmt.setDouble(5, fat);
				int affectedRows = pstmt.executeUpdate();
				if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
								String SQL2 = "INSERT INTO Mojeprodukty (Produkt_idProdukt,Uzytkownik_idUzytkownik) "
								 + "VALUES(?,?)";
								try (Connection conn2 = connect();
								 PreparedStatement pstmt2 = conn2.prepareStatement(SQL2)) {
								pstmt2.setInt(1, id);
								pstmt2.setInt(2, userId);
								pstmt2.executeUpdate();
								} catch (SQLException ex) {
									System.out.println(ex.getMessage());
									return null;
								}
								
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
				
				productArr=new ArrayList<>();
				productArr.add(new Product(id,productName,kcal,proteins,carbo,fat));
				
				return productArr;
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
				return null;
        }
		
	}
	public boolean addMyProductById(Integer userId,Integer productId)
	{

		ArrayList<TableObject> productArr=new ArrayList<>();
		String SQL = "INSERT INTO Mojeprodukty (Produkt_idProdukt,Uzytkownik_idUzytkownik) "
		 + "VALUES(?,?)";
		try (Connection conn = connect();
		 PreparedStatement pstmt = conn.prepareStatement(SQL)) {
		pstmt.setInt(1, productId);
		pstmt.setInt(2, userId);
		pstmt.executeUpdate();
		return true;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}

		
	}
		
	public boolean addMyMealById(Integer userId, Integer mealId)
	{
		String SQL = "INSERT INTO MojePosilki (Uzytkownik_idUzytkownik, Posilek_idPosilek) "
                + "VALUES(?,?)";
		try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL) ){
            pstmt.setInt(1, userId);
            pstmt.setInt(2, mealId);
				pstmt.executeUpdate();

				return true;
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
				return false;
        }
		
	}
	public boolean deleteMyProductById(Integer userId,Integer productId)
	{

		ArrayList<TableObject> productArr=new ArrayList<>();
		String SQL = "DELETE FROM MojeProdukty WHERE Uzytkownik_idUzytkownik=? AND Produkt_idProdukt=? ";
		try (Connection conn = connect();
		 PreparedStatement pstmt = conn.prepareStatement(SQL)) {
		pstmt.setInt(1, userId);
		pstmt.setInt(2, productId);
		pstmt.executeUpdate();
		return true;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}

		
	}
		
	public boolean deleteMyMealById(Integer userId, Integer mealId)
	{
		String SQL = "DELETE FROM MojePosilki WHERE Uzytkownik_idUzytkownik=? AND Posilek_idPosilek=? ";
		try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL) ){
            pstmt.setInt(1, userId);
            pstmt.setInt(2, mealId);
				pstmt.executeUpdate();
				return true;
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
				return false;
        }
		
	}
	public Integer addMeal(String name, ArrayList<Integer> idArr,  ArrayList<Double> weightArr)
	{
		String SQL = "INSERT INTO Posilek (nazwa) "
                + "VALUES(?)";
		ArrayList<TableObject> productArr=new ArrayList<>();
		try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS)) {
				int id=0;
            pstmt.setString(1, name);
				int affectedRows = pstmt.executeUpdate();
				if (affectedRows > 0) {
               try (ResultSet rs = pstmt.getGeneratedKeys()) {
						if (rs.next()) {
							 id = rs.getInt(1);

							 for(int i=0;i<idArr.size() && i<weightArr.size();i++)
							 {
								 if(idArr.get(i)!=null && weightArr.get(i)!=null)
								 {
								 String SQL2 = "INSERT INTO Posilek_has_Produkt (Produkt_idProdukt,Posilek_idPosilek,masaProduktu) "
							  + "VALUES(?,?,?)";
								 try (Connection conn2 = connect();
							  PreparedStatement pstmt2 = conn2.prepareStatement(SQL2)) {
								 pstmt2.setInt(1, idArr.get(i));
								 pstmt2.setInt(2, id);
								 pstmt2.setDouble(3, weightArr.get(i));
								 
								 pstmt2.executeUpdate();
								 } catch (SQLException ex) {
								 System.out.println(ex.getMessage());
								 return id;
									}	
								}
							 }
						}
					}
               catch (SQLException ex) {
                    System.out.println(ex.getMessage());
						  return 0;
               }
            }
				
				return 0;
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
				return 0;
        }
	}
	public boolean eatThisMeal(Integer userId, Integer mealId, Double weight, java.sql.Date sqlDate)
	{
		String SQL = "INSERT INTO DzienZywienia (Uzytkownik_idUzytkownik,kiedy) SELECT ?,?"+
                 "WHERE NOT EXISTS (SELECT kiedy FROM DzienZywienia WHERE kiedy = ?);";
		try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS)) {
				int id=0;
            pstmt.setInt(1, userId);
				pstmt.setDate(2, sqlDate);
				pstmt.setDate(3, sqlDate);
				pstmt.executeUpdate(); 
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
				return false;
        }
				  String SQL2 = "INSERT INTO DzienZywienia_has_Posilek (DzienZywienia_idDzienZywienia,Posilek_idPosilek,iloscPorcji) "
				+ "VALUES(?,?,?)";
				  try (Connection conn2 = connect();
				 PreparedStatement pstmt2 = conn2.prepareStatement(SQL2)) {
					Integer key=getDzienZywieniaKeyByDate(sqlDate);
				  pstmt2.setInt(1, key);
				  pstmt2.setInt(2, mealId);
				  pstmt2.setDouble(3, weight);
				  pstmt2.executeUpdate();
				  return true;
				  } catch (SQLException ex) {
				  System.out.println(ex.getMessage());
				  return false;
					}	
 }

public Integer getDzienZywieniaKeyByDate(java.sql.Date sqlDate)
	{
		String SQL = "SELECT idDzienZywienia FROM DzienZywienia where kiedy=?";
		try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            System.out.println(sqlDate);
				pstmt.setDate(1, sqlDate);
				
				ResultSet rs = pstmt.executeQuery();
				if(rs.next())
					return rs.getInt("idDzienZywienia");
				return null;
			}catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}


public ArrayList<TableObject> showWeightHistory(Integer userId)
{
		String SQL = "SELECT * FROM MasaCiala WHERE Uzytkownik_idUzytkownik = ? ORDER BY kiedy";
		
		ArrayList<TableObject> output = new ArrayList<>();
		try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {
				pstmt.setInt(1,userId);
            ResultSet rs = pstmt.executeQuery();
				while(rs.next()){
					Weight tmp= new Weight(rs.getInt("Uzytkownik_idUzytkownik"),
							  rs.getDouble("masa"),rs.getDate("kiedy"));
					
					output.add(tmp);
					}
            return output;
			} catch (SQLException ex) {
				 System.out.println(ex.getMessage());
			}
			return output;

 }
public String showYourGoals(Integer userId)
{
		String SQL = "SELECT * FROM MojeCele WHERE Uzytkownik_idUzytkownik = ?";
		String output="";
		try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {
				pstmt.setInt(1,userId);
            ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
					output+="Waga: "+rs.getString("Waga")+"</br>"+
							  "kcal: "+rs.getString("kcal")+"</br>"+
							  "bialko: "+rs.getString("bialko")+"</br>"+
							  "weglowodany: "+rs.getString("weglowodany")+"</br>"+
							  "tluszcze: "+rs.getString("tluszcze")+"</br>";
				}
            return output;
			} catch (SQLException ex) {
				 System.out.println(ex.getMessage());
			}
			return output;

 }
public String showEatByDay(Integer userId, java.sql.Date sqlDate)
{
		String SQL = "SELECT * FROM ZjedzoneDnia WHERE kiedy = ? and uID=?";
		String output="";
		try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {
				pstmt.setDate(1,sqlDate);
				pstmt.setInt(2,userId);
            ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
					output+=
							  "<tr><th>"+rs.getString("kiedy")+"</th>"
							  + "<th> kcal: "+rs.getString("kcal")+"</th>"+
							  "<th> bialko: "+rs.getString("bialko")+"</th>"+
							  "<th> weglowodany: "+rs.getString("weglowodany")+"</th>"+
							  "<th> tluszcze: "+rs.getString("tluszcze")+"</tr>";
				}
            return output;
			} catch (SQLException ex) {
				 System.out.println(ex.getMessage());
			}
			return output;
}

}

