import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class Weight implements TableObject{

	private Integer id;
	private Double weight;
	private Date data;
	Weight(Integer id,Double weight, java.sql.Date data)
	{
		this.id=id;
		this.weight=weight;
		this.data= data;
	}
	public Integer getid()
	{
		return this.id;
	}
	public Double getweight()
	{
		return this.weight;
	}
@Override
	public String showMeAsTableFragment()
	{
		return "<tr>" + "<th>"+weight+"</th>"+"<th>"+data+"</th>"+ "</tr>";
	}



}
