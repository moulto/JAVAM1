/**
 * Classe qui représente une recommandation
 * @author l'Ordre
 *
 */
public class Recommandation {
	private Etudiant recommandé;
	private Theme themereco;
	private Etudiant recommandant;
	
	public Recommandation(Etudiant recommandé,Theme themereco,Etudiant recommandant)
	{
		this.recommandé=recommandé;
		this.themereco=themereco;
		this.recommandant=recommandant;
	}
	public Etudiant getRecommandant() {
		return recommandant;
	}
	public void setRecommandant(Etudiant recommandant) {
		this.recommandant = recommandant;
	}
	public Theme getThemereco() {
		return themereco;
	}
	public void setThemereco(Theme themereco) {
		this.themereco = themereco;
	}
	public Etudiant getRecommandé() {
		return recommandé;
	}
	public void setRecommandé(Etudiant recommandé) {
		this.recommandé = recommandé;
	}
}


