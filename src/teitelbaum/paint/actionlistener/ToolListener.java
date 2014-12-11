package teitelbaum.paint.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import teitelbaum.paint.Canvas;
import teitelbaum.paint.drawlistener.CircleDrawListener;
import teitelbaum.paint.drawlistener.CircleFillDrawListener;
import teitelbaum.paint.drawlistener.LineDrawListener;
import teitelbaum.paint.drawlistener.PencilDrawListener;
import teitelbaum.paint.drawlistener.RectFillDrawListener;
import teitelbaum.paint.drawlistener.RectangleDrawListener;

public class ToolListener implements ActionListener
{
	private Canvas canvas;

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
			canvas.setDrawListener(new PencilDrawListener(canvas)); // draws with a "pencil"
			break;
		case "Rectangle":
			canvas.setDrawListener(new RectangleDrawListener(canvas)); // draws rectangles
			break;
		case "RectFill":
			canvas.setDrawListener(new RectFillDrawListener(canvas)); // draws filled rectangles
			break;
		case "Circle":
			canvas.setDrawListener(new CircleDrawListener(canvas)); // draws circles
			break;
		case "CircleFill":
			canvas.setDrawListener(new CircleFillDrawListener(canvas)); // draws filled circles
			break;
		case "Line":
			canvas.setDrawListener(new LineDrawListener(canvas)); // draws straight lines
			break;
		}
	}
}
