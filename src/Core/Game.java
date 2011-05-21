package Core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

import Turn.Turn;
import Zones.*;

import Cards.Card;
import Cards.EventPlaceholder;
import Cards.Permanent;
import Characteristics.Color;
import Characteristics.Manacost;
import Events.*;

public class Game {
	private static Game instance = null;
	Battlefield BF = new Battlefield();
	Turn curTurn;
	CoreInput in;
	CoreOutput out;
	boolean isDone = false;
	ArrayList<Card> KnownCards = new ArrayList<Card>();
	ArrayList<Player> Players = new ArrayList<Player>();
	ArrayList<Trigger> knownTriggers = new ArrayList<Trigger>();
	Stack STK = new Stack();
	
	
	public static Game GetInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

	public static String ParseCMC(String ManaCost) {
		// Parse a string containing a manacost using regex
		// Return a string containing the Converted Mana Cost
		return null;
	}

	public static Color ParseColor(Card C) {
		// Takes a card and uses it's rules text and mana cost to determine it's
		// color.
		return null;
	}

	protected Game() {
		try {
			this.out = new CoreOutput(this);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void begin() {
		this.createTestCards();
		this.Players.add(new Player("Max"));
		curTurn = new Turn(this.getPlayer(0));
		this.in = new CoreInput(this, this.getPlayer(0));
	}

	private void createTestCards() {
		Card Test1, Test2, Test3;
		Event tempEvent = new eventDrawCards(null, 1);
		Trigger tempTrigger = new triggerCiTP(null, tempEvent);
		Test1 = new Card(new Manacost("2"), "WallofOmens", "", "Creature");
		Test1.addTriggeredAbility(tempTrigger, tempEvent);
		Test2 = new Card(new Manacost("2"), "Bear", "", "Creature");
		Test3 = new Card(null, "Land", "", "Land");
		this.KnownCards.add(Test1);
		this.KnownCards.add(Test2);
		this.KnownCards.add(Test3);
	}

	public void emptyAllManaPools() {
		this.Players.get(0).MP.empty();
	}

	public void end() {
		this.isDone = true;
	}

	public boolean execStack() {
		return false;
	}

	public Card findCard(Player P, Zone Z, String Name) {
		Card temp = new Card(null, Name, "", "");
		return (Z.getCard((Z.findCardIndex(temp))));
	}

	public ArrayList<Permanent> getPermanentsControlledBy(Player P) {
		ArrayList<Permanent> toReturn = new ArrayList<Permanent>();
		Iterator<Permanent> itPerm = this.BF.getContentsIterator();
		Permanent tempPerm;
		while (itPerm.hasNext()) {
			tempPerm = itPerm.next();
			if (tempPerm != null && tempPerm.getController().equals(P)) {
				toReturn.add(tempPerm);
			}
		}
		return toReturn;
	}

	public Player getPlayer(int x) {
		return this.Players.get(x);
	}

	public void moveTo(Card C, Zone Z) {
		Zone curZone = C.getZone();
		int curIndex = curZone.findCardIndex(C);
		Z.addCard(C);
		curZone.removeCard(curIndex);
		C.MoveTo(Z);
	}

	public void moveTo(int cardIndex, Zone curZone, Zone Z) {
		Card tempCard = curZone.getCard(cardIndex);
		Z.addCard(tempCard);
		curZone.removeCard(cardIndex);
		tempCard.MoveTo(Z);
	}

	public Permanent moveToBattlefield(Card C) {
		Permanent tempPerm = C.getPermanent();
		Zone curZone = C.getZone();
		int curIndex = curZone.findCardIndex(C);
		((Permanent) tempPerm).setController(C.getOwner());
		this.BF.addCard(tempPerm);
		curZone.removeCard(curIndex);
		tempPerm.MoveTo(this.BF);
		Trigger tempTrigger = new triggerCiTP(C, null);
		this.notify(tempTrigger);
		return tempPerm;
	}

	public void passPriority(Player P) {
		if (this.STK.isEmpty()) {
			this.curTurn.advance();
			this.out.write("" + curTurn);
			if (this.curTurn.isDone()) {
				this.curTurn = new Turn(P);
			}
		} else {
			this.resolveOne();
		}
	}
	
	public void addTrigger(Trigger T)
	{
		knownTriggers.add(T);
	}
	
	public void notify(Trigger T)
	{
		Iterator<Trigger> itTriggers = knownTriggers.iterator();
		Trigger tempTrigger;
		while (itTriggers.hasNext())
		{
			tempTrigger = itTriggers.next();
			if (tempTrigger.equals(T))
			{
				tempTrigger.addToStack();
			}
		}
	}
	
	public void addAbilityToStack(EventPlaceholder E)
	{
		this.STK.addAbility(E);
	}

	public void play(String C, Player P) {
		Card temp = new Card(null, C, "", "");
		int x = P.HD.findCardIndex(temp);
		if (x != -1) {
			temp = P.HD.getCard(x);
			if (temp.isLand()) {
				if (P.canPlayLands > 0 && this.STK.isEmpty()
						&& this.curTurn.isMainPhase()) {
					temp = this.moveToBattlefield(temp);
					((Permanent) temp).setController(P);
					P.canPlayLands = -1;
					out.write("" + this.BF);
				} else {
					out.write("Error: That player can't play any more lands this turn. "
							+ P.canPlayLands);
				}
			} else {
				// It is a spell
				// check that the spell can be played (enough mana in mana pool,
				// legal target, etc)
				if (this.STK.isEmpty() || temp.hasFlash()) {
					if (temp.canPlay()) {
						P.MP.removeMana(temp.getManaCost());
						temp.MoveTo(STK);
						temp = temp.getSpell();
						this.STK.addSpell(temp);
						P.HD.removeCard(x);
						out.write("" + this.STK);
					} else {
						out.write("Error: Either insufficient mana or no legal targets.");
					}
				} else {
					out.write("Error: The stack is not empty and that card cannot be played as an instant.");
				}
			}
		} else {
			out.write("Error: No card of that name found in that player's hand.");
		}
	}

	public void printBattlefield() {
		out.write("" + this.BF);
	}

	public void printHand(Player P) {
		out.write("" + P.HD);
	}

	public void printStack() {
		out.write("" + this.STK);
	}

	public void readLine() throws IOException {
		this.in.readFromConsole();
	}

	public void newTurn() {
		this.curTurn = new Turn(this.getPlayer(0));
	}

	public void resolveOne() {
		this.STK.resolveOne();
		out.write("" + this.BF);
	}

	public int run() {
		if (this.curTurn == null)
			curTurn = new Turn(this.getPlayer(0));
		if (!(this.isDone)) {
			try {
				in.readFromConsole();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.run();
		}
		return 0;
	}

	public void tapForMana(String C, Player P) {
		Card temp = new Card(null, C, "", "");
		int x = this.BF.findUntappedPermanentIndex(temp);
		if (x != -1) {
			temp = this.BF.getCard(x);
			((Permanent) temp).tap();
			P.MP.addColorless();
			out.write("Colorless Floating: " + P.MP.getColorless());
		}
	}
}
