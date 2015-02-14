import java.util.ArrayList;
/**
 * Classe qui représente un thème
 * @author l'Ordre
 *
 */

public class Theme
{
	private String libelle;
	private ArrayList<Etudiant> Referents;
	
	/**
	 * Constructeur de la classe Etudiant
	 * @param nom Nom de l'étudiant
	 * @param prenom Prénom de l'étudiant
	 * @param pseudo Pseudo de l'étudiant
	 */
	public Theme(String libelle , ArrayList<Etudiant> Referents)
	{
		this.libelle=libelle;
		this.Referents=Referents;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public void addReferent(Etudiant etu)
	{
		Referents.add(etu);
	}
	
	public void delReferent(Etudiant etu)
	{
		Referents.remove(etu);
	}
	
}