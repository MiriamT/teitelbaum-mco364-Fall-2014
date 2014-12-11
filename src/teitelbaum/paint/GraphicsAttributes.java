package teitelbaum.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class GraphicsAttributes
{
	private int lineSize;
	private Color lineColor;
	private int endCap;
	private int lineJoin;
	private BasicStroke stroke;

	public GraphicsAttributes()
	{
		lineSize = 5;
		lineColor = Color.BLACK;
		endCap = BasicStroke.CAP_ROUND;
		lineJoin = BasicStroke.JOIN_MITER;
		stroke = new BasicStroke(lineSize, endCap, lineJoin);
	}

	public void updateGraphicsSettings(Graphics2D g)
	{
		g.setColor(lineColor);
		g.setStroke(stroke);
	}

	public int getLineSize()
	{
		return lineSize;
	}

	public void setLineSize(int lineSize)
	{
		this.lineSize = lineSize > 0 ? lineSize : 0;
		stroke = new BasicStroke(this.lineSize, endCap, lineJoin); // create a new stroke every time line size changes since cant set
																	// size of BasicStroke once created
	}

	public Color getLineColor()
	{
		return lineColor;
	}

	public void setLineColor(Color lineColor)
	{
		this.lineColor = lineColor;
	}

}
