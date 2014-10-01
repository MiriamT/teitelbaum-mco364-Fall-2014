package teitelbaum.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class SocketHandler extends Thread
{
	private Socket socket;
	private BlockingQueue<String> messages;
	
	public SocketHandler(Socket s, BlockingQueue<String> messages)
	{
		socket = s;
		this.messages = messages;
	}
	
	public void run()
	{
		try {
			InputStream in = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			String line;
			
			while( (line = reader.readLine()) != null)
			{
				//write to all clients
				messages.add(line);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
