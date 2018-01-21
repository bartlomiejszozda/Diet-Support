
import java.util.ArrayList;

public class Meal implements TableObject{
	private Integer id;
	private String name;
	private ArrayList<Ingredient> ingredients= new ArrayList<>();
	Meal(Integer id,String name,ArrayList<Ingredient> ingredients)
	{
		this.id=id;
		this.name=name;
		this.ingredients=ingredients;
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
		strTmp+="<th>"+name+"</th>";
		for(Ingredient el:ingredients)
		{
			strTmp+="<th>"+el.getproduct().getname()+" "+el.getweight()+"gram"+"</th>";
		}
		strTmp+="</tr>";
		return strTmp;
	}
}
