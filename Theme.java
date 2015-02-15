import java.util.ArrayList;
/**
 * Classe qui représente un thème
 * @author l'Ordre
 *
 */
public class Theme
{
	/**
	 * Libelle du theme
	 */
	private String libelle;
	
	/**
	 * Liste des etudiants qui sont referents sur le theme
	 */
	private ArrayList<Etudiant> referents;
	
	/**
	 * Constructeur de la classe Theme
	 * @param libelle Libelle du theme que l'on veut creer
	 */
	public Theme(String libelle)
	{
		this.libelle=libelle;
		/* On initialise le theme avec une liste de référents vide */
		this.referents = new ArrayList<Etudiant>();
	}
	
	/**
	 * Accesseur sur le libelle du theme
	 * @return libelle du theme
	 */
	public String getLibelle() {
		return this.libelle;
	}
	
	/**
	 * Modifie le libelle d'un theme
	 * @param libelle Nouveau libelle du theme
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	/**
	 * Accesseur sur la liste des référents pour un theme
	 * @return Liste des referents d'un theme
	 */
	public ArrayList<Etudiant> getListeReferents(){
		return this.referents;
	}
	
	/**
	 * Ajoute un referent pour le theme
	 * @prama etu Etudiant nouveau referent sur le theme
	 */
	public void addReferent(Etudiant etu)
	{
		this.referents.add(etu);
	}
	
	/**
	 * Supprime un referent pour le theme
	 * @param etu Ancien etudiant referent sur le theme
	 */
	public void delReferent(Etudiant etu)
	{
		this.referents.remove(etu);
	}
	
}