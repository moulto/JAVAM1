import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
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
	
	/**
	 * Donne le meilleur referent pour un theme a partir de la liste de toutes les recommendations
	 * @return Etudiant etant le plus recommende sur le theme
	 */
	public Etudiant getMeilleurReferent(listeRecommendation ArrayList<Recommendation>){
		
		/* Alogrithme pour trouver le meilleur referent :
		 * 	1 : extraire de la liste des recommendation celles qui concerne le theme dans une sous-liste
		 * 	2 : créer une MAP à partir de la liste des referents de type <Etudiant,int>
		 * 	3 : parcourir la sous-liste et incrémenter les compteurs de la map au fur et à mesure
		 * 	4 : parcourir la map et retourné le meilleur référent
		 */
		
		ArrayList<Recommendation> sousListe = new ArrayList<Recommendation>(); /* sous-liste des recommendations ne concernant que le theme voulu */
		Recommendation recommendation; /* variable pour comparer les themes et extraire les valeurs du theme que l'on cherche */
		/* On fait la Map à partir de la liste des référents en mettant un poids de 0 a tous les etudiants */
		Map<Etudiant,int> listeReferents = new Map<Etudiant,int>;
		
		/* on recupere les recommendation de notre theme */
		for(recommendation : listeRecommendation){
			/* on regarde si le theme est bien celui que l'on veut */
			if(recommendation.getTheme().getLibelle().equals(this.getLibelle())){
				/* On ajoute dans la sous-liste */
				sousListe.add(recommendation);
			}
		}
		
		/* On remplit la Map avec les etudiants referent sur le theme */
		for(Etudiant etu : this.referents){
			listeReferents.put(etu,0);
		}
		
		/* On incrémente les compteurs a chaques fois que l'on trouve une occurence d'un referent */
		for(recommendation : sousListe){
			Set<E> mapKeys = listeReferents.keySet();
			Iterator<E> iterateur = mapKeys.iterator();
			while(iterateur.hasNext()){
				if(recommendation.getRecommande().equals(iterateur.next())){
					listeReferents.put(iterateur.next(),listeReferents.get(iterateur.next())+1);
				}
			}
		}
		
		/* On parcours la MAP pour retourner le meilleur referent */
		Set<E> etudiants = listeReferents.keySet();
		Iterator<E> iterateur2 = etudiants.iterator();
		Etudiant meilleurReferent;
		int nbVote = 0;
		while(iterateur2.hasNext()){
			/* On regarde le poids de l'etudiant en cours */
			if(listeReferents.get(iterateur2.next()) > nbVote){
				/* Un etudiant a plus de vote, il devient meilleur referent */
				nbVote = listeReferents.get(iterateur2.next());
				meilleurReferent = iterateur2.next();
			}
		}
		
		/* On retourne le meilleur referent */
		return meilleurReferent;
		
		
	}
	
}