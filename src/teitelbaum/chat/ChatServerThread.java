package teitelbaum.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

public class ChatServerThread extends Thread
{
	private Socket socket;
	private JTextArea chatText;
	private String clientName;

	public ChatServerThread(Socket client, JTextArea chatText)
	{
		socket = client;
		this.chatText = chatText;
	}

	@Override
	public void run()
	{
		super.run();
		try
		{
			// read
			InputStream in = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			// first message will always be name of client
			clientName = reader.readLine();

			// announce client has connected to chat - move this to UI and have it send server a message that "client has connected"
			// since now UI handles all outgoing messages
			chatText.setText(chatText.getText() + "\n" + clientName + " has connected!");

			String message;
			while (!"".equals((message = reader.readLine()))) // readline is a blocking call so if nothing there will block and stop
																// execution until gets input, therefore check for empty string since
																// its
																// the last line signature
			{
				chatText.setText(chatText.getText() + "\n" + clientName + ": " + message);
			}
		}
		catch (IOException ioE)
		{
			ioE.printStackTrace();
		}
	}

}
