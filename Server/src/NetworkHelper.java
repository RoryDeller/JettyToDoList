import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetworkHelper {

    public static String getMyNetworkAdapter() throws SocketException
    {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements())
        {
            NetworkInterface iface = interfaces.nextElement();

            if (iface.isLoopback() || !iface.isUp()) continue; // filters out 127.0.0.1 and inactive interfaces

            Enumeration<InetAddress> addresses = iface.getInetAddresses();

            if (iface.getDisplayName().startsWith("wlan0")) continue;

            while(addresses.hasMoreElements())
            {
                InetAddress addr = addresses.nextElement();
                if (!(addr instanceof Inet4Address)) continue;
                return (addr.getHostAddress());
            }
        }
        return null;
    }

}

