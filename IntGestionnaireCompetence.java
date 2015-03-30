import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/* Serveur qui gere toute la partie competences des utilisateurs */
public interface IntGestionnaireCompetence extends Remote {

	/**
	 * Donne la liste de tous les utilisateurs qui sont identifies comme referents potentiels sur un theme
	 * @param Competence Theme/competence sur lequel l'utilisateur pourrait etre referent
	 * @return Liste des utilisateurs susceptibles d'etre referent sur le theme donne
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public String ListeRefPotentiel(String Competence) throws RemoteException, MalformedURLException;
	
	/**
	 * Ajoute un referent potentiel a la liste
	 * @param pseudo Pseudo de l'utilisateur susceptible d'etre referent
	 * @param competences Liste des competences sur lesquelles l'utilisateur dit etre competent
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public void addReferentPotentiel(String pseudo, ArrayList<String> competences) throws RemoteException, MalformedURLException;
}
