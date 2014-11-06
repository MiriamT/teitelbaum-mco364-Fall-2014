package teitelbaum.paint;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class DrawListener implements MouseMotionListener
{
	private Canvas canvas;

	public DrawListener(Canvas canvas)
	{
		this.canvas = canvas;
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		canvas.drawPoint(e.getX(), e.getY());
		canvas.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		canvas.setPoint(-1, -1);
	}

}
