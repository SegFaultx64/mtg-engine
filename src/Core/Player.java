package Core;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import Cards.Card;
import Cards.Permanent;
import Zones.*;


public class Player {
	String strName;
	Graveyard GY = new Graveyard();
	Hand HD = new Hand();
	Library LIB = new Library();
	public ManaPool MP = new ManaPool();
	int canPlayLands = 1;
	
	public void loadDeck()
	{
		for (int x = 0; x < 30; x++)
		{	
			Card toAdd = Game.GetInstance().KnownCards.get(0);
			toAdd.setOwner(this);
			toAdd.MoveTo(this.LIB);
			this.LIB.addCard(toAdd);
		}
		for (int x = 30; x < 60; x++)
		{
			Card toAdd = Game.GetInstance().KnownCards.get(2);
			toAdd.setOwner(this);
			toAdd.MoveTo(this.LIB);
			this.LIB.addCard(toAdd);
		}
	}
	
	
	public void Draw()
	{
		Game curGame = Game.GetInstance();
		Card topCard = this.LIB.getCard(0);
		curGame.moveTo(topCard, this.HD);
	}
	
	public void Draw(int x)
	{
		Game curGame = Game.GetInstance();
		for (int y = 0; y < x; y++)
		{
			curGame.moveTo(0, this.LIB, this.HD);
		}
	}
	
	public String printHand()
	{
		String temp;
		temp = "" + this.HD;
		return temp;
	}
	
	public void Shuffle()
	{
		this.LIB.shuffle();
	}
	
	@Override public boolean equals(Object C)
	{
		if (!(C instanceof Player))
		{
			return false;
		} else
		{
			Player Temp = (Player) C;
			return this.strName.equals(Temp.strName);
		}
	}

	public void uptapControlledPermanents()
	{
		Game curGame = Game.GetInstance();
		Permanent tempPerm;
		ArrayList<Permanent> temp = curGame.getPermanentsControlledBy(this);
		Iterator<Permanent> tempIt = temp.iterator();
		while (tempIt.hasNext())
		{
			tempPerm = tempIt.next();
			tempPerm.untap();
		}
	}
	
	public Player(String Name)
	{
		this.strName = Name;
	}
	
	public void canPlayAnotherLand()
	{
		this.canPlayLands = 1;
	}

	
}
