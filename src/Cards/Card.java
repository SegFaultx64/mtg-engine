package Cards;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Zones.Zone;
import Characteristics.Manacost;
import Characteristics.Typeline;
import Core.Player;


public class Card {
	Zone zneCurrentZone;
	Manacost mnaCost;
	String strName;
	Typeline typTypes;
	String strRulesText;
	Player Owner;
	int intPower, intToughness, intLoyalty = 0;
	
	public void setOwner(Player O)
	{
		this.Owner = O;
	}
	
	public Zone getZone() {
		return zneCurrentZone;
	}

	public void MoveTo(Zone Z) {
		this.zneCurrentZone = Z;
	}

	public Card(Manacost cost, String name, String rules, String types)
	{
		this.mnaCost = cost;
		this.strName = name;
		this.strRulesText = rules;
		this.typTypes = new Typeline(types);
	}
	
	public void getColor()
	{
		
	}

	@Override
	public String toString() {
		return "Card [strName=" + strName + "]";
	}

	/*
	protected int Destroy()
	{
		return 0;
	}
	 */

	@Override public boolean equals(Object C)
	{
		if (!(C instanceof Card))
		{
			return false;
		} else
		{
			Card Temp = (Card) C;
			return this.strName.equals(Temp.strName);
		}
	}
	
	public boolean isLand()
	{
		return this.typTypes.isLand();
	}
	
	public boolean isPermanent()
	{
		return this.typTypes.isPermanent();
	}
	
	public boolean hasFlash()
	{
		return this.typTypes.isInstant();
	}
	
	public Permanent getPermanent()
	{
		Permanent Temp = new Permanent(this.mnaCost, this.strName, this.strRulesText, this.typTypes.getString());
		Temp.zneCurrentZone = this.zneCurrentZone;
		Temp.intLoyalty = this.intLoyalty;
		Temp.intPower = this.intPower;
		Temp.intToughness = this.intToughness;
		return Temp;
	}
	
	public Spell getSpell()
	{
		Spell Temp = new Spell(this.mnaCost, this.strName, this.strRulesText, this.typTypes.getString());
		Temp.zneCurrentZone = this.zneCurrentZone;
		Temp.intLoyalty = this.intLoyalty;
		Temp.intPower = this.intPower;
		Temp.intToughness = this.intToughness;
		return Temp;
	}
	
	public boolean canPlay()
	{
		return this.mnaCost.canBePayed(this.Owner);
	}
	
	public Manacost getManaCost() 
	{
		return this.mnaCost;
	}
	

}
	
