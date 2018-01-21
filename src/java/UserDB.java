
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
 
public class UserDB{
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
	public HashMap<String,String> getIdByLogin(String login){
		String SQL = "SELECT idUzytkownik FROM Uzytkownik "
                + "WHERE login = ?";
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
				HashMap<String,String> hashmap =null;
				if(rs.next()){
				hashmap = new HashMap<>();
				hashmap.put("id",rs.getString("idUzytkownik"));}
            return hashmap;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
		  return null;
	}
	public Integer getIntIdByLogin(String login){
		String SQL = "SELECT idUzytkownik FROM Uzytkownik "
                + "WHERE login = ?";
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
				if(rs.next())
            return rs.getInt("idUzytkownik");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
		  return null;
	}
   public HashMap<String,String> findUserByEmail(String userEmail) {
        String SQL = "SELECT email,login FROM Uzytkownik "
                + "WHERE email = ?";
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, userEmail);
            ResultSet rs = pstmt.executeQuery();
				HashMap<String,String> hashmap =null;
				if(rs.next()){
				hashmap = new HashMap<>();
				hashmap.put("email",rs.getString("email"));
				hashmap.put("login",rs.getString("login"));}
            return hashmap;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
		  return null;
    }
   public HashMap<String,String>  findUserByLogin(String userLogin) {
        String SQL = "SELECT email,login FROM Uzytkownik "
                + "WHERE login = ?";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, userLogin);
            ResultSet rs = pstmt.executeQuery();
				HashMap<String,String> hashmap=null;
				if(rs.next()){
				hashmap = new HashMap<>();
				hashmap.put("email",rs.getString("email"));
				hashmap.put("login",rs.getString("login"));}
            return hashmap;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
		  return null;
    }
	public boolean loginPasswordCorrect(String userLogin,String password) {
		String SQL = "SELECT email,login FROM Uzytkownik "
				  + "WHERE login = ? AND haslo = ?";
		try (Connection conn = connect();
				  PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			 pstmt.setString(1, userLogin);
			 pstmt.setString(2, password);
			 ResultSet rs = pstmt.executeQuery();
			
			 String[] output=null;	  
			  while(rs.next()) 
			 {output=new String[2];
			 output[0]= rs.getString("email");
			 output[1]=rs.getString("login");
			 //output[2]=rs.getString("haslo");
			 //System.out.println( output[0] +" " +  output[1] + " "+  output[2] +"\n");
			 }
			 return output!=null;
		} catch (SQLException ex) {
			 System.out.println(ex.getMessage());
		}
		return false;
    }
	public boolean   signUp(String userEmail,String userLogin,String userPassword) {
		String SQL = "INSERT INTO Uzytkownik (email,login,haslo) "
                + "VALUES(?,?,?)";
		try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS)) {
 
            pstmt.setString(1, userEmail);
            pstmt.setString(2, userLogin);
				pstmt.setString(3, userPassword);
				pstmt.executeUpdate();
				return true;
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
				return false;
        }
		
	}
	/*
	public static void main(String[] args) {
		UserDB app = new UserDB();
		app.connect();
		app.loginPasswordCorrect("mojemail@gmail.com", "mojehaslo");
	}*/
}

