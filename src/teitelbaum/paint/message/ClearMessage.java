package teitelbaum.paint.message;

import java.awt.Color;
import java.awt.Graphics2D;

public class ClearMessage implements PaintMessage
{
	private int imageWidth, imageHeight;

	public ClearMessage(int imageWidth, int imageHeight)
	{
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
	}

	public int getImageWidth()
	{
		return imageWidth;
	}

	public void setImageWidth(int imageWidth)
	{
		this.imageWidth = imageWidth;
	}

	public int getImageHeight()
	{
		return imageHeight;
	}

	public void setImageHeight(int imageHeight)
	{
		this.imageHeight = imageHeight;
	}

	@Override
	public String toString()
	{
		return "CLEAR " + imageWidth + " " + imageHeight + "\n";
	}

	@Override
	public void apply(Graphics2D g)
	{
		g.setPaint(Color.WHITE);
		g.fillRect(0, 0, imageWidth, imageHeight);
	}

}
