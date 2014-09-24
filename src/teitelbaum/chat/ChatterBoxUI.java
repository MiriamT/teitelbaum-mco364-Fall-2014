package teitelbaum.chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatterBoxUI extends JFrame
{
	private JTextArea chatText;
	private JTextField textbox;
	private JButton sendButton;
	private Socket socket;

	public ChatterBoxUI() throws IOException
	{
		setSize(800, 600);
		setTitle("ChatterBox");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		System.out.println("b4 socket");
		socket = new Socket("10.41.116.67", 8080);
		final InputStream in = socket.getInputStream();
		final OutputStream out = socket.getOutputStream();
		System.out.println("after socket");

		chatText = new JTextArea();
		chatText.setText("Welcome to ChatterBox!");
		textbox = new JTextField(60);
		sendButton = new JButton("send");
		sendButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String text = textbox.getText();
				if (text != null && !"".equals(text))
				{
					// send to server
					try
					{
						out.write(text.getBytes());
						out.flush(); // sends out the data
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}

					chatText.setText(chatText.getText() + "\n" + text);
					textbox.setText("");
				}
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(textbox);
		panel.add(sendButton, BorderLayout.EAST);

		add(chatText, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
	}

	public static void main(String[] args)
	{
		
		try
		{
			ChatterBoxUI ui = new ChatterBoxUI();
			ui.setVisible(true);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
}
