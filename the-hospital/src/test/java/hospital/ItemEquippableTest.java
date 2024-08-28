package hospital;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.fasterxml.jackson.databind.ObjectMapper;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
@DisplayName("Testing Equippable Items: Medicine and Weapon")
class ItemEquippableTest
{

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
		potion = new Medicine(name_potion,des_potion, healing);
		sword = new Weapon(name_weapon,des_weapon, attack);
	}

	@Test
	@DisplayName("Testing the constructors")
	void testConstructors()
	{	
		Weapon weapon = new Weapon("name", "descriptoin", -attack);
		assertAll(
				() -> assertEquals(healing,potion.getStats()),
				() -> assertEquals(attack, sword.getStats()),
				() -> assertEquals(0, weapon.getStats(), "negative argument, the stats should be 0")
				);
	}
	
	@Test
	@DisplayName("Medicine object can be used only once")
	void tesGetMedicine()
	{
		potion.useMedicine();
		assertEquals(0, potion.getStats());
	}
	
	@Test
	@DisplayName("Testing the serialization")
	void testSer() throws Exception
	{
        ObjectMapper objectMapper = new ObjectMapper();
        
        String jsonStringWeapon = objectMapper.writeValueAsString(sword);
        String jsonStringPotion = objectMapper.writeValueAsString(potion);
        
        assertTrue(jsonStringPotion.contains("\"name\":\""+ name_potion+"\""), "name potion");
        assertTrue(jsonStringPotion.contains("\"description\":\""+des_potion+"\""), "description potion");
        assertTrue(jsonStringPotion.contains("\"stats\":" + potion.getStats()), "stats potion");
        assertTrue(jsonStringWeapon.contains("\"name\":\""+ name_weapon+"\""), "name sword");
        assertTrue(jsonStringWeapon.contains("\"description\":\""+des_weapon+"\""), "description sword");
        assertTrue(jsonStringWeapon.contains("\"stats\":" + sword.getStats()), "stats sword");
	}
	
	@Test
	@DisplayName("Testing the deserialization")
	void testDes() throws Exception
	{
		ObjectMapper objectMapper = new ObjectMapper();
        
		String jsonStringWeapon = """
                {
                	"@type": "weapon",
                    "name": "new_name",
                    "description": "new_description",
                    "stats": 89
                }
                """;
        
        Weapon item = objectMapper.readValue(jsonStringWeapon, Weapon.class);
        
        assertEquals("new_name", item.getName());
        assertEquals("new_description", item.getDescription());
        assertEquals(89, item.getStats());
        
		String jsonStringPotion = """
                {
                	"@type": "medicine",
                    "name": "new_name",
                    "description": "new_description",
                    "stats": 89
                }
                """;
        
        Weapon newItem = objectMapper.readValue(jsonStringWeapon, Weapon.class);
        
        assertEquals("new_name", newItem.getName());
        assertEquals("new_description", newItem.getDescription());
        assertEquals(89, newItem.getStats());
	}
}
