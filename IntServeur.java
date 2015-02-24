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
	 * @return Si le theme est creer on retourne sa reference sinon on retourne rien ""
	 * 
	 */
	public String creerTheme(String libelle) throws MalformedURLException, RemoteException, NotBoundException;
	
	/**
	 * Rtourne la reference d'un theme a partir de son libelle  
	 * @return Reference du theme si celui-ci existe et "" sinon
	 */
	public String getTheme(String libelle) throws RemoteException;
	
	/**
	 * Renvoi la liste de tous les themes connus par le serveur
	 * @return Liste des themes
	 * @throws RemoteException
	 */
	public String getListeThemes() throws RemoteException;
}
