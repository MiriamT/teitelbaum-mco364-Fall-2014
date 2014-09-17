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

		int counter = 0;
		while (true) //want to continuously accept requests from many clients
		{
			Socket socket = serverSocket.accept(); //to listen

			//read
			InputStream in = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String line;
			while (!"".equals((line = reader.readLine()))) //readline is a blocking call so if nothing there will block and stop execution until gets input, therefore check for empty string since its the last line signature
			{
				System.out.println(line);
			}

			//write 
			OutputStream out = socket.getOutputStream();
			String response = "<h2>This is request " + counter + " </h2>";
			out.write("HTTP/1.1 200 OK\n".getBytes());
			out.write("Content-Type: text/html; charset=utf-8\n".getBytes());
			out.write(("Content-Length: " + response.length() + "\n\n").getBytes());
			out.write(response.getBytes());
			out.flush(); //sends out the data
			out.close();

			counter++;
		}

	}
}
