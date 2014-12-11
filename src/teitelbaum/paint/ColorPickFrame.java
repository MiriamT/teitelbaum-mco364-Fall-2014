package teitelbaum.paint;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import teitelbaum.paint.actionlistener.ColorListener;

public class ColorPickFrame extends JFrame
{
	Paint mainFrame;
	JColorChooser chooser;

	public ColorPickFrame(Paint frame)
	{
		mainFrame = frame;
		setTitle("Color Picker");

		chooser = new JColorChooser(Color.BLACK);
		chooser.setPreviewPanel(new JPanel()); // i dont like the preview panel so this gets rid of it
		chooser.getSelectionModel().addChangeListener(new ColorListener(chooser, mainFrame));
		add(chooser);

		pack();
		// position on right side of main frame
		setLocation(mainFrame.getLocation().x + mainFrame.getWidth(), mainFrame.getLocation().y);
		setVisible(true);
	}

}
