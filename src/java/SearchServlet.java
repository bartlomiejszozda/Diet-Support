import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SearchServlet extends HttpServlet {
	SearchDB searchdb=null;
	@Override
   public void init(ServletConfig config) throws ServletException {
		super.init(config);
		searchdb=new SearchDB();
   }

	@Override
   public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		if (req.getParameter("formName") .equals("searchProductByName")) {

			searchProductByName(req, resp, req.getParameter("search"));
		}
		else if (req.getParameter("formName").equals("searchMealByName")) {
			searchMealByName(req, resp, req.getParameter("search"));
		}
		else if (req.getParameter("formName").equals("searchMealByProduct")) {
			searchMealByProduct(req, resp, req.getParameter("search"));
		}
   }
	private void searchProductByName(HttpServletRequest req,HttpServletResponse resp, String name)throws ServletException, IOException
	{
		/*			//Method using HashMap ( take database types (like Real -> Double ))
		HashMap<String,Double[]> hashmap = searchdb.searchProductByName(name);
		if(hashmap.isEmpty())
			createResponseSite(resp,"Nie znaleziono");
		else
			createResponseSiteForProducts(resp,hashmap.keySet(), hashmap.values());
		*/  
		ArrayList<TableObject> productArr = searchdb.searchProductByName(name);
		createTableFromTableObjectArr(resp,productArr);
		
	}
		private void searchMealByName(HttpServletRequest req,HttpServletResponse resp, String name)throws ServletException, IOException
	{
		ArrayList<TableObject> mealArr = searchdb.searchMealByName(name);
		createTableFromTableObjectArr(resp,mealArr);
	}
	private void searchMealByProduct(HttpServletRequest req,HttpServletResponse resp, String name)throws ServletException, IOException
	{
		ArrayList<TableObject> mealArr = searchdb.searchMealByProduct(name);
		createTableFromTableObjectArr(resp,mealArr);
	}
	public static void createTableFromTableObjectArr(HttpServletResponse resp,ArrayList<TableObject> tbObjArr) throws IOException 
	{
		try (PrintWriter out = resp.getWriter()) {
			resp.setContentType("text/html");
			out.println("<HTML><HEAD><TITLE>");
			out.println(":)");
			out.println("</TITLE>  "+
					" <script src='script.js'></script>\n" +
" <link rel='stylesheet' href='style.css' type='text/css' />"
					  + "</HEAD><BODY>");
			out.print("<table >");
			for(TableObject row:tbObjArr)
			{
					out.print(row.showMeAsTableFragment());
			}
			out.println("</table>");
			out.println("<H1><CENTER> <a href='indexLoggedIn.html'>Przejdź do twojej strony</a></CENTER></H1>");
			out.println("</BODY></HTML>");
			}
	}
	public static void createTableFromTableObjectArrWithMealAdd(HttpServletResponse resp,ArrayList<TableObject> tbObjArr) throws IOException 
	{
		try (PrintWriter out = resp.getWriter()) {
			resp.setContentType("text/html");
			out.println("<HTML><HEAD><TITLE>");
			out.println(":)");
			out.println("</TITLE>"
					  + " <script src='script.js'></script>\n" +
" <link rel='stylesheet' href='style.css' type='text/css' />"
					  +  "</HEAD><BODY>");
			out.print("<table >");
			for(TableObject row:tbObjArr)
			{
					out.print(row.showMeAsTableFragment());
			}
			out.println("</table>");
			out.print("<form name='addMeal' id='addMeal' action=LoggedInServlet method= 'GET'>"+
			"<input type='hidden' name='formName' value='addMeal'>"+"</br></br>"+
				"Nazwa Posiłku:<input type='text' name='name' value ='Podaj Nazwę ' >  </br>"+
				//"<input type='button' value ='Dodaj Produkt ' onclick='showHideDiv10()' >  </br>"+
				
				"<div id='showDiv10'  style='display: block;'>"+
						"Produkt 1</br>"+
						"id produktu</br><input type='number' name='id10' id='' value ='id'></br>"+
						"waga produktu</br><input type='number' name='weight10' id='' value ='waga'></br>"+
					  //"<input type='button' value ='Dodaj Produkt ' onclick='showHideDiv11()' >  </br>"+
				"</div>"+
				"<div id='showDiv11' >"+
					  "Produkt 2</br>"+
						"id produktu</br><input type='number' name='id11' id='' value ='id'></br>"+
						"waga produktu</br><input type='number' name='weight11' id='' value ='waga'></br>"+
					  	//"<input type='button' value ='Dodaj Produkt ' onclick='showHideDiv12()' >  </br>"+
				"</div>"+
				"<div id='showDiv12' >"+
					  "Produkt 3</br>"+
						"id produktu</br><input type='number' name='id12' id='' value ='id'></br>"+
						"waga produktu</br><input type='number' name='weight12' id='' value ='waga'></br>"+
						//"<input type='button' value ='Dodaj Produkt ' onclick='showHideDiv13()' >  </br>"+
				"</div>"+
				
				"<div id='showDiv13' >"+
					  "Produkt 4</br>"+
						"id produktu</br><input type='number' name='id13' id='' value =''></br>"+
						"waga produktu</br><input type='number' name='weight13' id='' value =''></br>"+
						//"<input type='button' value ='Dodaj Produkt ' onclick='showHideDiv14()' >  </br>"+
				"</div>"+
				
				"<div id='showDiv14' >"+
					  "Produkt 5</br>"+
						"id produktu</br><input type='number' name='id14' id='' value ='id'></br>"+
						"waga produktu</br><input type='number' name='weight14' id='' value ='waga'></br>"+
				"</div>"+
				"<div style='clear:both;'></div>"+
				"</br></br></br><input type='submit' name='addMyProductById' value='Dodaj Posilek' >"+
			"</form>");
			
			
			out.println("<H1><CENTER> <a href='indexLoggedIn.html'>Przejdź do twojej strony</a></CENTER></H1>");
			out.println("</BODY></HTML>");
			}
		
	}
	private void searchProductByNameAsString(HttpServletRequest req,HttpServletResponse resp, String name)throws ServletException, IOException
	{
		/*			//Method using HashMap ( take database types (like Real -> Double ))
		HashMap<String,Double[]> hashmap = searchdb.searchProductByName(name);
		if(hashmap.isEmpty())
			createResponseSite(resp,"Nie znaleziono");
		else
			createResponseSiteForProducts(resp,hashmap.keySet(), hashmap.values());
		*/  
		ArrayList<ArrayList<String>> strArr = searchdb.searchProductByNameAsString(name);
		createTableFromArrayListResponse(resp,strArr);
		
	}
		private void searchMealByNameAsString(HttpServletRequest req,HttpServletResponse resp, String name)throws ServletException, IOException
	{
		ArrayList<ArrayList<String>> strArr = searchdb.searchMealByNameAsString(name);
		createTableFromArrayListResponse(resp,strArr);
		
	}
	private void searchMealByProductAsString(HttpServletRequest req,HttpServletResponse resp, String name)throws ServletException, IOException
	{
		ArrayList<ArrayList<String>> strArr = searchdb.searchMealByProductAsString(name);
		createTableFromArrayListResponse(resp,strArr);
	}
