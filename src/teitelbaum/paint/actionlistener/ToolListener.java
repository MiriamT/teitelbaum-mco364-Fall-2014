package teitelbaum.paint.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;

import javax.swing.JComboBox;

import teitelbaum.paint.Canvas;
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
	private ObjectOutputStream out;

	public ToolListener(Canvas canvas)
	{
		this.canvas = canvas;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JComboBox cb = (JComboBox) e.getSource();
		String tool = (String) cb.getSelectedItem();

		switch (tool)
		{
		case "Pencil":
			canvas.setDrawListener(new PencilDrawListener(canvas, out)); // draws with a "pencil"
			break;
		case "Rectangle":
			canvas.setDrawListener(new RectangleDrawListener(canvas, out)); // draws rectangles
			break;
		case "RectFill":
			canvas.setDrawListener(new RectFillDrawListener(canvas, out)); // draws filled rectangles
			break;
		case "Circle":
			canvas.setDrawListener(new CircleDrawListener(canvas, out)); // draws circles
			break;
		case "CircleFill":
			canvas.setDrawListener(new CircleFillDrawListener(canvas, out)); // draws filled circles
			break;
		case "Line":
			canvas.setDrawListener(new LineDrawListener(canvas, out)); // draws straight lines
			break;
		case "BucketFill":
			canvas.setDrawListener(new BucketFillDrawListener(canvas)); // uses bucket to fill color area
			break;
		}
	}

	public void setObjectOutputStream(ObjectOutputStream objectOutputStream) 
	{
		out = objectOutputStream;
	}
}
