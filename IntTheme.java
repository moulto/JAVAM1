import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * Interface pour la classe theme
 *
 */
public interface IntTheme extends Remote{

	/**
	 * Retourne le libelle du theme
	 * @return Libelle du theme
	 */
	public String getLiblle() throws RemoteException;
	
	/**
	 * Retourne la liste des referents sur le theme sous forme d'un tableau
	 * @return Liste des referents sur le theme
	 */
	public String getListeReferents() throws RemoteException;
	
	/**
	 * Ajoute un referent pour le theme, si l'utilisateur a deja vote pour ce referent sur ce theme alors sa demande n'est pas prise en compte  
	 * @param recommandant Etudiant qui vote pour un camarade
	 * @param recommande Etudiant qui est recommande par un de ses camarades sur le theme
	 * @return Resultat de la demande d'ajout
	 */
	public String addReferent(String recommandant, String recommande) throws RemoteException;
	
	/**
	 * Retire le vote d'un etudiant pour l'un de ses camarade sur le theme
	 * @param recommandant Etudiant qui desire retirer son vote
	 * @param recommande Etudiant concerne par la demande de retrait du vote
	 * @return Resultat de la demande de retrait
	 */
	public String delReferent(String recommandant, String recommande) throws RemoteException;
	
	/**
	 * Donne pour le theme le meileur referent, c'est a dire celui qui a reçu le plus de vote
	 * @return Meilleur referent sur le theme
	 */
	public String getMeilleurReferent() throws RemoteException;
}
