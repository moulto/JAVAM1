import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/* La classe profil contient toutes les informations propres a un utilisateur */
public interface IntProfil extends Remote{
	
	/**
	 * Accesseur sur le pseudo, c'est a dire l'identifiant du profil
	 * @return Pseudo de l'utilisateur
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public String getPseudo() throws RemoteException, MalformedURLException;
	
	/**
	 * Retoure la lite des competences d'un utilisateur
	 * @return ArrayList qui contient les competences d'un utilisateur
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public ArrayList<String> getCompetences() throws RemoteException, MalformedURLException;
	
	/**
	 * Permet de donner le role moderateur a un utilisateur
	 */
	public void setModerateur() throws RemoteException, MalformedURLException;
	
	/**
	 * Permet de savoir si un utilisateur possede le role de moderateur
	 * @return True si l'utilisateur est moderateur et False sinon
	 * @throws RemoteException
	 */
	public boolean isModerateur() throws RemoteException;
	
	/**
	 * Permet a un utilisateur de renseigner ses competences
	 * @param competence Competence dans lequel l'utilisateur pretend etre competent
	 * @return Resultat de l'ajout de la nouvelle competence
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public String addCompetences(String competence) throws RemoteException, MalformedURLException;
}
