import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Classe qui représente un thème
 * @author l'Ordre
 *
 */
public class Theme extends UnicastRemoteObject implements IntTheme
{
	/**
	 * Generation auto
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Libelle du theme
	 */
	private String libelle;
	
	/**
	 * Contient la liste des referent sur le theme et pour chacun d'eux la liste des personnes qui les ont recommande
	 */
	private HashMap<String,ArrayList<String>> recommandation;
	
	
	/**
	 * Constructeur de la classe Theme
	 * @param libelle Libelle du theme que l'on veut creer
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	public Theme(String libelle) throws RemoteException, MalformedURLException
	{
		super();
		this.libelle=libelle;
		this.recommandation =new HashMap<String, ArrayList<String>>();
	}

	/**
	 * Retourne le libelle du theme
	 * @return Libelle du theme
	 */
	public String getLiblle() {
		// TODO Auto-generated method stub
		return this.libelle;
	}

	@Override
	/**
	 * Retourne la liste des referents sur le theme sous forme d'un tableau
	 * @return Liste des referents sur le theme
	 */
	public String getListeReferents() {
		Object tab[] =  this.recommandation.keySet().toArray();
		String liste = "";
		int i;
		for(i=0;i<tab.length;i++){
			liste += (String) "\n"+tab[i];
		}
		return liste;
	}

	@Override
	/**
	 * Ajoute un referent pour le theme, si l'utilisateur a deja vote pour ce referent sur ce theme alors sa demande n'est pas prise en compte
	 * @param recommandant Etudiant qui vote pour un camarade  
	 * @param recommande Etudiant qui est recommande par un de ses camarades sur le theme
	 * @return Resultat de la demande d'ajout
	 */
	public String addReferent(String recommandant, String recommande) {
		// TODO Auto-generated method stub
		if(this.recommandation.get(recommande) == null){
			this.recommandation.put(recommande, new ArrayList<String>());
			ArrayList<String> listeRecommandants = this.recommandation.get(recommande);
			listeRecommandants.add(recommandant);
			this.recommandation.put(recommande,listeRecommandants);
			return "Votre recommandation a ete ajoutee";
		}else{
			if(this.recommandation.get(recommande).contains(recommandant)){
				return "Vous avez deja vote pour ce referent";
			}else{
				ArrayList<String> listeRecommandants = this.recommandation.get(recommande);
				listeRecommandants.add(recommandant);
				this.recommandation.put(recommande,listeRecommandants);
				return "Votre recommandation a ete ajoutee";
			}
		}
	}

	@Override
	/**
	 * Retire le vote d'un etudiant pour l'un de ses camarade sur le theme
	 * @param recommandant Etudiant qui desire retirer son vote
	 * @param recommande Etudiant concerne par la demande de retrait du vote
	 * @return Resultat de la demande de retrait
	 */
	public String delReferent(String recommandant, String recommande) {
		// TODO Auto-generated method stub
		if(this.recommandation.get(recommande) == null){
			return "Vous ne pouvez pas retirer un vote que vous n'avez pas fait";
		}else{
			if(this.recommandation.get(recommande).contains(recommandant)){
				this.recommandation.get(recommande).remove(recommandant);
				if(this.recommandation.get(recommande).size() == 0){
					this.recommandation.remove(recommande);
				}
				return "Votre vote a bien ete retire";
			}else{
				return "Vous ne pouvez pas retirer un vote que vous n'avez pas fait";
			}
		}
	}

	@Override
	/**
	 * Donne pour le theme le meileur referent, c'est a dire celui qui a reçu le plus de vote
	 * @return Meilleur referent sur le theme
	 */
	public String getMeilleurReferent() {
		// TODO Auto-generated method stub
		Set listeRecommande = this.recommandation.keySet();
		Iterator iterateur = listeRecommande.iterator();
		int nbVote = 0;
		String meilleurReferent = null;
		while(iterateur.hasNext()){
			Object enCour = iterateur.next();
			if(this.recommandation.get(enCour).size() > nbVote){		
				nbVote = this.recommandation.get(enCour).size();
				meilleurReferent = (String) enCour+" ("+nbVote+" votes)";
			}
		}
		return meilleurReferent;
	}
	
}