package teitelbaum.chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatterBoxUI extends JFrame
{
	private JTextArea chatText;
	private JTextField textbox;
	private JButton sendButton;
	private Socket socket;
	private OutputStream out;

	public ChatterBoxUI() throws IOException
	{
		setSize(800, 600);
		setTitle("ChatterBox");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// add chat text display
		chatText = new JTextArea();
		chatText.setText("Welcome to ChatterBox!");
		JScrollPane scroll = new JScrollPane(chatText);
		add(scroll, BorderLayout.CENTER);

		// add panel with textbox and send button
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
					// send to server if have connection
					if (socket != null)
					{
						try
						{
							out.write((text + "\n").getBytes()); // need the \n because readLine() blocks until it receives it
							out.flush(); // sends out the data
							//chatText.setText(chatText.getText() + "\nMe: " + text);
							textbox.setText("");
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
					}
					else
					{
						chatText.setText(chatText.getText() + "\nNo connection to server - your message could not be sent");
						textbox.setText("");
					}
				}
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(textbox);
		panel.add(sendButton, BorderLayout.EAST);
		add(panel, BorderLayout.SOUTH);

		// socket and related components remain null until a connection is made
		ChatClientThread conn = new ChatClientThread(this);
		conn.start();
	}

	public JTextArea getChatText()
	{
		return chatText;
	}

	public void setSocketComponets(Socket socket)
	{
		this.socket = socket;
		try
		{
			out = socket.getOutputStream();
			chatText.setText(chatText.getText() + "\nConnection made - ready to chat!");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
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