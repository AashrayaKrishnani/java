import java.net.URI;
import java.net.URISyntaxException;

public class URIs {

    public static URI dbURI; 
    public static URI httpURI; 
    public static URI relativehttpURI; 
    public static URI relativehttpURIroot; 

    static{
        try{
            dbURI = new URI("db://username:password@mycompany.com:5000/catalogue/phones?os=android#samsung"); 
            httpURI = new URI("http://AashrayaKrishnani:devarunaashraya@github.com:8080/AashrayaKrishnani/quizApp?name=license#2.4"); 
            relativehttpURI = new URI("/AashrayaKrishnani/quizApp?name=license#2.4"); 
            relativehttpURIroot = new URI("https://AashrayaKrishnani:devarunaashraya@github.com:8080"); 
        } catch(URISyntaxException e)
        {
            e.printStackTrace();
        }
    }    
    public static void main(String[] args) throws Exception {
        
        print("Printing for URI: " + dbURI.toString() + '\n');

        print("Scheme: " + dbURI.getScheme());
        print("SchemeSpecificPart: " + dbURI.getSchemeSpecificPart());
        print("Authority: " + dbURI.getAuthority());
        print("UserInfo: " + dbURI.getUserInfo());
        print("Host: " + dbURI.getHost());
        print("Port: " + dbURI.getPort());
        print("Path: " + dbURI.getPath());
        print("Query: " + dbURI.getQuery());
        print("Fragment: " + dbURI.getFragment());
        

    }

    public static void print(String s) throws Exception
    {
        System.out.println(s);
        Thread.sleep(1000);
    }
    
}
