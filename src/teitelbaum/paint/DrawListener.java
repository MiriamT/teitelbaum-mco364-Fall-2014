package teitelbaum.paint;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class DrawListener implements MouseMotionListener 
{
	private Canvas canvas;
	//private int x1, x2, y1, y2;
	
	public DrawListener(Canvas canvas) 
	{
		this.canvas = canvas;
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		canvas.setPoint(e.getX(), e.getY());
		canvas.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		canvas.setPoint(-1, -1);
		
	}

}
