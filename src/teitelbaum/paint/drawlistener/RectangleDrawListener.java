package teitelbaum.paint.drawlistener;

import java.awt.Graphics2D;

import teitelbaum.paint.Canvas;

public class RectangleDrawListener extends ShapeDrawListener
{

	public RectangleDrawListener(Canvas canvas)
	{
		super(canvas);
	}

	@Override
	public void draw(Graphics2D g)
	{
		super.draw(g);
		g.drawRect(x, y, width, height);
	}
}
