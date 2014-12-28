package teitelbaum.paint.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import teitelbaum.paint.Canvas;
import teitelbaum.paint.NetworkModule;
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
	private NetworkModule networkMod;

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

	public NetworkModule getNetworkModule()
	{
		return networkMod;
	}

	public void setNetworkModule(NetworkModule netMod)
	{
		networkMod = netMod;
	}
}
