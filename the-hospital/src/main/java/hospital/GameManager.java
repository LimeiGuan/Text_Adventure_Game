package hospital;

public class GameManager
{
	static Player player = new Player();
	static Map map = new Map();
	static Enemy monster;
	static Inventory inventory = new Inventory();
	static boolean enemyFight = false;
	
	public String commandControl(String input)
	{
		input.toLowerCase();
		String[] splitInput = input.split(" ");
        String output = "";
        int operation;
        
        if(splitInput[0].equals("move"))
        {
        	if(enemyFight)
        		return "Not avaible during combat";
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
        	String target = "";
        	if(splitInput[0].equals("interact"))
        	{
        		if(enemyFight)
            		return "Not avaible during combat";
        		operation = 1;
        		target = splitInput[1];
        		if(splitInput.length == 3)
        			target+=splitInput[2];
        	}
        	else
        	{
        		if(input.equals("look around"))
        		{
        			if(enemyFight)
                		return "Not avaible during combat";
        			operation = 0;
        		}
        		else
        		{
        			if(splitInput[0].equals("attack"))
        			{
        				if(!enemyFight)
        					return "You are not in a combat.";
        				operation = 3;
        				target = "attack";
        			}
        			else
        			{
        				if(splitInput[0].equals("use"))
        				{
        					if(enemyFight)
        					{
        						operation = 3;
        						target = splitInput[1];
        					}
        					else
        						return use(splitInput[1]);
        				}
        				else
        					return "Game Error";
        				//Sistemo gli altri comandi
        			}        			
        		}
        	}
        	
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
	
	public static String newGame()
	{
		return "[You find yourself in a very run-down hospital room.]\r\n"
				+ "[Find a way out]\r\n";
	}
	public static String gameOver()
	{
		return "Player has been eliminated! Game Over!!!";
	}
	
	public static String room201(int operation, String target)
	{
		switch(operation)
		{
			case 0:
			{
				String lookAround = "";
				if(map.getFlag(1, 0))
					lookAround += "Right doll\n";
				if(map.getFlag(1, 1))
					lookAround += "Middle doll\n";
				if(map.getFlag(1, 2))
					lookAround += "Left doll\n";
				if(map.getFlag(1, 3))
					lookAround += "Cupboard";
				if(!map.getFlag(1, 0) && !map.getFlag(1, 1) && !map.getFlag(1, 2) && !map.getFlag(1, 3))
					lookAround = "There are only destroyed dolls left";
				return lookAround;
			}
			case 1:
			{
				if(target.equals("right doll") && map.getFlag(1, 0))
				{
					if(inventory.searchItem("scalpel"))
					{
						inventory.addItem(new Item("Key room 203","Key to unlock room 203"));
						map.setFlag(1, 0);
						return "[The doll can’t hear you.]\r\n"
								+ "[You destroy the right doll with the scalpel. Inside you find the key to room 203]";
					}
					else
						return "[The doll can’t hear you.]\r\n"
								+ "[I feel the urge to destroy the doll but i'm missing the tool]";
				}
				if(target.equals("middle doll") && map.getFlag(1, 1))
				{
					if(inventory.searchItem("scalpel"))
					{
						map.setFlag(1, 1);
						return "[The doll can’t tell you.]\r\n"
								+ "[You destroy the middle doll with the scalpel. Inside you find a paper. It says “Liar, Cheater, Psycho”.]";
					}
					else
						return "[The doll can’t tell you.]\r\n"
								+ "[I feel the urge to destroy the doll but i'm missing the tool]";
				}
				if(target.equals("left doll") && map.getFlag(1, 2))
				{
					if(inventory.searchItem("scalpel"))
					{
						enemyFight = true;
						monster = new Enemy(50);
						monster.setAttackValue(10);
						return "[The doll can’t see you.]\r\n"
								+ "[You try to destroy the left doll with the scalpel…]\r\n"
								+ "BATTLE\r\n"
								+ "[Kill  “Can’t hear, see, talk”]\r\n";
					}
					else
						return "[The doll can’t see you.]\r\n"
								+ "[I feel the urge to destroy the doll but i'm missing the tool]";
				}
				if(target.equals("cupboard") && map.getFlag(1, 3))
				{
					inventory.addItem(new Medicine("Syringe","It has the same liquid in the IV stand. Heal 25 Hp",25));
					inventory.addItem(new Medicine("Syringe","It has the same liquid in the IV stand. Heal 25 Hp",25));
					inventory.addItem(new Medicine("Syringe","It has the same liquid in the IV stand. Heal 25 Hp",25));
					map.setFlag(1, 3);
					return "[You find some syringe]";
				}
				return "[No such target]";
			}
			case 2:
			{
				int position = player.getPosition();
				if(position != 200)
					return "No such room";
				else
				{
					
					if(!inventory.searchItem("key room 201"))
					{
						return "[The door seems to be locked, try to find something to unlock the door]";
					}
					else
					{
						player.setPosition(201);
						return "You are now in Room 201";
					}
				}
			}
			case 3:
			{
				if(target.equals("attack"))
				{
					if(monster.applyDamage(operation))
					{
						inventory.addItem(new Medicine("Bandage","[A bandage. You can use it to recover HP. Heal 15 Hp]",15));
						map.setFlag(2, 0);
						return "[After you kill the monster, it drops something]";
					}
					else 
					{
						if(player.applyDamage(operation))
							return gameOver();
						else
							return "The enemy health is : " + monster.getCurrHealth();
					}
				}
				else
					return use(target);
			}
			default:
				return "Game Error!";
		}
	}
	public static String room202(int operation, String target)
	{
		switch(operation)
		{
			case 0:
			{
				String lookAround = "";
				
				if(map.getFlag(0, 0))
					lookAround += "Table\n";
				
				lookAround += "Door\nBed\nIV stand\n";
				
				if(map.getFlag(0, 1))
					lookAround += "Wardrobe\n";
				
				lookAround += "Window\nGlass Shards\n";
				
				if(map.getFlag(0, 2))
					lookAround += "Cupboard";
				return lookAround;
			}
			case 1:
			{
				if(target.equals("table") && map.getFlag(0, 0))
				{
					inventory.addItem(new Item("Key room 201","Key to unlock room 201"));
					map.setFlag(0, 0);
					return "[There is a key on the table]\r\n"
							+ "[You obtain key n° 201]\r\n";
				}
				if(target.equals("door"))
				{
					if(inventory.searchItem("key room 202"))
						return "[You unlock the door with the key.]";
					else
						return "[The door seems to be locked, try to find something to unlock the door]";
				}
				if(target.equals("bed"))
				{
					return "[You don’t feel sleepy at all.]";
				}
				if(target.equals("ivstand"))
				{
					return "[It was attached to your arm. You wonder what is inside.]";
				}
				if(target.equals("wardrobe") && map.getFlag(0, 1))
				{
					inventory.addItem(new Item("Key room 202","Key to unlock room 202"));
					map.setFlag(0, 1);
					return "[There is a key inside.]\r\n"
							+ "[You got the key n° 202]\r\n";
				}
				if(target.equals("window"))
				{
					return "[You can barely see anything outside. It’s night and misty]";
				}
				if(target.equals("glass shards"))
				{
					boolean deathFlag = player.applyDamage(5);
					if(!deathFlag)
					return "[You try to take a shard. Now your hand is dripping with blood, but strangely you don’t feel any pain.]\r\n"
							+ "[You lose HP.]\r\n";
					else
						return gameOver();
				}
				if(target.equals("cupboard") && map.getFlag(0, 2))
				{
					Weapon scalpel = new Weapon("Scalpel","A handy weapon",25);
					inventory.addItem(scalpel);
					map.setFlag(0, 2);
					return "[You find a scalpel. Perhaps it will return useful later.]\r\n"
							+ "[You've picked up a weapon! Equip it now to enhance your combat abilities.]";
				}
				return "[No such target]";
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
					return "[No such room]";
				else
				{
					player.setPosition(203);
					return "[You are now in Room 203]";
				}
			}
			default:
				return "Game Error!";
		}
	}
	public static String room101(int operation, String target)
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
					boolean key = false;
					if(key)
					{
						return "[The door seems to be locked, try to find something to unlock the door]";
					}
					else
					{
						player.setPosition(101);
						if(map.getFlag(3, 0))
						{
							return "enemy-[The shelfs are full of collections of eyes.]\r\n"
									+ "[You admit that they are pretty, but at the same time creepy.]\r\n"
									+ "[Suddenly the sound of glass shattering catches your attention.]\r\n"
									+ "[A monster made of many eyes is ready to attack you.]\r\n"
									+ "“KILL THE MONSTER”";
						}
						else
							return "You are now in Room 101";
					}
				}
			}
			default:
				return "Game Error!";
		}
	}
	public static String room102(int operation, String target)
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
			case 1:
			{
				if(target.equals("walls"))
				{
					return "[The handprints disappeared.]\r\n"
							+ "[There are words written]\r\n"
							+ "[I am not “Player name”]\r\n"
							+ "[“I am not a student”]\r\n"
							+ "[“I am not a human being.]\r\n"
							+ "[“I am not consciousness inhabiting an arbitrary body.]";
				}
				if(target.equals("Drawers") && map.getFlag(4, 1))
				{
					return "[You find other syringes]";
				}
				if(target.equals("Cupboard") && map.getFlag(4, 2))
				{
					return "[You find some matches and VERY FLAMMABLE alcohol]";
				}
				return "[No such target]";
			}
			case 2:
			{
				int position = player.getPosition();
				if(position != 100)
					return "No such room";
				else
				{
					//Controllo se c'è la chiave
					boolean key = false;
					if(key)
					{
						return "[The door seems to be locked, try to find something to unlock the door]";
					}
					else
					{
						player.setPosition(102);
						if(map.getFlag(4, 0))
						{
							return "enemy-[You enter Room 102.]\r\n"
									+ "[The walls are covered with handprints.]\r\n"
									+ "[As you slowly inspect the wall, you realize it is blood.]\r\n"
									+ "[Many hands try to grab you, but you dodge in time.]\r\n"
									+ "“KILL THE MONSTER”";
						}
						else
							return "You are now in Room 102";
					}
				}
			}
			case 3:
			{
				if(map.getFlag(4, 0))
				{
					if(target.equals("attack"))
					{
						if(monster.applyDamage(operation))
						{
							//Aggiungo chiave 103
							map.setFlag(4, 0);
							return "[They dropped something]";
						}
						else 
						{
							if(player.applyDamage(operation))
								return gameOver();
							else return "The enemy health is : " + monster.getCurrHealth();
						}
					}
					else
					{
						if(target.equals("glue"))
						{
							//Se uso la colla insta kill
							//Controllo se ho nell'intenventario la glue
							monster.applyDamage(999);
							use(target);
							//Aggiungo chiave
							map.setFlag(4, 0);
							return "[You use the glue and the hands get stuck together.]\r\n"
									+ "[Unable to move, they surrender and disappear.]\r\n"
									+ "[They dropped something]\r\n";
						}
						else
							use(target);
					}
				}
				else
					return "No enemy in the room";
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
				return "The room is dark. The lone figure has apperently left";
			}
			case 1:
			{
				return "Nothing to interact in this room";
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
	public static String hallway2(int operation, String target)
	{
		switch(operation)
		{
			case 0:
				return "[The hallway seems empty. Only faint light coming from the rooms are visible]";
			case 1:
				return "[No item to interact]";
			case 2:
			{
				int position = player.getPosition();
				if(position != 999 && position != 203 && position != 202 && position != 201)
					return "[No such room]";
				else
				{
					if(position == 202)
					{
						if(!inventory.searchItem("key room 202"))
						{
							return "[The door seems to be locked, try to find something to unlock the door]";
						}
					}
					player.setPosition(200);
					return "[You are now in the Hallway of Floor 2]";
				}
			}
			default:
				return "[Game error]";
		}
	}
	public String morgue(int operation, String target)
	{
		switch(operation)
		{
			case 0:
			{
				return "Only ashes remain";
			}
			case 1:
			{
				return "Nothing to interact in this room";
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
				return "All the monitors seem to be destroyed or not working";
			}
			case 1:
			{
				return "Nothing to interact in this room";
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
	
	public static String use(String target)
	{
		//Utilizzo l'item
		return "";
	}
	
	public void inventory()
	{
		
	}
	
}
