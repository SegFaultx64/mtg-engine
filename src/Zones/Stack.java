package Zones;

import Cards.*;
import Core.Game;

public class Stack extends Zone{
	
	public void addSpell(Spell S)
	{
		this.Contents.add(S);
	}
	
	public void addAbility()
	{
		//unimplemented
	}
	
	public void resolveOne()
	{
		Game curGame = Game.GetInstance();
		int size = this.Contents.size();
		Spell toResolve = (Spell) this.Contents.get(size - 1);
		if (toResolve.isPermanent())
		{
			curGame.moveToBattlefield(toResolve);
		}
	}

	public void addSpell(Card S) {
		// TODO Auto-generated method stub
		this.Contents.add(S);
	}

}
