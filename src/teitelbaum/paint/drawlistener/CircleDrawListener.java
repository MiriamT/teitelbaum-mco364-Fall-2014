package teitelbaum.paint.drawlistener;

import java.awt.Graphics2D;
import java.io.PrintWriter;

import teitelbaum.paint.Canvas;
import teitelbaum.paint.actionlistener.ToolListener;
import teitelbaum.paint.message.Shape;
import teitelbaum.paint.message.ShapeMessage;

public class CircleDrawListener extends ShapeDrawListener
{
	public CircleDrawListener(Canvas canvas, ToolListener toolListener)
	{
		super(canvas, toolListener);
	}

	@Override
	public void draw(Graphics2D g)
	{
		super.draw(g);
		g.drawOval(x, y, width, height);
	}

	@Override
	public void sendMessageToServer()
	{
		String stringMessage = new ShapeMessage(Shape.OVAL, super.x, super.y, super.width, super.height, super.canvas.getGraphicsAttributes().getLineColor().getRGB(), super.canvas
				.getGraphicsAttributes().getLineSize(), false).toString();
		PrintWriter writer = super.toolListener.getPrintWriter();
		writer.print(stringMessage);
		writer.flush();
	}

}