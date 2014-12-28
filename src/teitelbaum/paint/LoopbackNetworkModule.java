package teitelbaum.paint;

import java.awt.Graphics2D;

import teitelbaum.paint.message.PaintMessage;
import teitelbaum.paint.message.PaintMessageFactory;

public class LoopbackNetworkModule implements NetworkModule
{
	private PaintMessageFactory factory;
	private Canvas canvas;

	public LoopbackNetworkModule(Canvas canvas)
	{
		factory = new PaintMessageFactory(canvas);
		this.canvas = canvas;
	}

	@Override
	public void sendMessage(PaintMessage message)
	{
		PaintMessage msg = factory.getMessage(message.toString()); // need to redecode message because paintbucket and clear need
																	// canvas
		msg.apply((Graphics2D) canvas.getImage().getGraphics());
		canvas.repaint();
	}

}
