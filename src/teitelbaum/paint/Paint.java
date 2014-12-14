package teitelbaum.paint;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import teitelbaum.paint.actionlistener.ChooseColorListener;
import teitelbaum.paint.actionlistener.ClearListener;
import teitelbaum.paint.actionlistener.ScrollListener;
import teitelbaum.paint.actionlistener.ToolListener;

public class Paint extends JFrame
{
	private GraphicsAttributes settings;
	private JLabel brushSizeLabel;
	private JLabel colorLabel;
	private String[] toolTypes = { "Pencil", "Rectangle", "RectFill", "Circle", "CircleFill", "Line", "BucketFill" };

	public Paint()
	{
		settings = new GraphicsAttributes();

		setSize(800, 600);
		setTitle("Paint");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Canvas canvas = new Canvas(settings);
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
		canvas.addMouseWheelListener(new ScrollListener(settings, brushSizeLabel)); // must add after brushSizeLabel is instantiated

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
		pack();
	}

	public void updateGraphicsColor(Color c)
	{
		settings.setLineColor(c);
		colorLabel.setBackground(c);
	}

	public static void main(String[] args)
	{
		Paint p = new Paint();
		p.setVisible(true);
	}
}
