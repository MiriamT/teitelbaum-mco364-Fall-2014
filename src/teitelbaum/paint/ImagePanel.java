package teitelbaum.paint;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel
{

	public ImagePanel(BufferedImage image, LayerButton layerButton)
	{
		super();

		add(layerButton);

		// setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		ImageComponent ic = new ImageComponent(image);
		add(ic);

	}

}
