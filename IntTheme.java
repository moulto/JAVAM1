import java.util.ArrayList;


public interface IntTheme {

	public String getLiblle();
	
	public ArrayList<String> getListeReferents();
	
	public void addReferent(String recommandant, String recommande);
	
	public void delReferent(String recommandant, String recommande);
	
	public String getMeilleurReferent();
}
