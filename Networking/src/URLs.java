import java.net.URL;

public class URLs
{
    public static void main(String args[]) 
    {
        try{
            print("Trying to convert URI->URl\n" + "URI: " + URIs.dbURI);
            URL dbURL = URIs.dbURI.toURL();
            print("\nConverted URL: " + dbURL.toString());
        } catch(Exception e)
        {
            print("Error: " + e.getMessage());
        }

        try{
            print("\nTrying to convert URI->URl\n" + "URI: " + URIs.httpURI);
            URL httpURL = URIs.httpURI.toURL();
            print("\nConverted URL: " + httpURL.toString());
        } catch(Exception e)
        {
            print("Error: " + e.getMessage());
        }

        try{
            print("\nTrying to convert relative URI->URl\n" + "URI: " + URIs.relativehttpURI);
            URL relativehttpURL = URIs.relativehttpURI.toURL();
            print("\nConverted URL: " + relativehttpURL.toString());
        } catch(Exception e)
        {
            print("Error: " + e.getMessage());
        }

        try{
            print("\nTrying to convert (relative URI after resolving)->URl\n" + "URI: " + URIs.relativehttpURIroot.resolve(URIs.relativehttpURI));
            URL relativehttpURLResolved = URIs.relativehttpURIroot.resolve(URIs.relativehttpURI).toURL();
            print("\nConverted URL: " + relativehttpURLResolved.toString()+ "\n");
        } catch(Exception e)
        {
            print("Error: " + e.getMessage());
        }

    }

    public static void print(String s){
        System.out.println(s);
        try{
            Thread.sleep(2000);
        } catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}