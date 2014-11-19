package teitelbaum.paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Paint extends JFrame
{
	private int lineType;
	private Color lineColor;
	JLabel brushSizeLabel;
	JLabel colorLabel;

	public Paint()
	{
		lineType = 5;
		lineColor = Color.BLACK;

		setSize(800, 600);
		setTitle("Paint");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Canvas canvas = new Canvas(this);
		add(canvas, BorderLayout.CENTER);
		PencilListener listener = new PencilListener(canvas); //draws lines
		canvas.addMouseMotionListener(listener); 
		RectangleDrawListener rectDraw = new RectangleDrawListener(canvas); //draws rectangles
		canvas.addMouseMotionListener(rectDraw);
		canvas.addMouseListener(rectDraw);
		canvas.addMouseWheelListener(new ScrollListener());

		JPanel toolbar = new JPanel();
		toolbar.setBackground(Color.LIGHT_GRAY);

		toolbar.add(new JLabel("Brush Size: "));
		brushSizeLabel = new JLabel(String.valueOf(lineType));
		toolbar.add(brushSizeLabel);

		toolbar.add(new JLabel("Color: "));
		colorLabel = new JLabel("       ");
		colorLabel.setOpaque(true);
		colorLabel.setBackground(lineColor);
		toolbar.add(colorLabel);

		JButton colorButton = new JButton("CUSTOM COLOR");
		colorButton.addActionListener(new ChooseColorListener(this));
		toolbar.add(colorButton);

		JButton clearButton = new JButton("CLEAR");
		clearButton.addActionListener(new ClearListener(canvas));
		toolbar.add(clearButton);

		// implement this when have time - save the image to file
		// JButton saveButton = new JButton("SAVE");
		// saveButton.addActionListener(new SaveListener(canvas));
		// toolbar.add(saveButton);
		// try {
		// // retrieve image
		// BufferedImage bi = getMyImage();
		// File outputfile = new File("saved.png");
		// ImageIO.write(bi, "png", outputfile);
		// }

		add(toolbar, BorderLayout.NORTH);
	}

	public int getLineType()
	{
		return lineType;
	}

	public Color getLineColor()
	{
		return lineColor;
	}

	public void setBrushColor(Color c)
	{
		lineColor = c;
		colorLabel.setBackground(lineColor);
	}

	private class ClearListener implements ActionListener
	{
		private Canvas canvas;

		public ClearListener(Canvas c)
		{
			canvas = c;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			canvas.clear();
		}
	}

	private class ChooseColorListener implements ActionListener
	{
		private Paint frame;

		public ChooseColorListener(Paint frame)
		{
			this.frame = frame;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			new ColorPickFrame(frame);
		}
	}

	private class ScrollListener implements MouseWheelListener
	{
		@Override
		public void mouseWheelMoved(MouseWheelEvent e)
		{
			int notches = e.getWheelRotation();
			lineType += notches;
			if (lineType < 0)
			{
				lineType = 0;
			}
			brushSizeLabel.setText(String.valueOf(lineType));
		}
	}

	public static void main(String[] args)
	{
		Paint p = new Paint();
		p.setVisible(true);
	}
}
