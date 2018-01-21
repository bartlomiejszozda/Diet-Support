
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
 
   public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		  
        return conn;
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
							  rs.getDouble("weglowodany"),rs.getDouble("tluszcze"));
					
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
				Meal mealTmp=new Meal(idPosilek,namePosilek,ingrArrTmp);
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
							  rs.getDouble("weglowodany"),rs.getDouble("tluszcze"));
					
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
						  rs.getDouble("weglowodany"),rs.getDouble("tluszcze"));
					Ingredient ingr=new Ingredient(tmpProduct,rs.getDouble("masaproduktu") );
					ingrArrTmp.add(ingr);
					nextFlag=rs.next();
				}
				Meal mealTmp=new Meal(idPosilek,namePosilek,ingrArrTmp);
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
								 PreparedStatement pstmt2 = conn2.prepareStatement(SQL2,Statement.RETURN_GENERATED_KEYS)) {
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
	
	
}

