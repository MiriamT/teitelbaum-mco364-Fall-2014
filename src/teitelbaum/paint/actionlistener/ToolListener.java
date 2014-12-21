package teitelbaum.paint.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.JComboBox;

import teitelbaum.paint.Canvas;
import teitelbaum.paint.Paint;
import teitelbaum.paint.drawlistener.BucketFillDrawListener;
import teitelbaum.paint.drawlistener.CircleDrawListener;
import teitelbaum.paint.drawlistener.CircleFillDrawListener;
import teitelbaum.paint.drawlistener.LineDrawListener;
import teitelbaum.paint.drawlistener.PencilDrawListener;
import teitelbaum.paint.drawlistener.RectFillDrawListener;
import teitelbaum.paint.drawlistener.RectangleDrawListener;

public class ToolListener implements ActionListener
{
	private Canvas canvas;
	private PrintWriter writer;
	private Paint paint;
	private boolean connected;

	public ToolListener(Canvas canvas, Paint paint)
	{
		this.canvas = canvas;
		this.paint = paint;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JComboBox cb = (JComboBox) e.getSource();
		String tool = (String) cb.getSelectedItem();

		switch (tool)
		{
		case "Pencil":
			canvas.setDrawListener(new PencilDrawListener(canvas, this)); // draws with a "pencil"
			break;
		case "Rectangle":
			canvas.setDrawListener(new RectangleDrawListener(canvas, this)); // draws rectangles
			break;
		case "RectFill":
			canvas.setDrawListener(new RectFillDrawListener(canvas, this)); // draws filled rectangles
			break;
		case "Circle":
			canvas.setDrawListener(new CircleDrawListener(canvas, this)); // draws circles
			break;
		case "CircleFill":
			canvas.setDrawListener(new CircleFillDrawListener(canvas, this)); // draws filled circles
			break;
		case "Line":
			canvas.setDrawListener(new LineDrawListener(canvas, this)); // draws straight lines
			break;
		case "BucketFill":
			canvas.setDrawListener(new BucketFillDrawListener(canvas, this)); // uses bucket to fill color area
			break;
		}
	}

	public void setPrintWriter(PrintWriter writer)
	{
		this.writer = writer;
	}

	public PrintWriter getPrintWriter()
	{
		return writer;
	}

	public void setConnected(boolean connected)
	{
		this.connected = connected;
	}

	public boolean isConnected()
	{
		return connected;
	}
}
