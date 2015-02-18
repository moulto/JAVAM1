import java.util.ArrayList;
import java.util.HashMap;

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
	
	private HashMap<Theme,Etudiant> recom;
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
	
	public void devenirReferent(Theme theme)
	{

		//Recommandation reco = new Recommandation(Etudiant
	}
	public void ajouterRecommandation( ArrayList<Recommandation> listeRecommandation, Etudiant recommandant,Etudiant recommande, Theme theme)
	{

		Boolean found=false;
		for (Recommandation recommandation : listeRecommandation)
		{
			if (recommandation.getTheme().equals(theme) && recommandation.getRecommandant().getPseudo().equals(this.pseudo) && recommandation.getRecommande().equals(recommande) )
			{
				found = true;
				
			}
			else
			{
				found=false;
			}
		}
		if (found=false)
		{
			Recommandation reco = new Recommandation(recommandant,theme,recommande);
			listeRecommandation.add(reco);
			System.out.println("La recommandation a été ajoutée");
		}
		
		//Recommandation reco = new Recommandation(,theme,recommande) ;
	}
	public void retirerRecommandation(ArrayList<Recommandation> listeRecommandation, Etudiant recommandant,Etudiant recommande, Theme theme)
	{
		Recommandation reco = new Recommandation(recommandant,theme,recommande);
		if(listeRecommandation.remove(reco) == true)
			{
				System.out.println("La recommandation a été retirée");
			}
		
	}

}
