import java.rmi.Remote;


public interface intServeurNotification extends Remote{
	
	/**
	 * Creer une notification et l'ajoute à la liste
	 * @param utilSource Utilisateur emtteur
	 * @param utilCibe Utilisateur destinataire
	 * @param competence Objet de la notification
	 * @return Resultat
	 */
	public String creerNotification(String utilSource, String utilCibe, String competence, String type);
	
	public int getNombreNotification(String utilisateur);
	
}
