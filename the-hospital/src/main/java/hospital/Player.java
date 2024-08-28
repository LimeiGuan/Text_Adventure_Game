package hospital;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.UUID;

/**
 * The Player class represents a player in the game, which is a type of Character.
 * Each player has a unique ID, a name, and can equip items that modify their attack value.
 */
@JsonTypeName("player")
public class Player extends Character
{
    /**
     * The unique identifier for the player.
     */
	private String playerId;
    
    /**
     * The name of the player.
     */
	private String name = "";
    
    /**
     * The attack buff value when the player equips an item.
     */
    @JsonIgnore
	private int buff = 0;
    
    /**
     * The current position of the player on the map.
     */
	private int curr_Position = 202;
    
    /**
     * Indicates whether the player has an item equipped.
     */
    @JsonIgnore
	private boolean selected = false;
	
    /**
     * Default constructor. Initializes the player with default health, a random UUID as the player ID,
     * and sets the player's name to the UUID.
     */
	public Player() 
	{	
		super();
		this.playerId = UUID.randomUUID().toString();
		this.name = playerId;
	}
    /**
     * Parameterized constructor. Initializes the player with the specified health and name,
     * and generates a random UUID as the player ID.
     *
     * @param hp The maximum health of the player.
     * @param nm The name of the player.
     */
	@JsonCreator
	public Player(@JsonProperty("max_health") int hp, @JsonProperty("name") String nm)
	{	
		super(hp);
		this.playerId = UUID.randomUUID().toString();
		this.name = nm;
	}

    
    /**
     * Heals the player by a specified amount of health.
     * The player's health cannot exceed the maximum health value.
     *
     * @param hp The amount of health to heal. Negative values will be treated as positive.
     */	
	public void heal(int hp)
	{	
		int heal = 0;
		if(hp >= 0) heal = hp;
		else if (hp<0) heal = -hp;
		curr_health += heal;
		if(curr_health > max_health)
			curr_health = max_health;
	}
	
    /**
     * Equips an item that adds a damage buff to the player's attack value.
     * If an item was already equipped, it is unequipped before equipping the new item.
     *
     * @param dmg The damage buff value of the item to equip.
     */
	public void equip(int dmg)
	{	
		unequip();
		buff += dmg;
		selected = true;
	}
    /**
     * Unequips any currently equipped item, removing its damage buff.
     */
	public void unequip()
	{
		buff = 0;	
		selected = false;
	}
    /**
     * Checks if the player currently has an item equipped.
     *
     * @return {@code true} if the player has an item equipped, {@code false} otherwise.
     */
	public boolean isEquiped()
	{	return selected;
	}
	
    /**
     * Applies damage to the player, reducing their current health.
     * If the player's health drops to zero or below, the game ends.
     *
     * @param damageAmount	The amount of damage to apply. Both positive and negative
     * 						values decrease health.
     */
	@Override
	public void applyDamage(int damageAmount)
	{
	    super.applyDamage(damageAmount);
	    if (curr_health <= 0)
	    	GameManager.gameOver();
	}
	
	
    /**
     * Gets the unique player ID.
     *
     * @return The player's unique identifier.
     */
    @JsonProperty("player_id")
    public String getPlayerId()
    {
        return playerId;
    }
    /**
     * Restore the player's ID during the deserialization process.
     *
     * @param id	the unique identifier for the player, corresponding to the 
     *        		"player_id" field in the JSON data.
     */
    @JsonProperty("player_id")
    private void setPlayerId(String id)
    {
        this.playerId = id;
    }
    
    /**
     * Gets the player's name.
     *
     * @return The player's name.
     */
    @JsonProperty("name")
    public String getName()
    {	return name;
    	
    }
    /**
     * Gets the player's current attack value, including any equipped item buff.
     *
     * @return The player's attack value.
     */
	@Override
	public int getAttackValue()
	{	return attack + buff;
	}
    /**
     * Gets the player's current position on the map.
     *
     * @return The player's current position.
     */
    @JsonProperty("current_position")
	public int getPosition()
	{
		return curr_Position;
	}
	
    /**
     * Sets the player's position on the map.
     *
     * @param position The new position for the player.
     */
    @JsonProperty("current_position")
	public void setPosition(int position)
	{
		curr_Position = position;
	}
}
