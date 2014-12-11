package teitelbaum.paint.drawlistener;

import java.awt.Graphics2D;

import teitelbaum.paint.Canvas;

public class CircleDrawListener extends ShapeDrawListener
{
	public CircleDrawListener(Canvas canvas)
	{
		super(canvas);
	}

	@Override
	public void draw(Graphics2D g)
	{
		super.draw(g);
		g.drawOval(x, y, width, height);
	}
}