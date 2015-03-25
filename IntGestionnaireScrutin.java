import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntGestionnaireScrutin {
	/**
	 * Permet de creer un Scrutin a partir du pseudo de l'étudiant
	 * @param pseudo Pseudo de l'etudiant concerné par le scrutin
	 * @return Reference du nouveau scrutin
	 */
	public String creerScrutin(String pseudo) throws RemoteException, MalformedURLException;
}
