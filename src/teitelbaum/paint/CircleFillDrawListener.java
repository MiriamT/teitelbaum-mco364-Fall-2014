package teitelbaum.paint;

import java.awt.Graphics2D;

public class CircleFillDrawListener extends ShapeDrawListener
{
	public CircleFillDrawListener(Canvas canvas)
	{
		super(canvas);
	}

	@Override
	public void draw(Graphics2D g)
	{
		super.draw(g);
		g.fillOval(x, y, width, height);
	}
}
