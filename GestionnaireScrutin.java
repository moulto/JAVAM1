import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/* Serveur qui assure la gestion des scrutins pour le poste de modérateur */
public class GestionnaireScrutin extends UnicastRemoteObject implements IntGestionnaireScrutin {

	/**
	 * Auto
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * HashMap qui associe à chaques utilisateur son scrutin
	 */
	private HashMap<String,Scrutin> listeScrutins;
	
	/**
	 * Liste des thread de scrutin
	 */
	private HashMap<String,Thread> listeThreads;
	
	/**
	 * Constructeur de la classe
	 * @throws RemoteException
	 */
	protected GestionnaireScrutin() throws RemoteException {
		super();
		this.listeScrutins = new HashMap<String, Scrutin>();
		this.listeThreads = new HashMap<String, Thread>();
	}
	
	/**
	 * Créer le thread de durée pour un scrutin
	 * @param pseudoCandidat Nom du candidat au poste de modérateur
	 * @param time Durée du scrutin en secondes
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public void creerThreadScrutin(String pseudoCandidat,int time)throws RemoteException, MalformedURLException{
		ThreadScrutin t1 = new ThreadScrutin(time,pseudoCandidat);
		Thread thread = new Thread(t1);
		thread.start();
		this.listeThreads.put(pseudoCandidat, thread);
	}
	
	/**
	 * Arrête un thread de durée car le scrutin est terminé
	 * @param pseudoCandidat Nom du candidat au poste de modérateur
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws InterruptedException
	 */
	public void stopThreadScrutin(String pseudoCandidat)throws RemoteException, MalformedURLException, InterruptedException{
		Thread th = this.listeThreads.get(pseudoCandidat);
		th.interrupt();
	}
	
	/**
	 * Permet de créer un scrutin
	 * @param pseudo Nom du candidat
	 * @param time Durée du scrutin en secondes
	 * @param nbParticipants Nombre total d'utilisateur et donc de participants à ce scrutin
	 * @return
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public String creerScrutin(String pseudo,Integer time, int nbParticipants) throws RemoteException, MalformedURLException{
		Scrutin scrutin = new Scrutin(pseudo,nbParticipants);
		/* On check si un scrutin pour cet utilisateur n'existe pas deja */
		if(this.listeScrutins.containsKey(pseudo)){
			return "Vous avez deja un scrutin en cuours";
		}else{
			this.listeScrutins.put(pseudo, scrutin);
			this.creerThreadScrutin(pseudo, time);
			/* si c'est ok on retourne ok */
			return "ok";
		}
	}
	
	/**
	 * Permet d'enregistrer le vote d'un utilisateur sur un scrutin
	 * @param pseudoCandidat Nom du candidat => permet l'identification du scrutin
	 * @param pseudoVotant Nom de l'utilisateur qui vote
	 * @param voix Vote de l'utilisateur (pour,contre ou blanc)
	 * @return Résultat de l'enregistrement du vote
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws InterruptedException
	 */
	public String voter(String pseudoCandidat,String pseudoVotant,int voix)throws RemoteException, MalformedURLException, InterruptedException{
		/* On regarde qu'il y a bien un scrutin en cours pour ce candidat */
		if(!listeScrutins.containsKey(pseudoCandidat)){
			return "Le scrutin pour lequel vous voulez voter n'existe pas ou est termine";
		}else{
			String message = this.listeScrutins.get(pseudoCandidat).voter(pseudoVotant, voix);
			/* On regarde si le message indique que le vote est fini */
			if(this.listeScrutins.get(pseudoCandidat).isTermine()){
				this.stopThreadScrutin(pseudoCandidat);
				return "fin";
			}else{
				return message;
			}
		}
	}
	
	/**
	 * Permet de cloturer un scrutin et de calculer le résultat
	 * @param pseudoCandidat Nom du candidat au poste de moderateur
	 * @return True si le candidat est élu et False sinon
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public boolean terminerScrutin(String pseudoCandidat)throws RemoteException, MalformedURLException{
		boolean resultat = this.listeScrutins.get(pseudoCandidat).terminerScrutin();
		this.listeScrutins.remove(pseudoCandidat);
		return resultat;
	}
	
	/**
	 * Fonction principale du gestionnaire de scrutins
	 * @param args
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public static void main(String args[]) throws RemoteException, MalformedURLException{
		try{
			LocateRegistry.createRegistry(1099);
		}
		catch(RemoteException e){
			LocateRegistry.getRegistry(1099);
		}
		GestionnaireScrutin gestionnaire = new GestionnaireScrutin();
		Naming.rebind("gestionnaireScrutin", gestionnaire);
	}


}
