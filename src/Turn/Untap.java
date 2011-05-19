package Turn;

import Core.Game;

public class Untap extends Step{
	
	
	public Untap(Phase P) {
		super(P);
		// TODO Auto-generated constructor stub
	}

	public void beginStep()
	{
		this.Parent.getParent().getController().uptapControlledPermanents();
		this.Parent.getParent().getController().canPlayAnotherLand();
	}

	@Override
	public void endStep() {
		// TODO Auto-generated method stub
		
	}
}
