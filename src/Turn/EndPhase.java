package Turn;

public class EndPhase extends Phase {

	public EndPhase(Turn T) {
		super(T);
		Step tempStep;
		tempStep = new EndStep(this);
		this.Steps.add(tempStep);
		tempStep = new Cleanup(this);
		this.Steps.add(tempStep);
		curStep = this.Steps.get(0);
		this.numSteps = 1;
		this.execStep();
		// TODO Auto-generated constructor stub
	}

}
