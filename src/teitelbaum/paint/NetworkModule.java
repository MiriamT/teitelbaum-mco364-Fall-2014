package teitelbaum.paint;

import teitelbaum.paint.message.PaintMessage;

public interface NetworkModule 
{
	public void sendMessage(PaintMessage message);
}
