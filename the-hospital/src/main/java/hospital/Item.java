package hospital;


public class Item
{
	private static int id_counter = 0;
	protected int id;
	protected String name = "unknown";
	protected String description = "What is that?!";

	
	
	public Item() {}
	public Item(String name, String des)
	{
		this.id = ++id_counter;
		this.name= name;
		setDescription(des);
	}
	
	//set
	public void setDescription(String des)
	{	description = des;
	}
	
	//get
	public String getDescription() 
	{	return this.description;
	}
	public String getName()
	{	return this.name;
	}
	public int getID()
	{	return id;
	}
	
}

