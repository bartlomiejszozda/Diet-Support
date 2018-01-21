
public class Goal implements TableObject {
	private Integer id;
	private Double weight;
	private Double kcal;
	private Double proteins;
	private Double carbohydrates;
	private Double fat;
	Goal(Integer id,Double weight,Double kcal,Double proteins,Double carbohydrates,Double fat)
	{
		this.id=id;
		this.weight=weight;
		this.kcal=kcal;
		this.proteins=proteins;
		this.carbohydrates=carbohydrates;
		this.fat=fat;
	}
	public Integer getid()
	{
		return this.id;
	}
	public Double getweight()
	{
		return this.weight;
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
		return "<tr>" + "<th>"+weight+"</th>"+"<th>"+kcal+"</th>"+"<th>"+proteins+"</th>"+"<th>"+carbohydrates+"</th>"+"<th>"+fat+"</th>"+ "</tr>";
	}
}
