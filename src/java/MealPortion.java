public class MealPortion {
	private Meal meal;
	private Double portion;
	MealPortion(Meal meal, Double portion)
	{
		this.meal = meal;
		this.portion = portion;
	}
	public Meal getmeal()
	{
		return meal;
	}
	public Double getportion()
	{
		return portion;
	}
}