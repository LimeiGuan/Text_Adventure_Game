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
	int max_hp = 200;

	
	@BeforeEach
	void setUp() throws Exception
	{	npc = new Character(max_hp);
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
	@DisplayName("Testing the setMaxHealth and setCurrHealth Method")
	class SetHp
	{
		@Test
		@DisplayName("normal inputs for setMaxHealth")
		void testNormalMax()
		{
			int hp_expected = 2;
			npc.setMaxHealth(hp_expected);
			assertEquals(hp_expected, npc.getMaxHealth());
			assertNotEquals(npc.getMaxHealth(), npc.getCurrHealth());
		}
		
		@Test
		@DisplayName("illegal inputs for both set methods")
		void testAbnormalMax()
		{
			assertThrows(IllegalArgumentException.class, ()->npc.setMaxHealth(-1));
			assertThrows(IllegalArgumentException.class, ()->npc.setCurrHealth(-1));
		}
		
		@Test
		@DisplayName("normal inputs for setCurrHealth")
		void testNormalCurr()
		{
			npc.setCurrHealth(max_hp/2);
			assertEquals(max_hp/2, npc.getCurrHealth());
			assertNotEquals(npc.getCurrHealth(), npc.getMaxHealth());
		}
		
		@Test
		@DisplayName("inputs that are greater than max_health")
		void testMaxCurr()
		{
			npc.setCurrHealth(max_hp*2);
			assertEquals(npc.getCurrHealth(), npc.getMaxHealth());
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
		
		@Test
		@DisplayName("damage > cur_health")
		void testGameOver()
		{
			assertFalse(npc.applyDamage(max_hp/2));
			assertTrue(npc.applyDamage(max_hp*2));
			
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
		assertEquals(0, npc.getAttackValue(), "setAttack should be 0");
	}
}
