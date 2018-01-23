
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product  implements TableObject{

	private Integer id;
	private String name;
	private Double kcal;
	private Double proteins;
	private Double carbohydrates;
	private Double fat;
	private String addButton;
	private String addAmountButton;
	Product(Integer id,String name,Double kcal,Double proteins,Double carbohydrates,Double fat)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		this.id=id;
		this.name=name;
		this.kcal=kcal;
		this.proteins=proteins;
		this.carbohydrates=carbohydrates;
		this.fat=fat;
		this.addButton="<form name='addMyProductById' id='addMyProductById' action=LoggedInServlet method= 'GET'>"+
				"<div id='showNeverDiv' style='display: none' >"+
                "<input type='hidden' name='formName' value='addMyProductById'>"+
						"<input type='number' name='id' id='' value ="+id+"></br>"+
						"</div>"+
                "<input type='submit' name='addMyProductById' value='dodaj do ulubionych' style='float: right;'>"+
            "</form>";
		/*this.addAmountButton="<form name='addProductToSession' id='addProductToSession' action=LoggedInServlet method= 'GET'>"+
				"<div id='showNeverDiv' style='display: none' >"+
                "<input type='hidden' name='formName' value='addProductToSession'>"+
						"<input type='number' name='id' id='' value ="+id+"></br>"+
						"</div>"+
					"<input type='number' name='weight' id='' value =''></br>"+
                "<input type='submit' name='addProductToSession' value='dodaj do pamięci' style='float: right;'>"+
            "</form>";*/
				this.addAmountButton="<div id='showNeverDiv' style='display: none' >"+
						  "<form name='EatThisProduct' id='EatThisProduct' action=LoggedInServlet method= 'GET'>"+
                "<input type='hidden' name='formName' value='EatThisProduct'>"+
						"<input type='number' name='id' id='' value ="+id+"></br>"+
						  "<input type='text' name='name' id='' value ="+name+"></br>"+
				"</div>"+
						  "zjedz mnie!"+
					"<br><input type='number' name='weight' id='' value ='' style='float:right' placeholder='podaj wagę' step='0.01'>"+
						  "<div style='clear: both'><div>"+
						   "<input type='date' name='date' style='float: left' value='" +dateFormat.format(date) + "'>"+
                "<input type='submit' name='EatThisProduct' value='Zjedz' style='float: left'>"+
            "</form>";
	}
	Product(Integer id,String name,Double kcal,Double proteins,Double carbohydrates,Double fat,Integer mode)
	{//mode=0 - typical (Dodaj do ulubioonych button)
		//mode=1 - usuń z ulubionych
		this( id, name, kcal, proteins, carbohydrates, fat);
		if(mode.equals(1))
		{
				this.addButton="<form name='deleteMyProductById' id='deleteMyProductById' action=LoggedInServlet method= 'GET'>"+
			"<div id='showNeverDiv' style='display: none' >"+
				 "<input type='hidden' name='formName' value='deleteMyProductById'>"+
					"<input type='number' name='id' id='' value ="+id+"></br>"+
					"</div>"+
				 "<input type='submit' name='deleteMyProductById' value='Usuń z ulubionych' style='float: right;'>"+
			"</form>";
		}
	}
	public Integer getid()
	{
		return this.id;
	}
	public String getname()
	{
		return this.name;
	}
	public Double getkcal()
	{
		return this.kcal;
	}
	public Double getproteins()
	{
		return this.proteins;
	}
	public Double getcarbohydrates()
	{
		return this.carbohydrates;
	}
	public Double getfat()
	{
		return this.fat;
	}
	public String getaddButton()
	{
		return this.addButton;
	}
	public String showMeAsTableFragment()
	{
		return "<tr>" + "<th>"+id+"</th>"+"<th>"+name+"</th>"+"<th>"+kcal+"</th>"+"<th>"+proteins+"</th>"+"<th>"+carbohydrates+"</th>"+"<th>"+fat+"</th>"+"<th>"+addButton+"</th><th>"+addAmountButton+"</th></tr>";
	}
}

        