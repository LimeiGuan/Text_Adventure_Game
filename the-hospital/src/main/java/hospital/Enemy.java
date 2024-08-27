package hospital;

public class Enemy extends Character
{
	
	//constructors
	public Enemy() 
	{	super();
	}
	public Enemy(int hp)
	{	super(hp);
	}

	@Override
	public void applyDamage(int damageAmount)
	{
		super.applyDamage(damageAmount);
	    if (curr_health <= 0)
	    	System.out.println("Battle ended!");
	}
	
}
