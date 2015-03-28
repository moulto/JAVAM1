import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/* Serveur qui gère toute  la parte comptences des utilisateurs */
public interface IntGestionnaireCompetence extends Remote {

	/**
	 * Donne la liste de tous les utilisateurs qui sont identifiés comme référents potentiels sur un thème
	 * @param Competence Thème/ompétence sur lequel l'utilisateur pourrait être référent
	 * @return Liste des utilisateurs sucepibles d'être réfrent sur le thème donné
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public String ListeRefPotentiel(String Competence) throws RemoteException, MalformedURLException;
	
	/**
	 * Ajoute un référent potentiel à la liste
	 * @param pseudo Pseudo de l'utilisateur suceptible d'être référent
	 * @param competences Liste des compétences sur lesquelles l'utilisateur dit être compétent
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public void addReferentPotentiel(String pseudo, ArrayList<String> competences) throws RemoteException, MalformedURLException;
}
