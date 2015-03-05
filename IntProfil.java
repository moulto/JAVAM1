import java.rmi.Remote;
import java.util.ArrayList;


public interface IntProfil extends Remote{
	
	public String getPseudo();
	
	public ArrayList<String> getCompetences();
}
