package hospital;

public class Character
{
	protected int max_health = 100;
	protected int curr_health;
	protected int attack = 50;
	
	
	//constructors
	public Character()
	{	curr_health=max_health;	}
	public Character(int heart)
	{
		try
		{	setMaxHealth(heart);}
			catch(IllegalArgumentException e)
			{
				System.out.println(e.getMessage() + "Illegal Argument" + "\nSetting Default Value");
			}
			curr_health = max_health;
		
	}
	
	//Damage
	public void applyDamage(int damageAmount)
	{
		if(damageAmount < 0) curr_health += damageAmount;
		else if(damageAmount > 0) curr_health -= damageAmount;
	}

	//set
	public void setMaxHealth(int hp)
	{
		if (hp <= 0) 
			throw new IllegalArgumentException("Health must be a positive integer.");
		this.max_health=hp;
		
		//should also add curr_health = max_health ?
	}
	public void setAttackValue(int stats)
	{
		if(stats > 0)	attack = stats;
		else if(stats<0) attack = -stats;
	}
	
	//get
	public int getCurrHealth()
	{	return curr_health;
	}
	public int getMaxHealth()
	{	return max_health;
	}
	public int getAttackValue()
	{	return attack;
	}
}
