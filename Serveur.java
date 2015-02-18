import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.HashMap;


public class Serveur extends UnicastRemoteObject implements IntServeur{

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> listeThemes;
	
	
	public Serveur() throws RemoteException{
		super();
		this.listeThemes = new ArrayList<String>();
	}
	
	public int creerTheme(String libelle){
		/* Test si le theme existe dejà */
		try {
			Theme theme = new Theme(libelle);
			this.listeThemes.add(theme.getLiblle());
			return 0;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}
	}

	@Override
	public int themeExiste(String libelle) {
		if(this.listeThemes.contains(libelle)){
			return 0;
		}else{
			return 1;
		}
	}
	
	
}
