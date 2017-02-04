package cdn.node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;

public class Router {
	
		public static String DiscoveryIP, DiscoveryPort, RouterID;
		public static void main (String[] args) throws IOException {
			
			//Read the command line arguments for router ID and discovery information
			RouterID=args[0];
			DiscoveryIP=args[1];
			DiscoveryPort=args[2];
				
			//Launch the sender and the receiver threads
	    	RouterRegisterThread register = new RouterRegisterThread(DiscoveryIP,DiscoveryPort,RouterID);
	    	register.start();
	    	
	    	
			
		}

}
