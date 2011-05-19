package Turn;

import Core.Game;

public abstract class Step {
	Phase Parent;
	
	abstract public void beginStep();
	
	public void endStep()
	{
		Game curGame = Game.GetInstance();
		curGame.emptyAllManaPools();
		curGame.execStack();
	}
	
	public Step(Phase P)
	{
		this.Parent = P;
	}

	@Override
	public String toString() {
		return "Step=" + this.getClass().getName();
	}
}
