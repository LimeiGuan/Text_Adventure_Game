package hospital;

public class Medicine extends ItemEquipable
{
	private int use = 1;
	
	public Medicine(String nm, String des, int hp)
	{
		super(nm, des, hp);
	}
	
	@Override
	public int getStats()
	{
		int healing = use*stats;
		use = 0;
		return healing;
	}
}
