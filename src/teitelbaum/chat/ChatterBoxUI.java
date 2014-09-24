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
	private InputStream in;
	private OutputStream out;

	public ChatterBoxUI() throws IOException
	{
		setSize(800, 600);
		setTitle("ChatterBox");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		System.out.println("b4 socket");
		socket = new Socket("192.168.117.129", 8080);
		
		
		
		in = socket.getInputStream();
		out = socket.getOutputStream();
		System.out.println("after socket");

		chatText = new JTextArea();
		chatText.setText("Welcome to ChatterBox!");
		
		ChatConnThread conn = new ChatConnThread(socket, chatText);
		conn.start();
		
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
						out.write((text + "\n").getBytes());
						out.flush(); // sends out the data
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}

					chatText.setText(chatText.getText() + "\nMe: " + text);
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
