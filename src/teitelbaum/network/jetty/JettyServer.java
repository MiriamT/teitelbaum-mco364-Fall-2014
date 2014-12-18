package teitelbaum.network.jetty;

import org.eclipse.jetty.server.Server;

public class JettyServer
{
	public static void main(String[] args) throws Exception
	{
		Server server = new Server(1891);
		server.start();
	}
}
