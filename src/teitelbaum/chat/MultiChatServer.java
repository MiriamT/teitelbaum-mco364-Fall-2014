package teitelbaum.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MultiChatServer 
{
	public static void main( String[] args ) throws IOException
	{
		ServerSocket serverSocket = new ServerSocket(3773);
		BlockingQueue<String> messages = new LinkedBlockingQueue<String>();
		List<Socket> sockets = new ArrayList<Socket>();
		MessageSender sender = new MessageSender(messages, sockets);
		sender.start();
		
		while (true)
		{
			Socket socket = serverSocket.accept();
			sockets.add(socket);
			SocketHandler handler = new SocketHandler(socket, messages);
			handler.start();
		}
		
		
		
	}
	
}
