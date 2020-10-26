package control;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import model.FromThread;
import model.HighLight;
import model.MailBox;
import model.MailMan;
import view.Gui;

public class Controller implements FocusListener,FromThread{
	Gui gui;
	MailMan mm;
	MailBox mb;
	HighLight hl;
	
	String smartass;

	public Controller(){
		gui=new Gui();
		mm=new MailMan(this);
		mb=new MailBox(this);
		hl=new HighLight(this);
		
		gui.tfAddress.addFocusListener(this);
		for(int i=0;i<8;i++){
			gui.brightness[i].addFocusListener(this);
		}
		
		gui.setVisible(true);
		boot();
	}
	
	private void boot(){
		gui.boot(true);
		mm.connect(gui.tfAddress.getText());
	}
	
	@Override
	public void focusGained(FocusEvent e){
		smartass=((JTextField)e.getSource()).getText();
		((JTextField)e.getSource()).setForeground(Color.BLACK);
	}
	
	@Override
	public void focusLost(FocusEvent e){
		JTextField src=(JTextField)e.getSource();
		String s=src.getText();
		boolean f=false;
		
		
		if(!s.equals(smartass)){
			if(src==gui.tfAddress){
				if(s.matches("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")){
					boot();
					f=true;
				}
			}
			else{
				if(s.matches("\\b(1?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\b")){
					int n,v=0;
					v=Integer.parseInt(s);
					for(n=0;n<8&&gui.brightness[n]!=src;n++);
					mm.send(new int[]{n,v});
					f=true;
				}
			}
			if(!f){
				System.out.println("Validator: dead");
				src.setText(smartass);
			}
		}
	}

	@Override
	public void threadReceived(int id,String[] s,int[] v){
		switch(id){
		case 0:
			gui.boot(false);
			break;
		case 1:
			hl.launch();
			for(int i=0;i<8;i++){
				if(!gui.brightness[i].isFocusOwner())
					gui.brightness[i].setText(v[i]+"");
			}
			break;
		case 2:
			for(int i=0;i<8;i++){
				if(!gui.brightness[i].isFocusOwner())
					gui.brightness[i].setForeground(new Color(v[0],0,0));
			}
			break;
		}
	}
}
