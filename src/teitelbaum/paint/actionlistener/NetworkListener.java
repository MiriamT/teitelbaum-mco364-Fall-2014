package teitelbaum.paint.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import teitelbaum.paint.Canvas;
import teitelbaum.paint.ClientReceiver;
import teitelbaum.paint.LoopbackNetworkModule;

public class NetworkListener implements ActionListener
{
	private ClientReceiver conn;
	private boolean connected;
	private ToolListener toolListener;
	private ClearListener clearListener;
	private Canvas canvas;

	public NetworkListener(Canvas canvas, ToolListener toolListener, ClearListener clearListener)
	{
		this.toolListener = toolListener;
		this.clearListener = clearListener;
		this.canvas = canvas;
		toolListener.setNetworkModule(new LoopbackNetworkModule(canvas));
		clearListener.setNetworkModule(new LoopbackNetworkModule(canvas));
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
		conn = new ClientReceiver(canvas, toolListener, clearListener, true);
		conn.start();
		// networkMod for listeners set inside ClientReciever
	}

	private void disconnect(JButton b)
	{
		connected = false;
		b.setText("CONNECT TO NETWORK");
		// need to end connection
		conn.setConnected(false);

		toolListener.setNetworkModule(new LoopbackNetworkModule(canvas));
		clearListener.setNetworkModule(new LoopbackNetworkModule(canvas));
	}

}
