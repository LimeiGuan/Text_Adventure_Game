package hospital;


public class Weapon extends ItemEquipable
{

	public Weapon(String nm, String des, int dmg)
	{
		super(nm, des, dmg);
		setStats(dmg);
	}
	
	@Override
	public void setStats(int st)
	{
		if(st<0) stats = -st;
		else if(st>0) stats = st;	
	}
}
