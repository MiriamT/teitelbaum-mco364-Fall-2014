package teitelbaum.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JTextArea;

public class ChatClientThread extends Thread
{
	private Socket socket;
	private ChatterBoxUI chatUI;
	private JTextArea chatbox;

	public ChatClientThread(ChatterBoxUI UI)
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
			socket = new Socket("localhost", 9097);
			// once connection is made, send it back to the UI
			chatUI.setSocketComponets(socket);

			InputStream in = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String line;
			while (!"".equals((line = reader.readLine()))) // readline is a blocking call so if nothing there will block and stop
															// execution until gets input, therefore check for empty string since its
															// the last line signature
			{
				if (line != null)
				{
					chatbox.setText(chatbox.getText() + "\n" + line);
				}
			}
			// not sure if i need this to allow time for client to send messages...
			sleep(1000);
		}
		catch (InterruptedException | IOException e)
		{
			if (e instanceof ConnectException || e instanceof SocketException)
			{
				// i want to see if i can use this to "restart" thread and still maintain connection with UI
				// **what happens to the failed threads?
				chatbox.setText(chatbox.getText() + "\nConnection timeout - restarting connection");
				new ChatClientThread(chatUI).start();
			}
			else
			{
				e.printStackTrace();
			}
		}
	}
}