package cdn.node;

import java.util.ArrayList;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Timer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.security.MessageDigest;


import cdn.wireformats.*;

public class RouterRegisterThread extends Thread {
	
	        public String hostname = null, ip = null, ServerIP, ServerPort, RouterID;
	        public int portno;
	        byte[] message;
	        public Socket clientsocket; 
	        public RouterRegisterThread(String ServerIP, String ServerPort, String RouterID){
	        	this.ServerIP=ServerIP;
	        	this.ServerPort=ServerPort;
	        	this.RouterID=RouterID;
	        }
	        
	        public byte[] convertStringToByteArray(String input){
	            byte[] theByteArray = input.getBytes();
	            return theByteArray;
	        }
	        
	        
	        public void run() {
		        try {
	                //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	                	
	                	hostname = InetAddress.getLocalHost().getHostName();
			        	clientsocket = new Socket(ServerIP,Integer.parseInt(ServerPort));
				        Data messageObject = new Data();
				        messageObject.data=hostname;
				        
				        OutputStream socketOutputStream = clientsocket.getOutputStream();
				        String lenghtOfData=Integer.toString(messageObject.data.length());
				        if(lenghtOfData.length() < 2){
				        	lenghtOfData = "0"+lenghtOfData; 
				        }
				        socketOutputStream.write(convertStringToByteArray(lenghtOfData));
				        socketOutputStream.write(convertStringToByteArray(messageObject.data));
				       
			      //Launch the Receiver
				      RouterResponseReceiverThread routerReceiver = new RouterResponseReceiverThread(clientsocket);
				      routerReceiver.start();
				      System.out.println("Router at "+hostname+" launched");
		        }catch (Exception e){ 
		        	e.printStackTrace();
		        }

	        }


}
