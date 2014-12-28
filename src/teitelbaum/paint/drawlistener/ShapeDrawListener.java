package teitelbaum.paint.drawlistener;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import teitelbaum.paint.Canvas;
import teitelbaum.paint.actionlistener.ToolListener;

public abstract class ShapeDrawListener implements DrawListener
{
	protected Canvas canvas;
	protected boolean preview;
	protected Point startPt, currentPt;
	protected int x, y, width, height;
	protected ToolListener toolListener;

	public ShapeDrawListener(Canvas canvas, ToolListener toolListener)
	{
		this.canvas = canvas;
		this.toolListener = toolListener;
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
		// get points and start drawing preview of rect
		preview = true;
		startPt = e.getPoint();
		currentPt = e.getPoint();
		canvas.repaint(); // draws the preview cuz preview called inside
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		currentPt = e.getPoint();
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
		x = Math.min(startPt.x, currentPt.x);
		y = Math.min(startPt.y, currentPt.y);
		width = Math.abs(startPt.x - currentPt.x);
		height = Math.abs(startPt.y - currentPt.y);
		// actual shape drawn determined by child class
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
