package teitelbaum.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class Canvas extends JComponent 
{
	private int x, y;
	private BufferedImage image;
	
	
	public Canvas()
	{
		image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB_PRE);
		x = y = -1;
	}

	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		g.drawImage(image, 0,  0, null);
		
		
	}

	public void setPoint(int x, int y) 
	{
		
		Graphics g = image.getGraphics();
		g.setColor(Color.CYAN); //move to constructor?
		//g.fillOval(x, y, 10, 10);
		
		if (this.x != -1 && this.y != -1 )
		{
			//not the first click, so need to draw a line between prev and curr point
			g.drawLine(this.x, this.y, x, y);
		}
		
		this.x = x;
		this.y = y;
	}
	
	
	
}
