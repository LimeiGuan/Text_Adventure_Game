package hospital;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The Enemy class represents an enemy character in the game, extending the Character class.
 * It inherits properties such as health and attack value and adds additional behavior specific to enemies.
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
	public Enemy(@JsonProperty("max_health") int hp)
	{	super(hp);
	}

	
}
