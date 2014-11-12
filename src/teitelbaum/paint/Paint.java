package teitelbaum.paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Paint extends JFrame
{
	private String[] lineTypes = { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	private String[] colors = { "", "BLACK", "BLUE", "CYAN", "GRAY", "GREEN", "MAGENTA", "ORANGE", "PINK", "RED", "WHITE", "YELLOW" };
	private int lineType;
	private Color lineColor;
	//JComboBox typeCombo;
	//JComboBox colorCombo;
	JLabel brushSizeLabel;
	JLabel colorLabel;

	public Paint()
	{
		setSize(800, 600);
		setTitle("Paint");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Canvas canvas = new Canvas(this);
		add(canvas, BorderLayout.CENTER);

		DrawListener listener = new DrawListener(canvas);

		canvas.addMouseMotionListener(listener);
		canvas.addMouseWheelListener(new ScrollListener());

		JPanel toolbar = new JPanel();
		toolbar.setBackground(Color.LIGHT_GRAY);

		/*toolbar.add(new JLabel("Choose Brush Size:"));
		typeCombo = new JComboBox(lineTypes);
		typeCombo.addActionListener(new TypeListener());
		typeCombo.setSelectedIndex(3);
		toolbar.add(typeCombo);

		toolbar.add(new JLabel("Choose Color:"));
		colorCombo = new JComboBox(colors);
		colorCombo.addActionListener(new ColorListener());
		colorCombo.setSelectedIndex(1); //start black
		toolbar.add(colorCombo);*/
		
		toolbar.add(new JLabel("Brush Size: "));
		brushSizeLabel = new JLabel("");
		toolbar.add(brushSizeLabel);
		
		toolbar.add(new JLabel("Color: "));
		colorLabel = new JLabel("");
		toolbar.add(colorLabel);
		
		
		JButton colorButton = new JButton("CUSTOM COLOR");
		colorButton.addActionListener(new ChooseColorListener(this));
		toolbar.add(colorButton);
		
		JButton clearButton = new JButton("CLEAR");
		clearButton.addActionListener(new ClearListener(canvas));
		toolbar.add(clearButton);
		
		
		
		
		
		//implement this when have time - save the image to file
		//JButton saveButton = new JButton("SAVE");
		//saveButton.addActionListener(new SaveListener(canvas));
		//toolbar.add(saveButton);
		//try {
		//    // retrieve image
		//    BufferedImage bi = getMyImage();
		//    File outputfile = new File("saved.png");
		//    ImageIO.write(bi, "png", outputfile);
		//}
		

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
	
	public void setLineColor(Color c)
	{
		lineColor = c;
	}

	private class TypeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JComboBox cb = (JComboBox) e.getSource();
			String s = (String) cb.getSelectedItem();
			if (!"".equals(s))
			{
				lineType = Integer.valueOf(s);
			}			
		}
	}

	private class ColorListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JComboBox cb = (JComboBox) e.getSource();
			String s = (String) cb.getSelectedItem();
			
			if (!"".equals(s)) //"" denotes using color picker
			{
				try
				{
					final Field f = Color.class.getField(s);
					lineColor = (Color) f.get(null);
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
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
			//colorCombo.setSelectedIndex(0);
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
			//typeCombo.setSelectedIndex(0); //show blank so user knows changed with scroll
		}
	}

	public static void main(String[] args)
	{
		Paint p = new Paint();
		p.setVisible(true);
	}
}
