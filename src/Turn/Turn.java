package Turn;

import java.util.ArrayList;

import Core.*;

public class Turn {
	boolean isDone = false;
	Phase curPhase;
	int curIndex, numPhase = 0;
	Player playerController;
	ArrayList<Phase> Phases = new ArrayList<Phase>();
	
	public Player getController()
	{
		return this.playerController;
	}
	
	public Turn(Player Controller)
	{
		this.playerController = Controller;
		Phase tempPhase;
		tempPhase = new BeginingPhase(this);
		this.Phases.add(tempPhase);
		tempPhase = new MainPhase(this);
		this.Phases.add(tempPhase);
		curPhase = this.Phases.get(0);
		this.numPhase = 1;
	}
	
	public void advance()
	{
		if (!(this.curPhase.isDone()))
		{
			this.curPhase.advanceStep();
		} else {
			if (curIndex < numPhase)
			{
				this.curIndex += 1;
				this.curPhase = this.Phases.get(curIndex);
			} else {
				this.isDone = true;
			}
		}
	}
	
	@Override
	public String toString() {
		return "Turn [curPhase=" + curPhase + "]";
	}

	public boolean isDone()
	{
		return this.isDone;
	}
	
	public boolean isMainPhase()
	{
		return this.curPhase.isMainPhase();
	}
}
