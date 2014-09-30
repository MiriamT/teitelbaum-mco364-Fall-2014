package teitelbaum.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.JTextArea;

public class ChatClientThreadv2 extends Thread
{
	private Socket socket;
	private ChatterBoxUIv2 chatUI;
	private JTextArea chatbox;

	public ChatClientThreadv2(ChatterBoxUIv2 UI)
	{
		chatUI = UI;
		chatbox = UI.getChatText();
	}

	@Override
	public void run()
	{
		super.run();
		try
		{
			// read
			socket = new Socket("localhost", 8080);
			// once connection is made, send it back to the UI
			chatUI.setSocketComponets(socket);

			InputStream in = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String incomingServerMessage;
			while (!"".equals((incomingServerMessage = reader.readLine()))) // readline is a blocking call so if nothing there will
																			// block and stop
			// execution until gets input, therefore check for empty string since its
			// the last line signature
			{
				if (incomingServerMessage != null)
				{
					chatbox.setText(chatbox.getText() + "\n" + incomingServerMessage);
				}
			}
			// not sure if i need this to allow time for client to send messages...
			sleep(1000);
		}
		catch (InterruptedException | IOException e)
		{
			if (e instanceof ConnectException)
			{
				// i want to see if i can use this to "restart" thread and still maintain connection with UI
				// **what happens to the failed threads?
				chatbox.setText(chatbox.getText() + "\nConnection timeout - restarting connection");
				new ChatClientThreadv2(chatUI).start();
			}
			else
			{
				e.printStackTrace();
			}
		}
	}
}
