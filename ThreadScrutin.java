import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/* Thread qui permet de gerer la duree d'un scrutin */
public class ThreadScrutin implements Runnable{

	/**
	 * Duree du scrutin
	 */
	private int time; 
	
	/**
	 * Identifian du scrutin
	 */
	private String pseudo;
	
	/**
	 * Constructeur du thread
	 * @param time Duree du scrutin
	 * @param pseudo Identifian du scrutin
	 */
	public ThreadScrutin(int time,String pseudo)
	{
		this.time=time*1000; /* On transforme en milli-secondes */
		this.pseudo=pseudo;
		
	}
	
	/**
	 * Methode principale du thread
	 */
	public void run()
	{

			try {
				/* On met l thread en attente de la duree du scrtin */
				Thread.sleep(this.time); 
				try{
					LocateRegistry.createRegistry(1099);
				}
				catch(RemoteException e){
					try {
						LocateRegistry.getRegistry(1099);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				/* A la fin de l duree on lance la cloture du scrutin */
				IntServeur serveur = (IntServeur) Naming.lookup("//localhost/serveur");
				serveur.terminerScrutin(pseudo);
			} catch (RemoteException | MalformedURLException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch(InterruptedException i){}
			
			

		
	}
}
