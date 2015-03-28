import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Serveur de notification qui gere toutes les notifications
 *
 *
 */
public class serveurNotification extends UnicastRemoteObject implements IntServeurNotification{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Liste des notifications avec en clef le pseudo de l'etudiant cible
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
	 * Creer une notification et l'ajoute a la liste
	 * @param utilSource Utilisateur emtteur
	 * @param utilCibe Utilisateur destinataire
	 * @param competence Objet de la notification
	 * @return Resultat
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public String creerNotification(String utilSource, String utilCible,String competence, String type) throws RemoteException, MalformedURLException, NotBoundException{
		Notification notification = new Notification(utilSource, utilCible, competence, type);
		ArrayList<Notification> listeNotifications;
		if(this.listeNotification.get(utilCible) != null){
			listeNotifications = this.listeNotification.get(utilCible);
		}else{
			listeNotifications = new ArrayList<Notification>();
		}
		listeNotifications.add(notification);
		this.listeNotification.put(utilCible, listeNotifications);
		return("Votre notification a bien ete envoyee");
	}

	/**
	 * Donne le nombre de notifications qui concernent l'utilisateur en parametre
	 * @param utilisateur Utilisateur concerne par les notifications
	 * @return Nombre de notifications pour l'utilisateur
	 */
	public int getNombreNotification(String utilisateur) throws RemoteException{
		Integer nbNotif;
		if(this.listeNotification.containsKey(utilisateur)){
			nbNotif = this.listeNotification.get(utilisateur).size();
			return nbNotif;
		}else{
			return 0;
		}
	}
	
	/**
	 * Supprime une notification
	 * @param utilisateurCible Destinataire
	 * @param utilisateurSource Emetteur
	 * @param competence Competence concernee
	 * 
	 */
	public void delNotification(String utilisateurCible,String utilisateurSource, String competence) throws RemoteException{
		if(this.listeNotification.get(utilisateurCible).size()>0){
			int index = 0;
			int trouve = 0;
			for(Notification notif : this.listeNotification.get(utilisateurCible)){
				if(notif.getUtilisateurEmetteur().equals(utilisateurSource)){
					if(notif.getCompetenceConcernee().equals(competence)){
						index = this.listeNotification.get(utilisateurCible).indexOf(notif);
						trouve = 1;
						break;
					}
				}
			}
			if(trouve == 1){
				this.listeNotification.get(utilisateurCible).remove(index);
			}
		}
	}
	
	/**
	 * Envoi la liste des notifications qui le concerne a un utilisateur
	 * @param utilisateur Utilisateur qui demande ses notifications
	 * @return Liste de ses notifications avec les champs separes par des #
	 */
	public ArrayList<String> getNotificationsUtilisateur(String utilisateur) throws RemoteException{
		ArrayList<Notification> listeNotifications = this.listeNotification.get(utilisateur);
		if(listeNotifications != null){
			ArrayList<String> notifications = new ArrayList<String>();
			for(Notification notif : listeNotifications){
				notifications.add(notif.getUtilisateurEmetteur()+"#"+notif.getCompetenceConcernee()+"#"+notif.getType());
			}
			return notifications;
		}else{
			return null;
		}
	}
	
	
	public static void main(String args[]) throws RemoteException, MalformedURLException{
		try{
			LocateRegistry.createRegistry(1099);
		}
		catch(RemoteException e){
			LocateRegistry.getRegistry(1099);
		}
		serveurNotification notification = new serveurNotification();
		Naming.rebind("notification", notification);
	}

	
	/**
	 * Affiche une popup sur l'écran de l'utilisateur pour l'informer d'une nouvelle notification
	 * @param utilisateur Utilisateur que l'on doit notifier
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws NotBoundException
	 */
	public void notifier(String utilisateur) throws RemoteException, MalformedURLException, NotBoundException {
		IntClient cl = (IntClient) Naming.lookup("//localhost/"+utilisateur);
		cl.afficherNotif();	
	}
	
	
	
}
