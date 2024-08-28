package hospital;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameState
{
	/**
	 * The GameState class is responsible for managing the game's saved state
	 * using JSON serialization. It allows for saving, loading, removing, and clearing
	 * game state data to and from a specified file.
	 * <p>
	 * The game state is stored in a file specified by the path provided to the constructor.
	 * The class uses Jackson's {@link ObjectMapper} to handle JSON serialization and deserialization.
	 * </p>
	 */

	/**Memory for saved data*/
    private Map<String, Object> savedData;  
    /**Jackson JSON handler*/
    private final ObjectMapper objectMapper;  
    /**Reference to the save.json file*/
    private final File saveFile; 

    /**
     * Constructs a GameState instance with the specified file path for saving and loading data.
     * <p>
     * Initializes the {@link ObjectMapper}
     * </p>
     * 
     * @param saveFilePath the path to the file where the game state will be saved and loaded from
     */
    public GameState(String saveFilePath)
    {
        this.objectMapper = new ObjectMapper();
        this.saveFile = new File(saveFilePath);
        this.savedData = new HashMap<>();
    }

    /**
     * Saves a key-value pair to the game state and persists it to the file.
     * <p>
     * Updates the in-memory data map and writes the changes to the save file.
     * </p>
     * 
     * @param key the key associated with the data to be saved
     * @param data the data to be saved
     */
    public void save(String key, Object data)
    {
    	savedData.put(key, data);  // Save the data in the map
    	persist();  // Update the file
    }

    
    /**
     * Save multiple object with only a function
     * 
     * @param map object that contain all the flags
     * @param basic information of the player
     * @param items of inventory
     */
    public void updateProgress(Map map, Player player, Inventory inventory)
    {
        save("map", map);
        save("player", player);
        save("inventory", inventory);
    }
    
    /**
     * Loads a value from the game state associated with the specified key and converts it to the specified type.
     * <p>
     * Retrieves the data from the in-memory map and converts it using Jackson's {@link ObjectMapper}.
     * </p>
     * 
     * @param key the key associated with the data to be loaded
     * @param valueType the class type to which the data should be converted
     * @param <T> the type of the data to be returned
     * @return the data associated with the key, converted to the specified type, or {@code null} if not found
     */
    public <T> T load(String key, Class<T> valueType)
    {
        return objectMapper.convertValue(savedData.get(key), valueType);
    }

    /**
     * Writes the current game state to the save file.
     * <p>
     * Serializes the in-memory data map to JSON and writes it to the specified file.
     * </p>
     */
    private void persist()
    {
        try 
        {
            objectMapper.writeValue(saveFile, savedData);
            System.out.println("Game state saved to " + saveFile.getPath());
        } catch (IOException e) {
            System.err.println("Failed to save game state: " + e.getMessage());
        }
    }

    /**
     * Loads the game state from the save file into memory.
     * <p>
     * Deserializes the JSON data from the file into the in-memory data map if the file exists.
     * </p>
     */
    private void load()
    {
        if (saveFile.exists())
        {  // Check if the file exists
            try 
            {
                savedData = objectMapper.readValue(saveFile, new TypeReference<Map<String, Object>>() {});
                System.out.println("Game state loaded from " + saveFile.getPath());
            }
            catch (IOException e) 
            {
                System.err.println("Failed to load game state: " + e.getMessage());
            }
        }
    }

    /**
     * Removes a key-value pair from the game state and persists the changes to the file.
     * <p>
     * Deletes the entry associated with the specified key from the in-memory data map and updates the file.
     * </p>
     * 
     * @param key the key of the entry to be removed
     */
    public void remove(String key) {
        savedData.remove(key);  // Remove the object from the map
        persist();  // Update the file
    }

    /**
     * Clears all data from the game state and persists the changes to the file.
     * <p>
     * Empties the in-memory data map and writes the empty map to the file.
     * </p>
     */
    public void clear() {
        savedData.clear();   // Clear all data in the map
        persist();  // Update the file
    }
}
