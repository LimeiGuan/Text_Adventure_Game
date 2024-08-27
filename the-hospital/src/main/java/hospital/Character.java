package hospital;

/**
 * This class represents a default character, with attributes for stamina and damage
 */
public class Character
{
	/**
     * The maximum health value of the character is set by default to 100.
     */
	protected int max_health = 100;
    /**
     * The current health value of the character.
     */
	protected int curr_health;
    /**
     * The attack value of the character is set by default to 50.
     */
	protected int attack = 50;

	
    /**
     * Default constructor. Initializes the character's current health to maximum health.
     */
	public Character()
	{	
		curr_health=max_health;
	}
    /**
     * Constructor that initializes the character with a specified maximum health value.
     *
     * @param heart The maximum health of the character. Must be a positive integer.
     * @throws IllegalArgumentException if the specified maximum health is not positive.
     */
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
	
    /**
     * Applies damage to the character.
     *
     * @param damageAmount	The amount of damage to apply. The damageAmount will always reduce 
     * 						the current health of the character, unless it is 0. 0 does nothing.
     */
	public void applyDamage(int damageAmount)
	{
		if(damageAmount < 0) curr_health += damageAmount;
		else if(damageAmount > 0) curr_health -= damageAmount;
	}

    /**
     * Sets the maximum health of the character. Note that the current health will also be changed
     *
     * @param hp The new maximum health value. Must be positive.
     * @throws IllegalArgumentException if the specified maximum health is not positive.
     */
	public void setMaxHealth(int hp)
	{
		if (hp <= 0) 
			throw new IllegalArgumentException("Health must be a positive integer.");
		this.max_health = hp;
		curr_health = hp;
	}
	
    /**
     * Sets the attack value of the character.
     *
     * @param stats The new attack value. Negative values will be converted to positive.
     */
	public void setAttackValue(int stats)
	{
		if(stats > 0)	attack = stats;
		else if(stats<0) attack = -stats;
	}
	
    /**
     * Gets the current health of the character.
     *
     * @return The current health value.
     */
	public int getCurrHealth()
	{	
		return curr_health;
	}
    /**
     * Gets the maximum health of the character.
     *
     * @return The maximum health value.
     */
	public int getMaxHealth()
	{	
		return max_health;
	}
    /**
     * Gets the attack value of the character.
     *
     * @return The attack value.
     */
	public int getAttackValue()
	{	
		return attack;
	}
}
