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
	    @JsonSubTypes.Type(value = ItemEquippable.class, name = "item_equippable"),
	    @JsonSubTypes.Type(value = Weapon.class, name = "weapon"),
	    @JsonSubTypes.Type(value = Medicine.class, name = "medicine")
	})
/**
 * Represents a generic item with a name and description.
 * The class provides methods to get and set the item's name and description.
 */
public class Item
{
    /** 
     * The name of the item.
     */
	protected String name = "unknown";
    /** 
     * The description of the item.
     */
	protected String description = "What is that?!";

	
    /**
     * Default constructor that initializes the item with default values.
     */
	public Item() {}
    /**
     * Constructs an item with the specified name and description.
     *
     * @param name the name of the item
     * @param des the description of the item
     */
	@JsonCreator
	public Item(@JsonProperty("name") String name, @JsonProperty("description") String des)
	{
		this.name= name;
		setDescription(des);
	}
	
    /**
     * Sets the description of the item.
     *
     * @param des the new description of the item
     */
	public void setDescription(String des)
	{	description = des;
	}
	
    /**
     * Returns the description of the item.
     *
     * @return the description of the item
     */
	@JsonProperty("description")
	public String getDescription() 
	{	return this.description;
	}
    /**
     * Returns the name of the item.
     *
     * @return the name of the item
     */
	@JsonProperty("name")
	public String getName()
	{	return this.name;
	}
	
}

