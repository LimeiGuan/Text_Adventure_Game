package hospital;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The Enemy class represents an enemy character in the game, extending the Character class.
 * It inherits properties such as health and attack value and adds additional behavior specific to enemies.
 * 
 * <p>This class integrates with Jackson for JSON serialization and deserialization, allowing it to be
 * easily converted to and from JSON format.</p>
 * 
 * <p>When an enemy takes damage and its current health drops to 0 or below, a message indicating the
 * end of the battle is displayed.</p>
 */
@JsonTypeName("enemy")
public class Enemy extends Character
{
	
    /**
     * Default constructor. Initializes the enemy with default values for health and attack.
     */
	public Enemy() 
	{	super();
	}
    /**
     * Constructs an enemy with the specified maximum health.
     *
     * @param hp The maximum health for the enemy. Must be a positive integer.
     */
	public Enemy(int hp)
	{	super(hp);
	}

    /**
     * Applies damage to the enemy. If the current health drops to 0 or below, a message is printed to
     * indicate that the battle has ended.
     *
     * @param damageAmount The amount of damage to apply.The damageAmount will always reduce 
     * 						the current health of the enemy, unless it is 0. 0 does nothing.
     */
	@Override
	public void applyDamage(int damageAmount)
	{
		super.applyDamage(damageAmount);
	    if (curr_health <= 0)
	    	System.out.println("Battle ended!");
	}
	
}
