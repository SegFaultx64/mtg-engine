package Turn;

import Core.Game;

public class Draw extends Step{

	public Draw(Phase P) {
		super(P);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beginStep() {
		// TODO Auto-generated method stub
		this.Parent.getParent().getController().Draw(1);
	}

	@Override
	public void endStep() {
		// TODO Auto-generated method stub
		Game curGame = Game.GetInstance();
		while (curGame.execStack())
		{
			//this space left intentionally blank
		}
	}

}
