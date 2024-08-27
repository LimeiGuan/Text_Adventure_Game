package hospital;


public class GameManager
{
	Player player = new Player();
	public String commandControl(String input)
	{
		String[] splitInput = input.split(" ");
        String output = "";
        if(splitInput[0].equals("move"))
        {
        	if(splitInput[1].equals("hallway2"))
        		hallway2();
        	else
        	{
        		if(splitInput[1].equals("hallway1"))
            		hallway1();
        		else
        		{
        			if(splitInput[1].equals("controlroom"))
                		controlRoom(2);
        			else
        			{
        				if(splitInput[1].equals("morgue"))
                    		morgue(2);
        				else
        				{
        					if(splitInput[1].equals("lift"))
                        		output = lift();
        					else
        					{
	        					int room;
	        		        	try
	        		        	{
	        		        	   room = Integer.parseInt(splitInput[1]);
	        		        	}
	        		        	catch (NumberFormatException e)
	        		        	{
	        		        	   room = -1;
	        		        	}
	        		        	switch(room)
	        		        	{
	        		        		case 201:
	        		        		{
	        		        			room201(2);
	        		        			break;
	        		        		}
	        		        		case 202:
	        		        		{
	        		        			room202(2);
	        		        			break;
	        		        		}
	        		        		case 203:
	        		        		{
	        		        			room203(2);
	        		        			break;
	        		        		}
	        		        		case 101:
	        		        		{
	        		        			room101(2);
	        		        			break;
	        		        		}
	        		        		case 102:
	        		        		{
	        		        			room102(2);
	        		        			break;
	        		        		}
	        		        		case 103:
	        		        		{
	        		        			room103(2);
	        		        			break;
	        		        		}
	        		        		default:
	        		        		{
	        		        			output = "No such room!";
	        		        			break;
	        		        		}
	        		        	}
        					}
        				}
        			}
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
	
	public void room201(int operation)
	{
		//move - 2
		//Controllo se ho la chiave
		//Returno missing key se mancante
		//Returno messaggio quando entro la stanza altrimenti
	}
	public void room202(int operation)
	{
		//Sono nella stanza
		
		//look around() - 0, " "
		//return tutte le opzioni
		
		//interact      - 1, "stringa"
		//Errore se non c'è corrispondenza con stringa
		//Alternativa switch case con le opzioni
		//Returno output e modifico inventario
		
	}
	public void room203(int operation)
	{
		
	}
	public void room101(int operation)
	{
		
	}
	public void room102(int operation)
	{
		
	}
	public void room103(int operation)
	{
		
	}
	public String hallway1()
	{
		int position = player.getPosition();
		if(position != 999)
			return "No such room!";
		else
			return "You are now in the HallWay of Floor 1";
	}
	public String hallway2()
	{
		int position = player.getPosition();
		if(position != 999)
			return "No such room!";
		else
			return "You are now in the HallWay of Floor 2";
	}
	public void controlRoom(int operation)
	{
		
	}
	public void morgue(int operation)
	{
		
	}
	public String lift()
	{
		int position = player.getPosition();
		if(position != 200 && position != 100 && position != 0)
			return "No such room!";
		else
			return "You are now in the lift";
	}
}