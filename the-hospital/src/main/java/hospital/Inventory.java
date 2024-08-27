package hospital;


import java.util.ArrayList;

public class Inventory
{
	private ArrayList<ItemEquipable> inventory = new ArrayList<>();

	public void addItem(ItemEquipable item)
	{
		inventory.add(item);
	}
	public void removeItem(ItemEquipable item)
	{
		inventory.remove(item);
	}
	public boolean searchItem(ItemEquipable item)
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
		for (ItemEquipable item : inventory)
		{
            items = items + item.getName() + " ";
        }
		return items;
	}
}

