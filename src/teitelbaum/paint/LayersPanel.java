package teitelbaum.paint;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import teitelbaum.paint.actionlistener.LayerListener;

public class LayersPanel extends JPanel
{
	public LayersPanel(Canvas canvas)
	{
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		LayerButton[] layerButtons = new LayerButton[4];
		ImagePanel[] imagePanels = new ImagePanel[4];
		BufferedImage[] images = canvas.getImages();
		LayerListener layerListener = new LayerListener(canvas, layerButtons);
		for (int i = 0; i < 4; i++)
		{
			if (i == 0)
			{
				layerButtons[i] = new LayerButton("BACKGROUND", i);
				layerButtons[i].setForeground(Color.BLUE);
			}
			else
			{
				layerButtons[i] = new LayerButton("LAYER " + i, i);
			}
			layerButtons[i].addActionListener(layerListener);

			imagePanels[i] = new ImagePanel(images[i], layerButtons[i]);
			add(imagePanels[i]);
		}
	}

}
