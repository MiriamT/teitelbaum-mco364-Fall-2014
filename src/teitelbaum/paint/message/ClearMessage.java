package teitelbaum.paint.message;

import java.awt.Color;
import java.awt.Graphics2D;

import teitelbaum.paint.Canvas;

public class ClearMessage implements PaintMessage
{
	private int imageWidth, imageHeight;

	public ClearMessage(Canvas canvas)
	{
		imageWidth = canvas.getImage().getWidth();
		imageHeight = canvas.getImage().getHeight();
	}

	@Override
	public String toString()
	{
		return "CLEAR\n";
	}

	@Override
	public void apply(Graphics2D g)
	{
		g.setPaint(Color.WHITE);
		g.fillRect(0, 0, imageWidth, imageHeight);
	}

}
