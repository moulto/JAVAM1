import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface du gestionnaire de profil qui permet de creer des profils
 *
 */
public interface IntGestionnaireProfil extends Remote {
	
	/**
	 * Creer un profil remote
	 * @param pseudo Identifiant de l'etudiant
	 * @param competences Liste des competences
	 * @return Resultat de la creation
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	 public String creerProfil(String pseudo) throws RemoteException, MalformedURLException;
}
