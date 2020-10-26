package model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

public class MailMan{
	DatagramSocket ds;

	public MailMan(){
		System.out.println("MailMan: istantiated");
		
		try{
			ds=new DatagramSocket();
			ds.setBroadcast(true);
		}catch(SocketException ex){
			System.out.println("MailMan: error istantiating the socket");
		}
	}

	public void send(int v[]){
		byte t[]=Co.getByteArray(v);

		try{
			ds.send(new DatagramPacket(t,t.length,InetAddress.getByName("255.255.255.255"),1235));
			System.out.println("MailMan: sent \""+Arrays.toString(v)+"\"");
		}catch(IOException ex){
			System.out.println("MailMan: error sending \""+Arrays.toString(v)+"\"");
		}
	}
}