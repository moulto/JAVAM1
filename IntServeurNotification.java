import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Interface du serveur de notification qui gere toutes les notifications
 *
 *
 */
public interface IntServeurNotification extends Remote{
	
	/**
	 * Creer une notification et l'ajoute a la liste
	 * @param utilSource Utilisateur emetteur
	 * @param utilCibe Utilisateur destinataire
	 * @param competence Objet de la notification
	 * @return Resultat
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public String creerNotification(String utilSource, String utilCible, String competence, String type) throws RemoteException, MalformedURLException, NotBoundException;
	
	/**
	 * Donne le nombre de notifications qui concernent l'utilisateur en parametre
	 * @param utilisateur Utilisateur concerne par les notifications
	 * @return Nombre de notifications pour l'utilisateur
	 * @throws RemoteException 
	 */
	public int getNombreNotification(String utilisateur) throws RemoteException;
	
	/**
	 * Supprime une notification
	 * @param utilisateurCible Destinataire
	 * @param utilisateurSource Emetteur
	 * @param competence Competence concernee
	 * @throws RemoteException 
	 * 
	 */
	public void delNotification(String utilisateurCible,String utilisateurSource, String competence) throws RemoteException;
	
	/**
	 * Envoi la liste des notifications qui le concerne a un utilisateur
	 * @param utilisateur Utilisateur qui demande ses notifications
	 * @return Liste de ses notifications avec les champs separes par des #
	 * @throws RemoteException 
	 */
	public ArrayList<String> getNotificationsUtilisateur(String utilisateur) throws RemoteException;
	
	/**
	 * Affiche une popup sur l'ecran de l'utilisateur pour l'informer d'une nouvelle notification
	 * @param utilisateur Utilisateur que l'on doit notifier
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws NotBoundException
	 */
	public void notifier(String utilisateur) throws RemoteException, MalformedURLException, NotBoundException;
	
}
