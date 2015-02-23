import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface pour la classe gestionnaireTheme
 */
public interface IntGestionnaireTheme extends Remote{
	
	/**
	 * Permet de creer un theme a partir de son libelle
	 * @param libelle Libelle du theme
	 * @return Reference du noueau theme que l'on a cree
	 */
	public String creerTheme(String libelle) throws RemoteException, MalformedURLException;

}
