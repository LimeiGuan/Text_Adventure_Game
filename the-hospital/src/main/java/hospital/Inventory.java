package hospital;


import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Inventory class manages a collection of items.
 * It provides methods to add, remove, and search for items within the inventory.
 */
public class Inventory
{
    /** The list that holds the items in the inventory. */
	private ArrayList<Item> inventory = new ArrayList<>();

    /**
     * Default constructor 
     */
	public Inventory() {}
	/**
	 * Constructor for deserialization.
	 */
    @JsonCreator
    public Inventory(@JsonProperty("inventory") ArrayList<Item> inventory)
    {
        this.inventory = inventory != null ? inventory : new ArrayList<>();
    }
    
    /**
     * Adds an item to the inventory.
     * 
     * @param item the item to be added to the inventory
     */
	public void addItem(Item item)
	{
		inventory.add(item);
	}
	
    /**
     * Removes an item from the inventory by its name.
     * 
     * @param item the name of the item to be removed (case-insensitive)
     */
	public void removeItem(String item)
	{
		for(int i=0;i<inventory.size();i++)
		{
			if(inventory.get(i).getName().toLowerCase().equals(item))
			{
				inventory.remove(inventory.get(i));
				return ;
			}
		}
	}
	
    /**
     * Searches for an item in the inventory by its name.
     * 
     * @param item the name of the item to search for (case-insensitive)
     * @return true if the item is found, false otherwise
     */
	public boolean searchItem(String item)
	{
		for(int i=0;i<inventory.size();i++)
		{
			if(inventory.get(i).getName().toLowerCase().equals(item))
				return true;
		}
		return false;
	}
	
	/**
	 * Returns a string representation of all the item names in the inventory.
	 *
	 * @return a concatenated string of all item names in the inventory, separated by "+" characters. If the inventory is empty, an empty string is returned.
	 */
	
	public String printAll()
	{
		String output = "";
		for(int i=0;i<inventory.size();i++)
		{
			output+=inventory.get(i).getName();
			output+="+";
		}
		return output;
	}
	
    /**
     * Gets the list of items in the inventory.
     * 
     * @return the list of items
     */
    @JsonProperty("inventory")
    public ArrayList<Item> getInventory() {
        return inventory;
    }

}

