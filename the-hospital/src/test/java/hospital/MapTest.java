package hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Testing Map Class")
class MapTest
{
    ObjectMapper mapper;
    
	@BeforeEach
	void setUp() throws Exception
	{
		mapper = new ObjectMapper();
	}

    void testSerialization() throws Exception {
        // Create a Map instance
        Map map = new Map();

        // Serialize the Map to JSON
        String json = mapper.writeValueAsString(map);

        // Check that the JSON string is not null or empty
        assertNotNull(json);
        assertEquals("{\"rooms\":[{\"itemFlag\":[true,true,true]},{\"itemFlag\":[true,true]},{\"itemFlag\":[true,true,true]},{\"itemFlag\":[true,true]},{\"itemFlag\":[true,true,true]},{\"itemFlag\":[true]},{\"itemFlag\":[true]},{\"itemFlag\":[true]}]}", json);
    }

    @Test
    void testDeserialization() throws Exception {
        // JSON representation of the Map
        String json = "{\"rooms\":[{\"itemFlag\":[true,true,true]},{\"itemFlag\":[true,true]},{\"itemFlag\":[true,true,true]},{\"itemFlag\":[true,true]},{\"itemFlag\":[true,true,true]},{\"itemFlag\":[true]},{\"itemFlag\":[true]},{\"itemFlag\":[true]}]}";

        // Deserialize the JSON to a Map instance
        Map deserializedMap = mapper.readValue(json, Map.class);

        // Verify that the deserialized Map has the expected state
        assertNotNull(deserializedMap);
        assertNotNull(deserializedMap.getRooms());
        assertEquals(8, deserializedMap.getRooms().length);
        assertEquals(3, deserializedMap.getRooms()[0].itemFlag.size());
        assertEquals(true, deserializedMap.getFlag(0, 0));
        assertEquals(true, deserializedMap.getFlag(0, 1));
        assertEquals(true, deserializedMap.getFlag(0, 2));
    }
}
