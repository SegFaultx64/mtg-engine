package Core;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Cards.Card;

public class CoreInput {
	BufferedReader brIn;
	Game curGame;
	Player curPlayer;
	int intPort;
	boolean useNetwork = false;
	ServerSocket listener = null;
	Socket connection;
	DataOutputStream clientOut;
	
	public CoreInput (Game G, Player P)
	{
		brIn = new BufferedReader(new InputStreamReader(System.in));
		this.curGame = G;
		this.curPlayer = P;
	}
	
	public void readFromConsole()
	{
		String curCommand = "";
		try {
			curCommand = brIn.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.evalCommand(curCommand);
	}
	
	public void readFromNetwork()
	{
		// set up our readers/writers
		BufferedReader clientIn = null;
		try {
			clientIn = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clientOut = new DataOutputStream(
					connection.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// read a line from the client
		String clientDataIn = null;
		try {
			clientDataIn = clientIn.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("< Received: " + clientDataIn);
		this.evalCommand(clientDataIn);
	}
	
	public void useNetwork(int port)
	{
		this.intPort = port;
		useNetwork = true;
		//try to create listener, if it fails halt execution and display error
		try {
			listener = new ServerSocket(intPort);
		} catch (Exception e)
		{
			System.out.print("! ERROR: Could not listen on port: ");
			System.out.println(intPort);
			System.exit(1);
		}
		System.out.println("! Listening on port: " + listener.getLocalPort());
		try {
			connection = listener.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("! Accepted connection from: " + 
				connection.getRemoteSocketAddress());
	}
	
	public void writeToNetwork(String S)
	{
		try {
			String S2 = S + "\n";
			byte[] temp = S2.getBytes("UTF-8");
			this.clientOut.write(temp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void readIn()
	{
		if (!(useNetwork))
		{
			readFromConsole();
		} else {
			readFromNetwork();
		}
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
		re1="GetCard\\([A-Z][a-z, A-Z]+,[0-9]+\\)";	// Any Single Character 2
		p = Pattern.compile(re1);
		m = p.matcher(curCommand);
		if (m.find())
		{
			String strTemp = (String) curCommand.subSequence(8, curCommand.length() - 1);
			String[] strsTemp = strTemp.split(",");
			System.out.println(strsTemp[0]);
			System.out.println(strsTemp[1]);
			System.out.println(strTemp);
			String temp = curGame.findCard(curGame.Players.get(0).HD,Integer.parseInt(strsTemp[1])).getCardforSend();
			curGame.write(temp);
		}
	}
}
