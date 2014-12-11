package teitelbaum.paint.message;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class LineMessage implements PaintMessage
{
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int color;
	private int stroke;

	public LineMessage(int x1, int y1, int x2, int y2, int color, int stroke)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.stroke = stroke;
	}

	@Override
	public void apply(Graphics2D g)
	{
		g.setColor(new Color(color));
		g.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
		g.drawLine(x1, y1, x2, y2);
	}

	public int getX1()
	{
		return x1;
	}

	public void setX1(int x1)
	{
		this.x1 = x1;
	}

	public int getY1()
	{
		return y1;
	}

	public void setY1(int y1)
	{
		this.y1 = y1;
	}

	public int getX2()
	{
		return x2;
	}

	public void setX2(int x2)
	{
		this.x2 = x2;
	}

	public int getY2()
	{
		return y2;
	}

	public void setY2(int y2)
	{
		this.y2 = y2;
	}

	public int getColor()
	{
		return color;
	}

	public void setColor(int color)
	{
		this.color = color;
	}

	public int getStroke()
	{
		return stroke;
	}

	public void setStroke(int stroke)
	{
		this.stroke = stroke;
	}

	@Override
	public String toString()
	{
		return "LINE " + x1 + " " + y1 + " " + x2 + " " + y2 + " " + color + " " + stroke + "\n";
	}
}
