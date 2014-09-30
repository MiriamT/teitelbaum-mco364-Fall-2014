package teitelbaum.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerChatLD extends JFrame {

	private JTextField field;
	private JTextArea area;
	private JPanel panel;
	private JButton send;
	private Socket socket;

	public ServerChatLD() {
		this.panel = new JPanel();
		this.panel.setLayout(new BorderLayout());
		field = new JTextField();

		area = new JTextArea();
		area.setEditable(false);
		area.setBackground(Color.getHSBColor(255, 255, 12));

		send = new JButton("Send");
		send.setBackground(Color.getHSBColor(255, 255, 204));
		send.setForeground(Color.getHSBColor(255, 10, 255));
		send.addActionListener(new ButtonListener());

		this.add(new JScrollPane(area), BorderLayout.CENTER);
		this.panel.add(field, BorderLayout.CENTER);
		this.panel.add(send, BorderLayout.EAST);
		this.add(panel, BorderLayout.SOUTH);

		this.setTitle("Chat Room");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

		try {
			area.setText("Server started \n");
			ServerSocket server = new ServerSocket(8000);
			socket = server.accept();
			area.setText("Client connected\n");
			InputStream in = socket.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;

			while (!"".equals((line = reader.readLine()))) {
				String client = "Client: ";
				area.setText(area.getText() + client + line);
			}
		} catch (IOException e) {
			System.err.print(e);
		}

	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			try {
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				out.write((field.getText() + "\n").getBytes());
				out.flush();
				area.setText(area.getText() + "\nMe:   " + field.getText().toString());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {
		new ServerChatLD();
	}
}
