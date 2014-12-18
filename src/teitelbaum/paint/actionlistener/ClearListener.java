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

	public ClearListener(Canvas c)
	{
		canvas = c;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// canvas.clear();
		sendMessageToServer();
	}

	public void sendMessageToServer()
	{
		BufferedImage image = canvas.getImage();
		String stringMessage = new ClearMessage(image.getWidth(), image.getHeight()).toString();
		writer.println(stringMessage);
		writer.flush();
	}

	public void setPrintWriter(PrintWriter writer)
	{
		this.writer = writer;
	}
}