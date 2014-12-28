package teitelbaum.paint.drawlistener;

import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface DrawListener extends MouseListener, MouseMotionListener
{
	void draw(Graphics2D g);

	void drawPreview(Graphics2D graphics);
}
