/**
 * Classe qui représente un étudiant
 * @author l'Ordre
 *
 */
public class Etudiant {
	
	/**
	 * Nom de l'étudiant
	 */
	private String nom;
	
	/**
	 * Prénom de l'étudiant
	 */
	private String prenom;
	
	/**
	 * Pseudo de l'étudiant
	 */
	private String pseudo;
	
	/**
	 * Constructeur de la classe Etudiant
	 * @param nom Nom de l'étudiant
	 * @param prenom Prénom de l'étudiant
	 * @param pseudo Pseudo de l'étudiant
	 */
	public Etudiant(String nom, String prenom,String pseudo){
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
	}
	
	/**
	 * Accesseur sur le nom de l'étudiant
	 * @return nom
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * Accesseur sur le prénom de l'étudiant
	 * @return prenom
	 */
	public String getPrenom(){
		return this.prenom;
	}

	public String getPseudo() {
		return this.pseudo;
	}

}
