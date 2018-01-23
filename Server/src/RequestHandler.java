import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class RequestHandler extends AbstractHandler {

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String method = request.getMethod();
        String path = request.getRequestURI();
        String client = request.getRemoteAddr();
        String query = request.getQueryString();

        BufferedReader reader = request.getReader();
        StringBuilder data = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) data.append(line);

        String info = "Client: " + client + "\t\t" +
                "Method: " + method + "\t\t" +
                "Path: " + path + "\t\t" +
                "Query: " + query + "\t\t" +
                "Data:" + data;

        System.out.println(info);

        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Access-Control-Allow-Origin", "*");

        if (method.equals("POST")) {

            if (path.equals("/add")) {

                String[] keyAndValue = data.toString().split("=");
                if (keyAndValue.length == 2) {
                    switch (keyAndValue[0]) {
                        case "first":
                            Main.myList.add(0, keyAndValue[1]);
                            break;
                        case "last":
                            Main.myList.add(keyAndValue[1]);
                            break;
                    }
                }

            } else if (path.equals("/delete")) {

                String item = data.toString();
                Main.myList.remove(item);

            } else if (path.equals("/clear")) {

                Main.myList.clear();

            } else if (path.equals("/get")) {

                // No action required

            }

            response.getWriter().print(String.join(",", Main.myList));

        } else if (method.equals("GET")) {

            response.getWriter().print("<h2>Sorry, this server only accepts HTTP POST requests</h2><hr/>");
            response.getWriter().print("<h3>Debug info:</h3>");
            response.getWriter().print(info.replaceAll("\\t", "<br>"));

        }


        baseRequest.setHandled(true);
    }



}