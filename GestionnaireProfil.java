import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Gestionnaire de profil qui permet de creer des objets remote Profils
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
	 * @param pseudo Identifiant de l'etudiant
	 * @param competences Liste des competences
	 * @return Resultat de la creation
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public String creerProfil(String pseudo) throws RemoteException, MalformedURLException {
		Profil profil = new Profil(pseudo);
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
