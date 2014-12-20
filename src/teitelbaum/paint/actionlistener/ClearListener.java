package teitelbaum.paint.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import teitelbaum.paint.Canvas;

public class ClearListener implements ActionListener
{
	private Canvas canvas;

	public ClearListener(Canvas c)
	{
		canvas = c;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		canvas.clear(canvas.getCurrentLayer());
	}
}