import java.util.ArrayList;

/* Classe qui represente un scrutin */
public class Scrutin{

	// Etat du scrutin, 1 ouvert, 0 fermé
	private boolean termine;
	
	/**
	 * Pseudo du candiat
	 */
	private String pseudo ;
	
	/**
	 * Nombre de participants au scrutin
	 */
	private int nbParticipants;
	
	/**
	 * Nombre de votes pours
	 */
	private int nbPour;
	
	/**
	 * Nombre de vote contres
	 */
	private int nbContre;
	
	/**
	 * Nombre de votes blancs
	 */
	private int nbBlanc;
	
	/**
	 * Liste des utilisateurs ayant deja votes
	 */
	private ArrayList<String> listeDejaVote;

	/**
	 * Constructeur de la classe
	 * @param pseudo Pseudo du candiat
	 * @param nbParticipants Nombre de participants au scrutin
	 */
	protected Scrutin(String pseudo, int nbParticipants){
		this.termine=false;
		this.pseudo=pseudo;
		this.nbParticipants = nbParticipants;
		this.listeDejaVote = new ArrayList<String>();
		this.nbBlanc = 0;
		this.nbPour = 0;
		this.nbContre = 0;
	}
	
	/**
	 * Enregistre le vote d'un utilisateur
	 * @param votant Utilisateur qu vote
	 * @param voix Choix de l'utilisateur sur le vote (0 = contre / 1 = blanc / 2 = pour)
	 */
	public String voter(String votant, Integer voix){
		/* On regarde si l'utilisateur n'a pas deja vote pour ce scrutin */
		if(this.listeDejaVote.contains(votant)){
			return "Vous avez deja vote pour ce scrutin";
		}else{
			this.listeDejaVote.add(votant);
			switch (voix){
			case 0:
				this.nbContre++;
				break;
			case 1:
				this.nbBlanc++;
				break;
			case 2:
				this.nbPour++;
				break;
			}
			/* On regarde si la majorite des participants se sont exprimes */
			int sommeVotes = nbContre + nbPour + nbBlanc;
			if(sommeVotes > (nbParticipants/2)){
				/* La majorite des participants se sont exprimes, le vote est maintenant termine */
				this.termine=true;
			}
			return "Votre vote a bien ete pris en compte";
		}
		
	}
	
	/**
	 * Termine le scrutin et retourne le resultat
	 * @return True si le candidat est elu et false sinon
	 */
	public boolean terminerScrutin(){
		/* On va maintenant comparer les votes */
		/* On regarde si les votes POUR sont superieurs aux autres */
		if(nbPour > nbBlanc && nbPour > nbContre){
			return true;
		}else{
			return false;
		}
	}
			
	
	/**
	 * Donne l'etat d'un scrutin
	 * @return True si le scrutin est termine et False sinon
	 */
	public boolean isTermine(){
		return this.termine;
	}
	
	/**
	 * Retourne le pseudo de l'utilisateur concerne par le scrutin
	 * @return Pseudo utilisateur
	 */
	public String getPseudo(){
		return this.pseudo;
	}
	
}
