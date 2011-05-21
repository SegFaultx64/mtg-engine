package Events;

import Core.*;

public abstract class Event {
	Player controller;
	Trigger parent;
	
	
	public Event(Player P)
	{
		controller = P;
	}
	
	abstract public void exec();
}
