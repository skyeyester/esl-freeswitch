import java.util.Map;
import java.util.Set;
 
import org.freeswitch.esl.client.IEslEventListener;
import org.freeswitch.esl.client.transport.event.EslEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class MyEslEventListener implements IEslEventListener {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
 
  @Override
  public void eventReceived(EslEvent event) {
    log.info("eventReceived [{}]\n[{}]\n", event, getEventToLog(event));
  }
 
  @Override
  public void backgroundJobResultReceived(EslEvent event) {
    log.info("backgroundJobResultReceived [{}]\n[{}]\n", event, getEventToLog(event));
  }
 
  private String getEventToLog(EslEvent event) {
    StringBuffer buf = new StringBuffer();
    Map<String, String> map = event.getEventHeaders();
    Set<String> set = map.keySet();
    for (String name : set) {
      buf.append(name + " " + map.get(name) + "\n");
    }
    return buf.toString();
  }
}
