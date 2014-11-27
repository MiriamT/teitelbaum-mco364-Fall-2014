package teitelbaum.paint;

import java.awt.Graphics2D;

public class LineDrawListener extends ShapeDrawListener
{
	public LineDrawListener(Canvas canvas)
	{
		super(canvas);
	}

	@Override
	public void draw(Graphics2D g)
	{
		// does not use super.draw() since works with 2 points, not width/height
		canvas.setBrush(g);
		g.drawLine(startPt.x, startPt.y, currentPt.x, currentPt.y);
	}

}
