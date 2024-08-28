package hospital;
/**
 * The GameManager class handles the game logic and controls the interaction 
 * between the player, map, enemies, and items in the hospital-themed game.
 * It manages the game state, processes player commands, and controls the 
 * game's flow.
 */
import java.io.IOException;

public class GameManager
{
	static Player player = new Player();
	static Map map = new Map();
	static Enemy monster;
	static Inventory inventory = new Inventory();
	static boolean enemyFight = false;
	static boolean questionFlag = false;
	private static boolean endGame = true;
	GameSaveServices gameSaveServices = new GameSaveServices();
	static GameState gameState;


	/**
	* Processes and executes player commands.
	*
	* @param input the command entered by the player
	* @return the response based on the executed command
	*/
	public String commandControl(String input)
	{
		input = input.toLowerCase();
		String[] splitInput = input.split(" ");
        String output = "";
        int operation;
        
        if(splitInput[0].equals("move"))
        {
        	if(enemyFight)
        		return "[Not avaible during combat]";
        	if(questionFlag)
				return "[Not avaible right now]";
        	if(splitInput[1].equals("exit"))
        		output = exit(2, "");
        	else
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
    		if(splitInput[1].equals("controlroom") || splitInput[1].equals("staircase"))
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
	            			output = "[No such room]";
	            			break;
	            		}
	            	}
    			}
    			else
    				output = "[No such room]";
    		}}}}}}}
        }
        else
        {
        	String target = "";
        	if(splitInput[0].equals("interact"))
        	{
        		if(enemyFight)
            		return "[Not avaible during combat]";
        		if(questionFlag)
					return "[Not avaible right now]";
        		operation = 1;
        		target = splitInput[1];
        		if(splitInput.length == 3)
        			target+= " "+splitInput[2];
        	}
        	else
        	{
        		if(input.equals("look around"))
        		{
        			if(enemyFight)
                		return "[Not avaible during combat]";
        			if(questionFlag)
    					return "[Not avaible right now]";
        			operation = 0;
        		}
        		else
        		{
        			if(splitInput[0].equals("attack"))
        			{
        				if(!enemyFight)
        					return "[You are not in a combat]";
        				if(questionFlag)
        					return "[Not avaible right now]";
        				operation = 3;
        				target = "attack";
        			}
        			else
        			{
        				if(splitInput[0].equals("use"))
        				{
        					if(!questionFlag)
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
        						return "[Not avaible right now]";
        				}
        				else
        				{
        					if(splitInput[0].equals("I'm") && questionFlag)
        					{
        						operation = 3;
        						if(splitInput.length == 1)
        							target = "";
        						if(splitInput.length == 2)
        							target = splitInput[1];
        						if(splitInput.length == 3)
        		        			target = splitInput[1] + " " + splitInput[2];
        					}
        					else
        					{
        						if(splitInput[0].equals("inventory") && !questionFlag)
        							return inventory();
        						else
        						{
        							if(splitInput[0].equals("help"))
        								return help();
        							else
        								if(splitInput[0].equals("newgame"))
        									return newGame(splitInput[1]);
        								else
        									if(splitInput[0].equals("save"))
        										return save();
        									else
        										if(splitInput[0].equals("loadGame"))
            										return loadGame();
        										else
        											return "[Command not avaible]";
        						}
        					}
        				}
        				
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
	/**
     	* Starts a new game with the specified player name.
     	*
     	* @param target the name of the player
     	* @return a description of the starting room
     	*/
	public static String newGame(String target)
	{
		gameState = new GameState("save.json");
		endGame = false;
		player = new Player(100,target);
		return "[You find yourself in a very run-down hospital room.]\r\n"
				+ "[Find a way out]\r\n"
				+ "[You are in room 202]";
	}
	
	/**
	* Ends the game and displays a game over message.
     	*
     	* @return the game over message
     	*/
	public static String gameOver()
	{
		endGame = true;
		return "Player has been eliminated! Game Over!!!";
	}

	/**
     	* Handles the interactions and actions in Room 201.
     	*
     	* @param type of operation (0 for look around, 1 for interact, 2 for moving, 3 for attack)
     	* @param target item or entity to interact with
     	* @return the response based on the action taken
     	*/
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
						inventory.addItem(new Item("Key room203","Key to unlock room 203"));
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
								+ "[Kill  “Can’t hear, see, talk”]";
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
					if(!inventory.searchItem("key room201"))
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
					if(monster.applyDamage(player.getAttackValue()))
					{
						inventory.addItem(new Medicine("Bandage","[A bandage. You can use it to recover HP. Heal 15 Hp]",15));
						map.setFlag(1, 2);
						enemyFight = false;
						return "[After you kill the monster, it drops something]\r\n"
								+ "[A bandage. You can use it to recover HP]";
					}
					else 
					{
						if(player.applyDamage(monster.getAttackValue()))
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
	/**
     	* Handles the interactions and actions in Room 202.
     	*
     	* @param type of operation (0 for look around, 1 for interact, 2 for moving, 3 for attack)
     	* @param target item or entity to interact with
     	* @return the response based on the action taken
     	*/
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
					inventory.addItem(new Item("Key room201","Key to unlock room 201"));
					map.setFlag(0, 0);
					return "[There is a key on the table]\r\n"
							+ "[You obtain key n° 201]";
				}
				if(target.equals("door"))
				{
					if(inventory.searchItem("key room202"))
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
					inventory.addItem(new Item("Key room202","Key to unlock room 202"));
					map.setFlag(0, 1);
					return "[There is a key inside.]\r\n"
							+ "[You got the key n° 202]";
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
							+ "[You lose HP.]";
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
					return "[No such room]";
				else
				{
					player.setPosition(202);
					return "[You are now in Room 202]";
				}
			}
			default:
				return "Game Error!";
		}
	}
	/**
     	* Handles the interactions and actions in Room 203.
     	*
     	* @param type of operation (0 for look around, 1 for interact, 2 for moving, 3 for attack)
     	* @param target item or entity to interact with
     	* @return the response based on the action taken
     	*/
	public static String room203(int operation, String target)
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
				if(!map.getFlag(2, 1) && !map.getFlag(2, 2))
					lookAround = "[There is only a broken piano]";
				return lookAround;
			}
			case 1:
			{
				if(target.equals("cupboard") && map.getFlag(2, 1))
				{
					inventory.addItem(new Item("Pepper spray","It's effective against a certain type of monster"));
					map.setFlag(2, 1);
					return "[You find pepper spray.]\r\n"
							+ "[It's effective against a certain type of monster]";
				}
				if(target.equals("piano") && map.getFlag(2, 2))
				{
					inventory.addItem(new Item("Key lift","Key to operate the lift"));
					map.setFlag(2, 2);
					return "[You play some notes and realize the sound is out of tune.]\r\n"
							+ "[You open the piano and find the keycard to the elevator.]";
				}
				return "[No such target]";
			}
			case 2:
			{
				int position = player.getPosition();
				if(position != 200)
					return "[No such room]";
				else
				{
					if(!inventory.searchItem("key room203"))
					{
						return "[The door seems to be locked, try to find something to unlock the door]";
					}
					else
					{
						player.setPosition(203);
						if(!map.getFlag(2, 0))
							return "[You are now in Room 203]";
						else
						{
							enemyFight = true;
							monster = new Enemy(65);
							monster.setAttackValue(15);
							return "[You enter Room 203.]\r\n"
									+ "[A dark tall figure looms in front of a broken piano. You try to leave, but it hears you and launches towards you.]\r\n"
									+ "BATTLE\r\n"
									+ "[Kill “Amnesia”]";
						}
					}
				}
			}
			case 3:
			{
				if(target.equals("attack"))
				{
					if(monster.applyDamage(player.getAttackValue()))
					{
						inventory.addItem(new Medicine("Bandage","[A bandage. You can use it to recover HP. Heal 15 Hp]",15));
						map.setFlag(2, 0);
						enemyFight = false;
						return "[After you kill the monster, it drops something]\r\n"
								+ "[A bandage. You can use it to recover HP]";
					}
					else 
					{
						if(player.applyDamage(monster.getAttackValue()))
							return gameOver();
						else
							return "[The enemy health is : " + monster.getCurrHealth() + " ]";
					}
				}
				else
					return use(target);
			}
			default:
				return "Game Error!";
		}
	}
	/**
     	* Handles the interactions and actions in Room 101.
     	*
     	* @param type of operation (0 for look around, 1 for interact, 2 for moving, 3 for attack)
     	* @param target item or entity to interact with
     	* @return the response based on the action taken
     	*/
	public static String room101(int operation, String target)
	{
		switch(operation)
		{
			case 0:
			{
				String lookAround = "Eyes\n";
				if(map.getFlag(3, 1))
					lookAround += "Table";
				return lookAround;
			}
			case 1:
			{
				if(target.equals("eyes"))
					return "[The eyes are still staring at you]";
				if(target.equals("table") && map.getFlag(3, 1))
				{
					map.setFlag(3, 1);
					inventory.addItem(new Item("Glue","It's effective against a certain type of monster"));
					return "[You find some glue]\r\n"
							+ "[It's effective against a certain type of monster]";
				}
			}
			case 2:
			{
				int position = player.getPosition();
				if(position != 100)
					return "No such room";
				else
				{
					player.setPosition(101);
					if(map.getFlag(3, 0))
					{
						enemyFight = true;
						monster = new Enemy(125);
						monster.setAttackValue(10);
						return "[You enter Room 101]\r\n"
								+ "[The shelfs are full of collections of eyes.]\r\n"
								+ "[You admit that they are pretty, but at the same time creepy.]\r\n"
								+ "[Suddenly the sound of glass shattering catches your attention.]\r\n"
								+ "[A monster made of many eyes is ready to attack you.]\r\n"
								+ "“KILL THE MONSTER”";
					}
					else
						return "[You are now in Room 101]";
				}
			}
			case 3:
			{
				if(target.equals("attack"))
				{
					if(monster.applyDamage(player.getAttackValue()))
					{
						inventory.addItem(new Item("Key room102","Key to unlock room 102"));
						inventory.addItem(new Medicine("Ban aid","[A band aid. You can use it to recover HP. Heal 20 Hp]",20));
						map.setFlag(3, 0);
						enemyFight = false;
						return "[The monster dropped something.]\r\n"
								+ "[A band aid]\r\n"
								+ "[Key n° 102]";
					}
					else 
					{
						if(player.applyDamage(monster.getAttackValue()))
							return gameOver();
						else
							return "[The enemy health is : " + monster.getCurrHealth() + " ]";
					}
				}
				else
				{
					if(target.equals("pepper spray"))
					{
						if(inventory.searchItem("pepper spray"))
						{
							monster.applyDamage(999);
							inventory.removeItem("pepper spray");
							inventory.addItem(new Item("Key room102","Key to unlock room 102"));
							inventory.addItem(new Medicine("Ban aid","[A band aid. You can use it to recover HP. Heal 20 Hp]",20));
							map.setFlag(3, 0);
							enemyFight = false;
							return "[The monster dropped something.]\r\n"
									+ "[A band aid]\r\n"
									+ "[Key n° 102]";
						}
						else
							return "[Item not found]";
					}
					else
						return use(target);
				}
			}
			default:
				return "Game Error!";
		}
	}
	/**
     	* Handles the interactions and actions in Room 102.
     	*
     	* @param type of operation (0 for look around, 1 for interact, 2 for moving, 3 for attack)
     	* @param target item or entity to interact with
     	* @return the response based on the action taken
     	*/
	public static String room102(int operation, String target)
	{
		switch(operation)
		{
			case 0:
			{
				String lookAround = "Walls\n";
				if(map.getFlag(4, 1))
					lookAround += "Drawers\n";
				if(map.getFlag(4, 2))
					lookAround += "Cupboard";
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
					map.setFlag(4, 1);
					inventory.addItem(new Medicine("Syringe","It has the same liquid in the IV stand. Heal 25 Hp",25));
					inventory.addItem(new Medicine("Syringe","It has the same liquid in the IV stand. Heal 25 Hp",25));
					return "[You find other syringes]";
				}
				if(target.equals("Cupboard") && map.getFlag(4, 2))
				{
					map.setFlag(4, 2);
					inventory.addItem(new Item("Matches","Could be useful"));
					return "[You find some a box of matches]";
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
					if(!inventory.searchItem("key room102"))
					{
						return "[The door seems to be locked, try to find something to unlock the door]";
					}
					else
					{
						player.setPosition(102);
						if(map.getFlag(4, 0))
						{
							enemyFight = true;
							monster = new Enemy(50);
							monster.setAttackValue(35);
							return "[You enter Room 102.]\r\n"
									+ "[The walls are covered with handprints.]\r\n"
									+ "[As you slowly inspect the wall, you realize it is blood.]\r\n"
									+ "[Many hands try to grab you, but you dodge in time.]\r\n"
									+ "“KILL THE MONSTER”";
						}
						else
							return "[You are now in Room 102]";
					}
				}
			}
			case 3:
			{
				if(target.equals("attack"))
				{
					if(monster.applyDamage(player.getAttackValue()))
					{
						inventory.addItem(new Item("Key room103","Key to unlock room 103"));
						map.setFlag(4, 0);
						enemyFight = false;
						return "[They dropped something]\r\n"
								+ "[Key room number 103]";
					}
					else 
					{
						if(player.applyDamage(monster.getAttackValue()))
							return gameOver();
						else
							return "[The enemy health is : " + monster.getCurrHealth() + " ]";
					}
				}
				else
				{
					if(target.equals("glue"))
					{
						if(inventory.searchItem("glue"))
						{
							monster.applyDamage(999);
							inventory.removeItem("glue");
							inventory.addItem(new Item("Key room103","Key to unlock room 103"));
							map.setFlag(4, 0);
							enemyFight = false;
							return "[You use the glue and the hands get stuck together.]\r\n"
									+ "[Unable to move, they surrender and disappear.]\r\n"
									+ "[They dropped something]\r\n"
									+ "[Key room number 103]";
						}
						else
							return "[Item not found]";
					}
					else
						return use(target);
				}
			}
			default:
				return "Game Error!";
		}
	}
	
	/**
     	* Handles the interactions and actions in Room 103.
     	*
     	* @param type of operation (0 for look around, 1 for interact, 2 for moving, 3 for attack)
     	* @param target item or entity to interact with
     	* @return the response based on the action taken
     	*/
	public static String room103(int operation, String target)
	{		
		switch(operation)
		{
			case 0:
			{
				return "[The room is dark. The lone figure has apperently left]";
			}
			case 1:
			{
				return "[Nothing to interact in this room]";
			}
			case 2:
			{
				int position = player.getPosition();
				if(position != 100)
					return "No such room";
				else
				{
					if(!inventory.searchItem("key room103"))
						return "[The door seems to be locked, try to find something to unlock the door]";
					else
					{
						player.setPosition(103);
						if(map.getFlag(5, 0))
							return "[You are now in Room 103]";
						else
						{
							questionFlag = true;
							return "[You enter Room 103.]\r\n"
									+ "[The room is dark, but you can see a lone figure in the corner.]\r\n"
									+ "[He is facing you, but you can’t see his face.]\r\n"
									+ "[It suddenly speak to you]\r\n"
									+ "[“Who are you?”]\r\n\n"
									+ "[Possible answers: ]\r\n"
									+ "[I'm "+player.getName()+"]\r\n"
									+ "[I'm student]\r\n"
									+ "[I'm human being]\r\n"
									+ "[I'm consciousness]\r\n"
									+ "[I'm]";
						}
					}
				}
			}
			case 3:
			{
				if(target.equals(player.getName()))
					return "[“This is not who you are, that is only what you are called”]";
				if(target.equals("student"))
					return "[“That is what you do, not who you are”]";
				if(target.equals("human being"))
					return "[“That is only your species, not who you are”]";
				if(target.equals("consciousness"))
					return "[“That is merely what you are, not who you are”]\r\n";
				if(target.equals(""))
				{
					questionFlag = false;
					map.setFlag(5, 0);
					inventory.addItem(new Item("Key groundLift","Key to operate the lift to the ground floor"));
					return "[You obtain the key card elevator for ground floor]";
				}
				return "[The lone being seems to not understand you]";
			}
			default:
				return "Game Error!";
		}
	}
	/**
     	* Handles the interactions and actions in mainhall.
     	*
     	* @param type of operation (0 for look around, 1 for interact, 2 for moving)
     	* @param target item or entity to interact with
     	* @return the response based on the action taken
     	*/
	public String mainhall(int operation, String target)
	{
		switch(operation)
		{
			case 0:
				return "[The main hall seems empty. You can see the exit door]";
			case 1:
				return "[Nothing to interact]";
			case 2:
			{
				int position = player.getPosition();
				if(position != 999 && position != 3 && position != 2 && position != 1)
					return "[No such room]";
				else
				{
					if(inventory.searchItem("key groundlift"))
					{
						player.setPosition(0);
						return "[You are now in the main hall]";
					}
					else
						return "[The lift require a key to go to the ground floor]";
				}
			}
			default:
				return "Game error!";
		}
		
	}
	/**
     	* Handles the interactions and actions in hallway 1.
     	*
     	* @param type of operation (0 for look around, 1 for interact, 2 for moving)
     	* @param target item or entity to interact with
     	* @return the response based on the action taken
     	*/
	public String hallway1(int operation, String target)
	{
		switch(operation)
		{
			case 0:
				return "[The hallway seems empty. Only faint light coming from the rooms are visible]";
			case 1:
				return "[Nothing to interact]";
			case 2:
			{
				int position = player.getPosition();
				if(position != 999 && position != 103 && position != 102 && position != 101)
					return "[No such room]";
				else
				{
					if(inventory.searchItem("key lift"))
					{
						player.setPosition(100);
						return "[You are now in the Hallway of Floor 1]";
					}
					else
						return "[The lift require a key to go to the first floor]";
				}
			}
			default:
				return "Game error!";
		}
	}
	/**
     	* Handles the interactions and actions in hallway 2.
     	*
     	* @param type of operation (0 for look around, 1 for interact, 2 for moving)
     	* @param target item or entity to interact with
     	* @return the response based on the action taken
     	*/
	public static String hallway2(int operation, String target)
	{
		switch(operation)
		{
			case 0:
				return "[The hallway seems empty. Only faint light coming from the rooms are visible]";
			case 1:
				return "[Nothing to interact]";
			case 2:
			{
				int position = player.getPosition();
				if(position != 999 && position != 203 && position != 202 && position != 201)
					return "[No such room]";
				else
				{
					if(position == 202)
					{
						if(!inventory.searchItem("key room202"))
						{
							return "[The door seems to be locked, try to find something to unlock the door]";
						}
					}
					player.setPosition(200);
					return "[You are now in the Hallway of Floor 2]";
				}
			}
			default:
				return "Game error!";
		}
	}
	/**
     	* Handles the interactions and actions in morgue.
     	*
     	* @param type of operation (0 for look around, 1 for interact, 2 for moving, 3 for attack)
     	* @param target item or entity to interact with
     	* @return the response based on the action taken
     	*/
	public static String morgue(int operation, String target)
	{
		switch(operation)
		{
			case 0:
			{
				return "[Only ashes remain]";
			}
			case 1:
			{
				return "[Nothing to interact in this room]";
			}
			case 2:
			{
				int position = player.getPosition();
				if(position != 0)
					return "[No such room]";
				else
				{
					player.setPosition(1);
					if(map.getFlag(6, 0))
					{
					enemyFight = true;
					monster = new Enemy(player.curr_health);
					monster.setAttackValue(player.getAttackValue());
					return "[You enter the Morgue.]\r\n"
							+ "[It’s really cold.]\r\n"
							+ "[There is a corpse on the table.]\r\n"
							+ "[You come closer to inspect the body.]\r\n"
							+ "[It’s “you”]\r\n"
							+ "[A sense of anguish overcomes you, as you fail to notice the body slowly rising.]\r\n"
							+ "[It looks at you and tries to snatch your neck.]\r\n"
							+ "“KILL THE MONSTER”\r\n"
							+ "[The room looks like it's covered in gasoline]";
					}
					else
						return "[You are now in the morgue]";
				}
			}
			case 3:
			{
				if(target.equals("attack"))
				{
					if(monster.applyDamage(player.getAttackValue()))
					{
						inventory.addItem(new Item("Key staircase","Key to unlock the control room"));
						map.setFlag(6, 0);
						enemyFight = false;
						return "[They dropped something]\r\n"
								+ "[Key staircase]";
					}
					else 
					{
						if(player.applyDamage(monster.attack))
							return gameOver();
						else
							return "[The enemy health is : " + monster.getCurrHealth() + " ]";
					}
				}
				else
				{
					if(target.equals("matches"))
					{
						if(inventory.searchItem("matches"))
						{
							monster.applyDamage(999);
							inventory.removeItem("matches");
							inventory.addItem(new Item("Key staircase","Key to unlock the control room"));
							map.setFlag(6, 0);
							enemyFight = false;
							return "[As last resort you set the monster on fire and close the door behind]\r\n"
									+ "[After some time, you enter the room and find only ashes of the monster and the key to the staircase.]\r\n";
						}
						else
							return "[Item not found]";
					}
					else
						return use(target);
				}
			}
			default:
				return "Game Error!";
		}
	}
	/**
     	* Handles the interactions and actions in control room.
     	*
     	* @param type of operation (2 for moving, 3 for attack)
     	* @param target item or entity to interact with
     	* @return the response based on the action taken
     	*/
	public static String controlRoom(int operation, String target)
	{
		
		switch(operation)
		{
			case 2:
			{
				int position = player.getPosition();
				if(position != 0)
					return "[No such room]";
				else
				{
					
					if(inventory.searchItem("key staircase"))
					{
						enemyFight = true;
						monster = new Enemy(100);
						monster.setAttackValue(25);
						player.setPosition(3);
						return "[You take the starway and reach the control room.]\r\n"
								+ "[As you enter the room, you see many monitors on.]\r\n"
								+ "[All the monitors display your actions in the many rooms.]\r\n"
								+ "[Someone was monitoring your every move.]\r\n"
								+ "[Creeped by the situation, you feel the urgency to find the key to the exit.]\r\n"
								+ "[As soon as you try to leave the room, a strange figure appears behind you.]";
					}
					else
						return "[The door seems to be locked, try to find something to unlock the door]";
				}
			}
			case 3:
			{
				if(target.equals("attack"))
				{
					if(monster.applyDamage(player.getAttackValue()))
					{
						map.setFlag(7, 0);
						enemyFight = false;
						endGame = true;
						return "[It dropped something on the ground.]\r\n"
								+"[You got the key to the exit]"
								+ "[The sound of the key unlocking the exit door makes you feel relieved.]\r\n"
								+ "[The door opens and finally you can breathe the air from outside.]\r\n"
								+ "[As you step outside, some kind of invisible wall makes you unable to leave.]\r\n"
								+ "[The truth hits you.]\r\n"
								+ "[This is a simulation.]\r\n"
								+ "[You are going to wake up soon.]\r\n"
								+ "[To the real world.]";
					}
					else 
					{
						if(player.applyDamage(monster.attack))
							return gameOver();
						else
							return "[The enemy health is : " + monster.getCurrHealth() + " ]";
					}
				}
				else
				{
					if(target.equals("syringe"))
					{
						if(inventory.searchItem("syringe"))
						{
							monster.applyDamage(999);
							inventory.removeItem("syringe");
							map.setFlag(7, 0);
							enemyFight = false;
							endGame = true;
							return "[The monster screams as it disappears.]\r\n"
									+ "[It dropped something on the ground.]\r\n"
									+ "[You got the key to the exit]"
									+ "[The sound of the key unlocking the exit door makes you feel relieved.]\r\n"
									+ "[The door opens and finally you can breathe the air from outside.]\r\n"
									+ "[As you step outside, some kind of invisible wall makes you unable to leave.]\r\n"
									+ "[The truth hits you.]\r\n"
									+ "[This is a simulation.]\r\n"
									+ "[You are going to wake up soon.]\r\n"
									+ "[To the real world.]";
							
						}
						else
							return "[Item not found]";
					}
					else
						return use(target);
				}
			}
			default:
				return "Game Error!";
		}
	}
	/**
     	* Handles the interactions and actions in lift.
     	*
     	* @param type of operation (0 for look around, 1 for interact, 2 for moving)
     	* @param target item or entity to interact with
     	* @return the response based on the action taken
     	*/
	public String lift(int operation, String target)
	{
		switch(operation)
		{
			case 0:
				return "[The lift is somehow still working]";
			case 1:
				return "[Nothing to interact]";
			case 2:
			{
				int position = player.getPosition();
				if(position != 200 && position != 100 && position != 0)
					return "[No such room]";
				else
				{
					player.setPosition(999);
					return "[You are now in the lift]";
				}
			}
			default:
				return "Game error!";
		}
	}
	/**
     	* Handles the interactions and actions with the exit.
     	*
     	* @return the response based on the action taken
     	*/
	public String exit(int operation, String target)
	{
		int position = player.getPosition();
		if(position != 200 && position != 100 && position != 0)
			return "[No such room]";
		else
		{
			player.setPosition(999);
			return "[The door is locked]";
		}
	}
	/**
     	* Handles the items being used
     	* @return the response based on the item used
     	*/
	public static String use(String target)
	{
		if(target.equals("scalpel"))
		{
			player.equip(15);
			return "[Scalpel equipped]";
		}
			
		
		if(target.equals("bandage"))
		{
			if(inventory.removeItem("target"))
			{
				player.heal(15);
				return "[15 hp healed]";
			}
			else
				return "[Item not avaible]";
		}
			
		if(target.equals("band aid"))
		{
			if(inventory.removeItem("target"))
			{
				player.heal(20);
				return "[20 hp healed]";
			}
			else
				return "[Item not avaible]";			
		}
		
		if(target.equals("syringe"))
		{
			if(inventory.removeItem("target"))
			{
				player.heal(25);
				return "[25 hp healed]";
			}
			else
				return "[Item not avaible]";
			
		}
		
		return inventory.printDescription(target);
	}
	/**
     	* Displays the items in the player's inventory.
     	*
     	* @return a list of items in the inventory
     	*/
	public String inventory()
	{
		return inventory.printAll();
	}

	/**
     	* Checks if the game has ended.
     	*
     	* @return true if the game has ended, false otherwise
     	*/
	public boolean getEndGame()
	{
		return endGame;
	}

	/**
     	* Displays the help menu with available commands.
     	*
     	* @return a list of available commands
     	*/
	public String help()
	{
		if(enemyFight)
			return "Attack -> deal damage to the enemy\r\n"
					+ "Use -> use an item\r\n"
					+ "Inventory -> prints the inventory in the output";
		
		return "Look Around -> prints the interactable items in the room\r\n"
				+ "Interact [item name] ->interact with the item in the room\r\n"
				+ "Use -> use an item\r\n"
				+ "Save -> save the game\r\n"
				+ "NewGame [name player] -> start a new game\r\n"
				+ "LoadGame [save game name] -> load a new game\r\n"
				+ "Inventory -> prints the inventory in the output";
	}

	/**
     	* Loads a saved game.
     	*
     	* @return the result of the load operation
     	*/
	public String loadGame()
	{
		try
		{
			gameState = gameSaveServices.loadGame(player.getName());
			map = gameState.load("map", Map.class);
			player = gameState.load("player", Player.class);
			inventory = gameState.load("inventory", Inventory.class);
			return "[Load successful]";
		}
		catch (IOException e)
		{
			return "[Error while loading]";
		}
	}
	
	/**
     	* Saves the current game state.
     	*
     	* @return the result of the save operation
     	*/
	public String save()
	{
		if(enemyFight || questionFlag)
			return "[Not avaible right now]";
		else
		{
			gameState.updateProgress(map, player, inventory);
			gameSaveServices.saveGame(player.getName(), gameState);
			return "[Save successful]";
		}
	}
}
