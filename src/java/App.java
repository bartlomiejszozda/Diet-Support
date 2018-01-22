/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class App{
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

    public int getProductCount() {
        String SQL = "SELECT count(*) FROM Produkt";
        int count = 0;
 
        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
 
        return count;
    }

    public String getProducts() {
 
        String SQL = "SELECT nazwa,kcal,bialko,weglowodany,tluszcze FROM Produkt";
 
        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL)) {
            return displayProduct(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
		  return "blad";
    }
    private String displayProduct(ResultSet rs) throws SQLException {
		 String outString="<pre>";
        while (rs.next()) {
            outString+=rs.getString("nazwa") + "   "
                    +rs.getString("kcal") + "   "
                    + rs.getString("weglowodany") + "   "
                    + rs.getString("tluszcze");
 
        }
		  outString+="<pre>";
		  return outString;
    }
    public void findProductById(int ProductId) {
        String SQL = "SELECT nazwa,kcal,bialko,weglowodany,tluszcze FROM Produkt "
                + "WHERE IdProdukt = ?";
 
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, ProductId);
            ResultSet rs = pstmt.executeQuery();
            displayProduct(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
*/