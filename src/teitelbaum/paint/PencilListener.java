package teitelbaum.paint;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class PencilListener implements DrawListener
{
	private int x1, y1, x2, y2;
	private Canvas canvas;

	public PencilListener(Canvas canvas)
	{
		this.canvas = canvas;
	}

	@Override
	public void draw(Graphics2D g)
	{
		canvas.setBrush(g);
		g.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		x2 = e.getX();
		y2 = e.getY();
		draw((Graphics2D) canvas.getImage().getGraphics());
		canvas.repaint();
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
		draw((Graphics2D) canvas.getImage().getGraphics()); // lets u draw dots by clicking
		canvas.repaint();
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
