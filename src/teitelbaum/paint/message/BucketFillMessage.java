package teitelbaum.paint.message;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Stack;

import teitelbaum.paint.Canvas;

public class BucketFillMessage implements PaintMessage
{
	private int x;
	private int y;
	private int color;
	private BufferedImage image;

	public BucketFillMessage(int x, int y, int color, Canvas canvas)
	{
		this.x = x;
		this.y = y;
		this.color = color;
		image = canvas.getImage();
	}

	public BucketFillMessage(int x, int y, int color)
	{
		this.x = x;
		this.y = y;
		this.color = color;
	}

	@Override
	public void apply(Graphics2D g)
	{
		floodFillScanLine(x, y, new Color(color), image);
	}

	private void floodFillScanLine(int x, int y, Color newColor, BufferedImage image)
	{
		Color oldColor = new Color(image.getRGB(x, y));

		if (oldColor.equals(newColor))
		{
			return;
		}

		Stack<Point> stack = new Stack<>();

		int y1;
		boolean spanLeft, spanRight;

		stack.push(new Point(x, y));

		while (!stack.isEmpty())
		{
			Point p = stack.pop();
			int currentX = (int) p.getX();
			int currentY = (int) p.getY();

			y1 = currentY;
			while (y1 >= 0 && oldColor.equals(new Color(image.getRGB(currentX, y1))))
			// seek up to highest point of same color
			{
				y1--;
			}
			y1++; // went one row too high which caused loop to break, so go back to the last "good" point

			spanLeft = spanRight = false;
			int width = image.getWidth();
			int height = image.getHeight();

			while (y1 < height && oldColor.equals(new Color(image.getRGB(currentX, y1))))
			// scan down to lowest point of same color, coloring each one in the process and adding points to the right of left to stak
			// to look into later
			{

				image.setRGB(currentX, y1, newColor.getRGB()); // change current pixel to new color

				if (!spanLeft && currentX > 0 && oldColor.equals(new Color(image.getRGB(currentX - 1, y1))))
				// if we can still scan left within the same color
				{
					stack.push(new Point(currentX - 1, y1));
					spanLeft = true;
				}
				else if (spanLeft && currentX > 0 && !oldColor.equals(new Color(image.getRGB(currentX - 1, y1))))
				// if spanLeft is true, but we havnt reached the left edge of image yet and current pixel is diff than oldColor,
				// then set spanLeft false, since oldColor might occur again farther left
				{
					spanLeft = false;
				}

				if (!spanRight && currentX < width - 1 && oldColor.equals(new Color(image.getRGB(currentX + 1, y1))))
				// if we can still scan right within the same color
				{
					stack.push(new Point(currentX + 1, y1));
					spanRight = true;
				}
				else if (spanRight && currentX < width - 1 && !oldColor.equals(new Color(image.getRGB(currentX + 1, y1))))
				// if spanRight is true, but we havnt reached the right edge of image yet and current pixel is diff than oldColor,
				// then set spanRight false, since oldColor might occur again farther right
				{
					spanRight = false;
				}
				y1++;
			}
		}
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
