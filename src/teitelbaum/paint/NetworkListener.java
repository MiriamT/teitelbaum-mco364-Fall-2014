package teitelbaum.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import teitelbaum.paint.actionlistener.ClearListener;
import teitelbaum.paint.actionlistener.ToolListener;

public class NetworkListener implements ActionListener
{
	private ClientReceiver conn;
	private boolean connected;
	private ToolListener toolListener;
	private ClearListener clearListener;

	public NetworkListener(Canvas canvas, ToolListener toolListener, ClearListener clearListener)
	{
		this.toolListener = toolListener;
		this.clearListener = clearListener;
		// connect to server
		conn = new ClientReceiver(canvas, toolListener, clearListener);
		conn.start();

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton) e.getSource();
		if (connected)
		{
			disconnect(b);
		}
		else
		{
			connect(b);
		}

	}

	private void connect(JButton b)
	{
		connected = true;
		b.setText("DISCONNECT");
		conn.setConnected(connected);
		toolListener.setConnected(connected);
		clearListener.setConnected(connected);
	}

	private void disconnect(JButton b)
	{
		connected = false;
		b.setText("CONNECT TO NETWORK");
		conn.setConnected(connected);
		toolListener.setConnected(connected);
		clearListener.setConnected(connected);
	}

}
