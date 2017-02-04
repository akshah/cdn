package cdn.node;

import java.io.OutputStream;
import java.net.Socket;

import cdn.wireformats.Data;

public class DiscoveryResponse {
	
	public String RouterPort, RouterIP; 
	
	public DiscoveryResponse(String RouterIP, String RouterPort){
		this.RouterIP=RouterIP;
		this.RouterPort=RouterPort;
	}
	
	
	
	
}
