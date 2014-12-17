package teitelbaum.paint.drawlistener;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.ObjectOutputStream;

import teitelbaum.paint.Canvas;
import teitelbaum.paint.message.LineMessage;

public class LineDrawListener extends ShapeDrawListener
{
	public LineDrawListener(Canvas canvas, ObjectOutputStream out)
	{
		super(canvas, out);
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
		try {
			super.out.writeObject(new LineMessage(startPt.x, startPt.y, currentPt.x, currentPt.y, super.canvas.getGraphicsAttributes().getLineColor().getRGB(), super.canvas.getGraphicsAttributes().getLineSize()));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
