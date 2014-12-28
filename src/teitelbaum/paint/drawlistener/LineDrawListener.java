package teitelbaum.paint.drawlistener;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import teitelbaum.paint.Canvas;
import teitelbaum.paint.actionlistener.ToolListener;
import teitelbaum.paint.message.LineMessage;

public class LineDrawListener extends ShapeDrawListener
{
	public LineDrawListener(Canvas canvas, ToolListener toolListener)
	{
		super(canvas, toolListener);
	}

	@Override
	public void draw(Graphics2D g)
	{
		// does not use super.draw() since drawLine() works with 2 points, not width/height
		g.drawLine(startPt.x, startPt.y, currentPt.x, currentPt.y);
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		preview = false;
		LineMessage msg = new LineMessage(startPt.x, startPt.y, currentPt.x, currentPt.y, super.canvas.getGraphicsAttributes().getLineColor().getRGB(), super.canvas.getGraphicsAttributes()
				.getLineSize());
		toolListener.getNetworkModule().sendMessage(msg);
	}

}
