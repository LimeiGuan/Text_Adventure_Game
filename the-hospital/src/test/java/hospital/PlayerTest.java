package hospital;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Testing Player Class")
class PlayerTest
{
	Player npc;
	int max_hp = 100;

	
	@BeforeEach
	void setup() throws Exception
	{
		npc = new Player(max_hp, "name");
	}
	
	@Test
	@DisplayName("Testing the construcor")
	void constructor()
	{	int hp_expected = 2;
		Player bok = new Player(hp_expected, "name");
		assertEquals(hp_expected, bok.getMaxHealth());
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
			assertNotEquals(max_hp + (healing - dmg), npc.getCurrHealth());
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

	@Disabled
	@Test
	@DisplayName("testing game over")
	void gameOver()
	{
		//check if curr_health <= 0 does something
	}
	
	@Disabled
	@Test
	@DisplayName("Testing position")
	void testPosition()
	{
		//test if position can be changed
	}

}
