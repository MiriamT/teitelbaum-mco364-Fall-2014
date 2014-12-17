package teitelbaum.paint.drawlistener;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.ObjectOutputStream;

import teitelbaum.paint.Canvas;
import teitelbaum.paint.message.Shape;
import teitelbaum.paint.message.ShapeMessage;

public class CircleFillDrawListener extends ShapeDrawListener
{
	public CircleFillDrawListener(Canvas canvas, ObjectOutputStream out)
	{
		super(canvas, out);
	}

	@Override
	public void draw(Graphics2D g)
	{
		super.draw(g);
		g.fillOval(x, y, width, height);
	}
	
	@Override
	public void sendMessageToServer() 
	{
		try {
			super.out.writeObject(new ShapeMessage(Shape.OVAL, super.x, super.y, super.width, super.height, super.canvas.getGraphicsAttributes().getLineColor().getRGB(), super.canvas.getGraphicsAttributes().getLineSize(), true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
