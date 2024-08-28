package hospital;

import static org.junit.jupiter.api.Assertions.*;

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
		Item two = new Item("first", "object");
		two.setDescription("another_object");
		assertAll(
				() -> assertEquals("first", one.getName()),
				() -> assertEquals("object", one.getDescription()),
				() -> assertEquals("another_object", two.getDescription())
				);
	}
	


}
