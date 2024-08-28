package hospital;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameState {

    private Map<String, Object> savedData;  // Memoria dei dati
    private final ObjectMapper objectMapper;  // Gestore JSON di Jackson
    private final File saveFile;  // Riferimento al file saves.json

    // Costruttore
    public GameState(String saveFilePath)
    {
        this.objectMapper = new ObjectMapper();
        this.saveFile = new File(saveFilePath);
        this.savedData = new HashMap<>();
        load();  // Carica i dati dal file se esiste
    }

    public void save(String key, Object data)
    {
    	savedData.put(key, data);  // Salva il dato nella mappa
    	persist();  // Aggiorna il file saves.json
    }

    public <T> T load(String key, Class<T> valueType)
    {
        return objectMapper.convertValue(savedData.get(key), valueType);
    }

    private void persist()
    {
        try {
            objectMapper.writeValue(saveFile, savedData);
            System.out.println("Game state saved to " + saveFile.getPath());
        } catch (IOException e) {
            System.err.println("Failed to save game state: " + e.getMessage());
        }
    }

    private void load()
    {
        if (saveFile.exists())
        {  // Verifica se il file esiste
            try 
            {
                savedData = objectMapper.readValue(saveFile, new TypeReference<Map<String, Object>>() {});
                System.out.println("Game state loaded from " + saveFile.getPath());
            } catch (IOException e) 
            {
                System.err.println("Failed to load game state: " + e.getMessage());
            }
        }
    }

    public void remove(String key) {
        savedData.remove(key);  // Rimuove l'oggetto dalla mappa
        persist();  // Aggiorna il file saves.json
    }


    public void clear() {
        savedData.clear();  // Cancella tutti i dati nella mappa
        persist();  // Aggiorna il file saves.json
    }
}
