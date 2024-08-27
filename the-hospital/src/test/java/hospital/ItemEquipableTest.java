package hospital;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
@DisplayName("Testing Equipable Items")
class ItemEquipableTest
{

	Medicine potion;
	Weapon sword;
	int healing  = 99;
	int attack = 34;
	@BeforeEach
	void setUp() throws Exception
	{	potion = new Medicine("bandaid", "restores your health, can be used only once", healing);
		sword = new Weapon("sword", "makes the enemies run away", attack);
	}

	@Test
	@DisplayName("Testing the constructors")
	void testConstructors()
	{	Weapon weapon = new Weapon("name", "descriptoin", -attack);
		assertAll(
				() -> assertEquals(healing,potion.getStats()),
				() -> assertEquals(attack, sword.getStats()),
				() -> assertEquals(attack, weapon.getStats())
				);
	}
	
	@Test
	@DisplayName("Medicine object can be used only once")
	void tesGetMedicine()
	{
		potion.getStats();
		assertEquals(0, potion.getStats());
	}
	

}
