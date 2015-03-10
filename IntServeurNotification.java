import java.rmi.Remote;
import java.util.ArrayList;

/**
 * Interface du serveur de notification qui gere toutes les notifications
 *
 *
 */
public interface IntServeurNotification extends Remote{
	
	/**
	 * Creer une notification et l'ajoute a la liste
	 * @param utilSource Utilisateur emtteur
	 * @param utilCibe Utilisateur destinataire
	 * @param competence Objet de la notification
	 * @return Resultat
	 */
	public String creerNotification(String utilSource, String utilCible, String competence, String type);
	
	/**
	 * Donne le nombre de notifications qui concernent l'utilisateur en parametre
	 * @param utilisateur Utilisateur concerne par les notifications
	 * @return Nombre de notifications pour l'utilisateur
	 */
	public int getNombreNotification(String utilisateur);
	
	/**
	 * Supprime une notification
	 * @param utilisateurCible Destinataire
	 * @param utilisateurSource Emetteur
	 * @param competence Competence concernee
	 * 
	 */
	public void delNotification(String utilisateurCible,String utilisateurSource, String competence);
	
	/**
	 * Envoi la liste des notifications qui le concerne a un utilisateur
	 * @param utilisateur Utilisateur qui demande ses notifications
	 * @return Liste de ses notifications avec les champs separes par des #
	 */
	public ArrayList<String> getNotificationsUtilisateur(String utilisateur);
	
}
