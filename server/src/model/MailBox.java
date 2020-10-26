package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MailBox extends Runner{
	 ServerSocket ssk;

	public MailBox(FromThread fromThread){
		super(fromThread,false);
		threadName="MailBox";
		try{
			ssk = new ServerSocket(1234);
		}catch(IOException ex){
			System.out.println("MailBox: failed creating socket");
			ex.printStackTrace();
		}
		launch();
	}

	private Object quicker(Socket sk){
		return new Highway(sk,fromThread);
}
	
	@Override
	public void run(){
			Socket sk;
			while(true){
		         try{
		        	sk=ssk.accept();
					quicker(sk);
					System.out.println("MailBox: cheers from "+sk.getInetAddress().getHostAddress());
				}catch(IOException ex){
					System.out.println("MailBox: failed receiving");
				}
		}
	}
}
