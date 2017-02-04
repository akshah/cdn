package cdn.node;

import java.io.DataInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import cdn.wireformats.*;

public class DiscoveryReceiverThread extends Thread{
	public String discoveryPortNumber;
	public DiscoveryRegisterList discoveryRegisterList;
	
	public DiscoveryReceiverThread(String discoveryPortNumber,DiscoveryRegisterList discoveryRegisterList){
		this.discoveryPortNumber=discoveryPortNumber;
		this.discoveryRegisterList=discoveryRegisterList;
	}
	
	 public String convertByteToString(byte[] BytesReceived){
         return(BytesReceived.toString());
	 }
	
	 public byte[] convertStringToByteArray(String input){
         byte[] theByteArray = input.getBytes();
         return theByteArray;
     }
	 
	 public void DiscResponse(Socket clientsocket,String RouterIP, String RouterPort){
			try{
			
					
	        Data messageObject = new Data();
	        messageObject.data="Router "+RouterIP+" Registered";
	        OutputStream socketOutputStream = clientsocket.getOutputStream();
	        
	        String lenghtOfData=Integer.toString(messageObject.data.length());
	        if(lenghtOfData.length() < 2){
	        	lenghtOfData = "0"+lenghtOfData; 
	        }
	        socketOutputStream.flush();
	        socketOutputStream.write(convertStringToByteArray(lenghtOfData));
	        socketOutputStream.write(convertStringToByteArray(messageObject.data));
	        
	      
	        socketOutputStream.close();
	        clientsocket.close();
	        
	        System.out.println("REGISTER_RESPONSE sent to "+RouterIP+"\n");
	    	
			}catch(Exception e){
				e.printStackTrace();
			}
	  
		}
	 
	public void run() {
        try {
        	System.out.println("Discovery Node Launched\n");
			ServerSocket server = new ServerSocket(Integer.parseInt(discoveryPortNumber));
            Socket socket;
            DiscoveryResponse discoverRespose;
            
            while(true){
	       
	            socket=server.accept();
	           
	            int LEN_BYTES=2;
	        	DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());  
	        	byte [] bytesnum = new byte [LEN_BYTES];
	        	
	        	int numRead = dataInputStream.read (bytesnum);
	        	while (numRead != LEN_BYTES) {
	          	
	          	  numRead += dataInputStream.read (bytesnum, numRead, LEN_BYTES - numRead);
	          	}
	        	String lenghtOfMessage = new String(bytesnum);
	        
	        	LEN_BYTES=Integer.parseInt(lenghtOfMessage);
	        	//System.out.println("Message Length: "+LEN_BYTES);
	        	byte [] bytes = new byte [LEN_BYTES];
	        	// read returns the number of bytes actually read
	        	int numReadMsg = dataInputStream.read (bytes);
	        	while (numReadMsg != LEN_BYTES) {
	        	  // read more bytes and put them into the array starting at position "numRead",
	        	  numRead += dataInputStream.read (bytes, numRead, LEN_BYTES - numRead);
	        	}
	        	
	        	//String RouterIP=convertByteToString(bytes);
	        	//System.out.println(convertByteToString(bytes));
	        	//System.out.print("Registered Router "+RouterIP);
	        	
	        	String ReceivedData = new String(bytes);
	        	/*System.out.print("Registered Router: ");
      
            	for(int i=0; i < LEN_BYTES ; i++){
            		System.out.print((char)bytes[i]);
            	}
            	                        	                       	
            	System.out.println("");
            	*/
	        	 
	        	System.out.println("REGISTER_REQUEST received from: "+ReceivedData);
            	System.out.println("Registered Router: "+ReceivedData+"\n");
	        	
	        	DiscResponse(socket,ReceivedData,"51370");
            }
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
}
