package Zones;

import java.util.Iterator;

import Cards.Card;
import Cards.Permanent;

public class Battlefield extends Zone{

	public int findUntappedPermanentIndex(Card C)
	{
		Iterator<Permanent> itPerm = this.getContentsIterator();
		int x = 0;
		Permanent tempPerm;
		while (itPerm.hasNext())
		{
			tempPerm = itPerm.next();
			if (tempPerm.isUntapped() && tempPerm.equals(C))
			{
				return x;
			}
			x++;
		}
		return -1;
	}
	
}
