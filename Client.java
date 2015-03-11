import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Client extends UnicastRemoteObject implements IntClient{
	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Client() throws RemoteException {
		super();
	}

	public static void main(String args[]) throws RemoteException, MalformedURLException
	{
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Bienvenue dans l'application de recommandation");
		System.out.println("Veuillez taper votre pseudo ");
		
		String pseudo = sc.nextLine();
		
		Client client = new Client();
		try{
			LocateRegistry.createRegistry(1099);
		}
		catch(RemoteException e){
			LocateRegistry.getRegistry(1099);
		}
		Naming.rebind(pseudo, client);
		
		
		try {
			Integer choix;
			do{
				IntServeur serveur = (IntServeur) Naming.lookup("//localhost/serveur");
				IntGestionnaireProfil gestionnaireProfil = (IntGestionnaireProfil) Naming.lookup("//localhost/gestionnaireProfil");
				IntGestionnaireCompetence gestionnaireComp = (IntGestionnaireCompetence) Naming.lookup("//localhost/gestionnaireComp");
				IntServeurNotification serveurNotification = (IntServeurNotification) Naming.lookup("//localhost/notification");
				int nbnotifs = serveurNotification.getNombreNotification(pseudo);
				
				IntTheme ServeurTheme = null;
				String url = "";
				System.out.println("\nVous etes bien connectes sur le serveur");
				System.out.println("Tapez le numero de l'action a realiser");
				System.out.println("1 - Devenir referent");
				System.out.println("2 - Recommander un etudiant");
				System.out.println("3 - Supprimer une recommandation");
				System.out.println("4 - Afficher la liste des referents");
				System.out.println("5 - Afficher le meilleur referent");
				System.out.println("6 - Afficher la liste des themes");
				System.out.println("7 - Renseigner ses competences");
				System.out.println("8 - Afficher les notifications ("+nbnotifs+")");
				System.out.println("9 - Rafraichir la page");
				System.out.println("0 - Quitter");
				choix = sc.nextInt();
				switch (choix)
				{
				case 1 :
					
					System.out.println("Veuillez taper le theme sur lequel vous souhaitez etre referent");
					/* On vide avant de lire une autre chaine */
					sc.nextLine();
					String theme = sc.nextLine();
					url = serveur.getTheme(theme);
					if(!url.isEmpty() ) 
					{
						ServeurTheme = (IntTheme) Naming.lookup(url);
						System.out.println(ServeurTheme.addReferent(pseudo,pseudo));
						
					}
					else
					{
						/* Le theme n'existe pas on le cree */
						url = serveur.creerTheme(theme);
						if(!url.isEmpty() ) 
						{
							ServeurTheme = (IntTheme) Naming.lookup(url);
							System.out.println(ServeurTheme.addReferent(pseudo,pseudo));
							
						}
						else
						{
							System.out.println("Impossible de devenir referent : erreur lors de la creation du theme");
						}
					}
					break;
				case 2 :
					System.out.println("Veuillez taper le theme sur lequel vous souhaitez recommander un etudiant");
					/* On vide avant de lire une autre chaine */
					sc.nextLine();
					String theme1 = sc.nextLine();
					System.out.println("Veuillez taper le pseudo de l'etudiant que vous souhaitez recommander");
					String pseudo1 = sc.nextLine();
					url = serveur.getTheme(theme1);
					if(!url.isEmpty() ) 
					{
						ServeurTheme = (IntTheme) Naming.lookup(url);
						System.out.println(ServeurTheme.addReferent(pseudo,pseudo1));
						
					}
					else
					{
						System.out.println("Impossible de recommander un etudiant : le theme n'existe pas");
					}
					break;
				case 3 :
					System.out.println("Veuillez taper le theme sur lequel vous enlever la recommandation");
					/* On vide avant de lire une autre chaine */
					sc.nextLine();
					String theme2 = sc.nextLine();
					System.out.println("Veuillez taper le pseudo de l'etudiant");
					String pseudo2 = sc.nextLine();
					url = serveur.getTheme(theme2);
					if(!url.isEmpty() ) 
					{
						ServeurTheme = (IntTheme) Naming.lookup(url);
						System.out.println(ServeurTheme.delReferent(pseudo,pseudo2));
						
					}
					else
					{
						System.out.println("Impossible d'enlever la recommandation : le theme n'existe pas");
					}
	
					break;
				case 4 :
					System.out.println("Veuillez taper le theme pour afficher la liste des referents");
					/* On vide avant de lire une autre chaine */
					sc.nextLine();
					String theme3 = sc.nextLine();
					url = serveur.getTheme(theme3);
					if(!url.isEmpty() ) 
					{
						ServeurTheme = (IntTheme) Naming.lookup(url);
						System.out.println("Liste des referents sur le theme "+theme3+" : "+ServeurTheme.getListeReferents());
					}
					else
					{
						System.out.println("Aucun referent pour ce theme, nous allons rechercher les referents potentiels : ");
						System.out.println("Voici les referents potentiels : ");
						System.out.println(gestionnaireComp.ListeRefPotentiel(theme3));
						System.out.println("Souhaitez vous qu'un des etudiants ci-dessus devienne referent sur ce theme ? ( Oui ou non)");
						String choix1 = sc.nextLine();
						if(choix1.toLowerCase().equals("oui"))
						{
							System.out.println("Taper le nom de l'etudiant a referencer :");
							String etu = sc.nextLine();
							serveurNotification.creerNotification(pseudo, etu, theme3, "req");
							System.out.println(etu + "a ete prevenu de votre demande : nous vous alerterons des qu'il aura repondu");
						}
						
					}
					break;
				case 5 :
					System.out.println("Veuillez taper le theme pour afficher le meilleur referent ");
					/* On vide avant de lire une autre chaine */
					sc.nextLine();
					String theme4 = sc.nextLine();
					url = serveur.getTheme(theme4); 
					if(!url.isEmpty()) 
					{ 
						ServeurTheme = (IntTheme) Naming.lookup(url);
						System.out.println("Meilleur referent sur le theme "+theme4+" : "+ServeurTheme.getMeilleurReferent());
					}
					else
					{
						System.out.println("Impossible de renvoyer le meilleur referent : le theme n'existe pas");
					}
					break;
				case 6:
					System.out.println("Liste des themes en base : "+serveur.getListeThemes());
					break;
				case 7:
					ArrayList<String> listeCompetences = new ArrayList<String>();
					System.out.println("Veuillez entrer vos competences (Taper 0 pour sortir)");
					String competence = null;
					do
					{
						competence = sc.nextLine();
						listeCompetences.add(competence);
					}while(!competence.equals("0"));
					// On enleve le 0 de la liste
					listeCompetences.remove(listeCompetences.size()-1);
					// On ajoute les competences de l'utilisateur courant
					gestionnaireComp.addReferentPotentiel(pseudo, listeCompetences);
					serveur.creerProfil(pseudo, listeCompetences);
					
					break;
				case 8:
					sc.nextLine();
					System.out.println("Liste des notifications");
					ArrayList<String> listeNotifications = serveurNotification.getNotificationsUtilisateur(pseudo);
					if(listeNotifications != null){
						for(String notification  : listeNotifications){
							String[] tabNotifs = notification.split("#");
							if(tabNotifs[2].equals("req")){
								/* Demande */
								System.out.println(tabNotifs[0]+" veut savoir si tu voudrais devenir referent sur le theme "+tabNotifs[1]);
								System.out.println("Votre choix (oui / non) : ");
								String reponse = sc.nextLine();
								if(reponse.equals("oui")){
									String comp = tabNotifs[1];
									url = serveur.getTheme(comp);
									if(!url.isEmpty() ) 
									{
										ServeurTheme = (IntTheme) Naming.lookup(url);
										System.out.println(ServeurTheme.addReferent(pseudo,pseudo));
										serveurNotification.creerNotification(pseudo, tabNotifs[0], tabNotifs[1], "rep");
										
									}
									else
									{
										/* Le theme existe pas on le cree */
										url = serveur.creerTheme(comp);
										if(!url.isEmpty() ) 
										{
											ServeurTheme = (IntTheme) Naming.lookup(url);
											System.out.println(ServeurTheme.addReferent(pseudo,pseudo));
											serveurNotification.creerNotification(pseudo, tabNotifs[0], tabNotifs[1], "rep");
											
										}
										else
										{
											System.out.println("Impossible de devenir referent : erreur lors de la creation du theme");
										}
									}
								}
								serveurNotification.delNotification(pseudo, tabNotifs[0], tabNotifs[1]);
							}else{
								/* Reponse */
								System.out.println(tabNotifs[0]+" a accepte de devenir referent sur le theme "+tabNotifs[1]);
								serveurNotification.delNotification(pseudo, tabNotifs[0], tabNotifs[1]);
							}
						}
					}

					break;
				case 9:
					break;
				case 0:
					System.out.println("Fin du programme");
					break;
				default :
					System.out.println("Choix incorrect");
					break;
				}
			
			}while(choix != 0);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();
		
	}

	@Override
	public void afficherNotif() throws RemoteException {
		JOptionPane.showMessageDialog(null,"Vous avez une nouvelle notification !","Nouvelle notification",JOptionPane.INFORMATION_MESSAGE);	
	}
}
