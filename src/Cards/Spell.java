package Cards;

import Characteristics.Manacost;

public class Spell extends Card {

	public Spell(Manacost cost, String name, String rules, String types) {
		super(cost, name, rules, types);
		// TODO Auto-generated constructor stub
	}
	protected int Destroy() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void Counter()
	{
		this.Destroy();
	}
	
	@Override
	public String toString() {
		return "Spell [strName=" + this.strName + "]";
	}

}
