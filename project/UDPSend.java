package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPSend {
	public static void sendback(byte[] backdata) throws IOException{
			DatagramSocket ds;
			try {
				ds = new DatagramSocket(1500);
				byte[] buf=backdata;
	            DatagramPacket dp=new DatagramPacket(buf,buf.length,InetAddress.getByName("111.115.74.92"),1025);
                ds.send(dp);
                ds.close();
			     } catch (SocketException e) {
				    e.printStackTrace();
			        }
	}

	public static void main(String[] args) {
		
	}
}
