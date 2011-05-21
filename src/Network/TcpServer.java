package Network;
import java.io.*;
import java.net.*;

// A TCP Server class that listens on a given TCP port
// (specified as the command-line argument to the program)
// That reads a string from the client, and then sends that 
// same data back to the client.
//
//	Usage:  java TcpServer <portNum>
//		e.g. java TcpServer 9000
//
public class TcpServer {
	int intPort;
	
	public TcpServer(int Port)
	{
		this.intPort = Port;
	}

	public void run()
	{
		
	}
	public static void main(String[] args) throws Exception {
		//see that user inputed the correct number of args, if they didn't halt execution 
		// and display proper usage
		if (args.length != 1) {
			System.out.println("Correct usage: TcpServer Port");
			System.exit(1);
		}
		ServerSocket listener = null;
		//try to create listener, if it fails halt execution and display error
		try {
			listener = new ServerSocket(Integer.parseInt(args[0]));
		} catch (Exception e)
		{
			System.out.print("! ERROR: Could not listen on port: ");
			System.out.println(args[0]);
			System.exit(1);
		}
		System.out.println("! Listening on port: " + listener.getLocalPort());

		while (true) {
			// listen for a client connection
			Socket connection = listener.accept();
			System.out.println("! Accepted connection from: " + 
					connection.getRemoteSocketAddress());

			// set up our readers/writers
			BufferedReader clientIn = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));
			DataOutputStream clientOut = new DataOutputStream(
					connection.getOutputStream());

			// read a line from the client
			String clientDataIn = clientIn.readLine();
			System.out.println("< Received: " + clientDataIn);
			try {
				int intDataIn = Integer.parseInt(clientDataIn);			
				// write that data back to the client - NOTE:
				// we append a newline to the end of the data to indicate
				// the end of our transmission to the client
				for (int x = intDataIn; x > 0; x--)
				{
					for (int y = 0; y < x; y++)
					{
						clientOut.writeBytes("#");
					}
					clientOut.writeBytes("\n");
				}
				System.out.println("Sent Data");
				// close the connection
			}
			//catch if the server recieved and invalid integer from the client
			// most commonly null
			catch (NumberFormatException e)
			{
				System.out.println("! ERROR: Recieved bad number");
			}
			connection.close();
			System.out.println("! Remote Client Disconnected: " + 
					connection.getInetAddress());				
		}
	}
}
