package hospital;


import java.util.ArrayList;

public class Inventory
{
	private ArrayList<Item> inventory = new ArrayList<>();

	public void addItem(Item item)
	{
		inventory.add(item);
	}
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
	public boolean searchItem(String item)
	{
		for(int i=0;i<inventory.size();i++)
		{
			if(inventory.get(i).getName().toLowerCase().equals(item))
				return true;
		}
		return false;
	}
}

