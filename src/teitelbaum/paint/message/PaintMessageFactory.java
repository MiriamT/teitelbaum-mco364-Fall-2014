package teitelbaum.paint.message;

import java.util.Scanner;

public class PaintMessageFactory 
{
	public PaintMessage getMessage(String string)
	{
		Scanner s = new Scanner(string);
		PaintMessage message = null;
		switch ( PaintMessageType.valueOf( s.next() ) )
		{
		case LINE:
			message = new LineMessage(s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt());
			break;
		case SHAPE:
			message = new ShapeMessage(Shape.valueOf(s.next()), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), Boolean.valueOf(s.next()));
			break;
		case CLEAR:
			message = new ClearMessage();
			break;
		case BUCKET_FILL:
			message = new BucketFillMessage(s.nextInt(), s.nextInt(), s.nextInt());
			break;
		}
		return message;
	}
}
