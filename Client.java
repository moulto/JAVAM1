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

	public static void main(String args[]) throws RemoteException, MalformedURLException, InterruptedException
	{
		
		Scanner sc = new Scanner(System.in); /* Scanner pour les saisie utilisateurs */
 		boolean profilCree = false; /* Booleen pour savoir si le profil de l'utilisateur est bien cree */
		boolean isModerateur = false; /* Booleen pour savoir si l'utilisateur est moderateur */
		Client cl = new Client(); /* Objet utilisateur */
		
		/* On demande le pseudo de l'utilisateur pour créer son profil */
		System.out.println("Bienvenue dans l'application de recommandation");
		System.out.println("Veuillez taper votre pseudo ");	
		String pseudo = sc.nextLine();
		
		
		
		/* On récupère le Regustry */
		try{
			LocateRegistry.createRegistry(1099);
		}
		catch(RemoteException e){
			LocateRegistry.getRegistry(1099);
		}
		
		/* On enregisre le client au catalogue à partir de son pseudo */
		Naming.rebind(pseudo, cl);
		
		/* Menu Pinicpal */
		try {
			Integer choix;
			do{
				/* On récupère les objets distribués dont on a besoin dans le clien */
				IntServeur serveur = (IntServeur) Naming.lookup("//localhost/serveur");
				IntGestionnaireCompetence gestionnaireComp = (IntGestionnaireCompetence) Naming.lookup("//localhost/gestionnaireComp");
				IntServeurNotification serveurNotification = (IntServeurNotification) Naming.lookup("//localhost/notification");
				IntTheme ServeurTheme = null;
				String url = "";
				
				/* On vérifie si le profil de l'utilisateur a bie ete cree */
				if(profilCree == false){
					/* on cree le profil */
					if(serveur.creerProfil(pseudo).equals("")){
						System.out.println("Erreur a la creation du profil");
					}else{
						profilCree =true;
					}
				}else{
					IntProfil profil = (IntProfil) Naming.lookup(serveur.getProfil(pseudo));
					isModerateur = profil.isModerateur();
				}
				
				/* On récupère le nombre de notifications de l'utilisateur */
				int nbnotifs = serveurNotification.getNombreNotification(pseudo);
				
				
				System.out.println("\nVous etes bien connectes sur le serveur");
				System.out.println("Tapez le numero de l'action a realiser");
				System.out.println("1  - Devenir referent");
				System.out.println("2  - Recommander un etudiant");
				System.out.println("3  - Supprimer une recommandation");
				System.out.println("4  - Afficher la liste des referents");
				System.out.println("5  - Afficher le meilleur referent");
				System.out.println("6  - Afficher la liste des themes");
				System.out.println("7  - Renseigner ses competences");
				System.out.println("8  - Afficher les notifications ("+nbnotifs+")");
				System.out.println("9  - Rafraichir la page");
				if(isModerateur){
				System.out.println("10 - Menu moderateur");
				}else{
				System.out.println("10 - Postuler en tant que moderateur");
				}
				System.out.println("0  - Quitter");
				choix = sc.nextInt();
				switch (choix)
				{
				case 1 : /* Menu pour se proposer comme référent sur un theme */
					
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
				case 2 : /* Menu pour recommander un étudiant comme référent sur un thème */
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
				case 3 : /* Menu pour supprimer une recommandation sur un étudiant */
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
				case 4 : /* Menu pour afficher la liste des référents d'un thème */
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
							serveurNotification.notifier(etu);
							System.out.println(etu + "a ete prevenu de votre demande : nous vous alerterons des qu'il aura repondu");
						}
						
					}
					break;
				case 5 : /* Menu pour afficher le pseudo du meilleur référent sur un thème */
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
				case 6: /* Menu pour afficher la liste ds thèmes */
					System.out.println("Liste des themes en base : "+serveur.getListeThemes());
					break;
				case 7: /* Menu pour renseigner ses compétences */
					ArrayList<String> listeCompetences = new ArrayList<String>();
					System.out.println("Veuillez entrer vos competences (Taper 0 pour sortir)");
					String competence = null;
					do
					{
						competence = sc.nextLine();
						listeCompetences.add(competence);
						IntProfil profil = (IntProfil) Naming.lookup(serveur.getProfil(pseudo));
						if(!competence.equals("0")){
							System.out.println(profil.addCompetences(competence));
						}
					}while(!competence.equals("0"));
					// On enleve le 0 de la liste
					listeCompetences.remove(listeCompetences.size()-1);
					// On ajoute les competences de l'utilisateur courant
					gestionnaireComp.addReferentPotentiel(pseudo, listeCompetences);
					
					
					break;
				case 8: /* Menu pour afficher les notifications */
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
											serveurNotification.notifier(tabNotifs[0]);
										}
										else
										{
											System.out.println("Impossible de devenir referent : erreur lors de la creation du theme");
										}
									}
								}
							}else{
								if(tabNotifs[2].equals("rep")){
								/* Reponse */
								System.out.println(tabNotifs[0]+" a accepte de devenir referent sur le theme "+tabNotifs[1]);
								serveurNotification.delNotification(pseudo, tabNotifs[0], tabNotifs[1]);
								}else{
									/* Demande d'election de moderateur */
									if(tabNotifs[2].equals("vote")){
										String vote = null;
										/* On boucle tant que l'utilisateur ne saisie pas oui / non ou blanc */
										do{
											/* On affiche la demande */
											System.out.println(tabNotifs[0]+" souhaite devenir moderateur, merci de voter : (oui/non/blanc)");
											vote = sc.nextLine();
										}while(!vote.equalsIgnoreCase("oui") && !vote.equalsIgnoreCase("non") && !vote.equalsIgnoreCase("blanc"));
										 /* On enregistre le vote de l'utilisateur dans le scrutn */
										/* On transforme le choix de l'utilisateur en voix (0,1 ou 2) */
										int voix;
										if(vote.equalsIgnoreCase("oui")){
											voix = 2;
										}else if(vote.equalsIgnoreCase("blanc")){
											voix = 1;
										}else{
											voix = 0;
										}
										System.out.println(serveur.voter(tabNotifs[0], pseudo, voix));
										
									}else{
										/* Victoire a un scrutin */
										if(tabNotifs[2].equals("v")){
											System.out.println(tabNotifs[0]+" a ete elu moderateur");
										}else{
											/* Defaite a un scrutin */
											if(tabNotifs[2].equals("d")){
												System.out.println(tabNotifs[0]+" n'a pas ete elu moderateur");
											}
										}
									}
								}
							}
							serveurNotification.delNotification(pseudo, tabNotifs[0], tabNotifs[1]);
						}
					}

					break;
				case 9: /* Raffraichit la page */
					break;
				case 10: /* Postuler en tant que moderateur ou afficher le menu moderateur si on est moderateur */
					if(!isModerateur){
						/* Se proposer moderateur */
						System.out.println("Combien de secondes voulez vous que le vote dure ? (0 pour quitter)");
						int time = sc.nextInt();
						sc.nextLine();
						if(time != 0){
							serveur.creerScrutin(pseudo, time);
							System.out.println("Votre demande a bien ete prise en compte, vous serez informe du resultat a la fin du vote");
						}
					}
					break;
				case 0: /* Quitter le programme */
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
	/**
	 * Affiche une popup qui informe de la présence d'une nouvelle notification
	 * @throws RemoteException
	 */
	public void afficherNotif() throws RemoteException {
		JOptionPane.showMessageDialog(null,"Vous avez une nouvelle notification !","Nouvelle notification",JOptionPane.INFORMATION_MESSAGE);	
	}
}
