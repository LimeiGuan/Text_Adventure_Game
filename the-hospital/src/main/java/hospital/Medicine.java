package hospital;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Represents a medicine item that can be used to heal.
 * This class extends ItemEquippable and provides functionality specific to healing.
 */
@JsonTypeName("medicine")
public class Medicine extends ItemEquippable
{	
    /**
     * Constructs a Medicine object with the specified name, description, and stats.
     *
     * @param nm the name of the medicine
     * @param des the description of the medicine
     * @param hp	the effect statistics of the medicine, which determine the healing amount. 
     */
	public Medicine(@JsonProperty("name") String nm, @JsonProperty("description") String des, 
			@JsonProperty("stats") int hp)
	{
		super(nm, des, hp);
	}
	
	/**
	 * Uses the medicine, applying its healing effect.Can me used only once.
	 * 
	 * @return the amount of healing provided by the medicine. This is the value of
	 *         the `stats` before it is reset to 0.
	 */
	public int useMedicine()
	{
		int healing = this.stats;
		this.stats = 0;
		return healing;
	}
}
