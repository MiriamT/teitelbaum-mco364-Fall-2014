package teitelbaum.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import teitelbaum.paint.drawlistener.DrawListener;

public class Canvas extends JComponent
{
	private BufferedImage image;
	private DrawListener listener;
	private GraphicsAttributes graphicsAttributes;

	public Canvas(GraphicsAttributes graphicsAttributes)
	{
		image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB_PRE);
		clear();
		this.graphicsAttributes = graphicsAttributes;
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.drawImage(image, 0, 0, null);

		graphicsAttributes.updateGraphicsSettings((Graphics2D) g);
		listener.drawPreview((Graphics2D) g);
	}

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

	public GraphicsAttributes getGraphicsAttributes()
	{
		return graphicsAttributes;
	}

}
