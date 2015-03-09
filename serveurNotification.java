import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Serveur de notification qui gère toutes les notifications
 *
 *
 */
public class serveurNotification extends UnicastRemoteObject implements intServeurNotification{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Liste des notifications avec en clef le pseudo de l'étudiant cible
	 */
	private HashMap<String,ArrayList<Notification>> listeNotification;
	
	/**
	 * Constructeur de la classe
	 */
	public serveurNotification() throws RemoteException{
		super();
		this.listeNotification = new HashMap<String, ArrayList<Notification>>();
	}

	/**
	 * Creer une notification et l'ajoute à la liste
	 * @param utilSource Utilisateur emtteur
	 * @param utilCibe Utilisateur destinataire
	 * @param competence Objet de la notification
	 * @return Resultat
	 */
	public String creerNotification(String utilSource, String utilCible,String competence, String type) {
		Notification notification = new Notification(utilSource, utilCible, competence, type);
		ArrayList<Notification> listeNotifications = this.listeNotification.get(utilCible);
		listeNotifications.add(notification);
		this.listeNotification.put(utilCible, listeNotifications);
		return("Votre notification a bien ete envoyee");
	}

	/**
	 * Donne le nombre de notifications qui concernent l'utilisateur en paramètre
	 * @param utilisateur Utilisateur concerné par les notifications
	 * @return Nombre de nptifictions pour l'tilisateur
	 */
	public int getNombreNotification(String utilisateur) {
		int nbNotif;
		nbNotif = this.listeNotification.get(utilisateur).size();
		return nbNotif;
	}
	
	/**
	 * Supprime une notification
	 * @param utilisateurCible Destinataire
	 * @param utilisateurSource Emetteur
	 * @param competence Competence concernée
	 * 
	 */
	public void delNotification(String utilisateurCible,String utilisateurSource, String competence){
		if(this.listeNotification.get(utilisateurCible).size()>0){
			for(Notification notif : this.listeNotification.get(utilisateurCible)){
				if(notif.getUtilisateurEmetteur().equals(utilisateurSource)){
					if(notif.getCompetenceConcernee().equals(competence)){
						int index = this.listeNotification.get(utilisateurCible).indexOf(notif);
						this.listeNotification.get(utilisateurCible).remove(index);
					}
				}
			}
		}
	}
	
	/**
	 * Envoi la liste des notifications qui le concerne à un utilisateur
	 * @param utilisateur Utilisateur qui demande ses notifications
	 * @return Liste de ses notifications avec les champs séparés par des #
	 */
	public ArrayList<String> getNotificationsUtilisateur(String utilisateur){
		ArrayList<Notification> listeNotifications = this.listeNotification.get(utilisateur);
		if(listeNotifications.size()>0){
			ArrayList<String> notifications = new ArrayList<String>();
			for(Notification notif : listeNotifications){
				notifications.add(notif.getUtilisateurEmetteur()+"#"+notif.getCompetenceConcernee()+"#"+notif.getType());
			}
			return notifications;
		}else{
			return null;
		}
	}
	
	
}
