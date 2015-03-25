import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class Profil extends UnicastRemoteObject implements IntProfil {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean moderateur;
	/**
	 * Pseudo de l'etudiant auquel le profil appartient
	 */
	private String pseudo;
	
	/**
	 * Competences de l'etudiant
	 */
	private ArrayList<String> competences;

	
	public Profil(String pseudo) throws RemoteException{
		super();
		this.pseudo = pseudo;
		this.competences = new ArrayList<String>();
		this.moderateur = false;
	}


	@Override
	public String getPseudo()  throws RemoteException{
		// TODO Auto-generated method stub
		return this.pseudo;
	}

	public void setModerateur() throws RemoteException{
		this.moderateur=true;
	}
	

	@Override
	public ArrayList<String> getCompetences() throws RemoteException{
		// TODO Auto-generated method stub
		return this.competences;
	}


	@Override
	public boolean isModerateur() throws RemoteException {
		// TODO Auto-generated method stub
		return this.moderateur;
	}
	
	public String addCompetences(String competence) throws RemoteException, MalformedURLException{
		if(this.competences.contains(competence)){
			return "";
		}else{
			this.competences.add(competence);
			return "La competence  a bien ete ajoutee";
		}
	}
	
}
