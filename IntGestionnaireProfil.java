import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Interface du gestionnaire de profil qui permet de créer des profils
 *
 */
public interface IntGestionnaireProfil extends Remote {
	
	/**
	 * Creer un profil remote
	 * @param pseudo Identifiant de l'étudiant
	 * @param competences Liste des competences
	 * @return Résultat de la création
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	 public String creerProfil(String pseudo) throws RemoteException, MalformedURLException;
}
