package hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Testing Player Class")
class PlayerTest
{
	Player npc;
	int max_hp = 200;
	ObjectMapper objectMapper;
	
	@BeforeEach
	void setup() throws Exception
	{
		npc = new Player(max_hp, "name");
        objectMapper = new ObjectMapper();
	}
	
	
	@Nested
	@DisplayName("Testing the serialization and deserialization")
	class Jackson
	{
		@Test
		@DisplayName("testing serializatoin")
	    void testSerialization() throws Exception
		{
	        Player player = new Player(max_hp, "name");
	        player.setPosition(305);
	        
	        String jsonString = objectMapper.writeValueAsString(player);
	        
	        assertTrue(jsonString.contains("\"name\":\"name\""));
	        assertTrue(jsonString.contains("\"max_health\":" + player.getMaxHealth()));
	        assertTrue(jsonString.contains("\"current_position\":" + player.getPosition()));
	        assertTrue(jsonString.contains("\"player_id\":\"" + player.getPlayerId() + "\""));
	    }

	    @Test
	    @DisplayName("testing deserialization")
	    void testDeserialization() throws Exception
	    {
	        String jsonString = """
	                {
	                    "@type": "player",
	                    "name": "name",
	                    "max_health": 100,
	                    "current_position": 305,
	                    "player_id": "123e4567-e89b-12d3-a456-426614174000"
	                }
	                """;
	        
	        Player player = objectMapper.readValue(jsonString, Player.class);
	        
	        assertEquals("name", player.getName());
	        assertEquals(100, player.getMaxHealth());
	        assertEquals(305, player.getPosition());
	        assertEquals("123e4567-e89b-12d3-a456-426614174000", player.getPlayerId());
	    }
	}
	
	@Test
	@DisplayName("Testing the construcor")
	void constructor()
	{	
		int hp_expected = 2;
		Player bok = new Player(hp_expected, "name");
		assertEquals(hp_expected, bok.getMaxHealth());
		assertEquals("name", bok.getName());
		Player bob = new Player();
		assertNotEquals(bok.getPlayerId(), bob.getPlayerId());
	}
	
	
	@Nested
	@DisplayName("Testing the healing")
	class Heal
	{
		
		@Test
		@DisplayName("value < totDamage")
		void testHeal()
		{
			int dmg = 20;
			int healing = dmg/2;
			npc.applyDamage(dmg);
			npc.heal(healing);
			assertEquals(max_hp + (healing - dmg), npc.getCurrHealth());
		}
		
		@Test
		@DisplayName("|value| < totDamage")
		void testHealNegative()
		{
			int dmg = 20;
			int healing = dmg/2;
			npc.applyDamage(dmg);
			npc.heal(-healing);
			assertEquals(max_hp + (healing - dmg), npc.getCurrHealth());
		}
		
		@Test
		@DisplayName("value > totDamage")
		void testGreatHeal()
		{
			int dmg = 20;
			int healing = dmg*2;
			npc.applyDamage(dmg);
			npc.heal(healing);
			assertEquals(npc.getMaxHealth(), npc.getCurrHealth());
		}
	}

		@Test
		@DisplayName("Testing Equip and Unequip")
		void testEquip()
		{
			int initial_attack = npc.getAttackValue();
			int weapon  = 20;
			npc.equip(weapon);
			assertAll(
					() -> assertEquals(initial_attack + weapon, npc.getAttackValue(), "equip doesn't change the attack value"),
					() -> assertTrue(npc.isEquiped())
					);
			npc.unequip();
			assertAll(
					() -> assertEquals(initial_attack, npc.getAttackValue(), "unequip doesn't restore the attack value"),
					() -> assertFalse(npc.isEquiped())
					);
		}

	

}
