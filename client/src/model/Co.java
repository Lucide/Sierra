package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Co{

	public Co(){

	}

	public static int[] getIntArray(byte[] src){
		ByteArrayInputStream ais=new ByteArrayInputStream(src);
		DataInputStream dis=new DataInputStream(ais);
		int v[]=null;
		try{
			v=new int[dis.readInt()];
			for(int i=0;i<v.length;i++)
				v[i]=dis.readInt();
		}catch(IOException e){
			System.out.println("getIntArray: fail");
		}
		return v;
	}

	public static void writeArray(int src[],OutputStream os){
		ByteArrayOutputStream aos=new ByteArrayOutputStream();
		DataOutputStream dos=new DataOutputStream(aos);
		try{
			dos.writeInt(src.length);
			for(int i=0;i<src.length;i++)
				dos.writeInt(src[i]);
			os.write(aos.toByteArray());
		}catch(IOException ex){
			System.out.println("getByteArray: fail");
		}
	}
}