//used for print data receive as ArrayList<ArrayList<String>> from SearchDB methods: searchProductByNameAsString, searchMealByNameAsString and searchMealByProductAsString 
	private void createTableFromArrayListResponse(HttpServletResponse resp,ArrayList<ArrayList<String>> strArr) throws IOException 
	{
		try (PrintWriter out = resp.getWriter()) {
			resp.setContentType("text/html");
			out.println("<HTML><HEAD><TITLE>");
			out.println(":)");
			out.println("</TITLE> <style>table, th, tr{border: 1px solid black;}</style> </HEAD><BODY>");
			out.print("<table >");
			for(ArrayList<String> row:strArr)
			{
				out.print("<tr>");
				for(String cell:row)
				{
					out.print("<th> "+cell +" </th>");
				}
				out.print("</tr>");
			}
			out.println("</table>");
			out.println("<H1><CENTER> <a href='index.html'>Wróć do strony głównej</a></CENTER></H1>");
			out.println("</BODY></HTML>");
			}
	}
//used in the past, only for print data returned by  method searchProductByNameHashMapReturned (from class SearchDB) 
	private void createResponseSiteForProducts(HttpServletResponse resp,Set<String> set,Collection<Double[]> coll) throws IOException 
	{
		try (PrintWriter out = resp.getWriter()) {
			resp.setContentType("text/html");
			out.println("<HTML><HEAD><TITLE>");
			out.println(":)");
			out.println("</TITLE></HEAD><BODY>");
			Iterator <Double[]> iterVal=coll.iterator();
			Iterator <String> iterKey=set.iterator();
			while(iterVal.hasNext() &&iterKey.hasNext() )
			{
				Double[] elVal=iterVal.next();
				String elKey=iterKey.next();
				out.println("<H1><CENTER> "+ elKey+ " "+elVal[0]+" "+elVal[1]+" "+elVal[2]+" "+elVal[3]+" " +" </CENTER></H1>");
			}
			out.println("<H1><CENTER> <a href='index.html'>Wróć do strony głównej</a></CENTER></H1>");
			out.println("</BODY></HTML>");
			}
	}
//used for tests 
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
//used for tests 
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
//used for tests 
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
