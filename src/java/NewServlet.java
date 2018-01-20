import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest
;import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class NewServlet extends HttpServlet {
	App jdbc=null;
	@Override
   public void init(ServletConfig config) throws ServletException {
		super.init(config);
		jdbc=new App();
   }

	@Override
   public void doGet(HttpServletRequest req, HttpServletResponse resp)
         throws ServletException, IOException {
      String fname = req.getParameter("firstname");
      String lname = req.getParameter("lastname");
		        HttpSession session=req.getSession();  

      displayHTML(resp, (String)session.getAttribute("login"), lname);
   }
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
			//out.println(jdbc.getProducts() + "</br>");
			out.println("<H1><CENTER>Hello, " + fn + " " + ln + "</CENTER></H1>");
			//out.println(String.valueOf(jdbc.getProductCount()));
			out.println("<H1><CENTER></CENTER></H1>");
			out.println("</BODY></HTML>");
		}
		catch(Exception e)
		{
			
		}
   }
}
