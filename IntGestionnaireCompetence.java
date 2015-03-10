import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IntGestionnaireCompetence extends Remote {

	public String ListeRefPotentiel(String Competence) throws RemoteException, MalformedURLException;
	public void addReferentPotentiel(String pseudo, ArrayList<String> competences) throws RemoteException, MalformedURLException;
}
