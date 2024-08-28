package hospital;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a map of rooms within a hospital. Each room can contain items and/or enemies.
 * The `Map` class tracks the state of items or enemies in each room through flags.
 */
public class Map
{
    /**
     * Represents a room within the hospital. Each room contains a list of flags
     * indicating the presence of items or enemies.
     */
	public static class Room
	{
        /** A list of boolean flags representing items or enemies in the room. */
        protected List<Boolean> itemFlag;
        
        /**
         * Constructs a Room object with the given list of item or enemy flags.
         * 
         * @param itemFlag a list of boolean values representing the presence of items or enemies.
         *                 If null, an empty list is created.
         */
        @JsonCreator
        public Room(@JsonProperty("itemFlag") List<Boolean> itemFlag)
        {
            this.itemFlag = itemFlag != null ? itemFlag : new ArrayList<>();
        }
        /** Default constructor required for deserialization. */
        public Room()
        {
        	this.itemFlag = new ArrayList<>();
        }
	}
	
    /** An array of rooms within the hospital map. */
	@JsonProperty("rooms")
	private Room[] rooms;
	
	// 0 -> room 202
	// 1 -> room 201
	// 2 -> room 203
	// 3 -> room 101
	// 4 -> room 102
	// 5 -> room 103
	// 6 -> morgue
	// 7 -> control room
	
    /**
     * Initializes the map with predefined rooms and their respective items or enemies.
     * Each room is initialized with a set number of flags representing the items or enemies.
     */
	public Map()
	{
		rooms = new Room[8];
        for (int i = 0; i < rooms.length; i++)
        {
            rooms[i] = new Room();
        }
        
		//Room 202 -> 3 items
		rooms[0].itemFlag.add(true);
		rooms[0].itemFlag.add(true);
		rooms[0].itemFlag.add(true);
		
		//Room 201 -> 2 items, 1 enemy, 1 item
		
		rooms[1].itemFlag.add(true);
		rooms[1].itemFlag.add(true);
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
	
    /**
     * Constructor for loading saved files.
     * 
     * @param rooms array of Room objects to initialize the map with.
     */
	@JsonCreator
	public Map(@JsonProperty("rooms") Room[] rooms  )
	{
		this.rooms = rooms;
	}	
	
    /**
     * Retrieves the array of rooms within the map. Each room contains flags representing
     * the presence of items or enemies.
     * 
     * @return an array of Room objects representing the hospital's rooms.
     */
	@JsonProperty("rooms")
    public Room[] getRooms()
    {
        return rooms;
    }
    
    /**
     * Retrieves the flag value for a specific room and flag index.
     * The flag indicates the presence of an item or enemy.
     *
     * @param room the index of the room (0-7)
     * @param flag the index of the flag within the room's list of flags
     * @return the boolean value of the flag, where true indicates the presence
     *         of an item or enemy, and false indicates its absence
     */
	public boolean getFlag(int room, int flag)
	{
	    if (room < 0 || room >= rooms.length || flag < 0 || flag >= rooms[room].itemFlag.size())
	    {
	        throw new IndexOutOfBoundsException("Invalid room or flag index.");
	    }
		return rooms[room].itemFlag.get(flag);
	}
	
    /**
     * Sets the flag value for a specific room and flag index to false,
     * indicating that the item or enemy is no longer present.
     *
     * @param room the index of the room (0-7)
     * @param flag the index of the flag within the room's list of flags
     */
	public void setFlag(int room, int flag)
	{
	    if (room < 0 || room >= rooms.length || flag < 0 || flag >= rooms[room].itemFlag.size())
	    {
	        throw new IndexOutOfBoundsException("Invalid room or flag index.");
	    }
		rooms[room].itemFlag.set(flag, false);
	}
}

