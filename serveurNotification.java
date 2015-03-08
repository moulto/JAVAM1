import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class serveurNotification extends UnicastRemoteObject implements IntServeurNotification{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HashMap<String,Notification> listeNotification;
	
	public serveurNotification() throws RemoteException{
		super();
		this.listeNotification = new HashMap<String, Notification>();
	}
	
	public String getListeNotifications (String utilCible)
	{
		ArrayList<String> notifs = null;
		for(Entry<String, Notification> entry : this.listeNotification.entrySet()){
		    if(entry.getKey()==utilCible)
		    {
		    	notifs.add(entry.getValue().toString());
		    }
		}
		Object tab[] =  notifs.toArray();
		String liste = "";
		int i;
		for(i=0;i<tab.length;i++){
			liste += (String) "\n"+tab[i];
		}
		return liste;
	}
	
	@Override
	public String creerNotification(String utilSource, String utilCible,
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
