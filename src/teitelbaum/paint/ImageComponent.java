package teitelbaum.paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

public class ImageComponent extends JComponent
{

	private BufferedImage image;

	public ImageComponent(BufferedImage image)
	{
		super();
		this.image = image;
		setPreferredSize(new Dimension(200, 150));
		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Image scaledImage = image.getScaledInstance(200, 150, BufferedImage.SCALE_FAST);
		g.drawImage(scaledImage, 0, 0, null);

	}

}
