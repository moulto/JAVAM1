import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class Scrutin extends UnicastRemoteObject implements IntScrutin{

	protected Scrutin(String pseudo) throws RemoteException {
		super();
		this.Etat=1;
		this.pseudo=pseudo;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Etat du scrutin, 1 ouvert, 0 fermé
	private Integer Etat;
	
	private String pseudo ;
	
	private HashMap<String , Integer > listeVoix;
	
	public void voter(String pseudo, String votant, Integer voix) throws RemoteException, MalformedURLException
	{
		this.listeVoix.put(votant, voix);
	}
	
	public void terminerScrutin() throws RemoteException, MalformedURLException
	{
		this.Etat=0;
		try {
			IntProfil profil = (IntProfil) Naming.lookup("//localhost/"+this.pseudo+"-profil");
			profil.setModerateur();
			
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
}
