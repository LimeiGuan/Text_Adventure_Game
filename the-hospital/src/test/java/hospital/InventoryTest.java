package hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
@DisplayName("Testing Inventory")
class InventoryTest
{
	
	Inventory inventory;
	Medicine potion;
	Weapon sword;
	int healing  = 99;
	int attack = 34;
	String name_potion = "bandaid";
	String des_potion = "restores your health, can be used only once";
	String name_weapon = "sword";
	String des_weapon =  "makes the enemies run away";
	
	@BeforeEach
	void setUp() throws Exception
	{	
		inventory = new Inventory();
		potion = new Medicine(name_potion,des_potion, healing);
		sword = new Weapon(name_weapon,des_weapon, attack);
		
	}
	
	@Test
	@DisplayName("Testing the add and remove methods")
	void testAddRemove()
	{
		inventory.addItem(potion);
		inventory.addItem(sword);
		assertTrue(inventory.searchItem(sword.getName()), "Item should be added to the inventory");
		inventory.removeItem(sword.getName());
		assertFalse(inventory.searchItem(sword.getName()), "Item should be removed from the inventory");
	}
	
	@Test
	@DisplayName("Testing the searchItem method")
	void testSearch()
	{
		inventory.addItem(sword);
		assertTrue(inventory.searchItem(sword.getName()), "Item should be added to the inventory");
		assertFalse(inventory.searchItem(potion.getName()), "Item should not be found in the inventory");
	}
	
    @Test
    @DisplayName("Tesing the serialization")
    void testSerialization() throws IOException
    {
    	ObjectMapper mapper = new ObjectMapper();
        inventory.addItem(potion);
        inventory.addItem(sword);
        
        String json = mapper.writeValueAsString(inventory);
        assertNotNull(json, "Serialized JSON should not be null");
        assertTrue(json.contains(sword.getName()), "Serialized JSON should contain the item name 'Sword name'");
        assertTrue(json.contains(potion.getName()), "Serialized JSON should contain the item name 'Potion name'");
    }


    @Test
    void testDeserialization() throws IOException
    {
    	ObjectMapper mapper = new ObjectMapper();
        String jsonString = """
                {
                    "inventory": [
                        {
                            "@type": "medicine",
                            "name": "bandaid",
                            "description": "restores your health, can be used only once",
                            "stats": 99
                        },
                        {
                            "@type": "weapon",
                            "name": "sword",
                            "description": "makes the enemies run away",
                            "stats": 34
                        }
                    ]
                }
                """;

        Inventory new_inventory = mapper.readValue(jsonString, Inventory.class);
        List<Item> items = new_inventory.getInventory();
        assertNotNull(items, "Inventory items should not be null");
        assertEquals(2, items.size(), "Inventory should contain two items");
        
        Item firstItem = items.get(0);
        assertTrue(firstItem instanceof Medicine, "First item should be of type Medicine");
        Medicine medicine = (Medicine) firstItem;
        assertEquals("bandaid", medicine.getName());
        assertEquals("restores your health, can be used only once", medicine.getDescription());
        assertEquals(99, medicine.getStats());
        
        Item secondItem = items.get(1);
        assertTrue(secondItem instanceof Weapon, "Second item should be of type Weapon");
        Weapon weapon = (Weapon) secondItem;
        assertEquals("sword", weapon.getName());
        assertEquals("makes the enemies run away", weapon.getDescription());
        assertEquals(34, weapon.getStats());
    }
}
