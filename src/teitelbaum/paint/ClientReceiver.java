package teitelbaum.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import teitelbaum.paint.actionlistener.ToolListener;
import teitelbaum.paint.message.PaintMessage;
import teitelbaum.paint.message.PaintMessageFactory;

public class ClientReceiver extends Thread
{

	private Socket socket;
	private Canvas canvas;
	private ToolListener toolListener;
	private PaintMessageFactory paintFactory;

	public ClientReceiver(Canvas canvas, ToolListener toolListener)
	{
		this.canvas = canvas;
		this.toolListener = toolListener;
		paintFactory = new PaintMessageFactory();
	}

	@Override
	public void run()
	{
		super.run();
		try
		{
			// establish the connection
			socket = new Socket("192.168.117.107", 3773);

			// can only have 1 instance of output stream, so sending it to directly to toolListener because its the only thing that needs it
			toolListener.setObjectOutputStream(new ObjectOutputStream(socket.getOutputStream()));

			ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); // should be the only in stream
			

			// now receives the PianoPackets
			PaintMessage message;
			while (true)
			{
				
				message = (PaintMessage) in.readObject();
				message.apply((Graphics2D)canvas.getImage().getGraphics());				
			}
		}
		catch (SocketException e)
		{
			// "restart" thread
			new ClientReceiver(canvas, toolListener).start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
