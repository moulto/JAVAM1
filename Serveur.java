import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 * Classe qui represente le serveur, c'est a dire l'object avec lequel l'utilisateur va echanger
 *
 */
public class Serveur extends UnicastRemoteObject implements IntServeur{

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Liste des themes avec leur reference
	 */
	private HashMap<String,String> listeThemes;
	
	/**
	 * Construceur de la classe
	 * @throws RemoteException
	 */
	public Serveur() throws RemoteException{
		super();
		this.listeThemes = new HashMap<String,String>();
	}
	
	/**
	 * Permet de creer un theme
	 * @param libelle Nom du theme que l'on veut creer
	 * @return Si le theme est creer on retourne sa reference sinon on retourne rien ""
	 * 
	 */
	public String creerTheme(String libelle) throws MalformedURLException, RemoteException, NotBoundException{
		if(this.listeThemes.containsKey(libelle)){
			return "";
		}else{
			IntGestionnaireTheme gestionnaire = (IntGestionnaireTheme) Naming.lookup("//localhost/gestionnaire");
			String url = gestionnaire.creerTheme(libelle);
			this.listeThemes.put(libelle, url);
			return url;
		}
		
	}

	@Override
	/**
	 * Rtourne la reference d'un theme a partir de son libelle
	 * @return Reference du theme si celui-ci existe et "" sinon
	 */
	public String getTheme(String libelle) {
		if(this.listeThemes.containsKey(libelle)){
			return this.listeThemes.get(libelle);
		}else{
			return "";
		}
	}
	
	
	/**
	 * Renvoi la liste de tous les themes connus par le serveur 
	 * @return Liste des themes
	 * @throws RemoteException
	 */
	public String getListeThemes() throws RemoteException{
		Object tab[] =  this.listeThemes.keySet().toArray();
		String liste = "";
		int i;
		for(i=0;i<tab.length;i++){
			liste += (String) "\n"+tab[i];
		}
		return liste;
	}
	
	/**
	 * Fonction principale du serveur
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
		Serveur serveur = new Serveur();
		Naming.rebind("serveur", serveur);
		System.out.println("Le serveur a ete cree");
		
		/* On met des données en mémoire pour faire des tests */
	}
	
}
