package model;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.NetworkInterface;

public class Co{
	public static NetworkInterface face;

	public Co(){

	}
	
	public static int[] readArray(InputStream is) throws IOException{
		DataInputStream dis=new DataInputStream(is);
		int v[]=null;
		try{
			v=new int[dis.readInt()];
			for(int i=0;i<v.length;i++)
				v[i]=dis.readInt();
		}catch(IOException ex){
			System.out.println("getIntArray: fail");
			throw ex;
		}
		return v;
	}

	public static byte[] getByteArray(int src[]){
		ByteArrayOutputStream aos=new ByteArrayOutputStream();
		DataOutputStream dos=new DataOutputStream(aos);
		try{
			dos.writeInt(src.length);
			for(int i=0;i<src.length;i++)
				dos.writeInt(src[i]);
		}catch(IOException ex){
			System.out.println("getByteArray: fail");
		}
		return aos.toByteArray();
	}
}
