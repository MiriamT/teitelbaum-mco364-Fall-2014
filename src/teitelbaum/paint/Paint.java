package teitelbaum.paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Paint extends JFrame
{
	private GraphicsAttributes settings;
	private JLabel brushSizeLabel;
	private JLabel colorLabel;
	private String[] toolTypes = { "Pencil", "Rectangle", "RectFill", "Circle", "CircleFill", "Line" };

	public Paint()
	{
		settings = new GraphicsAttributes();

		setSize(800, 600);
		setTitle("Paint");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Canvas canvas = new Canvas(settings);
		canvas.addMouseWheelListener(new ScrollListener());
		add(canvas, BorderLayout.CENTER);

		JPanel toolbar = new JPanel();
		toolbar.setBackground(Color.LIGHT_GRAY);

		toolbar.add(new JLabel("Choose Draw Type:"));
		JComboBox toolCombo = new JComboBox(toolTypes);
		toolCombo.addActionListener(new ToolListener(canvas));
		toolCombo.setSelectedIndex(0);
		toolbar.add(toolCombo);

		toolbar.add(new JLabel("Brush Size: "));
		brushSizeLabel = new JLabel(String.valueOf(settings.getLineSize()));
		toolbar.add(brushSizeLabel);

		toolbar.add(new JLabel("Color: "));
		colorLabel = new JLabel("       ");
		colorLabel.setOpaque(true);
		colorLabel.setBackground(settings.getLineColor());
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

	public void updateGraphicsColor(Color c)
	{
		settings.setLineColor(c);
		colorLabel.setBackground(c);
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
			settings.setLineSize(settings.getLineSize() + notches);
			brushSizeLabel.setText(String.valueOf(settings.getLineSize()));
		}
	}

	private class ToolListener implements ActionListener
	{
		private Canvas canvas;

		public ToolListener(Canvas canvas)
		{
			this.canvas = canvas;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			JComboBox cb = (JComboBox) e.getSource();
			String tool = (String) cb.getSelectedItem();

			switch (tool)
			{
			case "Pencil":
				canvas.setDrawListener(new PencilDrawListener(canvas)); // draws with a "pencil"
				break;
			case "Rectangle":
				canvas.setDrawListener(new RectangleDrawListener(canvas)); // draws rectangles
				break;
			case "RectFill":
				canvas.setDrawListener(new RectFillDrawListener(canvas)); // draws filled rectangles
				break;
			case "Circle":
				canvas.setDrawListener(new CircleDrawListener(canvas)); // draws circles
				break;
			case "CircleFill":
				canvas.setDrawListener(new CircleFillDrawListener(canvas)); // draws filled circles
				break;
			case "Line":
				canvas.setDrawListener(new LineDrawListener(canvas)); // draws straight lines
				break;
			}
		}
	}

	public static void main(String[] args)
	{
		Paint p = new Paint();
		p.setVisible(true);
	}
}
