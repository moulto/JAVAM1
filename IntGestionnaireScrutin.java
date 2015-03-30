import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/* Serveur qui assure la gestion des scrutins pour le poste de moderateur */
public interface IntGestionnaireScrutin extends Remote{
	
	/**
	 * Creer le thread de duree pour un scrutin
	 * @param pseudoCandidat Nom du candidat au poste de moderateur
	 * @param time Duree du scrutin en secondes
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public void creerThreadScrutin(String pseudoCandidat,int time)throws RemoteException, MalformedURLException;
	
	/**
	 * Arrete un thread de duree car le scrutin est termine
	 * @param pseudoCandidat Nom du candidat au poste de moderateur
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws InterruptedException
	 */
	public void stopThreadScrutin(String pseudoCandidat) throws RemoteException, MalformedURLException, InterruptedException;
	
	/**
	 * Permet de creer un scrutin
	 * @param pseudo Nom du candidat
	 * @param time Duree du scrutin en secondes
	 * @param nbParticipants Nombre total d'utilisateur et donc de participants a ce scrutin
	 * @return
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public String creerScrutin(String pseudo,Integer time, int nbParticipants) throws RemoteException, MalformedURLException;
	
	/**
	 * Permet d'enregistrer le vote d'un utilisateur sur un scrutin
	 * @param pseudoCandidat Nom du candidat => permet l'identification du scrutin
	 * @param pseudoVotant Nom de l'utilisateur qui vote
	 * @param voix Vote de l'utilisateur (pour,contre ou blanc)
	 * @return Resultat de l'enregistrement du vote
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws InterruptedException
	 */
	public String voter(String pseudoCandidat,String pseudoVotant,int voix)throws RemoteException, MalformedURLException, InterruptedException;
	
	/**
	 * Permet de cloturer un scrutin et de calculer le resultat
	 * @param pseudoCandidat Nom du candidat au poste de moderateur
	 * @return True si le candidat est elu et False sinon
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public boolean terminerScrutin(String pseudoCandidat)throws RemoteException, MalformedURLException;
}
