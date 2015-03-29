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
	 * Liste des profils des utilisateurs
	 */
	private HashMap<String,String> listeProfils;
	
	/**
	 * Construceur de la classe
	 * @throws RemoteException
	 */
	public Serveur() throws RemoteException{
		super();
		this.listeThemes = new HashMap<String,String>();
		this.listeProfils = new HashMap<String, String>();
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
	 * Retourne la reference d'un theme a partir de son libelle
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
	 * Supprime un thème
	 * @param libelle Libelle du thème que l'on veut supprimer
	 * @return Résultat de la suppression du thème
	 * @throws RemoteException
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public String delTheme(String libelle)throws RemoteException, MalformedURLException, NotBoundException{
		/* On recupère l'objet */
		if(this.listeThemes.containsKey(libelle)){
			/* On supprime l'objet du catalogue des objets remote */
			Naming.unbind(this.listeThemes.get(libelle));
			/* On supprime également le thème de la liste des thèmes */
			this.listeThemes.remove(libelle);
			return "Le theme a bien ete supprime";
		}else{
			return "Le theme que vous voulez supprimer n'existe pas";
		}
	}
	
	/**
	 * Retourne la liste des profils
	 * @return Liste des pseudos des etudiants qui ont un profil
	 */
	public String getListeProfils() throws RemoteException{
		Object tab[] =  this.listeProfils.keySet().toArray();
		String liste = "";
		int i;
		for(i=0;i<tab.length;i++){
			liste += (String) "\n"+tab[i];
		}
		return liste;
	}
	
	/**
	 * Creer le profil d'un etudiant et retourne l'url de ce profil
	 * @param pseudo Pseudo de l'etudiant
	 * @param competences Liste des competences de l'etudiant
	 * @return Url de l'objet distribue profil
	 */
	public String creerProfil(String pseudo) throws RemoteException, MalformedURLException, NotBoundException{
		if(this.listeProfils.containsKey(pseudo)){
			return "";
		}else{
			IntGestionnaireProfil gestionnaireProfil = (IntGestionnaireProfil) Naming.lookup("//localhost/gestionnaireProfil");
			String url = gestionnaireProfil.creerProfil(pseudo);
			this.listeProfils.put(pseudo, url);
			return url;
		}
	}
	
	/**
	 * Retourne l'url de l'objet remote profil associé au pseudo utilisateur en paramètre
	 * @param pseudo Pseudo de l'utilisateur duquel on veut accèder au profil
	 * @return
	 * @throws RemoteException
	 */
	public String getProfil(String pseudo) throws RemoteException{
		if(this.listeProfils.containsKey(pseudo)){
			return this.listeProfils.get(pseudo);
		}else{
			return ""; 
		}
	}
	
	/**
	 * Permet de faire une demande de création d'un scrutin au gestionnaire de scrutin
	 * @param pseudo Pseudo du candidat
	 * @param time Durée du scrutin
	 * @return Résultat de la création du scrutin
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws NotBoundException
	 */
	public String creerScrutin(String pseudo, int time) throws RemoteException, MalformedURLException, NotBoundException{
		IntGestionnaireScrutin gestionnaireScrutin = (IntGestionnaireScrutin) Naming.lookup("//localhost/gestionnaireScrutin");
		IntServeurNotification serveurNotif = (IntServeurNotification) Naming.lookup("//localhost/notification");
		String resultat  = gestionnaireScrutin.creerScrutin(pseudo, time,this.getNbUtilisateurs());
		if(resultat.equals("ok")){
			Object tab[] =  this.listeProfils.keySet().toArray();
			int i;
			/* On notifie les utilisateurs du début du scrutin */
			for(i=0;i<tab.length;i++){
				serveurNotif.creerNotification(pseudo, (String) tab[i], "", "vote");
				serveurNotif.notifier((String) tab[i]);
			}
			return "Votre scrutin a bien ete cree";
		}else{
			return resultat;
		}
	}
	
	/**
	 * Permet à un utilisateur d'enregistrer un vote à un scrutin
	 * @param pseudoCandidat Nom du candidat => identifiant pour le scrutin
	 * @param pseudoVotant Nom de l'utilisateur qui vote
	 * @param voix Vote de l'utiliateur
	 * @return Résultat de la demande d'enregistrement vote
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws NotBoundException
	 * @throws InterruptedException
	 */
	public String voter(String pseudoCandidat,String pseudoVotant,int voix) throws RemoteException, MalformedURLException, NotBoundException, InterruptedException{
		IntGestionnaireScrutin gestionnaireScrutin = (IntGestionnaireScrutin) Naming.lookup("//localhost/gestionnaireScrutin");
		String retour = gestionnaireScrutin.voter(pseudoCandidat, pseudoVotant, voix);
		if(retour.equals("fin")){
			/* On lance la cloture du scrutin */
			this.terminerScrutin(pseudoCandidat);
			return "";
		}else{
			return retour;
		}
	}
	
	/**
	 * Permet de fermer un scrutin et de notifier les utilisateur du résultat
	 * @param pseudoCandidat Identifiant du scrutin
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws NotBoundException
	 */
	public void terminerScrutin(String pseudoCandidat) throws RemoteException, MalformedURLException, NotBoundException{
		IntGestionnaireScrutin gestionnaireScrutin = (IntGestionnaireScrutin) Naming.lookup("//localhost/gestionnaireScrutin");
		boolean resultat = gestionnaireScrutin.terminerScrutin(pseudoCandidat);
		String type = null;
		if(resultat){
			/* candidat élu on notifie qu'il a gagné */
			type = "v";
			IntProfil profilCandidat = (IntProfil) Naming.lookup("//localhost/"+pseudoCandidat+"-profil");
			profilCandidat.setModerateur();
		}else{
			/* on notifie qu'il a perdu */
			type = "d";
		}
		/* On notifie les utilisateurs du résultat */
		IntServeurNotification serveurNotif = (IntServeurNotification) Naming.lookup("//localhost/notification");
		Object tab[] =  this.listeProfils.keySet().toArray();
		int i;
		for(i=0;i<tab.length;i++){
			serveurNotif.creerNotification(pseudoCandidat, (String) tab[i], "", type);
			serveurNotif.notifier((String) tab[i]);
		}
	}
	
	/**
	 * Retourne le nombre d'utilisateurs dans le système
	 * @return Nombre d'utilsateur connus dans le système
	 * @throws RemoteException
	 */
	public int getNbUtilisateurs() throws RemoteException {
		return this.listeProfils.size();
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
		//System.out.println("Le serveur a ete cree");
		
		/* On met des donnees en memoire pour faire des tests */
	}

	
	
}
