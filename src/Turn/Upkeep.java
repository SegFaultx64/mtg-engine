package Turn;

import Core.Game;

public class Upkeep extends Step{

	public Upkeep(Phase P) {
		super(P);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beginStep() {
		// TODO Auto-generated method stub
		
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
