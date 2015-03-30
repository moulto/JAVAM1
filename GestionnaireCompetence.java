import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/* Serveur qui gere toute la partie competences des utilisateurs */
public class GestionnaireCompetence extends UnicastRemoteObject implements IntGestionnaireCompetence {
	
	private static final long serialVersionUID = 1L;
	
	// Liste des referents potentiels
	private HashMap<String, ArrayList<String>> RefPotentiel;
	
	/**
	 * Constructeur de la classe
	 * @throws RemoteException
	 */
	public GestionnaireCompetence() throws RemoteException
	{
		super();
		this.RefPotentiel = new HashMap <String , ArrayList<String> >();
		
	}
	
	/**
	 * Donne la liste de tous les utilisateurs qui sont identifies comme referents potentiels sur un theme
	 * @param Competence Theme/competence sur lequel l'utilisateur pourrait etre referent
	 * @return Liste des utilisateurs susceptibles d'etre referent sur le theme donne
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public String ListeRefPotentiel(String Competence)throws RemoteException, MalformedURLException
	{
		
		Object tab[] =  this.RefPotentiel.get(Competence).toArray();
		String liste = "";
		int i;
		for(i=0;i<tab.length;i++){
			liste += (String) "\n"+tab[i];
		}
		return liste;
	}

	/**
	 * Ajoute un referent potentiel a la liste
	 * @param pseudo Pseudo de l'utilisateur susceptible d'etre referent
	 * @param competences Liste des competences sur lesquelles l'utilisateur dit etre competent
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public void addReferentPotentiel(String pseudo, ArrayList<String> competences)
	{
		for(String comp : competences)
		{
			// Si la competence n'existe pas on la cree et on ajoute le profil
			if (!RefPotentiel.containsKey(comp))
					{
						ArrayList<String> Referents = new ArrayList<String>();
						Referents.add(pseudo);
						this.RefPotentiel.put(comp, Referents);
					}
			// Si la competence existe on ajoute juste le profil
			else
			{
				RefPotentiel.get(comp).add(pseudo);
			}
		}
	}
	
	/* Main de la classe qui permet de l'enregistrer dans le catalogue des objets remotes */
	public static void main(String args[]) throws RemoteException, MalformedURLException{
		try{
			LocateRegistry.createRegistry(1099);
		}
		catch(RemoteException e){
			LocateRegistry.getRegistry(1099);
		}
		GestionnaireCompetence gestionnaireComp = new GestionnaireCompetence();
		Naming.rebind("gestionnaireComp", gestionnaireComp);
	}
}
