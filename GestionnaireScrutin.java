import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Classe qui permet de creer les scrutins
 *
 */
public class GestionnaireScrutin extends UnicastRemoteObject implements IntGestionnaireScrutin {

	/**
	 * Auto
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur de la classe
	 * @throws RemoteException
	 */
	protected GestionnaireScrutin() throws RemoteException {
		super();
	}
	
	/**
	 * Permet de creer un Scrutin a partir du pseudo de l'étudiant
	 * @param pseudo Pseudo de l'etudiant concerné par le scrutin
	 * @return Reference du nouveau scrutin
	 */
	public String creerScrutin(String pseudo,Integer time) throws RemoteException, MalformedURLException{
		Scrutin scrutin = new Scrutin(pseudo);
		try{
			LocateRegistry.createRegistry(1099);
		}
		catch(RemoteException e){
			LocateRegistry.getRegistry(1099);
		}
		Naming.rebind(pseudo, scrutin);
		String url =  "//localhost/scrutin/"+pseudo;
		ThreadScrutin t1 = new ThreadScrutin(time,pseudo);
		t1.run();
		return url;
	} 
	
	/**
	 * Fonction principale du gestionnaire de scrutins
	 * @param args
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public static void main(String args[]) throws RemoteException, MalformedURLException{
		try{
			LocateRegistry.createRegistry(1099);
		}
		catch(RemoteException e){
			LocateRegistry.getRegistry(1099);
		}
		GestionnaireScrutin gestionnaire = new GestionnaireScrutin();
		Naming.rebind("gestionnaire", gestionnaire);
	}

}
