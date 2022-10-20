import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    ArrayList <String> items = new ArrayList <String>();

   
    public String handleRequest(URI url) {
        if(url.getPath().equals("/")){
            return  "Let's start!";
         }

         else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
           // if (parameters[0].equals("s")){
                items.add(parameters[1]);
           // } 
               return "Word "+ parameters[1] +" added successfully";
        }
         else {
            System.out.println("Path: " + url.getPath());

            if (url.getPath().contains("/search")){
                String[] search = url.getQuery().split("=");
                if (search[0].equals("s")){
                    String searchReturn = ""; 
                    for(String x: items){
                        if(x.contains(search[1])){
                            searchReturn += x + " ";
                        }
                    }
                    return searchReturn;
                }
            }
            return "404 Not Found!";
        }
        
    }

}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}