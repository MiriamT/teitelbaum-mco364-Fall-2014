package teitelbaum.paint;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class RectangleDrawListener implements MouseListener, MouseMotionListener
{
	private Canvas canvas;

	public RectangleDrawListener(Canvas canvas)
	{
		this.canvas = canvas;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		canvas.drawPoint(e.getX(), e.getY());
		canvas.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{

	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		canvas.drawRectangle(e.getX(), e.getY());
		canvas.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		canvas.setPoint(-1, -1);
	}

}
