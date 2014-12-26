package teitelbaum.paint;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import teitelbaum.paint.drawlistener.DrawListener;

public class Canvas extends JComponent
{
	private BufferedImage[] images;
	private DrawListener listener;
	private GraphicsAttributes graphicsAttributes;
	private final int numLayers;
	private int currentLayer;
	private Paint gui;

	public Canvas(GraphicsAttributes graphicsAttributes, Paint paint)
	{
		currentLayer = 0;
		numLayers = 4;
		images = new BufferedImage[numLayers];
		for (int i = 0; i < numLayers; i++)
		{
			images[i] = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB_PRE);
			clear(i);
		}

		this.graphicsAttributes = graphicsAttributes;
		this.setPreferredSize(new Dimension(800, 600));
		gui = paint;
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		for (int i = 0; i < 4; i++)
		{
			g.drawImage(images[i], 0, 0, null);
			if (i == currentLayer)
			{
				graphicsAttributes.updateGraphicsSettings((Graphics2D) g);
				listener.drawPreview((Graphics2D) g);
			}
		}
		gui.repaint();
	}

	public void clear(int layer)
	{
		Graphics2D graphics = images[layer].createGraphics();
		if (layer == 0) // background layer should be white
		{
			graphics.setPaint(Color.WHITE);
			graphics.fillRect(0, 0, images[layer].getWidth(), images[layer].getHeight());
		}
		else
		// all others transparent, but need white color to make bucketfill work properly
		{
			// graphics.setPaint(new Color(255, 255, 255, 1));
			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, images[layer].getWidth(), images[layer].getHeight());
			// reset composite
			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

		}
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

	public BufferedImage getCurrentImage()
	{
		return images[currentLayer];
	}

	public BufferedImage[] getImages()
	{
		return images;
	}

	public GraphicsAttributes getGraphicsAttributes()
	{
		return graphicsAttributes;
	}

	public void setCurrentLayer(int newLayer)
	{
		currentLayer = newLayer;
	}

	public int getCurrentLayer()
	{
		return currentLayer;
	}

}
