package teitelbaum.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class Canvas extends JComponent
{

	Point startDrag, endDrag;
	private BufferedImage image;
	private DrawListener listener;
	private Paint frame;

	public Canvas(Paint p)
	{
		image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB_PRE);
		clear();
		frame = p;
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.drawImage(image, 0, 0, null);

		listener.drawPreview((Graphics2D) g);
	}

	public void drawRectangle(int x, int y)
	{
		Graphics2D g = (Graphics2D) image.getGraphics();
		setBrush(g);

	}

	public void setBrush(Graphics2D g)
	{
		g.setColor(frame.getLineColor());
		g.setStroke(new BasicStroke(frame.getLineType(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
	}

	/*
	 * public void setStartDrag(Point p) { startDrag = p; }
	 * 
	 * public void setEndDrag(int x, int y) { endDrag = new Point(x, y); }
	 */
	public void clear()
	{
		Graphics2D graphics = image.createGraphics();
		graphics.setPaint(Color.WHITE);
		graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
		repaint();
	}

	public void setDrawListener(DrawListener dl)
	{
		if (listener != null)
		{
			this.removeMouseListener(listener);
			this.removeMouseMotionListener(listener);
		}
		listener = dl;
		this.addMouseListener(dl);
		this.addMouseMotionListener(dl);
	}

	public BufferedImage getImage()
	{
		return image;
	}

}
