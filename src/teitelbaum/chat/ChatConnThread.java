package teitelbaum.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JTextArea;

public class ChatConnThread extends Thread
{
	private Socket socket;
	private JTextArea chatbox;

	public ChatConnThread(Socket s, JTextArea textarea)
	{
		socket = s;
		chatbox = textarea;
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

			String line;
			while (!"".equals((line = reader.readLine()))) // readline is a blocking call so if nothing there will block and stop
															// execution until gets input, therefore check for empty string since its
															// the last line signature
			{
				if (line != null)
				{
					chatbox.setText(chatbox.getText() + "\nOther: " + line);
				}
			}
			
			sleep(1000);
		}
		catch (IOException | InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}