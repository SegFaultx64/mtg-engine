package Turn;

public class MainPhase extends Phase {

	public MainPhase(Turn T) {
		super(T);
		Step tempStep;
		tempStep = new MainStep(this);
		this.Steps.add(tempStep);
		this.isMainPhase = true;
		curStep = this.Steps.get(0);
		this.execStep();
		// TODO Auto-generated constructor stub
	}

}
