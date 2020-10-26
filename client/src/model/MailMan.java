package model;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class MailMan extends Runner{
	Socket sk;
	InetAddress ip;
	boolean enable;

	public MailMan(FromThread fromThread){
		super(fromThread,false);
		enable=false;
	}
	
	public void connect(String address){
		enable=false;
		try{
			ip=InetAddress.getByName(address);
		}catch(UnknownHostException ex){
			System.out.println("MailMan: error setting ip");
		}
		launch();
	}
	
	public void send(int v[]){
		if(enable){
			try{
				Co.writeArray(v,sk.getOutputStream());
				System.out.println("MailMan: sent \""+Arrays.toString(v)+"\"");
			}catch(IOException ex){
				System.out.println("MailMan: error sending \""+Arrays.toString(v)+"\"");
			}
		}
		else
			System.out.println("MailMan: offline dispatch \""+Arrays.toString(v)+"\"");
	}

	@Override
	public void run(){
		long start=System.nanoTime();
		try{
			sk=new Socket(ip, 1234);
			enable=true;
			System.out.println("MailMan: connected to "+sk.getInetAddress().getHostAddress());
		}catch(IOException ex){
			System.out.println("MailMan: error istantiating the socket");
		}
		try{
			TimeUnit.MILLISECONDS.sleep(5500-TimeUnit.NANOSECONDS.toMillis(System.nanoTime()-start));
		}catch(InterruptedException e){
			System.out.println("MailMan: failed waiting");
		}
		fromThread.threadReceived(0,null,null);
	}
}
