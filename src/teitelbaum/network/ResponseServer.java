package teitelbaum.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ResponseServer
{
	public static void main(String[] args) throws IOException
	{
		ServerSocket serverSocket = new ServerSocket(8080); //provide port
		Socket socket = serverSocket.accept(); //to listen

		//read
		InputStream in = socket.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		while ((line = reader.readLine()) != null)
		{
			System.out.println(line);
		}

		//write 
		OutputStream out = socket.getOutputStream();
		out.write("hello world!".getBytes());
		out.flush(); //sends out the data
		out.close();
	}
}
