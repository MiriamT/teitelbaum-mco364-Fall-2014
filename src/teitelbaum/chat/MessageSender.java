package teitelbaum.chat;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class MessageSender extends Thread 
{
	
	private List<Socket> sockets;
	private BlockingQueue<String> messages;
	
	public MessageSender(BlockingQueue<String> messages, List<Socket> sockets)
	{
		this.messages = messages;
		this.sockets = sockets;
	}
	
	public void run()
	{
		while(true)
		{			
			try 
			{
				String message = messages.take();
				for( Socket socket : sockets)
				{
					try 
					{
						OutputStream out = socket.getOutputStream();
						PrintWriter writer = new PrintWriter(out);
						writer.println(message);
						writer.flush();
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
				
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			
		}
	}
}
