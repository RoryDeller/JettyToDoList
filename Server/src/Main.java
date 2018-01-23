import org.eclipse.jetty.server.Server;

import java.util.ArrayList;

public class Main {

    public static ArrayList<String> myList;

    public static void main(String[] args) throws Exception {

        myList = new ArrayList<>();

        myList.add("<strike>One</strike>");
        myList.add("<strike>Two</strike>");
        myList.add("<strike>Three</strike>");

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