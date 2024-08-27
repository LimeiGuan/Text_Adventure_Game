package hospital;

import javafx.util.Pair;

public class Player extends Character
{
	private String name;
	private int buff = 0;
	private int curr_Position = 202;
	private boolean selected = false;
	
	//constructors
	public Player() 
	{	super();
	}
	public Player(int hp)
	{	super(hp);
	}

	//items	
	public void heal(int hp)
	{	
		int heal = 0;
		if(hp >= 0) heal = hp;
		else if (hp<0) heal = -hp;
		curr_health += heal;
		if(curr_health > max_health)
			curr_health = max_health;
		
		//somewhere (maybe) should consider what to do when trying to heal when already maxed_hp
	}
	
	public void equip(int dmg)
	{	
		unequip();	//in case there was already something
		buff += dmg;
		selected = true;
	}
	
	public void unequip()
	{
		buff = 0;	
		selected = false;
	}
	public boolean isEquiped()
	{	return selected;
	}
	
	//enemy
	@Override
	public void applyDamage(int damageAmount)
	{
	    super.applyDamage(damageAmount);
	    if (curr_health <= 0)
	    	GameManager.gameOver();
	}
	
	//position
	public void changePosition(int floor, int room)
	{
		//da controllare se i valori sono validi e se vengono aggiornati
	}
	
	//get
	@Override
	public int getAttackValue()
	{	return attack + buff;
	}
	
	public int getPosition()
	{
		return curr_Position;
	}
	public void setPosition(int position)
	{
		curr_Position = position;
	}
}
