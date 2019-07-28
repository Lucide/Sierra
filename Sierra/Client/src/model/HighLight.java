package model;

import java.util.concurrent.TimeUnit;

public class HighLight extends Runner{

	public HighLight(FromThread fromThread){
		super(fromThread,false);
		
	}

	@Override
	public void run(){
		int r;
		
		for(r=255;r>-1;r-=17){
			fromThread.threadReceived(2,null,new int[]{r});
			try{
				TimeUnit.MILLISECONDS.sleep(70);
			}catch(InterruptedException e){
				System.out.println("HighLight: error waiting");
			}
		}
		
	}

}
