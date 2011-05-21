package Network;
import java.io.*;
import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;

// A simple TCP client that connects to a remote server
// on the specified hostname (first command-line parameter)
// and port (second command-line parameter).  Usage:
//
//	java TcpClient <hostname> <port>
//		e.g.:  java TcpClient localhost 9000
public class TcpClient {

	public static void main(String[] args) throws Exception {
		//see that user inputed the correct number of args, if they didn't halt execution 
		// and display proper usage
		if (args.length != 2) {
			System.out.println("Correct usage: TcpClient Remote Hostname Port");
			System.exit(1);
		}
		// connect to the remote host (args[0]) on the specified port (args[1])
		Scanner in = new Scanner(System.in);
		Socket clientSocket = null;
		//Try to connect to server. If you can't display and error message and halt execution
		try {
		clientSocket = new Socket(args[0], Integer.parseInt(args[1]));
		} catch (ConnectException e)
		{
			System.out.println("! ERROR: Can't connect to server.");
			System.exit(1);
		}
		//Inform user of successful connection
		System.out.println("! Connected to : " +
				clientSocket.getInetAddress() + " on port: " +
				clientSocket.getLocalPort());

		//Get integer to send to server
		System.out.print("Enter an integer: ");
		try {
			int serverDataOut = in.nextInt();
			// set up the readers/writers
			DataOutputStream serverOut = 
				new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader serverIn = 
				new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));

			// write the string to the remote host.  NOTE:
			// we add a newline character to the end of the string to indicate
			// to the remote host that the transmission is complete
			serverOut.writeBytes(serverDataOut + "\n");
			System.out.println("> Sent: " + serverDataOut);

			// now we read the data returned from the remote host
			// we have to loop to read the correct amount of times
			for (int x = 1; x <= serverDataOut; x++)
			{
				String serverDataIn = serverIn.readLine();
				System.out.println("< Received: " + serverDataIn);
			}
			// and... we're done - close the socket
		} catch (InputMismatchException e) {
			System.out.println("! ERROR: Bad Integer");
		}
		clientSocket.close();
		System.out.println("! Disconnected from remote host: " +
				clientSocket.getInetAddress());
	}
}
