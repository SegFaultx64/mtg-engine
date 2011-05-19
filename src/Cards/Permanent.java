package Cards;

import Characteristics.Manacost;
import Core.Player;

public class Permanent extends Card {
	boolean isTapped = false;
	Player controller = null;
	
	public Permanent(Manacost cost, String name, String rules, String types) {
		super(cost, name, rules, types);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		return "Card [strName=" + strName + " is tapped: " + isTapped +"]";
	}
	
	public void setController(Player P)
	{
		this.controller = P;
	}
	
	public Player getController()
	{
		return this.controller;
	}
	
	public void tap()
	{
		this.isTapped = true;
	}
	
	public void untap()
	{
		this.isTapped = false;
	}
	
	public boolean isUntapped()
	{
		return !(isTapped);
	}

}
