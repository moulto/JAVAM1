import java.util.ArrayList;

/**
 * Interface de serveur.
 * Le serveur va contenir l'ensemble des donnees du systeme
 * @author PrietoF, PrietoC, Watre
 *
 */
public interface IntServeur {
	
	/* 0 = ok
	 * 1 = ko
	 */
	
	public int creerTheme(String libelle);
	
	public int themeExiste(String libelle);
}
