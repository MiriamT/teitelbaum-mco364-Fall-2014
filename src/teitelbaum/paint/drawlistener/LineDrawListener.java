package teitelbaum.paint.drawlistener;

import java.awt.Graphics2D;
import java.io.PrintWriter;

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
	public void sendMessageToServer()
	{
		String stringMessage = new LineMessage(startPt.x, startPt.y, currentPt.x, currentPt.y, super.canvas.getGraphicsAttributes().getLineColor().getRGB(), super.canvas.getGraphicsAttributes()
				.getLineSize()).toString();
		PrintWriter writer = super.toolListener.getPrintWriter();
		writer.print(stringMessage);
		writer.flush();
	}

}
