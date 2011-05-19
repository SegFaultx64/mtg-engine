package Core;

import java.io.*;

public class CoreOutput {
	BufferedWriter bwOut;
	Game curGame;
	
	public CoreOutput(Game G) throws UnsupportedEncodingException
	{
		this.curGame = G;
		bwOut = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
	}
	
	public void write(String S)
	{
		try {
			bwOut.write(S + "\n");
			bwOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
