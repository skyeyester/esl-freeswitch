import java.util.Scanner;
 
import org.freeswitch.esl.client.inbound.Client;
import org.freeswitch.esl.client.inbound.InboundConnectionFailure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
 
public class MyEslEventTest {
  private static final Logger log = LoggerFactory.getLogger(MyEslEventTest.class);
 
  public static void main(String[] args) throws InterruptedException, InboundConnectionFailure {
    System.out.println("--- hello freeswitch (terminate program to finish) ---\n");
    Thread.sleep(2000);
     
    log.info("--- connect client ---");
    Client client = new Client();
    client.addEventListener(new MyEslEventListener());
    client.connect("10.36.151.133", 8021, "ClueCon", 1);    
    //client.connect(new InetSocketAddress("10.36.151.133", 8021), "ClueCon", 1);
     
    log.info("--- event subscription ---");
    Thread.sleep(3000);
    client.setEventSubscriptions("plain", "all");
     
    log.info("--- call 1001 1002 ---");
    client.sendSyncApiCommand("originate", "user/1001 1002");
    new Scanner(System.in).nextLine();
     
    client.close();
    System.out.println("\n--- Bye freeswitch ---");
  }
}
