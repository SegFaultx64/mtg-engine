package Events;

import Cards.Card;
import Cards.EventPlaceholder;
import Core.Game;

public abstract class Trigger {
	Event curEvent;
	Game curGame;
	Card curCard;
	
	public Trigger(Card C, Event E)
	{
		this.curCard = C;
		this.curEvent = E;
	}
	
	public void fire()
	{
		this.curEvent.exec();
	}
	
	public void addToStack()
	{
		curGame = Game.GetInstance();
		this.curEvent.controller = curCard.getOwner();
		EventPlaceholder tempPlaceholder = new EventPlaceholder(null, null, null, null, curCard, this.curEvent);
		curGame.addAbilityToStack(tempPlaceholder);
	}
	
	public void setCard(Card C)
	{
		this.curCard = C;
		this.curEvent.controller = C.getOwner();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(getClass() == obj.getClass()))
		{
			return false;
		} else 
		{
			String strTemp = obj.getClass().getName();
			boolean toReturn = strTemp.equals(this.getClass().getName()) && ((triggerCiTP) obj).curCard.getName().equals(this.curCard.getName());
			return toReturn;
		}
	}
	
	public Event getEvent()
	{
		return this.curEvent;
	}

}
