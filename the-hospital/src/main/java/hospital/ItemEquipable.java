package hospital;


public class ItemEquipable extends Item
{
	protected int stats;
	
	public ItemEquipable(String name, String des, int effect)
	{
		super(name, des);
		stats = effect;
	}
	
	//set
	public void setStats(int st)
	{	stats = st;
	}
	
	//get
	public int getStats()
	{	return stats;
	}


}
