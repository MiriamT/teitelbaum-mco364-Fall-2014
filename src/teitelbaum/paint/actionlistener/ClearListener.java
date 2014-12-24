package teitelbaum.paint.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;

import teitelbaum.paint.Canvas;
import teitelbaum.paint.message.ClearMessage;

public class ClearListener implements ActionListener
{
	private Canvas canvas;
	private PrintWriter writer;
	private boolean connected;

	public ClearListener(Canvas c)
	{
		canvas = c;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (connected)
		{
			sendMessageToServer();
		}
		else
		{
			canvas.clear();
		}

	}

	public void sendMessageToServer()
	{
		BufferedImage image = canvas.getImage();
		String stringMessage = new ClearMessage(canvas).toString();
		writer.print(stringMessage);
		writer.flush();
	}

	public void setPrintWriter(PrintWriter writer)
	{
		this.writer = writer;
	}

	public void setConnected(boolean connected)
	{
		this.connected = connected;

	}
}