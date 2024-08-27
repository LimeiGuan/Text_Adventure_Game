package hospital;


import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Testing Enemy Class")
class EnemyTest
{

	Enemy npc;
	
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
	
}
