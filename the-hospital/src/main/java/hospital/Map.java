package hospital;

import java.util.ArrayList;

public class Map
{
	private class Room
	{
		ArrayList<Boolean> itemFlag = new ArrayList<>();
	}
	
	private Room[] rooms;
	
	// 0 -> room 202
	// 1 -> room 201
	// 2 -> room 203
	// 3 -> room 101
	// 4 -> room 102
	// 5 -> room 103
	// 6 -> morgue
	// 7 -> control room
	
	public Map()
	{
		rooms = new Room[8];
		
		//Room 202 -> 3 items
		
		rooms[0].itemFlag.add(true);
		rooms[0].itemFlag.add(true);
		rooms[0].itemFlag.add(true);
		
		//Room 201 -> 1 item, 1 enemy
		
		rooms[1].itemFlag.add(true);
		rooms[1].itemFlag.add(true);
		
		//Room 203 -> 1 enemy, 2 items
		
		rooms[2].itemFlag.add(true);
		rooms[2].itemFlag.add(true);
		rooms[2].itemFlag.add(true);
		
		//Room 101 -> 1 enemy, 1 item
		
		rooms[3].itemFlag.add(true);
		rooms[3].itemFlag.add(true);
		
		//Room 102 -> 1 enemy, 2 items
		
		rooms[4].itemFlag.add(true);
		rooms[4].itemFlag.add(true);
		rooms[4].itemFlag.add(true);
		
		//Room 103 -> 1 item
		
		rooms[5].itemFlag.add(true);
		
		//Room morgue -> 1 enemy
		
		rooms[6].itemFlag.add(true);
				
		//Room control room -> 1 enemy
		
		rooms[7].itemFlag.add(true);
	}
	
	public boolean getFlag(int room, int flag)
	{
		return rooms[room].itemFlag.get(flag);
	}
	
	public void setFlag(int room, int flag)
	{
		rooms[room].itemFlag.set(flag, false);
	}
}

