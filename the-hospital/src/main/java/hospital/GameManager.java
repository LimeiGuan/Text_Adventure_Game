public class GameManager
{
	Player player = new Player();
	Map map = new Map();
	
	public String commandControl(String input)
	{
		input.toLowerCase();
		String[] splitInput = input.split(" ");
        String output = "";
        int operation;
        
        if(splitInput[0].equals("move"))
        {
        	
        	if(splitInput[1].equals("hallway2"))
        		output = hallway2(2, "");
        	else
        	{
    		if(splitInput[1].equals("hallway1"))
        		output = hallway1(2, "");
    		else
    		{
    		if(splitInput[1].equals("mainhall"))
        		output = mainhall(2, "");
    		else
    		{
    		if(splitInput[1].equals("controlroom"))
    			output = controlRoom(2, "");
    		else
    		{
    		if(splitInput[1].equals("morgue"))
    			output = morgue(2, "");
    		else
    		{
    		if(splitInput[1].equals("lift"))
        		output = lift(2, "");
    		else
    		{
    			if(splitInput[1].equals("room"))
    			{
	    			int room;
	            	try
	            	{
	            	   room = Integer.parseInt(splitInput[2]);
	            	}
	            	catch (NumberFormatException e)
	            	{
	            	   room = -1;
	            	}
	            	switch(room)
	            	{
	            		case 201:
	            		{
	            			output = room201(2, "");
	            			break;
	            		}
	            		case 202:
	            		{
	            			output = room202(2, "");
	            			break;
	            		}
	            		case 203:
	            		{
	            			output = room203(2, "");
	            			break;
	            		}
	            		case 101:
	            		{
	            			output = room101(2, "");
	            			break;
	            		}
	            		case 102:
	            		{
	            			output = room102(2, "");
	            			break;
	            		}
	            		case 103:
	            		{
	            			output = room103(2, "");
	            			break;
	            		}
	            		default:
	            		{
	            			output = "No such room!";
	            			break;
	            		}
	            	}
    			}
    			else
    				output = "No such room!";
    		}}}}}}
        }
        else
        {
        	if(splitInput[0].equals("interact"))
        	{
        		operation = 1;
        	}
        	else
        	{
        		if(input.equals("look around"))
        			operation = 0;
        		else
        		{
        			return "Game Error";
        		}
        	}
        	
        	String target = "";
        	if(operation == 1)
        		target = splitInput[1];
        	
			int room = player.getPosition();
			switch(room)
        	{
				case 200:
				{
					output = hallway2(operation, target);
					break;
				}
        		case 201:
        		{
        			output = room201(operation, target);
        			break;
        		}
        		case 202:
        		{
        			output = room202(operation, target);
        			break;
        		}
        		case 203:
        		{
        			output = room203(operation, target);
        			break;
        		}
        		case 100:
				{
					output = hallway1(operation, target);
					break;
				}
        		case 101:
        		{
        			output = room101(operation, target);
        			break;
        		}
        		case 102:
        		{
        			output = room102(operation, target);
        			break;
        		}
        		case 103:
        		{
        			output = room103(operation, target);
        			break;
        		}
        		case 0:
				{
					output = mainhall(operation, target);
					break;
				}
        		case 1:
				{
					output = morgue(operation, target);
					break;
				}
        		case 3:
				{
					output = controlRoom(operation, target);
					break;
				}
        		case 999:
        		{
        			output = lift(operation, target);
        			break;
        		}
        		default:
        		{
        			output = "Game Error";
        			break;
        		}
        	}
        }
    	
        return output;
    }
	
	public static void newGame()
	{
		//set new Game condition
	}
	public static void gameOver()
	{
		//System.out.println("Player has been eliminated! Game Over!!!");
	}
	
	public String room201(int operation, String target)
	{
		switch(operation)
		{
			case 0:
			{
				String lookAround = "";
				if(map.getFlag(1, 0))
					lookAround += "Right doll\nMiddle doll\nLeft doll";
				if(map.getFlag(1, 1))
					lookAround += "\nCupboard";
				if(!map.getFlag(1, 0) && !map.getFlag(1, 1))
					lookAround = "There are only destroyed dolls left";
				return lookAround;
			}
			case 2:
			{
				int position = player.getPosition();
				if(position != 200)
					return "No such room";
				else
				{
					player.setPosition(201);
					return "You are now in Room 201";
				}
			}
			default:
				return "Game Error!";
		}
	}
	public String room202(int operation, String target)
	{
		switch(operation)
		{
			case 0:
			{
				String lookAround = "";
				
				if(map.getFlag(0, 0))
					lookAround += "Table\n";
				
				lookAround += "Door\nBed\nIV stand";
				
				if(map.getFlag(0, 1))
					lookAround += "\nWardrobe\n";
				
				lookAround += "Window\nGlass Shards";
				
				if(map.getFlag(0, 2))
					lookAround += "\nCupboard";
				return lookAround;
			}
			case 2:
			{
				int position = player.getPosition();
				if(position != 200)
					return "No such room";
				else
				{
					player.setPosition(202);
					return "You are now in Room 202";
				}
			}
			default:
				return "Game Error!";
		}
	}
	public String room203(int operation, String target)
	{
		
		switch(operation)
		{
			case 0:
			{
				String lookAround = "";
				if(map.getFlag(2, 1))
					lookAround += "Cupboard\n";
				if(map.getFlag(2, 2))
					lookAround += "Piano";
				return lookAround;
			}
			case 2:
			{
				int position = player.getPosition();
				if(position != 200)
					return "No such room";
				else
				{
					player.setPosition(203);
					return "You are now in Room 203";
				}
			}
			default:
				return "Game Error!";
		}
	}
	public String room101(int operation, String target)
	{
		switch(operation)
		{
			case 0:
			{
				String lookAround = "Eyes";
				if(map.getFlag(3, 1))
					lookAround += "\nTable";
				return lookAround;
			}
			case 2:
			{
				int position = player.getPosition();
				if(position != 100)
					return "No such room";
				else
				{
					player.setPosition(101);
					return "You are now in Room 101";
				}
			}
			default:
				return "Game Error!";
		}
	}
	public String room102(int operation, String target)
	{
		
		
		switch(operation)
		{
			case 0:
			{
				String lookAround = "Walls";
				if(map.getFlag(4, 1))
					lookAround += "\nDrawers";
				if(map.getFlag(4, 2))
					lookAround += "\nCupboard";
				return lookAround;
			}
			case 2:
			{
				int position = player.getPosition();
				if(position != 100)
					return "No such room";
				else
				{
					player.setPosition(102);
					return "You are now in Room 102";
				}
			}
			default:
				return "Game Error!";
		}
	}
	public String room103(int operation, String target)
	{
		
		
		switch(operation)
		{
			case 0:
			{
				return "The room is dark. The lone figure apperently has left";
			}
			case 2:
			{
				int position = player.getPosition();
				if(position != 100)
					return "No such room";
				else
				{
					player.setPosition(103);
					return "You are now in Room 103";
				}
			}
			default:
				return "Game Error!";
		}
	}
	public String mainhall(int operation, String target)
	{
		int position = player.getPosition();
		if(position != 999 && position != 3 && position != 2 && position != 1)
			return "No such room!";
		else
		{
			player.setPosition(0);
			return "You are now in the Main Hall";
		}
	}
	public String hallway1(int operation, String target)
	{
		int position = player.getPosition();
		if(position != 999 && position != 103 && position != 102 && position != 101)
			return "No such room!";
		else
		{
			player.setPosition(100);
			return "You are now in the Hallway of Floor 1";
		}
	}
	public String hallway2(int operation, String target)
	{
		int position = player.getPosition();
		if(position != 999 && position != 203 && position != 202 && position != 201)
			return "No such room!";
		else
		{
			player.setPosition(200);
			return "You are now in the Hallway of Floor 2";
		}
	}
	public String morgue(int operation, String target)
	{
		switch(operation)
		{
			case 0:
			{
				
			}
			case 2:
			{
				int position = player.getPosition();
				if(position != 0)
					return "No such room";
				else
				{
					player.setPosition(1);
					return "You are now in the Morgue";
				}
			}
			default:
				return "Game Error!";
		}
	}
	public String controlRoom(int operation, String target)
	{
		
		switch(operation)
		{
			case 0:
			{
				
			}
			case 2:
			{
				int position = player.getPosition();
				if(position != 0)
					return "No such room";
				else
				{
					player.setPosition(3);
					return "You are now in the Control Room";
				}
			}
			default:
				return "Game Error!";
		}
	}
	
	public String lift(int operation, String target)
	{
		int position = player.getPosition();
		if(position != 200 && position != 100 && position != 0)
			return "No such room!";
		else
		{
			player.setPosition(999);
			return "You are now in the lift";
		}
			
	}
	
	public String use(String target)
	{
		return "";
	}
	
	public void attack()
	{
		
	}
	
	public void inventory()
	{
		
	}
	
}