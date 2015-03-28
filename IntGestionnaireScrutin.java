import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/* Serveur qui assure la gestion des scrutins pour le poste de modérateur */
public interface IntGestionnaireScrutin extends Remote{
	
	/**
	 * Créer le thread de durée pour un scrutin
	 * @param pseudoCandidat Nom du candidat au poste de modérateur
	 * @param time Durée du scrutin en secondes
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public void creerThreadScrutin(String pseudoCandidat,int time)throws RemoteException, MalformedURLException;
	
	/**
	 * Arrête un thread de durée car le scrutin est terminé
	 * @param pseudoCandidat Nom du candidat au poste de modérateur
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws InterruptedException
	 */
	public void stopThreadScrutin(String pseudoCandidat) throws RemoteException, MalformedURLException, InterruptedException;
	
	/**
	 * Permet de créer un scrutin
	 * @param pseudo Nom du candidat
	 * @param time Durée du scrutin en secondes
	 * @param nbParticipants Nombre total d'utilisateur et donc de participants à ce scrutin
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
	 * @return Résultat de l'enregistrement du vote
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws InterruptedException
	 */
	public String voter(String pseudoCandidat,String pseudoVotant,int voix)throws RemoteException, MalformedURLException, InterruptedException;
	
	/**
	 * Permet de cloturer un scrutin et de calculer le résultat
	 * @param pseudoCandidat Nom du candidat au poste de moderateur
	 * @return True si le candidat est élu et False sinon
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public boolean terminerScrutin(String pseudoCandidat)throws RemoteException, MalformedURLException;
}
