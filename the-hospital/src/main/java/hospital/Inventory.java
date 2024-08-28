package hospital;


import java.util.ArrayList;

public class Inventory
{
	private ArrayList<ItemEquippable> inventory = new ArrayList<>();

	public void addItem(ItemEquippable item)
	{
		inventory.add(item);
	}
	public void removeItem(ItemEquippable item)
	{
		inventory.remove(item);
	}
	public boolean searchItem(ItemEquippable item)
	{
		if(inventory.contains(item))
		{
			//do eventual operation
			return true;
		}
		else
			return false;
	}
	public String displayItem()
	{
		String items = "";
		for (ItemEquippable item : inventory)
		{
            items = items + item.getName() + " ";
        }
		return items;
	}
}

