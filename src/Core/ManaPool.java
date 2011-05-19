package Core;

import Characteristics.Manacost;

public class ManaPool {
	int w,u,b,r,g,c = 0;

	public int getColorless() {
		return c;
	}

	public void addColorless() {
		this.c += 1;
	}
	
	public void removeColorless(int x) {
		this.c -= x;
	}

	public void removeMana(Manacost manaCost) {
		// TODO Auto-generated method stub
		this.c -= manaCost.getColorless();
	}
	
	public void empty()
	{
		w = 0;
		u = 0;
		b = 0;
		r = 0;
		g = 0;
		c = 0;
	}
}
