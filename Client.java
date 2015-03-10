import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;


public class Client {


	public static void main(String args[])
	{
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Bienvenue dans l'application de recommandation");
		System.out.println("Veuillez taper votre pseudo ");
		String pseudo = sc.nextLine();
		
		
		try {
			Integer choix;
			do{
				IntGestionnaireCompetence comp = (IntGestionnaireCompetence) Naming.lookup("//localhost/comp");
				IntServeurNotification notif = (IntServeurNotification) Naming.lookup("//localhost/notification");
				int nbnotifs=notif.getNombreNotification(pseudo);
				IntServeur serveur = (IntServeur) Naming.lookup("//localhost/serveur");
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
				System.out.println("7 - Renseigner ses compétences");
				System.out.println("8 - Afficher les notifications ("+nbnotifs+")");
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
						/* Le theme existe pas on le cree */
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
						System.out.println("Aucun réferent pour ce theme, nous allons rechercher les réferents potentiels");
						System.out.println(comp.ListeRefPotentiel(theme3));
						System.out.println("Souhaitez vous qu'un des étudiant devienne réferent sur ce theme ? ( Oui ou non");
						String choix1 = sc.nextLine();
						if(choix1.toLowerCase()=="oui")
						{
							System.out.println("Taper le nom de l'étudiant a referencer :");
							String etu = sc.nextLine();
							notif.creerNotification(pseudo, etu, theme3, "ref");
							System.out.println(etu + "a été prevenu de votre demande : nous vous alerterons des qu'il aura repondu");
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
					System.out.println("Veuillez entrer vos compétences ( Taper 0 pour sortir)");
					String competence = null;
					while (competence!="0")
					{
						competence = sc.nextLine();
						listeCompetences.add(competence);
					}
					// On enleve le 0 de la liste
					listeCompetences.remove(listeCompetences.size()-1);
					// On ajoute les competences de l'utilisateur courant
					comp.addReferentPotentiel(pseudo, listeCompetences);
					
					break;
				case 8:
					System.out.println("Liste des notifications");
					ArrayList<String> listeNotifications = null;
					System.out.println(listeNotifications);
					System.out.println("Taper le numero de la notification a supprimer");
					int numnotif = sc.nextInt();
					if (numnotif < listeNotifications.size() || numnotif > listeNotifications.size())
					{
						System.out.println("Erreur de saisie");
					}
					else
					{
						String[] notification = listeNotifications.get(numnotif).split("#");
						if (notification[4]=="validation")
						{
							System.out.println("Confirmez vous vouloir devenir referent sur ce theme( oui non)");
							String validation = sc.nextLine();
							if(validation.toLowerCase()=="oui")
							{
								notif.delNotification(pseudo, notification[2], notification[3]);
								notif.creerNotification(pseudo, notification[2], notification[3], "validationOK");
							}
							else
							{
								notif.delNotification(pseudo, notification[2], notification[3]);
								notif.creerNotification(pseudo, notification[2], notification[3], "validationNOK");
							}
					    
						}
						else if (notification[4]=="info" )
						{
							notif.delNotification(pseudo, notification[2], notification[3]);
						}
					}

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
}
