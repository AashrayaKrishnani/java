import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HTTP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Input Valid Web URL to view it: ");
        String input = sc.nextLine();

        try{
            URL url = new URL(input);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            print("Response Code: " + con.getResponseCode());

            print("\nCode: \n");


            String line;
            while((line=br.readLine()) != null)
            {
                System.out.println(line);
            }

        }
        catch(MalformedURLException e)
        {
            print("MalformedURLException: " + e.getMessage());
        }
        catch(IOException e)
        {
            print("IOException: " + e.getMessage());
        }

        sc.close();
    }

    public static void print(String s){
        try{
            System.out.println(s);
            Thread.sleep(1000);
        } catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
