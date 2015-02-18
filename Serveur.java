import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.HashMap;


public class Serveur extends UnicastRemoteObject implements IntServeur{

	protected Serveur() throws RemoteException {
		super();
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Etudiant> listeEtudiant;
	private ArrayList<Recommandation> listeRecommandation;
	private ArrayList<Theme> listeTheme;
	
	public static void main ()
	{
		
		try {
			LocateRegistry.createRegistry(1099);
			Serveur Serveur = new Serveur();
			Naming.rebind("Server", Serveur);
			System.out.println("Serveur cree");
		} 
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
