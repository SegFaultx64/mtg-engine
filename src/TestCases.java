import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Core.Game;
import Core.Player;


public class TestCases {
	public static void main (String[] args) throws IOException
	{
		Game temp = Game.GetInstance();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Player tempPlayer = temp.getPlayer(0);
		tempPlayer.loadDeck();
		tempPlayer.Shuffle();
		tempPlayer.Draw(7);
		System.out.println(tempPlayer.printHand());
		temp.run();
	}
}
