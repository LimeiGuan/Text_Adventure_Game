package hospital;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
@DisplayName("Test the Item class")
class ItemTest
{
	@Test
	@DisplayName("Testing the constructor")
	void testConstructor()
	{
		Item one = new Item("first", "object");
		Item two = new Item("second", "object");
		
		assertNotEquals(one.getID(), two.getID());
		assertAll(
				() -> assertEquals("first", one.getName()),
				() -> assertEquals("object", one.getDescription())
				);
	}

}
