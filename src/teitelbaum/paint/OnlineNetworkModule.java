package teitelbaum.paint;

import java.io.PrintWriter;

import teitelbaum.paint.message.PaintMessage;

public class OnlineNetworkModule implements NetworkModule
{
	private PrintWriter writer;

	public OnlineNetworkModule(PrintWriter writer)
	{
		this.writer = writer;
	}

	@Override
	public void sendMessage(PaintMessage message)
	{
		writer.print(message.toString());
		writer.flush();
	}
}
