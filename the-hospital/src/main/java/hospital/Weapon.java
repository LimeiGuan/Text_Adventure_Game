package hospital;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Represents a weapon item that can be equipped.
 * This class extends ItemEquippable and provides functionality specific to weapons.
 */
@JsonTypeName("weapon")
public class Weapon extends ItemEquippable
{

    /**
     * Constructs a Weapon object with the specified name, description, and damage.
     *
     * @param nm the name of the weapon
     * @param des the description of the weapon
     * @param dmg the damage value of the weapon
     */
	public Weapon(@JsonProperty("name") String nm, @JsonProperty("description") String des, 
			@JsonProperty("stats") int dmg)
	{
		super(nm, des, dmg);
		setStats(dmg);
	}
	
    /**
     * Sets the damage value of the weapon.
     * If the provided damage value is less than or equal to 0, it is set to 0.
     *
     * @param st the new damage value of the weapon
     */
	@Override
	public void setStats(int st)
	{
		if(st <= 0) stats = 0;
		else if(st>0) stats = st;	
	}
}
