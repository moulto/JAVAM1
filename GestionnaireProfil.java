import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class GestionnaireProfil extends UnicastRemoteObject implements IntGestionnaireProfil {

	protected GestionnaireProfil() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String creerProfil(String pseudo, ArrayList<String> competences) throws RemoteException, MalformedURLException {
		Profil profil = new Profil(pseudo,competences);
		try{
			LocateRegistry.createRegistry(1099);
		}
		catch(RemoteException e){
			LocateRegistry.getRegistry(1099);
		}
		Naming.rebind(pseudo, profil);
		String url =  "//localhost/"+pseudo;
		return url;
	}
	
	
	/**
	 * Fonction principale du gestionnaire de theme
	 * @param args
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public static void main(String args[]) throws RemoteException, MalformedURLException{
		try{
			LocateRegistry.createRegistry(1099);
		}
		catch(RemoteException e){
			LocateRegistry.getRegistry(1099);
		}
		GestionnaireProfil gestionnaireProfil = new GestionnaireProfil();
		Naming.rebind("gestionnaireProfil", gestionnaireProfil);
	}

}
