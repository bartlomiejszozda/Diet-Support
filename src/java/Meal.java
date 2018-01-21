
import java.util.ArrayList;

public class Meal implements TableObject{
	private Integer id;
	private String name;
	private String addButton;
	private String addAmountButton;
	private ArrayList<Ingredient> ingredients= new ArrayList<>();
	Meal(Integer id,String name,ArrayList<Ingredient> ingredients)
	{
		this.id=id;
		this.name=name;
		this.ingredients=ingredients;
		this.addButton="<form name='addMyMealById' id='addMyMealById' action=LoggedInServlet method= 'GET'>"+
				"<div id='showNeverDiv' style='display: none' >"+
                "<input type='hidden' name='formName' value='addMyMealById'>"+
						"<input type='number' name='id' id='' value ="+id+"></br>"+
						"</div>"+
                "<input type='submit' name='addMyMealById' value='dodaj do ulubionych'style='float:left;'>"+
            "</form>";
		//this.addAmountButton="<input type='number' name='id"+id+"' id='' value =''>";
				
		this.addAmountButton="<form name='EatThisMeal' id='EatThisMeal' action=LoggedInServlet method= 'GET'>"+
				"<div id='showNeverDiv' style='display: none' >"+
                "<input type='hidden' name='formName' value='EatThisMeal'>"+
						"<input type='number' name='id' id='' value ="+id+"></br>"+
						"</div>"+
					"</br><input type='number' name='weight' id='' value =''placeholder='Podaj ilość porcji:' step='0.01'></br>"+
				  "<input type='date' name='date' style='float: left'>"+
                "<input type='submit' name='EatThisMeal' value='Zjedz' style='float: right;'>"+
            "</form>";
	}
	Meal(Integer id,String name,ArrayList<Ingredient> ingredients,Integer mode)
	{//mode 0 typical mode, button dodaj do ulubionych
		//mode 1 - button usun z ulubionych
		this( id, name, ingredients);
		if(mode.equals(1))
		{
		this.addButton="<form name='deleteMyMealById' id='deleteMyMealById' action=LoggedInServlet method= 'GET'>"+
				"<div id='showNeverDiv' style='display: none' >"+
                "<input type='hidden' name='formName' value='deleteMyMealById'>"+
						"<input type='number' name='id' id='' value ="+id+"></br>"+
						"</div>"+
				  		
                "<input type='submit' name='deleteMyMealById' value='Usuń z ulubionych'style='float:left;'>"+
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
	public ArrayList<Ingredient> getingredients()
	{
		return this.ingredients;
	}
	public String showMeAsTableFragment()
	{
		String strTmp="<tr>";
		strTmp+="<th>"+name+"</br>" +addButton+"</th><th>"+addAmountButton+"</th>" ;
		for(Ingredient el:ingredients)
		{
			strTmp+="<th>"+el.getproduct().getname()+" "+el.getweight()+"gram"+"</br>" + el.getproduct().getaddButton()+"</th>";
		}
		strTmp+="</tr>";

		return strTmp;
	}
}
