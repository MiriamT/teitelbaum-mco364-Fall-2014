package teitelbaum.paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Paint extends JFrame
{
	private String[] lineTypes = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	private String[] colors = { "BLACK", "BLUE", "CYAN", "GRAY", "GREEN", "MAGENTA", "ORANGE", "PINK", "RED", "WHITE", "YELLOW" };
	private int lineType;
	private Color lineColor;

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

		JPanel toolbar = new JPanel();
		toolbar.setBackground(Color.LIGHT_GRAY);

		toolbar.add(new JLabel("Choose Brush Size:"));
		JComboBox typeCombo = new JComboBox(lineTypes);
		typeCombo.addActionListener(new TypeListener());
		typeCombo.setSelectedIndex(3);
		toolbar.add(typeCombo);

		toolbar.add(new JLabel("Choose Color:"));
		JComboBox colorCombo = new JComboBox(colors);
		colorCombo.addActionListener(new ColorListener());
		colorCombo.setSelectedIndex(0);
		toolbar.add(colorCombo);

		JButton clearButton = new JButton("CLEAR");
		clearButton.addActionListener(new ClearListener(canvas));
		toolbar.add(clearButton);

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

	private class TypeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JComboBox cb = (JComboBox) e.getSource();
			String s = (String) cb.getSelectedItem();
			lineType = Integer.valueOf(s);
		}
	}

	private class ColorListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JComboBox cb = (JComboBox) e.getSource();
			String s = (String) cb.getSelectedItem();

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

	public static void main(String[] args)
	{
		Paint p = new Paint();
		p.setVisible(true);
	}
}
