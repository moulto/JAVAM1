import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Classe qui représente un thème
 * @author l'Ordre
 *
 */
public class Theme extends UnicastRemoteObject implements IntTheme
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Libelle du theme
	 */
	private String libelle;
	
	
	/**
	 * Constructeur de la classe Theme
	 * @param libelle Libelle du theme que l'on veut creer
	 * @throws RemoteException 
	 */
	public Theme(String libelle) throws RemoteException
	{
		super();
		this.libelle=libelle;
	}

	@Override
	public String getLiblle() {
		// TODO Auto-generated method stub
		return this.libelle;
	}

	@Override
	public ArrayList<String> getListeReferents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addReferent(String recommandant, String recommande) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delReferent(String recommandant, String recommande) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getMeilleurReferent() {
		// TODO Auto-generated method stub
		return null;
	}
	
}