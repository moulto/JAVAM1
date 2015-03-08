public class Notification {
	
	
	private String utilisateurEmetteur;
	
	private String utilisateurCible;
	
	private String competenceConcernee;
	
	private String type;
	
	
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
	 * @return the utilisateurEmetteur
	 */
	public String getUtilisateurEmetteur() {
		return utilisateurEmetteur;
	}


	/**
	 * @return the utilisateurCible
	 */
	public String getUtilisateurCible() {
		return utilisateurCible;
	}


	/**
	 * @return the competenceConcernee
	 */
	public String getCompetenceConcernee() {
		return competenceConcernee;
	}
	
	public String getType(){
		return this.type;
	}
	
	

}
