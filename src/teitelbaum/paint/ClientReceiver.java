package teitelbaum.paint;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import teitelbaum.paint.actionlistener.ClearListener;
import teitelbaum.paint.actionlistener.ToolListener;
import teitelbaum.paint.message.PaintMessage;
import teitelbaum.paint.message.PaintMessageFactory;

public class ClientReceiver extends Thread
{

	private Socket socket;
	private Canvas canvas;
	private ToolListener toolListener;
	private PaintMessageFactory paintFactory;
	private ClearListener clearListener;
	private boolean connected;

	public ClientReceiver(Canvas canvas, ToolListener toolListener, ClearListener clearListener)
	{
		this.canvas = canvas;
		this.toolListener = toolListener;
		this.clearListener = clearListener;
		paintFactory = new PaintMessageFactory(canvas);
	}

	public void setConnected(boolean isConnected)
	{
		connected = isConnected;
	}

	@Override
	public void run()
	{
		super.run();
		try
		{
			// establish the connection
			// socket = new Socket("192.168.117.107", 3773);
			socket = new Socket("localhost", 3773);

			// should only have 1 instance of printwriter, so sending it to directly to toolListener and clearListener

			PrintWriter writer = new PrintWriter(socket.getOutputStream());

			toolListener.setPrintWriter(writer);
			clearListener.setPrintWriter(writer);

			InputStream in = socket.getInputStream(); // should be the only in stream
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String messageString;
			while ((messageString = reader.readLine()) != null)
			{
				if (!messageString.equals("") && connected) // need to check for "" because we append \n to toString and flush also
															// sends \n
				{
					PaintMessage message = paintFactory.getMessage(messageString);
					message.apply((Graphics2D) canvas.getImage().getGraphics());
					canvas.repaint();
				}
			}
		}
		catch (SocketException e)
		{
			// "restart" thread
			new ClientReceiver(canvas, toolListener, clearListener).start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}