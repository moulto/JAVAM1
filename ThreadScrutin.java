import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class ThreadScrutin implements Runnable{

	private Integer time;
	private String pseudo;
	public ThreadScrutin(Integer time,String pseudo)
	{
		this.time=time;
		this.pseudo=pseudo;
		
	}
	public void run()
	{

			try {
				Thread.sleep(this.time);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			IntScrutin scrutin;
			try {
				scrutin = (IntScrutin) Naming.lookup("//localhost/scrutin/"+pseudo);
				scrutin.terminerScrutin();
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
			

		
	}
}
