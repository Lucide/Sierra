package model;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class Highway extends Runner{
	Socket sk;

	public Highway(Socket sk,FromThread fromThread){
		super(fromThread,true);
		this.sk=sk;
	}

	@Override
	public void run(){
		int v[];
		boolean enable;
		
		do{
			enable=true;
			 try{
				v=Co.readArray(sk.getInputStream());
				System.out.println("Highway:  received \""+Arrays.toString(v)+"\"");
				fromThread.threadReceived(0,null,v);
			}catch(IOException ex){
				System.out.println("Highway: error reading or closing");
				enable=false;
			}
		}while(enable);
		try{
			sk.close();
		}catch(IOException e){
			System.out.println("Highway: can't close");
		}
	}

}
