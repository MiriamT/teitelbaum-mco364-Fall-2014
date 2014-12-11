package teitelbaum.paint.message;

import java.awt.Color;
import java.awt.Graphics2D;

public class ClearMessage implements PaintMessage
{

	@Override
	public String toString()
	{
		return "CLEAR\n";
	}

	@Override
	public void apply(Graphics2D g)
	{
		g.setPaint(Color.WHITE);
		// g.fillRect(0, 0, image.getWidth(), image.getHeight());
	}

}
