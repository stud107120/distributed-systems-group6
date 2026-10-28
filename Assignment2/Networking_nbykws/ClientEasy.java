import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ClientEasy
{
    public static void main (String[] args)
    {
        //1c)
        //System.setProperty("http.proxyHost", "proxy.example.de");
        //System.setProperty("http.proxyPort", "8080");
        
        try{            
            URL url = new URL("http://google.com");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
    
            String response;
            while((response = reader.readLine()) != null)
            {
                System.out.println("Server: '" + response + "'");
            }
            reader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
