package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Gui extends JFrame{
	private static final long serialVersionUID=-8673923143215360664L;

	public JLabel lbBoot;
	public JLabel lbBackground;
	public JTextField tfAddress;
	public JTextField brightness[];
	
	private JPanel contentPane;
	private ImageIcon boot;

	/**
	 * Create the frame.
	 */
	public Gui(){
		Font din;
		try{
			din=Font.createFont(Font.TRUETYPE_FONT,Gui.class.getResourceAsStream("/resources/DIN 1451 Mittelschrift Regular.ttf")).deriveFont(46f);
		}catch(FontFormatException|IOException ex){
			din=null;
			System.out.println("Initializer: error loading font");
		}
		boot=new ImageIcon(Gui.class.getResource("/resources/boot.gif"));
		
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,850,570);
		contentPane=new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbBoot = new JLabel("");
		lbBoot.setIcon(boot);
		lbBoot.setBounds(22, 550, 800, 500);
		contentPane.add(lbBoot);
		
		tfAddress=new JTextField();
		tfAddress.setBorder(null);
		tfAddress.setOpaque(false);
		tfAddress.setHorizontalAlignment(SwingConstants.CENTER);
		tfAddress.setFont(din);
		tfAddress.setText("localhost");
		tfAddress.setBounds(222,168, 400, 55);
		contentPane.add(tfAddress);
		
		brightness=new JTextField[8];
		for(int i=0;i<8;i++){
			brightness[i]=new JTextField();
			
			brightness[i].setBorder(null);
			brightness[i].setOpaque(false);
			brightness[i].setHorizontalAlignment(SwingConstants.RIGHT);
			brightness[i].setFont(din);
			brightness[i].setText("255");
			brightness[i].setBounds(65+(108*(i%4+1))+70*(i%4),223+97*(i/4+1)+55*(i/4), 70, 55);
			contentPane.add(brightness[i]);
			//System.out.println(brightness[i].getX()+";"+brightness[i].getY());
		}
		
		lbBackground = new JLabel("");
		lbBackground.setIcon(new ImageIcon(Gui.class.getResource("/resources/background.png")));
		lbBackground.setBounds(0, 0, 844, 541);
		contentPane.add(lbBackground);
		
		//844:541
	}
	
	public void boot(boolean enable){
		if(enable){
			boot.getImage().flush();
			lbBackground.setVisible(false);
			lbBoot.setBounds(20,22,800,500);
			lbBoot.setVisible(true);
		}
		else{
			lbBoot.setVisible(false);
			lbBoot.setBounds(20,550,800,500);
			lbBackground.setVisible(true);
		}
	}
}
