package hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Testing Charcter Class")
class CharacterTest
{
	
	Character npc;
	int max_hp = 200;
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@BeforeEach
	void setUp() throws Exception
	{	npc = new Character(max_hp);
	}
	
	@Nested
	@DisplayName("Testing the serialization and deserialization")
	class Jackson
	{
		@Test
		@DisplayName("testing serializatoin")
	    void testSerialization() throws Exception {
			npc.applyDamage(20);
			int curr_hp = npc.getCurrHealth();
			int dmg = npc.getAttackValue(); 
	        String jsonString = objectMapper.writeValueAsString(npc);
	        assertTrue(jsonString.contains("\"max_health\":"+ max_hp));
	        assertTrue(jsonString.contains("\"curr_health\":" + curr_hp));
	        assertTrue(jsonString.contains("\"attack\":" + dmg));
	    }

	    @Test
	    @DisplayName("testing deserialization")
	    void testDeserialization() throws Exception {
	        String jsonString = "{\"max_health\":120,\"curr_health\":120,\"attack\":60}";
	        Character character = objectMapper.readValue(jsonString, Character.class);
	        
	        assertEquals(120, character.getMaxHealth());
	        assertEquals(120, character.getCurrHealth());
	        assertEquals(60, character.getAttackValue());
	    }
	}
	 
	@Nested
	@DisplayName("Testing the constructors")
	class Constructor
	{
		@Test
		@DisplayName("testing the defaul constructor")
		void testDefault()
		{
	        Character character = new Character();
	        assertEquals(100, character.getMaxHealth());
	        assertEquals(100, character.getCurrHealth());
	        assertEquals(50, character.getAttackValue());
		}
		
		@Test
		@DisplayName("testing the parametrized constructor ")
		void testParam()
		{
			assertEquals(max_hp, npc.getMaxHealth(), "the given parameter doesn't change the default values");
			assertEquals(max_hp, npc.getCurrHealth(), "the given parameter doesn't change the default values");
			Character np = new Character(-1);
			assertNotEquals(-1, np.getMaxHealth(), "there is something wrong with setMaxHealth");
		}

	}
	@Nested
	@DisplayName("Testing the setMaxHealth Method")
	class SetMaxHp
	{
		@Test
		@DisplayName("normal inputs for setMaHealth")
		void testNormalMax()
		{
			int hp_expected = 2;
			npc.setMaxHealth(hp_expected);
			assertEquals(hp_expected, npc.getMaxHealth());
			assertEquals(hp_expected, npc.getCurrHealth());
		}
		
		@Test
		@DisplayName("illegal inputs for setMaxHealth")
		void testAbnormalMax()
		{
			assertThrows(IllegalArgumentException.class, ()->npc.setMaxHealth(-1));
		}
		
	}
	
	@Nested
	@DisplayName("Testing Damage Intake")
	class Damage
	{

		@Test
		@DisplayName("damage < cur_health")
		void damagePositiveInput()
		{
			int dmg = 20;
			npc.applyDamage(dmg);
			assertEquals(max_hp-dmg, npc.getCurrHealth());
		}
		
		@Test
		@DisplayName("|damage| < cur_health")
		void damageNegativeInput()
		{
			int dmg = -20;
			npc.applyDamage(dmg);
			assertEquals(max_hp+dmg, npc.getCurrHealth());
		}
		
		
	}

	@Test
	@DisplayName("Testing setAttack")
	void testAttack()
	{
		int new_attack = 20;
		npc.setAttackValue(new_attack);
		assertEquals(new_attack, npc.getAttackValue(), "setAttack doesn't work with positives");
		int another_attack = -20;
		npc.setAttackValue(another_attack);
		assertEquals(-another_attack, npc.getAttackValue(), "setAttack doesn't work with negatives");
	}
}
