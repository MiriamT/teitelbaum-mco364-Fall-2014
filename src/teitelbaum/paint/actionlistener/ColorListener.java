package teitelbaum.paint.actionlistener;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import teitelbaum.paint.Paint;

public class ColorListener implements ChangeListener
{
	private JColorChooser chooser;
	private Paint mainFrame;

	public ColorListener(JColorChooser chooser, Paint mainFrame)
	{
		this.chooser = chooser;
		this.mainFrame = mainFrame;
	}

	@Override
	public void stateChanged(ChangeEvent e)
	{
		Color newColor = chooser.getColor();
		mainFrame.updateGraphicsColor(newColor);
	}
}
