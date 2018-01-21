
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

 
public class SearchDB{
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
public ArrayList<TableObject> searchProductByName(String name)throws IOException
	{
		String SQL = "SELECT * FROM Produkt "
                + "WHERE nazwa LIKE ? " ;

		ArrayList<TableObject> output = new ArrayList<>();
		try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {		
            pstmt.setString(1, "%"+name+"%");
            ResultSet rs = pstmt.executeQuery();
				

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
	public ArrayList<TableObject>  searchMealByName(String name)throws IOException
	{
		String SQL = "SELECT * FROM PosilekProduktWszystkieKolumny "
                + "WHERE \"Nazwa Posilku\" LIKE ? " ;
		
		ArrayList<TableObject> output = new ArrayList<>();
		try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {		
            pstmt.setString(1, "%"+name+"%");
            ResultSet rs = pstmt.executeQuery();
				boolean nextFlag = false;
				if(rs.next())
					nextFlag = true;
				while(nextFlag){
					Integer idPosilek = rs.getInt("idposilek");
					String namePosilek = rs.getString("Nazwa Posilku");
					ArrayList<Ingredient> ingrArrTmp=new ArrayList<>();
					while(nextFlag && idPosilek.equals(rs.getInt("idposilek"))){
											System.out.println(rs.getString("Nazwa Posilku")+rs.getString("idPosilek")+rs.getString("nazwa")+rs.getString("idprodukt"));

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
	public ArrayList<TableObject> searchMealByProduct(String name)throws IOException
	{
		String SQL = "Select * from PosilekZProduktemWszystkieKolumny(?)" ;
		
				ArrayList<TableObject> output = new ArrayList<>();
		try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {		
            pstmt.setString(1, "%"+name+"%");
            ResultSet rs = pstmt.executeQuery();
				boolean nextFlag = false;
				if(rs.next())
					nextFlag = true;
				while(nextFlag){
					Integer idPosilek = rs.getInt("idposilek");
					String namePosilek = rs.getString("Nazwa Posilku");
					ArrayList<Ingredient> ingrArrTmp=new ArrayList<>();
					while(nextFlag && idPosilek.equals(rs.getInt("idposilek"))){
											System.out.println(rs.getString("Nazwa Posilku")+rs.getString("idPosilek")+rs.getString("nazwa")+rs.getString("idprodukt"));

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
	public ArrayList<ArrayList<String>> searchProductByNameAsString(String name)throws IOException
	{
		String SQL = "SELECT * FROM Produkt "
                + "WHERE nazwa LIKE ? " ;

		ArrayList<ArrayList<String>> output = new ArrayList<>();
		try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {		
            pstmt.setString(1, "%"+name+"%");
            ResultSet rs = pstmt.executeQuery();
				ArrayList<String> tmp= new ArrayList<>();
				if(rs.next()){
				tmp.add(("nazwa"));
				tmp.add(("kcal"));
				tmp.add(("bialko"));
				tmp.add(("weglowodany"));
				tmp.add(("tluszcze"));
				output.add(tmp);
				tmp= new ArrayList<>();
				tmp.add(rs.getString("nazwa"));
				tmp.add(rs.getString("kcal"));
				tmp.add(rs.getString("bialko"));
				tmp.add(rs.getString("weglowodany"));
				tmp.add(rs.getString("tluszcze"));
				output.add(tmp);
				}
				while(rs.next()){
					tmp= new ArrayList<>();
					tmp.add(rs.getString("nazwa"));
					tmp.add(rs.getString("kcal"));
					tmp.add(rs.getString("bialko"));
					tmp.add(rs.getString("weglowodany"));
					tmp.add(rs.getString("tluszcze"));
					output.add(tmp);
				}
            return output;
			} catch (SQLException ex) {
				 System.out.println(ex.getMessage());
			}
			return output;
	}
	public ArrayList<ArrayList<String>>  searchMealByNameAsString(String name)throws IOException
	{
		String SQL = "SELECT * FROM PosilekProdukt "
                + "WHERE \"Nazwa Posilku\" LIKE ? " ;
		ArrayList<ArrayList<String>> output = new ArrayList<>();

		try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {		
            pstmt.setString(1, "%"+name+"%");
            ResultSet rs = pstmt.executeQuery();
				ArrayList<String> tmp= new ArrayList<>();
				if(rs.next()){
				tmp.add(("Nazwa Posiłku"));
				tmp.add(("Masa produktu(gram)"));
				tmp.add(("Nazwa"));
				tmp.add(("kcal"));
				tmp.add(("białko"));
				tmp.add(("węglowodany"));
				tmp.add(("tłuszcze"));
				output.add(tmp);
				tmp= new ArrayList<>();
				tmp.add(rs.getString("Nazwa Posilku"));
				tmp.add(rs.getString("masaproduktu"));
				tmp.add(rs.getString("nazwa"));
				tmp.add(rs.getString("kcal"));
				tmp.add(rs.getString("bialko"));
				tmp.add(rs.getString("weglowodany"));
				tmp.add(rs.getString("tluszcze"));
				output.add(tmp);
				}
				while(rs.next()){
					tmp= new ArrayList<>();
					tmp.add(rs.getString("Nazwa Posilku"));
					tmp.add(rs.getString("masaproduktu"));
					tmp.add(rs.getString("nazwa"));
					tmp.add(rs.getString("kcal"));
					tmp.add(rs.getString("bialko"));
					tmp.add(rs.getString("weglowodany"));
					tmp.add(rs.getString("tluszcze"));
					output.add(tmp);
				}
            return output;
			} catch (SQLException ex) {
				 System.out.println(ex.getMessage());
			}
			return output;
	}
	public  ArrayList<ArrayList<String>>  searchMealByProductAsString(String name)throws IOException
	{
		String SQL = "Select * from PosilekZProduktem(?)" ;
		
		ArrayList<ArrayList<String>> output = new ArrayList<>();

		try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {		
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
				ArrayList<String> tmp= new ArrayList<>();
				if(rs.next()){
				tmp.add(("Nazwa Posiłku"));
				tmp.add(("Masa produktu(gram)"));
				tmp.add(("Nazwa"));
				tmp.add(("kcal"));
				tmp.add(("białko"));
				tmp.add(("węglowodany"));
				tmp.add(("tłuszcze"));
				output.add(tmp);
				tmp= new ArrayList<>();
				tmp.add(rs.getString("Nazwa Posilku"));
				tmp.add(rs.getString("masaproduktu"));
				tmp.add(rs.getString("nazwa"));
				tmp.add(rs.getString("kcal"));
				tmp.add(rs.getString("bialko"));
				tmp.add(rs.getString("weglowodany"));
				tmp.add(rs.getString("tluszcze"));
				output.add(tmp);
				}
				while(rs.next()){
					tmp= new ArrayList<>();
					tmp.add(rs.getString("Nazwa Posilku"));
					tmp.add(rs.getString("masaproduktu"));
					tmp.add(rs.getString("nazwa"));
					tmp.add(rs.getString("kcal"));
					tmp.add(rs.getString("bialko"));
					tmp.add(rs.getString("weglowodany"));
					tmp.add(rs.getString("tluszcze"));
					output.add(tmp);
				}
            return output;
			} catch (SQLException ex) {
				 System.out.println(ex.getMessage());
			}
			return output;
	}
	public HashMap<String,Double[]> searchProductByNameHashMapReturned(String name)throws IOException
	{
		String SQL = "SELECT * FROM Produkt "
                + "WHERE nazwa LIKE ? " ;

		HashMap<String,Double[]> hashmap = new HashMap<>();
		

		try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {		
            pstmt.setString(1, "%"+name+"%");
            ResultSet rs = pstmt.executeQuery();
				while(rs.next()){
					System.out.println(rs.getDouble("kcal"));
					Double[] dbArr=new Double[4];
					dbArr[0]=rs.getDouble("kcal");
					dbArr[1]=rs.getDouble("bialko");
					dbArr[2]=rs.getDouble("weglowodany");
					dbArr[3]=rs.getDouble("tluszcze");
					hashmap.put(rs.getString("nazwa"),dbArr);
				}
            return hashmap;
			} catch (SQLException ex) {
				 System.out.println(ex.getMessage());
			}
			return hashmap;
	}
	
}

