package hospital;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Testing Enemy Class")
class EnemyTest
{

	Enemy npc;
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@BeforeEach
	void init() throws Exception
	{	npc = new Enemy();}
	
	@Test
	@DisplayName("Testing the construcor")
	void constructor()
	{	int hp_expected = 2;
		Enemy bok = new Enemy(hp_expected);
		assertEquals(hp_expected, bok.getMaxHealth());
	}
	
	@Nested
	@DisplayName("Testing the serialization and deserialization")
	class Jackson
	{
		@Test
		@DisplayName("testing serializatoin")
	    void testSerialization() throws Exception
		{
			npc.applyDamage(20);
	        String jsonString = objectMapper.writeValueAsString(npc);
	        assertTrue(jsonString.contains("\"max_health\":"+ npc.getMaxHealth()));
	        assertTrue(jsonString.contains("\"curr_health\":" + npc.getCurrHealth()));
	        assertTrue(jsonString.contains("\"attack\":" + npc.getAttackValue()));
	    }

	    @Test
	    @DisplayName("testing deserialization")
	    void testDeserialization() throws Exception
	    {
	        String jsonString = "{\"@type\": \"enemy\", \"max_health\":120,\"curr_health\":100,\"attack\":60}";
	        Enemy character = objectMapper.readValue(jsonString, Enemy.class);
	        
	        assertEquals(120, character.getMaxHealth());
	        assertEquals(100, character.getCurrHealth());
	        assertEquals(60, character.getAttackValue());
	    }
	}
}
