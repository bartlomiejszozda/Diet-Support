
public class Ingredient{
	private Product product;
	private Double weight;
	Ingredient(Product product, Double weight)
	{
		this.product = product;
		this.weight = weight;
	}
	public Product getproduct()
	{
		return product;
	}
	public Double getweight()
	{
		return weight;
	}
}
