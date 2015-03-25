import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntScrutin extends Remote{
	public void voter(String votant, Integer voix) throws RemoteException, MalformedURLException;
	public void terminerScrutin() throws RemoteException, MalformedURLException;
}
