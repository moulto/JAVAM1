import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface de serveur.
 * Le serveur va contenir l'ensemble des donnees du systeme
 * @author PrietoF, PrietoC, Watre
 *
 */
public interface IntServeur extends Remote{
	
	/* 0 = ok
	 * 1 = ko
	 */
	
	/**
	 * Permet de creer un theme
	 * @param libelle Nom du theme que l'on veut creer
	 * @return Si le theme est cree on retourne sa reference sinon on retourne rien ""
	 * 
	 */
	public String creerTheme(String libelle) throws MalformedURLException, RemoteException, NotBoundException;
	
	/**
	 * Retourne la reference d'un theme a partir de son libelle  
	 * @return Reference du theme si celui-ci existe et "" sinon
	 */
	public String getTheme(String libelle) throws RemoteException;
	
	/**
	 * Renvoi la liste de tous les themes connus par le serveur
	 * @return Liste des themes
	 * @throws RemoteException
	 */
	public String getListeThemes() throws RemoteException;
	
	/**
	 * Supprime un theme
	 * @param libelle Libelle du theme que l'on veut supprimer
	 * @return Resultat de la suppression du theme
	 * @throws RemoteException
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public String delTheme(String libelle)throws RemoteException, MalformedURLException, NotBoundException;
	
	/**
	 * Retourne la liste des profils
	 * @return Liste des pseudos des etudiants qui ont un profil
	 */
	public String getListeProfils() throws RemoteException;
	
	/**
	 * Creer le profil d'un etudiant et retourne l'url de ce profil
	 * @param pseudo Pseudo de l'etudiant
	 * @param competences Liste des competences de l'etudiant
	 * @return Url de l'objet distribue profil
	 */
	public String creerProfil(String pseudo) throws RemoteException, MalformedURLException, NotBoundException;
	
	/**
	 * Retourne l'url de l'objet remote profil associe au pseudo utilisateur en parametre
	 * @param pseudo Pseudo de l'utilisateur duquel on veut acceder au profil
	 * @return
	 * @throws RemoteException
	 */
	public String getProfil(String pseudo) throws RemoteException;
	
	/**
	 * Permet de faire une demande de creation d'un scrutin au gestionnaire de scrutin
	 * @param pseudo Pseudo du candidat
	 * @param time Duree du scrutin
	 * @return Resultat de la creation du scrutin
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws NotBoundException
	 */
	public String creerScrutin(String pseudo,int time) throws RemoteException, MalformedURLException, NotBoundException;
	
	/**
	 * Permet a un utilisateur d'enregistrer un vote a un scrutin
	 * @param pseudoCandidat Nom du candidat => identifiant pour le scrutin
	 * @param pseudoVotant Nom de l'utilisateur qui vote
	 * @param voix Vote de l'utiliateur
	 * @return Resultat de la demande d'enregistrement vote
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws NotBoundException
	 * @throws InterruptedException
	 */
	public String voter(String pseudoCandidat,String pseudoVotant,int voix) throws RemoteException, MalformedURLException, NotBoundException, InterruptedException;
	
	/**
	 * Permet de fermer un scrutin et de notifier les utilisateur du resultat
	 * @param pseudoCandidat Identifiant du scrutin
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws NotBoundException
	 */
	public void terminerScrutin(String pseudoCandidat) throws RemoteException, MalformedURLException, NotBoundException;
	
	/**
	 * Retourne le nombre d'utilisateurs dans le systeme
	 * @return Nombre d'utilisateur connus dans le systeme
	 * @throws RemoteException
	 */
	public int getNbUtilisateurs() throws RemoteException;
}
