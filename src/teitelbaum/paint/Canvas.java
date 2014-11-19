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
	private int x, y; //convert this to a Point
	Point startDrag, endDrag;
	private BufferedImage image;
	private DrawListener listener;
	private Paint frame;

	public Canvas(Paint p)
	{
		image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB_PRE);
		clear();
		x = y = -1;
		frame = p;
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.drawImage(image, 0, 0, null);		
		listener.draw((Graphics2D)g);
	}

	public void drawPoint(int x, int y)
	{
		Graphics2D g = (Graphics2D) image.getGraphics();
		setBrush(g);

		if (this.x != -1 && this.y != -1) // dont draw the first click, because dont have a prev point
		{
			g.drawLine(this.x, this.y, x, y);			
		}

		this.x = x;
		this.y = y;
	}
	
	public void drawRectangle(int x, int y)
	{
		Graphics2D g = (Graphics2D) image.getGraphics();
		setBrush(g);
		
	}
	
	public void setBrush(Graphics2D g)
	{
		g.setColor(frame.getLineColor());
		g.setStroke(new BasicStroke(frame.getLineType(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER ));
	}

	public void setPoint(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setStartDrag(Point p)
	{
		startDrag = p;
	}
	
	public void setEndDrag(int x, int y)
	{
		endDrag = new Point(x, y);
	}
	
	

	public void clear()
	{
		Graphics2D graphics = image.createGraphics();
		graphics.setPaint ( Color.WHITE );
		graphics.fillRect ( 0, 0, image.getWidth(), image.getHeight() );
		repaint();
	}
}
