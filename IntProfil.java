import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/* La classe profil contient toutes les informations propres à un utilisateur */
public interface IntProfil extends Remote{
	
	/**
	 * Accesseur sur le pseudo, c'est à dire l'identifiant du profil
	 * @return Pseudo de l'utilisateur
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public String getPseudo() throws RemoteException, MalformedURLException;
	
	/**
	 * Retoure la lite des compétences d'un utilisateur
	 * @return ArrayList qui contient les compétences d'un utilisateur
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public ArrayList<String> getCompetences() throws RemoteException, MalformedURLException;
	
	/**
	 * Permet de donner le role modérateur à un utilisateur
	 */
	public void setModerateur() throws RemoteException, MalformedURLException;
	
	/**
	 * Permet de savoir si un utilisateur possède le role de modérateur
	 * @return Ture si l'utilisateur est modérateur et false sinon
	 * @throws RemoteException
	 */
	public boolean isModerateur() throws RemoteException;
	
	/**
	 * Permet à un utilisateur de renseigner ses compétences
	 * @param competence Compétence dans lequel l'utilisateur prétend être compétent
	 * @return Résultat de l'ajout de la nouvelle compétence
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public String addCompetences(String competence) throws RemoteException, MalformedURLException;
}
