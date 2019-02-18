

/**
 * Save est la sauvegarde de tous les �l�ments finaux d'une partie<BR>
 * donc du score de chaque joueur, de leurs nom ainsi que du gagant <BR>
 * 
 * @author Samy Bencharef 
 * @version 1.0
 */
public class Save {
	// Le score du joueur de pion blanc 
	private int scBlanc;
	
	// Le score du joueur de pion noir
	private int scNoir;
	
	// Le nom du joueur de pion blanc 
	private String joueurNoir;
	
	// Le nom du joueur de pion noir 
	private String joueurBlanc;
	
	// Le nom du gagnant
	private String gagnant;
	
	/**
	 * Constructeur d'une sauvegarde <BR>
	 * 
	 * @param pScBlanc sera le score du joueur blanc
	 * @param pScNoir  sera le score du joueur noir
	 * @param j1       sera le nom joueur blanc
	 * @param j2       sera le nom joueur noir
	 * @param pgagnant sera le nom du gagnant
	 * 
	 * @author Samy bencharef
	 * @version 1.0
	 */
	Save(int pScBlanc, int pScNoir, String j1, String j2, String pgagnant){
		scBlanc=pScBlanc;
		scNoir=pScNoir;
		joueurNoir=j1;
		joueurBlanc=j2;
		gagnant = pgagnant;
	}
	
	
	// Les getters ..
	
	/** M�thode getScB qui permet de renseigner sur le score du joueur blanc <BR>
	 * 
	 * @return (int) scBlanc 
	 * @author Samy Bencharef
	 * @version 1.0
	 * 
	 */
	int getScB(){
		return scBlanc;
	}
	
	/** M�thode getScN qui permet de renseigner sur le score du joueur noir <BR>
	 * 
	 * @return (int) scNoir 
	 * @author Samy Bencharef
	 * @version 1.0
	 * 
	 */
	int getScN(){
		return scNoir;
	}
	
	/** M�thode getJN qui permet de renseigner sur le nom du joueur noir <BR>
	 * 
	 * @return (String) joueurNoir 
	 * @author Samy Bencharef
	 * @version 1.0
	 * 
	 */
	String getJN(){
		return joueurNoir;
	}
	
	/** M�thode getJB qui permet de renseigner sur le nom du joueur blanc <BR>
	 * 
	 * @return (String) joueurBlanc 
	 * @author Samy Bencharef
	 * @version 1.0
	 * 
	 */
	String getJB(){
		return joueurBlanc;
	}
	
	/** M�thode getGG qui permet de renseigner sur le nom du gagnant <BR>
	 * 
	 * @return (String) gagnant 
	 * @author Samy Bencharef
	 * @version 1.0
	 * 
	 */
	String getGG(){
		return gagnant;
	}
}
