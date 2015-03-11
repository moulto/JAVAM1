import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IntClient extends Remote {

	public void afficherNotif() throws RemoteException;
}
