package Turn;

public class BeginingPhase extends Phase{

	public BeginingPhase(Turn T) {
		super(T);
		Step tempStep;
		tempStep = new Untap(this);
		this.Steps.add(tempStep);
		tempStep = new Upkeep(this);
		this.Steps.add(tempStep);
		tempStep = new Draw(this);
		this.Steps.add(tempStep);
		curStep = this.Steps.get(0);
		this.numSteps = 2;
		this.execStep();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
