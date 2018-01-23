import org.eclipse.jetty.server.Server;

import java.util.ArrayList;

public class Main {

    public static ArrayList<String> myList;

    public static void main(String[] args) throws Exception {

        myList = new ArrayList<>();

        myList.add("One");
        myList.add("Two");
        myList.add("Three");

        int port = 8080;
        Server s = new Server(port);
        RequestHandler r = new RequestHandler();
        s.setHandler(r);
        s.start();
        System.out.println("Server is live on http://" + NetworkHelper.getMyNetworkAdapter() + ":" + port);
        System.out.println("-----------------------------------------------------------------------");
        s.join();

    }

}