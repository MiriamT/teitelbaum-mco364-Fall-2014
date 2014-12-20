package teitelbaum.paint.actionlistener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import teitelbaum.paint.Canvas;
import teitelbaum.paint.LayerButton;

public class LayerListener implements ActionListener
{
	private Canvas canvas;
	private LayerButton[] buttons;

	public LayerListener(Canvas canvas, LayerButton[] layerButtons)
	{
		this.canvas = canvas;
		buttons = layerButtons;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		for (LayerButton b : buttons)
		{
			b.setForeground(Color.BLACK);
		}
		LayerButton lb = (LayerButton) e.getSource();
		canvas.setCurrentLayer(lb.getLayerID());
		lb.setForeground(Color.BLUE);
	}
}
