package teitelbaum.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ResponseServer
{
	public static void main(String[] args) throws IOException
	{
		ServerSocket serverSocket = new ServerSocket(8080); //provide port

		int counter = 0;
		while (true) //want to continuously accept requests from many clients
		{
			Socket socket = serverSocket.accept(); //to listen

			//hmm why no link to git?
			
			//create a thread to handle request
			ConnectionThread conn = new ConnectionThread(socket, counter);
			conn.start();
			
			counter++;
		}
	}
}
