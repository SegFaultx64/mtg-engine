package Characteristics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Core.Game;
import Core.Player;

public class Manacost {
	private String Cost;
	private int w, u, b, r, g, c;

	public String getCost() {
		return Cost;
	}
	
	public int getColorless()
	{
		return this.c;
	}
	public String getCMC() {
		String toReturn = Game.ParseCMC(Cost);
		return toReturn;
	}
	
	public Manacost(String init)
	{
		this.Cost = init;
		int numMatches = 0;
		String re1="W";
		Pattern p = Pattern.compile(re1);
		Matcher m = p.matcher(this.Cost);
		while (m.find() == true)
		    numMatches++;
		this.w = numMatches;
		re1="U";
		p = Pattern.compile(re1);
		m = p.matcher(this.Cost);
		while (m.find() == true)
		    numMatches++;
		this.u = numMatches;
		re1="B";	
		p = Pattern.compile(re1);
		m = p.matcher(this.Cost);
		while (m.find() == true)
		    numMatches++;
		this.b = numMatches;
		re1="R";
		p = Pattern.compile(re1);
		m = p.matcher(this.Cost);
		while (m.find() == true)
		    numMatches++;
		this.r = numMatches;
		re1="G";
		p = Pattern.compile(re1);
		m = p.matcher(this.Cost);
		while (m.find() == true)
		    numMatches++;
		this.g = numMatches;
		re1="[0-9]+";
		p = Pattern.compile(re1);
		m = p.matcher(this.Cost);
		if (m.find())
		{
			String colorless = m.toMatchResult().group();
			this.c = Integer.parseInt(colorless);
		} else {
			this.c = 0;
		}
	}

	public boolean canBePayed(Player owner) {
		// TODO Auto-generated method stub
		return (c <= owner.MP.getColorless());
	}
}
