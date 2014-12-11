package teitelbaum.paint.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import teitelbaum.paint.ColorPickFrame;
import teitelbaum.paint.Paint;

public class ChooseColorListener implements ActionListener
{
	private Paint frame;

	public ChooseColorListener(Paint frame)
	{
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		new ColorPickFrame(frame);
	}
}
