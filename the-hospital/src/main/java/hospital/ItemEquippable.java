package hospital;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Represents an equippable item with additional statistics.
 * This class extends class Item to include effect statistics.
 */
@JsonTypeName("item_equippable")
public class ItemEquippable extends Item
{
	/** 
	 * The effect statistics of the equippable item. 
	 */
	protected int stats = 0;
	
    /**
     * Constructs an equippable item with the specified name, description, and effect.
     *
     * @param name the name of the item
     * @param des the description of the item
     * @param effect the effect statistics of the item
     */
	@JsonCreator
	public ItemEquippable(@JsonProperty("name") String name, @JsonProperty("description") String des,
			@JsonProperty("stats") int effect)
	{
		super(name, des);
		this.stats = effect;
	}
	
    /**
     * Sets the effect statistics of the equippable item.
     *
     * @param st the new effect statistics of the item
     */
	public void setStats(int st)
	{	this.stats = st;
	}
	
    /**
     * Returns the effect statistics of the equippable item.
     *
     * @return the effect statistics of the item
     */
	@JsonProperty("stats") 
	public int getStats()
	{	return this.stats;
	}


}
