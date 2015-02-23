import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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
				IntServeur serveur = (IntServeur) Naming.lookup("//localhost/serveur");
				IntTheme ServeurTheme = null;
				String url = "";
				System.out.println("Vous etes bien connectés sur le serveur");
				System.out.println("Tapez le numero de l'action a réaliser");
				System.out.println("1 - Devenir référent");
				System.out.println("2 - Recommander un etudiant");
				System.out.println("3 - Supprimer une recommandation");
				System.out.println("4 - Afficher la liste des referents");
				System.out.println("5 - Afficher le meilleur referent ");
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
					System.out.println("Valeur de URL : "+url);
					if(!url.isEmpty() ) 
					{
						ServeurTheme = (IntTheme) Naming.lookup(url);
						System.out.println(ServeurTheme.addReferent(pseudo,pseudo));
						
					}
					else
					{
						/* Le theme existe pas on le cree */
						url = serveur.creerTheme(theme);
						System.out.println("Valeur de URL : "+url);
						if(!url.isEmpty() ) 
						{
							ServeurTheme = (IntTheme) Naming.lookup(url);
							System.out.println(ServeurTheme.addReferent(pseudo,pseudo));
							
						}
						else
						{
							System.out.println("Impossible de devenir referent");
						}
					}
					break;
				case 2 :
					System.out.println("Veuillez taper le theme sur lequel vous souhaitez recommander un etudiant");
					/* On vide avant de lire une autre chaine */
					sc.nextLine();
					String theme1 = sc.nextLine();
					System.out.println("Veuillez taper le pseudo de l'etudiant que vous souhaitez recommander");
					/* On vide avant de lire une autre chaine */
					sc.nextLine();
					String pseudo1 = sc.nextLine();
					url = serveur.getTheme(theme1);
					if(!url.isEmpty() ) 
					{
						ServeurTheme = (IntTheme) Naming.lookup(url);
						System.out.println(ServeurTheme.addReferent(pseudo,pseudo1));
						
					}
					else
					{
						System.out.println("Impossible de recommander un etudiant");
					}
					break;
				case 3 :
					System.out.println("Veuillez taper le theme sur lequel vous enlever la recommandation");
					/* On vide avant de lire une autre chaine */
					sc.nextLine();
					String theme2 = sc.nextLine();
					System.out.println("Veuillez taper le pseudo de l'etudiant");
					/* On vide avant de lire une autre chaine */
					sc.nextLine();
					String pseudo2 = sc.nextLine();
					url = serveur.getTheme(theme2);
					if(!url.isEmpty() ) 
					{
						ServeurTheme = (IntTheme) Naming.lookup(url);
						System.out.println(ServeurTheme.delReferent(pseudo,pseudo2));
						
					}
					else
					{
						System.out.println("Impossible d'enlever la recommandation");
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
						System.out.println(ServeurTheme.getListeReferents());
					}
					else
					{
						System.out.println("Impossible de renvoyer la liste des referents");
					}
					break;
				case 5 :
					System.out.println("Veuillez taper le theme pour afficher le meilleur referent");
					/* On vide avant de lire une autre chaine */
					sc.nextLine();
					String theme4 = sc.nextLine();
					url = serveur.getTheme(theme4);
					if(!url.isEmpty()) 
					{ 
						ServeurTheme = (IntTheme) Naming.lookup(url);
						System.out.println(ServeurTheme.getListeReferents());
					}
					else
					{
						System.out.println("Impossible de renvoyer le meilleur referent");
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
