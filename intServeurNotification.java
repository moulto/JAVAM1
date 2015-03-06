import java.rmi.Remote;
import java.util.ArrayList;

/**
 * Interface du serveur de notification qui gère toutes les notifications
 *
 *
 */
public interface intServeurNotification extends Remote{
	
	/**
	 * Creer une notification et l'ajoute à la liste
	 * @param utilSource Utilisateur emtteur
	 * @param utilCibe Utilisateur destinataire
	 * @param competence Objet de la notification
	 * @return Resultat
	 */
	public String creerNotification(String utilSource, String utilCible, String competence, String type);
	
	/**
	 * Donne le nombre de notifications qui concernent l'utilisateur en paramètre
	 * @param utilisateur Utilisateur concerné par les notifications
	 * @return Nombre de nptifictions pour l'tilisateur
	 */
	public int getNombreNotification(String utilisateur);
	
	/**
	 * Supprime une notification
	 * @param utilisateurCible Destinataire
	 * @param utilisateurSource Emetteur
	 * @param competence Competence concernée
	 * 
	 */
	public void delNotification(String utilisateurCible,String utilisateurSource, String competence);
	
	/**
	 * Envoi la liste des notifications qui le concerne à un utilisateur
	 * @param utilisateur Utilisateur qui demande ses notifications
	 * @return Liste de ses notifications avec les champs séparés par des #
	 */
	public ArrayList<String> getNotificationsUtilisateur(String utilisateur);
	
}
