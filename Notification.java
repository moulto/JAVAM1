/* Permet de manipuler les notifications pour les utilisateurs */
public class Notification {
	
	/**
	 * Utilisateur a l'origine de la notification
	 */
	private String utilisateurEmetteur;
	
	/**
	 * Utilisateur qui va recevoir la notification
	 */
	private String utilisateurCible;
	
	/**
	 * Competence concernee en cas de demande de referent et vide pour les votes
	 */
	private String competenceConcernee;
	
	/**
	 * Permet de savoir si il s'agit d'une demande  referent, d'une reponse, d'un vote, d'une victoire ou d'une defaite 
	 */
	private String type; /* Demande / Reponse / vote */
	
	/**
	 * Constructeur de la classe notification
	 * @param utilisateurEmetteur Utilisateur a l'origine de la notification
	 * @param utilisateurCible Utilisateur qui va recevoir la notification
	 * @param competenceConcernee Competence concernee en cas de demande de referent et vide pour les votes
	 * @param type Permet de savoir si il s'agit d'une demande  referent, d'une reponse, d'un vote, d'une victoire ou d'une defaite 
	 */
	public Notification(String utilisateurEmetteur, String utilisateurCible, String competenceConcernee, String type){
		this.utilisateurCible = utilisateurCible;
		this.utilisateurEmetteur = utilisateurEmetteur;
		this.competenceConcernee = competenceConcernee;
		this.type = type;
	}
	
	/**
	 * @return notification en String
	 */
	public String toString()
	{
		return (this.utilisateurEmetteur + "demande si vous voulez etre referent sur le theme : " + this.competenceConcernee);
	}

	/**
	 * Accesseur sur l'utilisateur emetteur
	 * @return the utilisateurEmetteur
	 */
	public String getUtilisateurEmetteur() {
		return utilisateurEmetteur;
	}


	/**
	 * Accesseur sur l'utilisateur cible
	 * @return the utilisateurCible
	 */
	public String getUtilisateurCible() {
		return utilisateurCible;
	}


	/**
	 * Accesseur sur la competence
	 * @return the competenceConcernee
	 */
	public String getCompetenceConcernee() {
		return competenceConcernee;
	}
	
	/**
	 * Accesseur sur le type
	 * @return Type de la notification
	 */
	public String getType(){
		return this.type;
	}
	
	

}
