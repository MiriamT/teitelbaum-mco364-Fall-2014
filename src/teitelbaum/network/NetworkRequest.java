package teitelbaum.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkRequest
{
	public static void main(String[] args) throws IOException
	{
		Socket socket = new Socket("www.amazon.com", 80); //args = IP address and port
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();
		String request = "GET /index.html\n\n"; //file name part of url
		out.write(request.getBytes());
		out.flush(); //sends out the data

		//now we need to read back the html
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		//read in all the lines and display
		String line;
		while ((line = reader.readLine()) != null)
		{
			System.out.println(line);
		}
	}
}
