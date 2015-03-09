import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class Profil extends UnicastRemoteObject implements IntProfil {

	protected Profil() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Pseudo de l'etudiant auquel le profil appartient
	 */
	private String pseudo;
	
	/**
	 * Competences de l'etudiant
	 */
	private ArrayList<String> competences;

	
	public Profil(String pseudo, ArrayList<String> competences) throws RemoteException{
		super();
		this.pseudo = pseudo;
		this.competences = competences;
	}


	@Override
	public String getPseudo() {
		// TODO Auto-generated method stub
		return this.pseudo;
	}


	@Override
	public ArrayList<String> getCompetences() {
		// TODO Auto-generated method stub
		return this.competences;
	}
	
	
}
