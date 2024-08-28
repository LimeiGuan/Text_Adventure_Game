package hospital;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo
	(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "@type"
	)
	@JsonSubTypes
	({
	    @JsonSubTypes.Type(value = Enemy.class, name = "enemy"),
	    @JsonSubTypes.Type(value = Player.class, name = "player")
	})

/**
 * The Character class represents a default character, with attributes for stamina and damage
 * It is the superclass for specific types of characters like Enemy and Player.
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
	@JsonCreator
	public Character(@JsonProperty("max_health") int heart)
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
     * @return {@code true} if the player has the health equal or lesser than 0, {@code false} otherwise.
     */
	public boolean applyDamage(int damageAmount)
	{
		if(damageAmount < 0) curr_health += damageAmount;
		else if(damageAmount > 0) curr_health -= damageAmount;
		 if (curr_health <= 0) 
		      return true; 
		     else  
		      return false; 
	}

    /**
     * Sets the maximum health of the character. Note that the current health will not be changed
     *
     * @param hp The new maximum health value. Must be positive.
     * @throws IllegalArgumentException if the specified maximum health is not positive.
     */
	public void setMaxHealth(int hp)
	{
		if (hp <= 0) 
			throw new IllegalArgumentException("Health must be a positive integer.");
		this.max_health = hp;
	}
    /**
     * Sets the current health of the character. 
     *
     * @param hp	The new current health value. Must be positive. If is equal or greater than maximum
     * 				health, the current health will be set to be equal to the maximum health;
     * @throws IllegalArgumentException if the specified current health is not positive.
     */
	@JsonProperty("curr_health")
	public void setCurrHealth(int hp)
	{
		if (hp <= 0) 
			throw new IllegalArgumentException("Health must be a positive integer.");
		if(hp >= max_health) 
			curr_health = max_health;
		else 
			curr_health = hp;
	}
    /**
     * Sets the attack value of the character.
     *
     * @param stats The new attack value. If less than or equal to 0, then the new attack will
     * 				be set to 0.
     */
	@JsonProperty("attack")
	public void setAttackValue(int stats)
	{
		if(stats > 0)	attack = stats;
		else if(stats <=0) attack = 0;
	}
	
    /**
     * Gets the current health of the character.
     *
     * @return The current health value.
     */
	@JsonProperty("curr_health")
	public int getCurrHealth()
	{	
		return curr_health;
	}
    /**
     * Gets the maximum health of the character.
     *
     * @return The maximum health value.
     */
	@JsonProperty("max_health")
	public int getMaxHealth()
	{	
		return max_health;
	}
    /**
     * Gets the attack value of the character.
     *
     * @return The attack value.
     */
	@JsonProperty("attack")
	public int getAttackValue()
	{	
		return attack;
	}
}
