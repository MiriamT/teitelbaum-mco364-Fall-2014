package teitelbaum.paint;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class RectangleDrawListener implements DrawListener
{
	private Canvas canvas;
	private boolean preview;
	private int x1, y1, x2, y2;

	public RectangleDrawListener(Canvas canvas)
	{
		this.canvas = canvas;
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
		// get points and start drawing preview rect
		preview = true;
		x1 = x2 = e.getX();
		y1 = y2 = e.getY();
		canvas.repaint(); // draws the preview cuz preview called inside
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		preview = false;
		draw((Graphics2D) canvas.getImage().getGraphics());
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		x2 = e.getX();
		y2 = e.getY();
		// draw preview by calling repaint
		canvas.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{

	}

	@Override
	public void draw(Graphics2D g)
	{
		canvas.setBrush(g);
		// see if can figure out how to let draw rect without restricting to dragging down and right
		g.drawRect(x1, y1, x2 - x1, y2 - y1);

	}

	@Override
	public void drawPreview(Graphics2D g)
	{
		if (preview)
		{
			draw(g);
		}

	}

}
