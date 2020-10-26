package model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

public class MailBox extends Runner{
	private DatagramSocket ds;
	private DatagramPacket dp;

	public MailBox(FromThread fromThread){
		super(fromThread,false);
		threadName="MailBox";
		try{
			ds=new DatagramSocket(1235);
		}catch(SocketException ex){
			System.out.println("MailBox: failed creating socket");
		}
		dp=new DatagramPacket(new byte[256],256);
		launch();
	}

	@Override
	public void run(){
			int v[];
			while(true){
				try{
					ds.receive(dp);
				}catch(IOException ex){
					System.out.println("MailBox: failed receiving");
				}
				v=Co.getIntArray(dp.getData());
				System.out.println("MailBox:  received \""+Arrays.toString(v)+"\"");
				fromThread.threadReceived(1,null,v);
		}
	}
}
