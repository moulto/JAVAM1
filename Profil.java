import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/* La classe profil contient toutes les informations propres a un utilisateur */
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
	 * Accesseur sur le pseudo, c'est a dire l'identifiant du profil
	 * @return Pseudo de l'utilisateur
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public String getPseudo()  throws RemoteException{
		// TODO Auto-generated method stub
		return this.pseudo;
	}

	/**
	 * Permet de donner le role moderateur a un utilisateur
	 */
	public void setModerateur() throws RemoteException{
		this.moderateur=true;
	}
	

	/**
	 * Retoure la lite des competences d'un utilisateur
	 * @return ArrayList qui contient les competences d'un utilisateur
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public ArrayList<String> getCompetences() throws RemoteException{
		// TODO Auto-generated method stub
		return this.competences;
	}


	/**
	 * Permet de savoir si un utilisateur possede le role de moderateur
	 * @return True si l'utilisateur est moderateur et false sinon
	 * @throws RemoteException
	 */
	public boolean isModerateur() throws RemoteException {
		// TODO Auto-generated method stub
		return this.moderateur;
	}
	
	/**
	 * Permet a un utilisateur de renseigner ses competences
	 * @param competence Competence dans lequel l'utilisateur pretend etre competent
	 * @return Resultat de l'ajout de la nouvelle competence
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public String addCompetences(String competence) throws RemoteException, MalformedURLException{
		if(this.competences.contains(competence)){
			return "Vous avez deja renseigne cette competence";
		}else{
			this.competences.add(competence);
			return "La competence a bien ete ajoutee";
		}
	}
	
}
