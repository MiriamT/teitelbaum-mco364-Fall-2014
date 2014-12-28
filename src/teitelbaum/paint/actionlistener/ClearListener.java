package teitelbaum.paint.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import teitelbaum.paint.Canvas;
import teitelbaum.paint.NetworkModule;
import teitelbaum.paint.message.ClearMessage;

public class ClearListener implements ActionListener
{
	private Canvas canvas;
	private NetworkModule networkMod;

	public ClearListener(Canvas c)
	{
		canvas = c;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		networkMod.sendMessage(new ClearMessage(canvas));
	}

	public void setNetworkModule(NetworkModule netMod)
	{
		networkMod = netMod;
	}
}