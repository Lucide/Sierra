package control;

import java.util.concurrent.TimeUnit;

import model.FromThread;
import model.MailBox;
import model.MailMan;
import model.Runner;
import view.Gui;

public class Controller extends Runner implements FromThread{
	Gui gui;
	MailMan mm;
	MailBox mb;
	
	int rooms[];

	public Controller(){
		super(null,false);
		gui=new Gui();
		mm=new MailMan();
		mb=new MailBox(this);
		
		rooms=new int[8];
		for(int i=0;i<8;i++){
			rooms[i]=127;
			gui.colorize(i,127);
		}

		gui.setVisible(true);
		
		launch();
	}

	@Override
	public void run(){
		while(true){
			mm.send(rooms);
			try{
				TimeUnit.SECONDS.sleep(3);
			}catch(InterruptedException ex){
				System.out.println("Controller: failed waiting");
			}
		}
		
	}
	
	@Override
	public void threadReceived(int id,String[] s,int[] v){
		switch(id){
		case 0:
			rooms[v[0]]=v[1];
			gui.colorize(v[0],v[1]);
			break;
		}
	}	
}
