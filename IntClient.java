import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IntClient extends Remote {

	/**
	 * Affiche une popup qui informe de la présence d'une nouvelle notification
	 * @throws RemoteException
	 */
	public void afficherNotif() throws RemoteException;
}
