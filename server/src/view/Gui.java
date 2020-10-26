package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Gui extends JFrame{
	private static final long serialVersionUID=-3200002445034528569L;

	public JLabel rooms[];
	public JLabel numbers[];
	
	private JPanel contentPane;
	private JLabel lbTitle;
	private JPanel panel;
	private JPanel panel1;
	private JPanel panel2;
	private JLayeredPane layeredPane;

	/**
	 * Create the frame.
	 */
	public Gui(){
		setResizable(false);
		setBackground(Color.WHITE);
		setAlwaysOnTop(true);
		Font din,univers;
		try{
			din=Font.createFont(Font.TRUETYPE_FONT,Gui.class.getResourceAsStream("/resources/DIN 1451 Mittelschrift Regular.ttf")).deriveFont(52f);
			univers=Font.createFont(Font.TRUETYPE_FONT,Gui.class.getResourceAsStream("/resources/Univers-LightUltraCondensed.otf")).deriveFont(52f);
		}catch(FontFormatException|IOException ex){
			din=null;
			univers=null;
			System.out.println("Initializer: error loading font");
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,740,420);
		contentPane=new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 10, 5, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane);
		
		panel2 = new JPanel();
		layeredPane.setLayer(panel2, 1);
		panel2.setBounds(0, 76, 714, 305);
		layeredPane.add(panel2);
		panel2.setBorder(null);
		panel2.setOpaque(false);
		panel2.setLayout(new GridLayout(2, 4, 10, 20));
		
		panel1 = new JPanel();
		panel1.setBounds(0, 0, 714, 381);
		layeredPane.add(panel1);
		panel1.setOpaque(false);
		panel1.setBackground(Color.WHITE);
		panel1.setLayout(new BorderLayout(0, 0));
		
		lbTitle = new JLabel("project Sierra");
		panel1.add(lbTitle, BorderLayout.NORTH);
		lbTitle.setBorder(new EmptyBorder(5, 0, 10, 0));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setFont(din);
		
		panel = new JPanel();
		panel.setBorder(null);
		panel1.add(panel, BorderLayout.CENTER);
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(2, 4, 10, 20));
		
		rooms=new JLabel[8];
		for(int i=0;i<8;i++){
			rooms[i]=new JLabel();
			rooms[i].setHorizontalAlignment(SwingConstants.CENTER);
			rooms[i].setBorder(new LineBorder(new Color(0, 0, 0)));
			rooms[i].setOpaque(true);
			rooms[i].setBackground(Color.BLACK);
			panel.add(rooms[i]);
		}
		
		numbers=new JLabel[8];
		for(int i=0;i<8;i++){
			numbers[i]=new JLabel("0"+(i+1));
			numbers[i].setHorizontalAlignment(SwingConstants.CENTER);
			numbers[i].setFont(univers);
			panel2.add(numbers[i]);
		}
	}
	
	public void colorize(int ind,int value){
		Color a=new Color (value,value,value);
		value=200-value;
		if(value<0)
			value=0;
		Color b=new Color (value,value,value);
		
		rooms[ind].setBackground(a);
		numbers[ind].setForeground(b);
	}
}
