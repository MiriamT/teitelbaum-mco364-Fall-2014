package teitelbaum.paint.message;

import java.awt.Graphics2D;
import java.io.Serializable;

public interface PaintMessage extends Serializable
{
	public void apply(Graphics2D g);
}
