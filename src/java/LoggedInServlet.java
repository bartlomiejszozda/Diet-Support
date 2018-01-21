import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoggedInServlet extends HttpServlet {
	LoggedInDB loggedinDB=null;
	@Override
   public void init(ServletConfig config) throws ServletException {
		super.init(config);
		loggedinDB=new LoggedInDB();
   }

	@Override
   public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		if (req.getParameter("formName").equals("logOutForm")) {
			logOut(req,resp);
		}
		else if (req.getParameter("formName").equals("addProductByName")) {
			addProductByName(req,resp);
		}
		else if (req.getParameter("formName").equals("showAllProducts")) {
			showAllProducts(req,resp);
		}
		else if (req.getParameter("formName").equals("showAllMeals")) {
			showAllMeals(req,resp);
		}
		else if (req.getParameter("formName").equals("showMyProducts")) {
			showMyProducts(req,resp);
		}
		else if (req.getParameter("formName").equals("showMyMeals")) {
			showMyMeals(req,resp);
		}
		else if (req.getParameter("formName").equals("setYourGoals")) {
			setYourGoals(req,resp);
		}
		else if (req.getParameter("formName").equals("addYourWeight")) {
			addYourWeight(req,resp);
		}
		else if (req.getParameter("formName").equals("addMyProduct")) {
			addMyProduct(req,resp);
		}
		else if (req.getParameter("formName").equals("addMyMealById")) {
			addMyMealById(req,resp);
		}
		else if (req.getParameter("formName").equals("addMyProductById")) {
			addMyProductById(req,resp);
		}
		else if (req.getParameter("formName").equals("deleteMyMealById")) {
			deleteMyMealById(req,resp);
		}
		else if (req.getParameter("formName").equals("deleteMyProductById")) {
			deleteMyProductById(req,resp);
		}
		else if (req.getParameter("formName").equals("addMealButton")) {
			addMealButton(req,resp);
		}
		else if (req.getParameter("formName").equals("addMeal")) {
			addMeal(req,resp);
		}
		else if (req.getParameter("formName").equals("EatThisMeal")) {
			eatThisMeal(req,resp);
		}
		else if (req.getParameter("formName").equals("EatThisProduct")) {
			eatThisProduct(req,resp);
		}
   }

	private void addProductByName(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		if(isLoggedIn(req))
		{
		SearchServlet.createTableFromTableObjectArr(resp,loggedinDB.addProductByName(req.getParameter("name"),Double.parseDouble(req.getParameter("kcal")),
				  Double.parseDouble(req.getParameter("proteins")),Double.parseDouble(req.getParameter("carbo")),
				  Double.parseDouble(req.getParameter("fat"))));

		}
		else
		{
			createResponseSiteIndex(resp,"Musisz być zalogowany");
		}
	}
	private boolean addMealButton(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		if(isLoggedIn(req))
		{
			SearchServlet.createTableFromTableObjectArrWithMealAdd(resp,loggedinDB.showAllProducts());
			return true;
		}
		else
		{
			createResponseSiteIndex(resp,"Musisz być zalogowany");
			return false;
		}
		
	}
				  
	private void showAllProducts(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		SearchServlet.createTableFromTableObjectArr(resp,loggedinDB.showAllProducts());
	}
	private void showAllMeals(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		SearchServlet.createTableFromTableObjectArr(resp,loggedinDB.showAllMeals());
	}
	private void showMyProducts(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
	HashMap<String,String> hashTmp=(HashMap<String,String>)req.getSession(false).getAttribute("id");
		SearchServlet.createTableFromTableObjectArr(resp,loggedinDB.showMyProducts(Integer.valueOf(hashTmp.get("id"))));
	}

	private void showMyMeals(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		HashMap<String,String> hashTmp=(HashMap<String,String>)req.getSession(false).getAttribute("id");
		SearchServlet.createTableFromTableObjectArr(resp,loggedinDB.showMyMeals(Integer.valueOf(hashTmp.get("id"))));
	}


	private void setYourGoals(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		HashMap<String,String> hashTmp=(HashMap<String,String>)req.getSession(false).getAttribute("id");
		SearchServlet.createTableFromTableObjectArr(resp,loggedinDB.setYourGoals(Double.parseDouble(req.getParameter("weight")),Double.parseDouble(req.getParameter("kcal")),
				  Double.parseDouble(req.getParameter("proteins")),Double.parseDouble(req.getParameter("carbo")),
				  Double.parseDouble(req.getParameter("fat")),Integer.valueOf(hashTmp.get("id"))) );
	}
	private void addYourWeight(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		if(isLoggedIn(req))
		{
		HashMap<String,String> hashTmp=(HashMap<String,String>)req.getSession(false).getAttribute("id");
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		SearchServlet.createTableFromTableObjectArr(resp,loggedinDB.addYourWeight(Integer.valueOf(hashTmp.get("id")),Double.parseDouble(req.getParameter("weight")), sqlDate));

		}
		else
		{
			createResponseSiteIndex(resp,"Musisz być zalogowany");
		}
	}
	private void addMyProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		if(isLoggedIn(req))
		{
		HashMap<String,String> hashTmp=(HashMap<String,String>)req.getSession(false).getAttribute("id");
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		SearchServlet.createTableFromTableObjectArr(resp,loggedinDB.addMyProduct(Integer.valueOf(hashTmp.get("id")),req.getParameter("name"),Double.parseDouble(req.getParameter("kcal")),
				  Double.parseDouble(req.getParameter("proteins")),Double.parseDouble(req.getParameter("carbo")),
				  Double.parseDouble(req.getParameter("fat"))));

		}
		else
		{
			createResponseSiteIndex(resp,"Musisz być zalogowany");
		}

	}
	private boolean addMyProductById(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		HashMap<String,String> hashTmp=(HashMap<String,String>)req.getSession(false).getAttribute("id");
		if( loggedinDB.addMyProductById(Integer.valueOf(hashTmp.get("id")),Integer.valueOf(req.getParameter("id"))))
		{
			createResponseSite(resp,"dodano");
			return true;
		}
		else 
		{
			createResponseSite(resp,"błąd");
			return false;
		}

	}
	public boolean addMyMealById(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		HashMap<String,String> hashTmp=(HashMap<String,String>)req.getSession(false).getAttribute("id");
		if( loggedinDB.addMyMealById(Integer.valueOf(hashTmp.get("id")),Integer.valueOf(req.getParameter("id"))))
		{
			createResponseSite(resp,"dodano");
			return true;
		}
		else 
		{
			createResponseSite(resp,"błąd");
			return false;
		}
		
	}
	public boolean deleteMyMealById(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		HashMap<String,String> hashTmp=(HashMap<String,String>)req.getSession(false).getAttribute("id");
		if( loggedinDB.deleteMyMealById(Integer.valueOf(hashTmp.get("id")),Integer.valueOf(req.getParameter("id"))))
		{
			createResponseSite(resp,"usunięto");
			return true;
		}
		else 
		{
			createResponseSite(resp,"błąd");
			return false;
		}
		
	}
	public boolean deleteMyProductById(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		HashMap<String,String> hashTmp=(HashMap<String,String>)req.getSession(false).getAttribute("id");
		if( loggedinDB.deleteMyProductById(Integer.valueOf(hashTmp.get("id")),Integer.valueOf(req.getParameter("id"))))
		{
			createResponseSite(resp,"usunięto");
			return true;
		}
		else 
		{
			createResponseSite(resp,"błąd");
			return false;
		}
		
	}
	public Integer addMeal(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		ArrayList<Double> weightArr=new ArrayList<>();
		ArrayList<Integer> idArr=new ArrayList<>();
		if(!req.getParameter("id10").equals("") && !req.getParameter("weight10").equals(""))
		{
		weightArr.add(Double.parseDouble(req.getParameter("weight10")));
		idArr.add(Integer.valueOf(req.getParameter("id10")));
		}
		if(!req.getParameter("id11").equals("") && !req.getParameter("weight11").equals(""))
		{
		weightArr.add(Double.parseDouble(req.getParameter("weight11")));
		idArr.add(Integer.valueOf(req.getParameter("id11")));
		}
		if(!req.getParameter("id12").equals("") && !req.getParameter("weight12").equals(""))
		{
		weightArr.add(Double.parseDouble(req.getParameter("weight12")));
		idArr.add(Integer.valueOf(req.getParameter("id12")));
		}
		if(!req.getParameter("id13").equals("") && !req.getParameter("weight13").equals(""))
		{
		weightArr.add(Double.parseDouble(req.getParameter("weight13")));
		idArr.add(Integer.valueOf(req.getParameter("id13")));
		}
		if(!req.getParameter("id14").equals("") && !req.getParameter("weight14").equals(""))
		{
		weightArr.add(Double.parseDouble(req.getParameter("weight14")));
		idArr.add(Integer.valueOf(req.getParameter("id14")));
		}
		Integer key;
		if( (key=loggedinDB.addMeal(req.getParameter("name"), idArr, weightArr))!=0)
		{
			createResponseSite(resp,"Dodano :)");
			return key;
		}
		else 
		{
			createResponseSite(resp,"błąd");
			return 0;
		}
		
	}
	public boolean eatThisMeal(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sqlDate = new java.sql.Date(format.parse(req.getParameter("date")).getTime());

		HashMap<String,String> hashTmp=(HashMap<String,String>)req.getSession(false).getAttribute("id");
		
		if( loggedinDB.eatThisMeal(Integer.valueOf(hashTmp.get("id")),Integer.valueOf(req.getParameter("id")),Double.parseDouble(req.getParameter("weight")),sqlDate))
		{
			createResponseSite(resp,"Dodano");
			return true;
		}
		else 
		{
			createResponseSite(resp,"błąd");
			return false;
		}}
		catch(ParseException pe)
		{
			System.out.println(pe.getMessage());
			return false;
		}
	}
