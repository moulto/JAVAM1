import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Gestionnaire de profil qui permet de créer des objets remote Profils
 *
 */
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
	/**
	 * Creer un profil remote
	 * @param pseudo Identifiant de l'étudiant
	 * @param competences Liste des competences
	 * @return Résultat de la création
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public String creerProfil(String pseudo, ArrayList<String> competences) throws RemoteException, MalformedURLException {
		Profil profil = new Profil(pseudo,competences);
		try{
			LocateRegistry.createRegistry(1099);
		}
		catch(RemoteException e){
			LocateRegistry.getRegistry(1099);
		}
		Naming.rebind(pseudo+"-profil", profil);
		String url =  "//localhost/"+pseudo+"-profil";
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
