import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/* La classe profil contient toutes les informations propres à un utilisateur */
public class Profil extends UnicastRemoteObject implements IntProfil {

	/**
	 * Auto
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Permet de savoir si il s'agit d'un moderateur
	 */
	private Boolean moderateur;
	
	/**
	 * Pseudo de l'etudiant auquel le profil appartient
	 */
	private String pseudo;
	
	/**
	 * Competences de l'etudiant
	 */
	private ArrayList<String> competences;

	/**
	 * Constructeur de la classe
	 * @param pseudo
	 * @throws RemoteException
	 */
	public Profil(String pseudo) throws RemoteException{
		super();
		this.pseudo = pseudo;
		this.competences = new ArrayList<String>();
		this.moderateur = false;
	}


	/**
	 * Accesseur sur le pseudo, c'est à dire l'identifiant du profil
	 * @return Pseudo de l'utilisateur
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public String getPseudo()  throws RemoteException{
		// TODO Auto-generated method stub
		return this.pseudo;
	}

	/**
	 * Permet de donner le role modérateur à un utilisateur
	 */
	public void setModerateur() throws RemoteException{
		this.moderateur=true;
	}
	

	/**
	 * Retoure la lite des compétences d'un utilisateur
	 * @return ArrayList qui contient les compétences d'un utilisateur
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public ArrayList<String> getCompetences() throws RemoteException{
		// TODO Auto-generated method stub
		return this.competences;
	}


	/**
	 * Permet de savoir si un utilisateur possède le role de modérateur
	 * @return Ture si l'utilisateur est modérateur et false sinon
	 * @throws RemoteException
	 */
	public boolean isModerateur() throws RemoteException {
		// TODO Auto-generated method stub
		return this.moderateur;
	}
	
	/**
	 * Permet à un utilisateur de renseigner ses compétences
	 * @param competence Compétence dans lequel l'utilisateur prétend être compétent
	 * @return Résultat de l'ajout de la nouvelle compétence
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public String addCompetences(String competence) throws RemoteException, MalformedURLException{
		if(this.competences.contains(competence)){
			return "Vous avez deja renseigne cette competence";
		}else{
			this.competences.add(competence);
			return "La competence  a bien ete ajoutee";
		}
	}
	
}