public boolean eatThisProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		ArrayList<Integer> idArr= new ArrayList<>();
		ArrayList<Double> weightArr= new ArrayList<>();
		idArr.add(Integer.valueOf(req.getParameter("id")));
		weightArr.add(Double.parseDouble(req.getParameter("weight")));
		Integer Primarykey;
		if((Primarykey= loggedinDB.addMeal("Produkt: "+req.getParameter("name"), idArr, weightArr))!=0)
		{
			try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date sqlDate = new java.sql.Date(format.parse(req.getParameter("date")).getTime());

			HashMap<String,String> hashTmp=(HashMap<String,String>)req.getSession(false).getAttribute("id");

			if( loggedinDB.eatThisMeal(Integer.valueOf(hashTmp.get("id")),Primarykey,Double.parseDouble(req.getParameter("weight")),sqlDate))
			{
				createResponseSite(resp,"Dodano");
				return true;
			}
			else 
			{
				createResponseSite(resp,"błąd");
				return false;
			}}
			catch(ParseException pe)
			{
				System.out.println(pe.getMessage());
				return false;
			}
		}
		createResponseSite(resp,"addMeal nie zatrybiło błąd");
		return false;
	}
	private boolean isLoggedIn(HttpServletRequest req)//isnt tested
	{
		HttpSession session=req.getSession(false);  
      if(session!=null){   
			return true;
		}
		return false;
	}
	private void logOut(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException
	{
		
		HttpSession session=req.getSession(false);  
      if(session!=null){   
			session.invalidate();
			createResponseSiteIndex(resp,"Wylogowano poprawnie" );
		}
		createResponseSiteIndex(resp,"Nie jestes zalogowany" );
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
			out.println("<H1><CENTER> <a href='indexLoggedIn.html'>Wróć</a></CENTER></H1>");
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
			out.println("<H1><CENTER> <a href='indexLoggedIn.html'>Wróć</a></CENTER></H1>");
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
			out.println("<H1><CENTER> <a href='indexLoggedIn.html'>Wróć</a></CENTER></H1>");
			out.println("</BODY></HTML>");
			}
	}
		private void createResponseSiteIndex(HttpServletResponse resp,String str1,String str2,String str3) throws IOException 
	{
		try (PrintWriter out = resp.getWriter()) {
			resp.setContentType("text/html");
			out.println("<HTML><HEAD><TITLE>");
			out.println(":)");
			out.println("</TITLE></HEAD><BODY>");
			out.println("<H1><CENTER> "+str1 +" </CENTER></H1>");
			out.println("<H1><CENTER> "+str2 +" </CENTER></H1>");
			out.println("<H1><CENTER> "+str3 +" </CENTER></H1>");
			out.println("<H1><CENTER> <a href='index.html'>Wróć</a></CENTER></H1>");
			out.println("</BODY></HTML>");
			}
	}
	private void createResponseSiteIndex(HttpServletResponse resp,String str1,String str2) throws IOException 
	{
		try (PrintWriter out = resp.getWriter()) {
			resp.setContentType("text/html");
			out.println("<HTML><HEAD><TITLE>");
			out.println(":)");
			out.println("</TITLE></HEAD><BODY>");
			out.println("<H1><CENTER> "+str1 +" </CENTER></H1>");
			out.println("<H1><CENTER> "+str2 +" </CENTER></H1>");
			out.println("<H1><CENTER> <a href='index.html'>Wróć</a></CENTER></H1>");
			out.println("</BODY></HTML>");
			}
	}
	private void createResponseSiteIndex(HttpServletResponse resp,String str1) throws IOException 
	{
		try (PrintWriter out = resp.getWriter()) {
			resp.setContentType("text/html");
			out.println("<HTML><HEAD><TITLE>");
			out.println(":)");
			out.println("</TITLE></HEAD><BODY>");
			out.println("<H1><CENTER> "+str1 +" </CENTER></H1>");
			out.println("<H1><CENTER> <a href='index.html'>Wróć</a></CENTER></H1>");
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
