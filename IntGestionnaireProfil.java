import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface IntGestionnaireProfil extends Remote {
	
	 public String creerProfil(String pseudo, ArrayList<String> competences) throws RemoteException, MalformedURLException;
}
