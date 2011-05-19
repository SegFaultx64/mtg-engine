package Characteristics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Typeline {
	String Types;
	public Typeline(String types2)
	{
		Types = types2;
	}
	
	public boolean isLand()
	{
		String re1="Land";	// Any Single Character 2
		Pattern p = Pattern.compile(re1);
		Matcher m = p.matcher(this.Types);
		return m.find();
	}
	
	public boolean isInstant()
	{
		String re1="Instant";	// Any Single Character 2
		Pattern p = Pattern.compile(re1);
		Matcher m = p.matcher(this.Types);
		return m.find();
	}
	
	public boolean isPermanent()
	{
		String re1="Land|Creature|Enchantment|Artifact|Planeswalker";	// Any Single Character 2
		Pattern p = Pattern.compile(re1);
		Matcher m = p.matcher(this.Types);
		return m.find();
	}
	
	public String getString()
	{
		return this.Types;
	}
}
