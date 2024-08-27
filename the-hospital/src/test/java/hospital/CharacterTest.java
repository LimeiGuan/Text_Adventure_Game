package hospital;


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
	int max_hp = 100;
	
	@BeforeEach
	void setUp() throws Exception
	{	npc = new Character(max_hp);
	}
	
	@Test
	@DisplayName("test the constructor with illegal argument")
	void testCharacter()
	{
		Character npc = new Character(-1);
		assertNotEquals(-1, npc.getMaxHealth());
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
		assertEquals(new_attack, npc.getAttackValue(), "setAttack doesn't work");
	}
}
