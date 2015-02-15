/**
 * Classe qui représente une recommandation
 * @author l'Ordre
 *
 */
public class Recommandation {
	
	/**
	 * Etudiant recommende sur un theme
	 */
	private Etudiant recommande;
	
	/**
	 * Theme sur lequel un etudiant est recommende
	 */
	private Theme theme;
	
	/**
	 * Etudiant qui recommende un autre etudiant sur un theme
	 */
	private Etudiant recommandant;
	
	/**
	 * Constructeur de la classe Recommendation
	 * @param recommende Etudiant qui est recommende pour le theme
	 * @param theme Theme sur le lequel l'etudiant est recommende
	 * @param recommandant Etuiant qui recommende un de ses camarades sur un theme
	 */
	public Recommandation(Etudiant recommande,Theme theme,Etudiant recommandant)
	{
		this.recommande=recommande;
		this.theme=theme;
		this.recommandant=recommandant;
	}
	
	/**
	 * Accesseur sur l'etudiant recommendant
	 * @return Etudiant recommendant
	 */
	public Etudiant getRecommandant() {
		return recommandant;
	}
	
	/**
	 * Accesseur sur le theme de la recommendantion
	 * @return Theme de la recommendantion
	 */
	public Theme getTheme() {
		return theme;
	}
	
	/**
	 * Accesseur sur l'etudiant qui est recommende
	 * @return Etudiant recommende sur le theme de la recommendation
	 */
	public Etudiant getRecommande() {
		return recommande;
	}
}


