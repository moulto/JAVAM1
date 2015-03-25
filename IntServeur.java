import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
	
	
	public String getListeProfils() throws RemoteException;
	
	
	public String creerProfil(String pseudo) throws RemoteException, MalformedURLException, NotBoundException;
	
	public String getProfil(String pseudo) throws RemoteException;
	
	public String creerScrutin(String pseudo,int time) throws RemoteException, MalformedURLException, NotBoundException;
	
	public int getNbUtilisateurs() throws RemoteException;
}
