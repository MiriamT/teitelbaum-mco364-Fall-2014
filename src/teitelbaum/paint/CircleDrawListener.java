package teitelbaum.paint;

import java.awt.Graphics2D;

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