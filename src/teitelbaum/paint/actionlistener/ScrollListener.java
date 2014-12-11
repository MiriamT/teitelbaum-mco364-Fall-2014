package teitelbaum.paint.actionlistener;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JLabel;

import teitelbaum.paint.GraphicsAttributes;

public class ScrollListener implements MouseWheelListener
{
	private GraphicsAttributes settings;
	private JLabel brushSizeLabel;

	public ScrollListener(GraphicsAttributes settings, JLabel brushSizeLabel)
	{
		this.settings = settings;
		this.brushSizeLabel = brushSizeLabel;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		int notches = e.getWheelRotation();
		settings.setLineSize(settings.getLineSize() + notches);
		brushSizeLabel.setText(String.valueOf(settings.getLineSize()));
	}
}
