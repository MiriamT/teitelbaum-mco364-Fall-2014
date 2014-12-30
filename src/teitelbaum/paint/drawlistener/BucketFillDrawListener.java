package teitelbaum.paint.drawlistener;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Stack;

import teitelbaum.paint.Canvas;

public class BucketFillDrawListener implements DrawListener
{
	private int x, y;
	private Color oldColor;
	private Canvas canvas;

	public BucketFillDrawListener(Canvas canvas)
	{
		this.canvas = canvas;
	}

	@Override
	public void draw(Graphics2D g)
	{
		floodFillScanLine(x, y, oldColor, canvas.getGraphicsAttributes().getLineColor(), canvas.getCurrentImage());
		// floodFill(x, y, oldColor, canvas.getGraphicsAttributes().getLineColor(), canvas.getCurrentImage());
	}

	private void floodFill(int x, int y, Color oldColor, Color newColor, BufferedImage image)
	{
		if (oldColor.equals(newColor))
		{
			return;
		}

		Point p = new Point(x, y);

		Stack<Point> stack = new Stack<>();
		stack.push(p);

		// if will use map:
		// Map<Point, Boolean> visitedPixels = new HashMap<>();
		// visitedPixels.put(p, true);

		// as soon as visit a pixel, put in map
		// to see if has been visited yet, use containsKey() bc usually O(1) - same as get()

		while (!stack.isEmpty())
		{
			p = stack.pop();
			int currentX = (int) p.getX();
			int currentY = (int) p.getY();

			image.setRGB(currentX, currentY, newColor.getRGB()); // change current pixel to new color
			if (currentX + 1 < image.getWidth() && oldColor.equals(new Color(image.getRGB(currentX + 1, currentY))))
			{
				stack.add(new Point(currentX + 1, currentY));
			}
			if (currentX - 1 > 0 && oldColor.equals(new Color(image.getRGB(currentX - 1, currentY))))
			{
				stack.add(new Point(currentX - 1, currentY));
			}
			if (currentY + 1 < image.getHeight() && oldColor.equals(new Color(image.getRGB(currentX, currentY + 1))))
			{
				stack.add(new Point(currentX, currentY + 1));
			}
			if (currentY - 1 > 0 && oldColor.equals(new Color(image.getRGB(currentX, currentY - 1))))
			{
				stack.add(new Point(currentX, currentY - 1));
			}
		}
		canvas.repaint();
	}

	private void floodFillScanLine(int x, int y, Color oldColor, Color newColor, BufferedImage image)
	{
		if (oldColor.equals(newColor))
		{
			if (!oldColor.equals(Color.BLACK))
			{
				return;
			}
			else
			// black is a special case because transparent is also equal to black
			{
				boolean transparent = new Color(image.getRGB(x, y), true).getAlpha() == 0;
				if (transparent)
				{
					floodFillScanLineTransparentToBlack(x, y, image);
					return;
				}
				else
				// color is actually black, so return because same
				{
					return;
				}

				// correct by first filling in white to get rid of transparency, then fill black
				// floodFillScanLine(x, y, Color.BLACK, Color.WHITE, image);
				// oldColor = Color.WHITE;
			}
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
			// scan down to lowest point of same color
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
		canvas.repaint();
	}

	private void floodFillScanLineTransparentToBlack(int x, int y, BufferedImage image)
	{
		Color black = Color.BLACK;

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
			while (y1 >= 0 && new Color(image.getRGB(currentX, y1), true).getAlpha() == 0)
			// seek up to highest transparent point
			{
				y1--;
			}
			y1++; // went one row too high which caused loop to break, so go back to the last "good" point

			spanLeft = spanRight = false;
			int width = image.getWidth();
			int height = image.getHeight();

			while (y1 < height && new Color(image.getRGB(currentX, y1), true).getAlpha() == 0)
			// scan down to lowest transparent point
			{
				image.setRGB(currentX, y1, black.getRGB()); // change current pixel to black, nontransparent

				if (!spanLeft && currentX > 0 && new Color(image.getRGB(currentX - 1, y1), true).getAlpha() == 0)
				// if point to the left is transparent
				{
					stack.push(new Point(currentX - 1, y1));
					spanLeft = true;
				}
				else if (spanLeft && currentX > 0 && !(new Color(image.getRGB(currentX - 1, y1), true).getAlpha() == 0))
				// if spanLeft is true, but we havnt reached the left edge of image yet and current pixel is not transparent,
				// then set spanLeft false, since transparent might occur again farther left
				{
					spanLeft = false;
				}

				if (!spanRight && currentX < width - 1 && new Color(image.getRGB(currentX + 1, y1), true).getAlpha() == 0)
				// if transparent point exists to the left
				{
					stack.push(new Point(currentX + 1, y1));
					spanRight = true;
				}
				else if (spanRight && currentX < width - 1 && !(new Color(image.getRGB(currentX + 1, y1), true).getAlpha() == 0))
				// if spanRight is true, but we havnt reached the right edge of image yet and current pixel is not transparent,
				// then set spanRight false, since transparent might occur again farther right
				{
					spanRight = false;
				}
				y1++;
			}
		}
		canvas.repaint();

	}

	private void floodFillRecursion(int x, int y, Color oldColor, Color newColor, BufferedImage image) // stackoverflows - BAD
	{
		if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) // point is in bounds
		{
			Color currentColor = new Color(image.getRGB(x, y));
			if (!oldColor.equals(currentColor) || oldColor.equals(newColor)) // base case
			{
				return;
			}
			else
			{
				try
				// for debugging, delete try when solved
				{
					image.setRGB(x, y, newColor.getRGB());
					floodFillRecursion(x + 1, y, oldColor, newColor, image); // right
					floodFillRecursion(x - 1, y, oldColor, newColor, image); // left
					floodFillRecursion(x, y + 1, oldColor, newColor, image); // down
					floodFillRecursion(x, y - 1, oldColor, newColor, image); // up
					canvas.repaint();
				}
				catch (Exception e)
				{
					e.getMessage();
					return;
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		x = e.getX();
		y = e.getY();
		oldColor = new Color(canvas.getCurrentImage().getRGB(x, y));
		Graphics2D g = (Graphics2D) canvas.getCurrentImage().getGraphics();
		draw(g);

	}

	@Override
	public void mouseDragged(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void drawPreview(Graphics2D graphics)
	{

	}

}
