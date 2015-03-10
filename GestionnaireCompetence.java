import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;



public class GestionnaireCompetence extends UnicastRemoteObject implements IntGestionnaireCompetence {
	
	private static final long serialVersionUID = 1L;
	
	// Liste des referents potentiels
	private HashMap<String , ArrayList<Profil> > RefPotentiel;
	
	public GestionnaireCompetence() throws RemoteException
	{
		super();
		this.RefPotentiel = new HashMap <String , ArrayList<Profil> >();
		
	}
	
	public String ListeRefPotentiel(String Competence)
	{
		
		Object tab[] =  this.RefPotentiel.get(Competence).toArray();
		String liste = "";
		int i;
		for(i=0;i<tab.length;i++){
			liste += (String) "\n"+tab[i];
		}
		return liste;
	}
	
	public void addReferentPotentiel(String pseudo, ArrayList<String> competences)
	{
		// a modifier
		Profil p = null;
		for(String comp : competences)
		{
			// Si la compétence n'existe pas on la crée et on ajoute le profil
			if (!RefPotentiel.containsKey(comp))
					{
						ArrayList<Profil> Referents = null;
						Referents.add(p);
						this.RefPotentiel.put(comp, Referents);
					}
			// Si la compétence existe on ajoute juste le profil
			else
			{
				RefPotentiel.get(comp).add(p);
			}
		}
	}
}
