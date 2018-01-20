import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserServlets extends HttpServlet {
	UserDB userdb=null;
	@Override
   public void init(ServletConfig config) throws ServletException {
		super.init(config);
		userdb=new UserDB();
   }

	@Override
   public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		if (req.getParameter("regDiv") == null) {
			logIn(req, resp, req.getParameter("login"),req.getParameter("password"));
		}
		else if (req.getParameter("logDiv") == null) {
			signUp(resp,req.getParameter("email"),req.getParameter("login"),req.getParameter("password"));
		}
   }
	private void signUp(HttpServletResponse resp,String email, String login, String password)throws ServletException, IOException
	{				
		if(isSignedUp(email,login)){
		createResponseSite(resp,"Adres email lub nazwa użytkownika zajęta!" );
		}
		else{	
			userdb.signUp(email,login,password);
			createResponseSite(resp,"Gratulacje, jesteś już zarejestrowany! :)" );
		}
	}
	private boolean isSignedUp(String email, String login)
	{
		return !(userdb.findUserByEmail(email)==null && userdb.findUserByLogin(login)==null);
	}
	private void logIn(HttpServletRequest req,HttpServletResponse resp, String login, String password)throws ServletException, IOException
	{
		if(loginPasswordCorrect(login,password))
		{
			HttpSession session=req.getSession();  
			session.setAttribute("login",login);  
			createResponseSite(resp,"Witaj!"+ login );
		}
		else
		{	
			createResponseSite(resp,"email lub login zajęty","Wróć do strony głównej i spróbuj ponownie.");
		}
	}
	private boolean isLoggedIn(HttpServletRequest req,String login)//isnt tested
	{
		HttpSession session=req.getSession();  
      return login.equals((String)session.getAttribute("login"));
	}
	private boolean loginPasswordCorrect(String login, String password)
	{
		return userdb.loginPasswordCorrect(login,password);
	}
	private void createResponseSite(HttpServletResponse resp,String str1,String str2,String str3) throws IOException 
	{
		try (PrintWriter out = resp.getWriter()) {
			resp.setContentType("text/html");
			out.println("<HTML><HEAD><TITLE>");
			out.println(":)");
			out.println("</TITLE></HEAD><BODY>");
			out.println("<H1><CENTER> "+str1 +" </CENTER></H1>");
			out.println("<H1><CENTER> "+str2 +" </CENTER></H1>");
			out.println("<H1><CENTER> "+str3 +" </CENTER></H1>");
			out.println("<H1><CENTER> <a href='index.html'>Wróć do strony głównej</a></CENTER></H1>");
			out.println("</BODY></HTML>");
			}
	}
	private void createResponseSite(HttpServletResponse resp,String str1,String str2) throws IOException 
	{
		try (PrintWriter out = resp.getWriter()) {
			resp.setContentType("text/html");
			out.println("<HTML><HEAD><TITLE>");
			out.println(":)");
			out.println("</TITLE></HEAD><BODY>");
			out.println("<H1><CENTER> "+str1 +" </CENTER></H1>");
			out.println("<H1><CENTER> "+str2 +" </CENTER></H1>");
			out.println("<H1><CENTER> <a href='index.html'>Wróć do strony głównej</a></CENTER></H1>");
			out.println("</BODY></HTML>");
			}
	}
	private void createResponseSite(HttpServletResponse resp,String str1) throws IOException 
	{
		try (PrintWriter out = resp.getWriter()) {
			resp.setContentType("text/html");
			out.println("<HTML><HEAD><TITLE>");
			out.println(":)");
			out.println("</TITLE></HEAD><BODY>");
			out.println("<H1><CENTER> "+str1 +" </CENTER></H1>");
			out.println("<H1><CENTER> <a href='index.html'>Wróć do strony głównej</a></CENTER></H1>");
			out.println("</BODY></HTML>");
			}
	}
	/*
	private void displayHTML(HttpServletResponse resp, String fn, String ln)
         throws ServletException, IOException {
		//System.out.println(jdbc.getProductCount());
				
			try (PrintWriter out = resp.getWriter()) {
			//System.out.println(jdbc.getProductCount());
			//jdbc.getProducts();
			resp.setContentType("text/html");
			out.println("<HTML><HEAD><TITLE>");
			out.println("Registrar Office");
			out.println("</TITLE></HEAD><BODY>");
			out.println("<H1><CENTER>Hello, " + fn + " " + ln + "</CENTER></H1>");
			out.println("<H1><CENTER>Your first servlet works!</CENTER></H1>");
			out.println("</BODY></HTML>");
		}
		catch(Exception e)
		{
			
		}
   }*/
}
