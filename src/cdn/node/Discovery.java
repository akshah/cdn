package cdn.node;

import java.io.IOException;

public class Discovery {
	public static String discoveryPortNumber;
	public static DiscoveryRegisterList discoveryRegisterList = new DiscoveryRegisterList();
	public static void main (String[] args) throws IOException {
		discoveryPortNumber=args[0];
		DiscoveryReceiverThread discoveryReceiver = new DiscoveryReceiverThread(discoveryPortNumber,discoveryRegisterList);
		discoveryReceiver.start();
	}
}
