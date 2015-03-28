/* Permet de manipuler es notifications pour les utilisateurs */
public class Notification {
	
	/**
	 * Utilisateur à l'origine de la notification
	 */
	private String utilisateurEmetteur;
	
	/**
	 * Utilisateur qui va recevoir la notification
	 */
	private String utilisateurCible;
	
	/**
	 * Competence concernée en cas de demande de référent et vide pour les votes
	 */
	private String competenceConcernee;
	
	/**
	 * Permet de savoir si il s'agit d'une demande  référent, d'une réponse, d'un vote, d'une victoire ou d'une défaite 
	 */
	private String type; /* Demande / Reponse / vote */
	
	/**
	 * Constructeur de la classe notification
	 * @param utilisateurEmetteur Utilisateur à l'origine de la notification
	 * @param utilisateurCible Utilisateur qui va recevoir la notification
	 * @param competenceConcernee Competence concernée en cas de demande de référent et vide pour les votes
	 * @param type Permet de savoir si il s'agit d'une demande  référent, d'une réponse, d'un vote, d'une victoire ou d'une défaite 
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
	 * Accesseur sur la compétence
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
