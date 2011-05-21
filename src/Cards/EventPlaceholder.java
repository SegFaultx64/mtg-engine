package Cards;

import Characteristics.Manacost;
import Events.Event;

public class EventPlaceholder extends Spell {
	Card crdSource;
	Event eveToExec;
	
	public EventPlaceholder(Manacost cost, String name, String rules,
			String types, Card source, Event toExec) {
		super(cost, name, rules, types);
		this.crdSource = source;
		this.eveToExec = toExec;
	}
	
	public String toString() {
		return "Ability [Source=" + this.crdSource.strName + "]";
	}
	
	public Event getEvent()
	{
		return this.eveToExec;
	}

	public Card getSource() {
		// TODO Auto-generated method stub
		return this.crdSource;
	}

}
