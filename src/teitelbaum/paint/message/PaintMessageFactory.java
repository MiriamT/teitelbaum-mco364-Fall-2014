package teitelbaum.paint.message;

import java.util.Scanner;

import teitelbaum.paint.Canvas;

public class PaintMessageFactory
{
	private Canvas canvas;

	public PaintMessageFactory(Canvas canvas)
	{
		this.canvas = canvas;
	}

	public PaintMessage getMessage(String string)
	{
		Scanner s = new Scanner(string);
		PaintMessage message = null;
		try
		{
			switch (PaintMessageType.valueOf(s.next()))
			{
			case LINE:
				message = new LineMessage(s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt());
				break;
			case SHAPE:
				message = new ShapeMessage(Shape.valueOf(s.next()), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), Boolean.valueOf(s.next()));
				break;
			case CLEAR:
				message = new ClearMessage(canvas);
				break;
			case BUCKET_FILL:
				message = new BucketFillMessage(s.nextInt(), s.nextInt(), s.nextInt(), canvas);
				break;
			}
		}
		catch(Exception e)
		{
			
		}

		return message;
	}
}
