public class Product  implements TableObject{

	private Integer id;
	private String name;
	private Double kcal;
	private Double proteins;
	private Double carbohydrates;
	private Double fat;
		private String addButton;
	Product(Integer id,String name,Double kcal,Double proteins,Double carbohydrates,Double fat)
	{
		this.id=id;
		this.name=name;
		this.kcal=kcal;
		this.proteins=proteins;
		this.carbohydrates=carbohydrates;
		this.fat=fat;
		this.addButton="<form name='addMyProduct' id='addMyProduct' action=LoggedInServlet method= 'GET'>"+
"<div id='showNeverDiv' style='display: none' >"+
                "<input type='hidden' name='formName' value='addMyProduct'>"+
                "Podaj nazwę: <input type='text' name='name'id='' value ="+name+"></br>"+
                "kilo kalorie: <input type='number' name='kcal' id='' value ="+kcal+"></br>"+
                "bialko: <input type='number' name='proteins' id='' value ="+proteins+"></br>"+
                "weglowodany: <input type='number' name='carbo' id='' value ="+carbohydrates+"></br>"+
                "tluszcze: <input type='number' name='fat' id='' value ="+fat+"></br>"+
						"</div>"+
                "<input type='submit' name='addMyProduct' value='dodaj do ulubionych'>"+
            "</form>";
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
	public String showMeAsTableFragment()
	{
		return "<tr>" + "<th>"+name+"</th>"+"<th>"+kcal+"</th>"+"<th>"+proteins+"</th>"+"<th>"+carbohydrates+"</th>"+"<th>"+fat+"</th>"+"<th>"+addButton+"</th>"+"</tr>";
	}
}

        