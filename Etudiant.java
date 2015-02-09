/**
 * Classe qui représente un étudiant
 * @author florian
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
	 * Constructeur de la classe Etudiant
	 * @param nom Nom de l'étudiant
	 * @param prenom Prénom de l'étudiant
	 */
	public Etudiant(String nom, String prenom){
		this.nom = nom;
		this.prenom = prenom;
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

}
