package teitelbaum.paint.drawlistener;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;

import teitelbaum.paint.Canvas;
import teitelbaum.paint.actionlistener.ToolListener;
import teitelbaum.paint.message.LineMessage;

public class PencilDrawListener implements DrawListener
{
	private int x1, y1, x2, y2;
	private Canvas canvas;
	private ToolListener toolListener;

	public PencilDrawListener(Canvas canvas, ToolListener toolListener)
	{
		this.canvas = canvas;
		this.toolListener = toolListener;
	}

	@Override
	public void draw(Graphics2D g)
	{
		canvas.getGraphicsAttributes().updateGraphicsSettings(g);
		g.drawLine(x1, y1, x2, y2);
		canvas.repaint();
	}

	@Override
	public void sendMessageToServer()
	{
		String stringMessage = new LineMessage(x1, y1, x2, y2, canvas.getGraphicsAttributes().getLineColor().getRGB(), canvas.getGraphicsAttributes().getLineSize()).toString();
		PrintWriter writer = toolListener.getPrintWriter();
		writer.print(stringMessage);
		writer.flush();
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		x2 = e.getX();
		y2 = e.getY();

		if (toolListener.isConnected())
		{
			sendMessageToServer();
		}
		else
		{
			draw((Graphics2D) canvas.getImage().getGraphics());
		}

		x1 = x2;
		y1 = y2;
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{

	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		x1 = x2 = e.getX();
		y1 = y2 = e.getY();
		// lets u draw dots by clicking
		if (toolListener.isConnected())
		{
			sendMessageToServer();
		}
		else
		{
			draw((Graphics2D) canvas.getImage().getGraphics());
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void drawPreview(Graphics2D graphics)
	{
		// TODO Auto-generated method stub

	}

}
