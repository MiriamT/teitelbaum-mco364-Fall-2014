package teitelbaum.paint;

import java.awt.Graphics2D;

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
