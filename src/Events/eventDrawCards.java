package Events;

import Core.Player;

public class eventDrawCards extends Event{
	int cardsToDraw = 0;

	public eventDrawCards(Player P, int I)
	{
		super(P);
		this.cardsToDraw = I;
	}

	@Override
	public void exec() {
		// TODO Auto-generated method stub
		this.controller.Draw(cardsToDraw);
	}

}