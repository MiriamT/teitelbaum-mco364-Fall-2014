package teitelbaum.chat;

import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

public class ChatServer
{

	public static void main(String[] args) throws IOException
	{
		JTextArea chatText = new JTextArea(); // the single chat area that all clients share
		chatText.setBackground(Color.CYAN);

		ServerSocket server = new ServerSocket(8080);

		while (true)
		{
			Socket client = server.accept();
			ChatServerThread conn = new ChatServerThread(client, chatText);
			conn.start();
		}

	}
}
