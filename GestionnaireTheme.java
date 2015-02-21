import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteRef;
import java.rmi.server.UnicastRemoteObject;

/**
 * Classe qui permet de creer les themes
 *
 */
public class GestionnaireTheme extends UnicastRemoteObject implements IntGestionnaireTheme {

	/**
	 * Auto
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur de la classe
	 * @throws RemoteException
	 */
	protected GestionnaireTheme() throws RemoteException {
		super();
	}
	
	/**
	 * Permet de creer un theme a partir de son libelle
	 * @param libelle Libelle du theme
	 * @return Reference du noueau theme que l'on a cree
	 */
	public String creerTheme(String libelle) throws RemoteException, MalformedURLException{
		Theme theme = new Theme(libelle);
		try{
			LocateRegistry.createRegistry(1099);
		}
		catch(RemoteException e){
			LocateRegistry.getRegistry(1099);
		}
		Naming.rebind(libelle, theme);
		String url =  ""+theme.getRef();
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
		GestionnaireTheme gestionnaire = new GestionnaireTheme();
		Naming.rebind("gestonnaire", gestionnaire);
	}

}
