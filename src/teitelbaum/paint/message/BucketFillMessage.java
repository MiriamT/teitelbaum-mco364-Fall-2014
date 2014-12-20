package teitelbaum.paint.message;

import java.awt.Graphics2D;

public class BucketFillMessage implements PaintMessage
{
	private int x;
	private int y;
	private int color;

	public BucketFillMessage(int x, int y, int color)
	{
		this.x = x;
		this.y = y;
		this.color = color;
	}

	@Override
	public void apply(Graphics2D g)
	{
		// TODO Auto-generated method stub

	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getColor()
	{
		return color;
	}

	public void setColor(int color)
	{
		this.color = color;
	}

	@Override
	public String toString()
	{
		return "BUCKET_FILL " + " " + x + " " + y + " " + color + "\n";
	}
}
