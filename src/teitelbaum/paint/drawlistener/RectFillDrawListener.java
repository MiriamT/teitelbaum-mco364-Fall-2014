package teitelbaum.paint.drawlistener;

import java.awt.Graphics2D;

import teitelbaum.paint.Canvas;

public class RectFillDrawListener extends ShapeDrawListener
{

	public RectFillDrawListener(Canvas canvas)
	{
		super(canvas);
	}

	@Override
	public void draw(Graphics2D g)
	{
		super.draw(g);
		g.fillRect(x, y, width, height);
	}
}
