
import java.util.ArrayList;


public class EatDay implements TableObject{
	private Integer id;
	private String date;
	private ArrayList<MealPortion> meals= new ArrayList<>();
	EatDay(Integer id,String date,ArrayList<MealPortion> meals)
	{
		this.id=id;
		this.date=date;
		this.meals=meals;
	}
	public Integer getid()
	{
		return this.id;
	}
	public String getdate()
	{
		return this.date;
	}
	public ArrayList<MealPortion> getmeals()
	{
		return this.meals;
	}
	public String showMeAsTableFragment()
	{
		String strTmp="<tr>";
		for(MealPortion el:meals)
		{
			strTmp+="<th>"+date+"</th>"+"<th>"+el.getmeal().getname()+"</th>";
		}
		strTmp+="</tr>";
		return strTmp;
	}
}
