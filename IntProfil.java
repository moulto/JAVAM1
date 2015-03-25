import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface IntProfil extends Remote{
	
	public String getPseudo() throws RemoteException, MalformedURLException;
	
	public ArrayList<String> getCompetences() throws RemoteException, MalformedURLException;
	
	public void setModerateur() throws RemoteException, MalformedURLException;
}
