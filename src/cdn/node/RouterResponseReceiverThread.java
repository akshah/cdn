package cdn.node;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import cdn.wireformats.*;

public class RouterResponseReceiverThread extends Thread {

        public static String hostname = null, ip = null;
        public static int portno = 51370;
        public Socket clientsocket;
        public static Data toObject(byte[] bytes){ 
        	Data message = null; 
        	try{ 
        		message = (Data) new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject(); 
        	}catch(Exception e){}
           	return message; 
        } 
        
        public RouterResponseReceiverThread(Socket clientsocket){
        	this.clientsocket=clientsocket;
        }
        public String convertByteToString(byte[] BytesReceived){
        	               
                return(BytesReceived.toString());
        }
        public void run() {
                try {
                                                
             
                            int LEN_BYTES=2;
                        	DataInputStream dataInputStream = new DataInputStream(clientsocket.getInputStream());  
                        	byte [] bytesnum = new byte [LEN_BYTES];
                        	// read returns the number of bytes actually read
                        	int numRead = dataInputStream.read (bytesnum);
                        	while (numRead != LEN_BYTES) {
                          	  // read more bytes and put them into the array starting at position "numRead",
                          	  // for the maximum of "EXPECTED_SIZE - numRead" bytes
                          	  numRead += dataInputStream.read (bytesnum, numRead, LEN_BYTES - numRead);
                          	}
                        	String lenghtOfMessage = new String(bytesnum);
                        	
                        	  
                        	//socket=server.accept();
                        	LEN_BYTES=Integer.parseInt(lenghtOfMessage);
                        	byte [] bytes = new byte [LEN_BYTES];
                        	// read returns the number of bytes actually read
                        	int numReadMsg = dataInputStream.read (bytes);
                        	while (numReadMsg != LEN_BYTES) {
                        	  // read more bytes and put them into the array starting at position "numRead",
                        	  numRead += dataInputStream.read (bytes, numRead, LEN_BYTES - numRead);
                        	}
                        	System.out.print("REGISTER_RESPONSE received: ");
                        	
                        	for(int i=0; i < LEN_BYTES ; i++){
                        		System.out.print((char)bytes[i]);
                        	}
                        	                        	                       	
                        	System.out.println("");
                        	while(true){}
                                          }
                catch(Exception e){
                	e.printStackTrace();
                }

        }

}


