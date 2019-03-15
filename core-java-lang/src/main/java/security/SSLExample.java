package security;

import java.net.URL;
import javax.net.ssl.*;

/**
 *
 * @author Panyaprach Tularak
 */
public class SSLExample {

    public static void main(String[] args) throws Exception{
        URL url = new URL("https://www.websecurity.symantec.com");
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.connect();
        System.out.println(con.getContent().getClass());
    }
    
}
