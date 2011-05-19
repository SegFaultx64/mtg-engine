package Zones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import Cards.Card;


public abstract class Zone {
	ArrayList<Card> Contents = new ArrayList<Card>(); //needs obfuscation or EVERYTHING WILL BREAK
	
	
	@Override
	public String toString() {
		return this.getClass().getName() + ": [" + Contents + "]";
	}


	public static void main (String[] args)
	{
		
	}
	
	public int findCardIndex(Card C)
	{
		int x = this.Contents.indexOf(C);
		return x;
	}
	
	public Card getCard(int Index)
	{
		Card C = this.Contents.get(Index);
		return C;
	}
	
	public void removeCard(int Index)
	{
		this.Contents.remove(Index);
	}
	
	public void addCard(Card C)
	{
		this.Contents.add(C);
	}
	
	public boolean isEmpty()
	{
		return this.Contents.isEmpty();
	}
	
	public void shuffle()
	{
		Collections.shuffle(this.Contents);
	}
	
	public Iterator getContentsIterator()
	{
		return this.Contents.iterator();
		
	}
	
}
