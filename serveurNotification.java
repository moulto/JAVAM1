import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;


public class serveurNotification extends UnicastRemoteObject implements intServeurNotification{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HashMap<String,Notification> listeNotification;
	
	public serveurNotification() throws RemoteException{
		super();
		this.listeNotification = new HashMap<String, Notification>();
	}

	@Override
	public String creerNotification(String utilSource, String utilCibe,
			String competence, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNombreNotification(String utilisateur) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
