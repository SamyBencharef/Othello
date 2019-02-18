/**
 * Case composant le plateau de jeux de notre othello<BR>
 * 
 * @see Plateau
 * @author Samy Bencharef 
 * @version 1.0
 */
public class Case {

	// Attribut de la case courrante.
	private String attribut;
	
	/**
	 * Constructeur d'une case <BR>
	 * 
	 * @param attribut sera l'attribut de la case
	 * 
	 * @author Samy bencharef
	 * @version 1.0
	 */
	Case(String attribut){
		this.attribut=attribut;	
	}
	
	/** Méthode setAttribut qui permet de donné un attribut à une case <BR>
	 * 
	 * @author Samy Bencharef
	 * @version 1.0
	 * 
	 */
	void setAttribut(String attri){
		this.attribut=attri;
	}
	
	/** Méthode getAttri qui permet de renseigner sur l'attribut de la case <BR>
	 * 
	 * @return (String) attribut
	 * @author Samy Bencharef
	 * @version 1.0
	 * 
	 */
	String getAttri(){
		return this.attribut;
	}
	
}
