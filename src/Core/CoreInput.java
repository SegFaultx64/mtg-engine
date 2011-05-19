package Core;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoreInput {
	BufferedReader brIn;
	Game curGame;
	Player curPlayer;
	
	public CoreInput (Game G, Player P)
	{
		brIn = new BufferedReader(new InputStreamReader(System.in));
		this.curGame = G;
		this.curPlayer = P;
	}
	
	public void readFromConsole() throws IOException
	{
		String curCommand;
		curCommand = brIn.readLine();
		this.evalCommand(curCommand);
	}
	
	private void evalCommand(String S)
	{
		String curCommand = S;
		String re1="Play\\([^\\)]+\\)";	// Any Single Character 2
		Pattern p = Pattern.compile(re1);
		Matcher m = p.matcher(curCommand);
		if (m.find())
		{
			String cur = m.toMatchResult().group();
			cur = cur.substring(5, cur.length() - 1);
			curGame.play(cur, curPlayer);
		}
		re1="Tap\\([^\\)]+\\)";	// Any Single Character 2
		p = Pattern.compile(re1);
		m = p.matcher(curCommand);
		if (m.find())
		{
			String cur = m.toMatchResult().group();
			cur = cur.substring(4, cur.length() - 1);
			curGame.tapForMana(cur, curPlayer);
		}
		re1="PassPriority\\(\\)";	// Any Single Character 2
		p = Pattern.compile(re1);
		m = p.matcher(curCommand);
		if (m.find())
		{
			curGame.passPriority(curPlayer);
		}
		re1="PrintStack\\(\\)";	// Any Single Character 2
		p = Pattern.compile(re1);
		m = p.matcher(curCommand);
		if (m.find())
		{
			curGame.printStack();
		}
		re1="PrintBattlefield\\(\\)";	// Any Single Character 2
		p = Pattern.compile(re1);
		m = p.matcher(curCommand);
		if (m.find())
		{
			curGame.printBattlefield();
		}
		re1="PrintHand\\(\\)";	// Any Single Character 2
		p = Pattern.compile(re1);
		m = p.matcher(curCommand);
		if (m.find())
		{
			curGame.printHand(curPlayer);
		}
		re1="ExitGame\\(\\)";	// Any Single Character 2
		p = Pattern.compile(re1);
		m = p.matcher(curCommand);
		if (m.find())
		{
			curGame.end();
		}
	}
}
