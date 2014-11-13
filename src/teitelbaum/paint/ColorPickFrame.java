package teitelbaum.paint;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorPickFrame extends JFrame
{
	Paint mainFrame;
	JColorChooser chooser;

	public ColorPickFrame(Paint frame)
	{
		mainFrame = frame;
		setTitle("Color Picker");

		chooser = new JColorChooser(Color.BLACK);
		chooser.getSelectionModel().addChangeListener(new ColorListener());
		add(chooser);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private class ColorListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent e)
		{
			Color newColor = chooser.getColor();
			mainFrame.setBrushColor(newColor);
		}
	}
}
