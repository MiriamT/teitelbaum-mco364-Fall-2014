package teitelbaum.paint;

import java.awt.Dimension;

import javax.swing.JButton;

public class LayerButton extends JButton
{
	private int layerID;

	public LayerButton(String text, int layerID)
	{
		super(text);
		this.layerID = layerID;
		this.setPreferredSize(new Dimension(115, 26));
	}

	public int getLayerID()
	{
		return layerID;
	}

}
