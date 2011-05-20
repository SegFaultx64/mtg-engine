package Turn;

import java.util.ArrayList;

public class Phase {
	
	@Override
	public String toString() {
		return this.getClass().getName() + this.curStep;
	}

	boolean isMainPhase = false;
	Step curStep;
	int curIndex, numSteps = 0;
	Turn Parent;
	public boolean isDone = false;
	ArrayList<Step> Steps = new ArrayList<Step>();
	
	public Turn getParent()
	{
		return this.Parent;
	}
	
	protected void init()
	{
		this.curStep = Steps.get(0);
	}
	
	public void execStep()
	{
		this.curStep.beginStep();
	}
	
	public void advanceStep()
	{
		if (this.curStep != null)
			this.curStep.endStep();
		if (curIndex < numSteps)
		{
			this.curIndex += 1;
			this.curStep = this.Steps.get(curIndex);
			this.execStep();
		} else {
			this.isDone = true;
			this.Parent.advance();
		}
	}
	
	public Phase(Turn T)
	{
		this.Parent = T;
	}
	
	public boolean isMainPhase()
	{
		return this.isMainPhase;
	}
	
	public boolean isDone()
	{
		return this.isDone;
	}
}
