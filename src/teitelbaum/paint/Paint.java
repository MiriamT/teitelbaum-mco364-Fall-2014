package teitelbaum.paint;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Paint extends JFrame
{
	public Paint()
	{
		setSize(800, 600);
		setTitle("Paint");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Canvas canvas = new Canvas();
		add(canvas, BorderLayout.CENTER);
		
		DrawListener listener = new DrawListener(canvas);
		
		canvas.addMouseMotionListener(listener);
	}
	
	public static void main(String[] args)
	{
		Paint p = new Paint();
		p.setVisible(true);
	}
}
