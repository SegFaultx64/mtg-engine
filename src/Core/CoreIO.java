package Core;

import java.io.UnsupportedEncodingException;

public class CoreIO {
	CoreInput in;
	CoreOutput out;
	boolean useNetwork = true;
	
	public CoreIO(Player P)
	{
		this.in = new CoreInput(Game.GetInstance(), P);
		try {
			this.out = new CoreOutput(Game.GetInstance());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeOut(String S)
	{
		if (!(useNetwork))
		{
			out.write(S);
		} else {
			in.writeToNetwork(S);
		}
	}
	
	public void readIn()
	{
		in.readIn();
	}
}
