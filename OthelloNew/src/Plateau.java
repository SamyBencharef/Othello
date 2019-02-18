/**
 * Algorithme du jeu OTHELLO <BR>
 * 
 * Le jeu sera placer dans un tableau deux dimensions de Case @see Case<BR>
 * Chaque case est composé d'un attribut qui caractérisera le pion de la Case<BR>
 * 
 * La taille du plateau de jeu est fixé par l'appel de la classe.<BR>
 * @author Samy Bencharef 
 * @version 1.0
 */
public class Plateau {
	// Plateau du jeux
	Case plateau[][];
	
	//Nombre de jeton blanc sur le plateau
	int nbBlanc;
	
	//Nombre de jeton noir sur le plateau
	int nbNoir;
	
	//Identifiant du joueur 'B' pour blanc et 'N' pour noir.. Oui un boolean aurait été plus simple.. :'(
	String joueur;
	
	//Nombre de colonne du plateau
	int nbCol;
	
	//Nombre de ligne du plateau
	int nbLign;
	
	//Boolean qui permet de savoir si la partie est terminée.
	boolean stop=false;
	
	//Boolean représentant le cas d'erreur ou un pion ne retourne rien
	boolean ouiNon;
	
	//Boolean représentant le cas d'erreur ou la case n'est pas vide
	boolean nonVide;
	
	
	/**
	 * Constructeur du jeu Othello <BR>
	 * 
	 * @param ligne sera la nombre de lignes de notre plateau
	 * @param colonne sera la nombre de colonnes de notre plateau
	 * 
	 * @author Samy bencharef
	 * @version 1.0
	 */
	public Plateau(int ligne, int colonne){
		this.nbLign = ligne;
		this.nbCol  = colonne;
		plateau = new Case[nbLign][nbCol];
		
		
		
		//Initialisation de nos grille. 
		for ( int i = 0 ; i < nbLign ; i++ ){
			for ( int j = 0 ; j < nbCol ; j++ ){
				if      ( i == (nbLign/2) - 1 && j == (nbCol/2) - 1 ||  i == nbLign/2       && j == nbCol/2 )
					plateau[i][j] = new Case("B"); // Placement des pions Blanc sur le plateau.
				else if ( i == nbLign/2       && j == (nbCol/2) - 1 ||  i == (nbLign/2) - 1 && j == nbCol/2 )
					plateau[i][j] = new Case("N"); // Placement des pions Noir sur le plateau.
				else 
					plateau[i][j] = new Case("V");// Placement des pions Vide sur le plateau.
			}
		}
		this.joueur="N";
		nbBlanc = 2;
		nbNoir = 2;
		affichage();
	}
	
	// Quelques getter utile pour nos IA's ainsi que pour savoir si la partie est terminée.
	
	
	/** Méthode getStop qui permet de renseigner si la partie est terminée <BR>
	 * 
	 * @return (boolean) stop : True si la partie est terminée / False si elle n'est pas terminée
	 * @author Samy Bencharef
	 * @version 1.0
	 * 
	 */
	boolean getStop(){
		return stop;
	}
	
	
	/** Méthode getOuiNon qui permet de renseigner si l'erreur ouiNon est présente <BR>
	 * 
	 * @return (boolean) ouiNon : True si l'erreur à eu lieu / False si elle n'a pas eu lieu
	 * @author Samy Bencharef
	 * @version 1.0
	 * 
	 */
	boolean getOuiNon(){
		return ouiNon;
	}
	
	
	/** Méthode getnonVide qui permet de renseigner si l'erreur nonVide est présente <BR>
	 * 
	 * @return (boolean) nonVide : True si l'erreur à eu lieu / False si elle n'a pas eu lieu
	 * @author Samy Bencharef
	 * @version 1.0
	 * 
	 */
	boolean getnonVide(){
		return nonVide;
	}
	
	/** Méthode getJoueur qui permet de renseigner sur le joueur courant <BR>
	 * 
	 * @return (String) joueur
	 * @author Samy Bencharef
	 * @version 1.0
	 * 
	 */
	String getJoueur(){
		return joueur;
	}
	
	
	/** Méthode getNbBlanc qui permet de renseigner sur le nombre de pion blanc <BR>
	 * 
	 * @return (int) nbBlanc
	 * @author Samy Bencharef
	 * @version 1.0
	 * 
	 */
	int getNbBlanc(){
		return nbBlanc;
	}
	
	/** Méthode getNbBlanc qui permet de renseigner sur le nombre de pion noir <BR>
	 * 
	 * @return (int) nbNoir
	 * @author Samy Bencharef
	 * @version 1.0
	 * 
	 */
	int getNbNoir(){
		return nbNoir;
	}
	
	
	
	/**
	 * Methode utilisé pour jouer un coup, le joueur sera identifié grâce à notre attribut<BR>
	 * tandis que la case sera identifié grâce au paramètres. <BR>
	 * 
	 * @param x
	 * @param y
	 * 
	 * @see affichage()
	 * @see testLimite()
	 * @author Samy Bencharef
	 * @version 1.0
	 */
	public void EffectuerCoup(int x, int y){
		if ( testLimite(x,  y, 0, 0)==true ){
			if( plateau[x][y].getAttri().equals("V")){
				ouiNon = false ; 
				nonVide=true;
				
				// On teste le cas ou le joueur courant serait le joueur Noir.
				if ( this.joueur.equals("N") ){
					int i;
					int direction = 1;
					int cmpt = -1; 
					
					// Ce While permettra de parcourir tout notre switch et ainsi essayé tout les chemins possible
					// Malheuresement deux boucles imbriqués (for) aurait été plus simple mais je l'ai réalisé trop tard.
					while ( direction < 9 ){
						switch (direction){
						
							// Test vers le bas
							case 1:
								i = 1;
								if ( testLimite(x, y, i, 0) == true ){
									while( plateau[x+i][y].getAttri().equals("B") ){
										i++;
										if ( testLimite(x, y, i, 0) == true ){
											if ( plateau[x+i][y].getAttri().equals("N") ){
												for ( int j=0 ; j < i ; j++ ){
													plateau[x+j][y].setAttribut("N");
												}
												nbNoir  = nbNoir  + i ;
												nbBlanc = nbBlanc - i + 1 ;
												cmpt = cmpt+1;
												ouiNon=true;
												break;
											} else if ( plateau[x+i][y].getAttri().equals("V") ){
												break;
											}
										}else{
											break;
										}
									}
								}
								direction ++;
								break;
								
							// Test vers la diagonal en bas à gauche
							case 2:
								i = 1;
								if ( testLimite(x, y, i, -i) == true ){
									while(plateau[x+i][y-i].getAttri().equals("B") ){
										i++;
										if ( testLimite(x, y, i, -i) == true ){
											if ( plateau[x+i][y-i].getAttri().equals("N") ){
												for ( int j=0 ; j < i ; j++ ){
													plateau[x+j][y-j].setAttribut("N");
												}
												nbNoir= nbNoir + i;
												nbBlanc=nbBlanc -i + 1;
												cmpt = cmpt+1;
												ouiNon=true;
												break;
											} else if ( plateau[x+i][y-i].getAttri().equals("V")){
												break;
											}
										}else{
											break;
										}
									}
								}
								direction ++;
								break;
								
							// Test vers la diagonal en bas à droite
							case 3:
								i = 1;
								if ( testLimite(x, y, i, i) == true ){
									while(plateau[x+i][y+i].getAttri().equals("B") ){
										i++;
											if ( testLimite(x, y, i, i) == true ){
												if ( plateau[x+i][y+i].getAttri().equals("N")  ){
													for ( int j=0 ; j < i ; j++ ){
														plateau[x+j][y+j].setAttribut("N");
													}
													nbNoir= nbNoir + i;
													nbBlanc=nbBlanc -i + 1;
													cmpt = cmpt+1;
													ouiNon=true;
													break;
												} else if ( plateau[x+i][y+i].getAttri().equals("V") ){
													break;
												}
											}else{
												break;
											}
									}
								}
								direction ++;
								break;
							
							// Test vers la droite
							case 4:
								i = 1;
								if ( testLimite(x, y, 0, i) == true ){
									while(plateau[x][y+i].getAttri().equals("B") ){
										i++;
										if ( testLimite(x, y, 0, i) == true ){
											if ( plateau[x][y+i].getAttri().equals("N") ){
												for ( int j=0 ; j < i ; j++ ){
													plateau[x][y+j].setAttribut("N");
												}
												nbNoir= nbNoir + i;
												nbBlanc=nbBlanc -i + 1;
												cmpt = cmpt+1;
												ouiNon=true;
												break;
											} else if ( plateau[x][y+i].getAttri().equals("V") ){
												break;
											}
										}else{
											break;
										}
									}
								}
								direction ++;
								break;
								
							// Test vers la diagonal en haut à droite
							case 5:
								i = 1;
								if ( testLimite(x, y, -i, i) == true ){
									while(plateau[x-i][y+i].getAttri().equals("B") ){
										i++;
										if ( testLimite(x, y, -i, i) == true ){
											if ( plateau[x-i][y+i].getAttri().equals("N") ){
												for ( int j=0 ; j < i ; j++ ){
													plateau[x-j][y+j].setAttribut("N");
												}
												nbNoir= nbNoir + i;
												nbBlanc=nbBlanc -i + 1;
												cmpt = cmpt+1;
												ouiNon=true;
												break;
											} else if (plateau[x-i][y+i].getAttri().equals("V") ){
												break;
											}
										}else{
											break;
										}
									}
								}
								direction ++;
							break;
								
							// Test vers le haut
							case 6:
								i = 1;
								if ( testLimite(x, y, -i, 0) == true ){
									while(plateau[x-i][y].getAttri().equals("B") ){
										i++;
										if ( testLimite(x, y, -i, 0) == true ){
											if ( plateau[x-i][y].getAttri().equals("N") ){
												for ( int j=0 ; j < i ; j++ ){
													plateau[x-j][y].setAttribut("N");
												}
												nbNoir  = nbNoir  + i;
												nbBlanc = nbBlanc - i + 1;
												cmpt = cmpt+1;
												ouiNon=true;
												break;
											} else if ( plateau[x-i][y].getAttri().equals("V") ){
												break;
											}
										}else{
											break;
										}
									}
								}
								direction ++;
							break;
							
							// Test vers la diagonal en haut à gauche
							case 7:
								i = 1;
								if ( testLimite(x, y, -i, -i) == true ){
									while(plateau[x-i][y-i].getAttri().equals("B") ){
										i++;
										if ( testLimite(x, y, -i, -i) == true ){
											if ( plateau[x-i][y-i].getAttri().equals("N") ){
												for ( int j=0 ; j < i ; j++ ){
													plateau[x-j][y-j].setAttribut("N");
												}
												nbNoir= nbNoir + i;
												nbBlanc=nbBlanc -i + 1;
												cmpt = cmpt+1;
												ouiNon=true;
												break;
											} else if ( plateau[x-i][y-i].getAttri().equals("V") ){
												break;
											}
										}else{
											break;
										}
									}
								}
								direction ++;
								break;
							
							// Test vers la gauche
							case 8:
								i = 1;
								if ( testLimite(x, y, 0, -i) == true ){
									while(plateau[x][y-i].getAttri().equals("B") ){
										i++;
										if ( testLimite(x, y, 0, -i) == true ){
											if ( plateau[x][y-i].getAttri().equals("N") ){
												for ( int j=0 ; j < i ; j++ ){
													plateau[x][y-j].setAttribut("N");
												}
												nbNoir= nbNoir + i;
												nbBlanc=nbBlanc -i + 1;
												cmpt = cmpt+1;
												ouiNon=true;
												break;
											} else if ( plateau[x][y-i].getAttri().equals("V")  ){
												break;
											}
										}else
											break;
									}
								}
								direction ++;
								break;	
							}
					}
					if ( ouiNon == true ){
						this.joueur="B";
						nbNoir= nbNoir-cmpt;
						if (testeCaseVide()==false){ // On regarde si le prochain joueur peut jouer, s'il peut pas on saute son tour
							joueur="N";	 
							if ( testeCaseVide()==false ){ // Et si le joueur courant lui même ne peux pas jouer non plus, la partie se termine !
								System.out.println("La partie est terminée");
								stop=true;
							}
						}
					}
					
					// On teste le cas ou le joueur courant serait le joueur Blanc.
				}else{
					int i;
					int direction = 1;
					int cmpt = -1;
					while ( direction < 9 ){
						switch (direction){
							// Test vers le bas
							case 1:
								i = 1;
								if ( testLimite(x, y, i, 0) == true ){
									while( plateau[x+i][y].getAttri().equals("N") ){
										i++;
										if ( testLimite(x, y, i, 0) == true ){
											if ( plateau[x+i][y].getAttri().equals("B") ){
												for ( int j = 0 ; j < i ; j++ ){
													plateau[x+j][y].setAttribut("B");
												}
												nbNoir= nbNoir - i + 1;
												nbBlanc=nbBlanc + i;
												ouiNon = true;
												cmpt = cmpt+1;
												break;
											} else if ( plateau[x+i][y].getAttri().equals("V") ){
												break;
											}
										}else{
											break;
										}
									}
								}
								direction ++;
								break;
								
							// Test vers la diagonal en bas à gauche
							case 2:
								i = 1;
								if ( testLimite(x, y, i, -i) == true ){
									while(plateau[x+i][y-i].getAttri().equals("N") ){
										i++;
										if ( testLimite(x, y, i, -i) == true ){
											if ( plateau[x+i][y-i].getAttri().equals("B") ){
												for ( int j=0 ; j < i ; j++ ){
													plateau[x+j][y-j].setAttribut("B");
												}
												nbNoir= nbNoir - i + 1;
												nbBlanc=nbBlanc + i;
												ouiNon=true;
												cmpt = cmpt+1;
												break;
											} else if ( plateau[x+i][y-i].getAttri().equals("V") ){
												break;
											}
										}else{
											break;
										}
									}
								}
								direction ++;
								break;
								
							// Test vers la diagonal en bas à droite
							case 3:
								i = 1;
								if ( testLimite(x, y, i, i) == true ){
									while(plateau[x+i][y+i].getAttri().equals("N") ){
										i++;
										if ( testLimite(x, y, i, i) == true ){
											if ( plateau[x+i][y+i].getAttri().equals("B")  ){
												for ( int j=0 ; j < i ; j++ ){
													plateau[x+j][y+j].setAttribut("B");
												}
												nbNoir= nbNoir - i + 1;
												nbBlanc=nbBlanc + i;
												ouiNon=true;
												cmpt = cmpt+1;
												break;
											} else if ( plateau[x+i][y+i].getAttri().equals("V") ){
												break;
											}
										}else{
											break;
										}
									}
								}
								direction ++;
								break;
							
							// Test vers la droite
							case 4:
								i = 1;
								if ( testLimite(x, y, 0, i) == true ){
									while(plateau[x][y+i].getAttri().equals("N") ){
										i++;
										if ( testLimite(x, y, 0, i) == true ){
											if ( plateau[x][y+i].getAttri().equals("B") ){
												for ( int j=0 ; j < i ; j++ ){
													plateau[x][y+j].setAttribut("B");
												}
												nbNoir= nbNoir - i + 1;
												nbBlanc=nbBlanc + i;
												ouiNon=true;
												cmpt = cmpt+1;
												break;
											} else if ( plateau[x][y+i].getAttri().equals("V") ){
												break;
											}
										}else{
											break;
										}
									}
								}
								direction ++;
								break;
							
							// Test vers la diagonal en haut à droite
							case 5:
								i = 1;
								if ( testLimite(x, y, -i, i) == true ){
									while(plateau[x-i][y+i].getAttri().equals("N") ){
										i++;
										if ( testLimite(x, y, -i, i) == true ){
											if ( plateau[x-i][y+i].getAttri().equals("B") ){
												for ( int j=0 ; j < i ; j++ ){
													plateau[x-j][y+j].setAttribut("B");
												}
												nbNoir= nbNoir - i + 1;
												nbBlanc=nbBlanc + i;
												ouiNon=true;
												cmpt = cmpt+1;
												break;
											} else if (plateau[x-i][y+i].getAttri().equals("V") ){
												break;
											}
										}else{
											break;
										}
									}
								}
								direction ++;
							break;
							
							// Test vers le haut
							case 6:
								i = 1;
								if ( testLimite(x, y, -i, 0) == true ){
									while(plateau[x-i][y].getAttri().equals("N") ){
										i++;
										if ( testLimite(x, y, -i, 0) == true ){
											if ( plateau[x-i][y].getAttri().equals("B") ){
												for ( int j=0 ; j < i ; j++ ){
													plateau[x-j][y].setAttribut("B");
												}
												nbNoir= nbNoir - i + 1; 
												nbBlanc=nbBlanc + i;
												ouiNon=true;
												cmpt = cmpt+1;
												break;
											} else if ( plateau[x-i][y].getAttri().equals("V") ){
												break;
											}
										}else{
											break;
										}
									}
								}
								direction ++;
							break;
							
							// Test vers la diagonal en haut à gauche    
							case 7:
								i = 1;
								if ( testLimite(x, y, -i, -i) == true ){
									while(plateau[x-i][y-i].getAttri().equals("N") ){
										i++;
										if ( testLimite(x, y, -i, -i) == true ){
											if ( plateau[x-i][y-i].getAttri().equals("B") ){
												for ( int j=0 ; j < i ; j++ ){
													plateau[x-j][y-j].setAttribut("B");
												}
												nbNoir= nbNoir - i + 1;
												nbBlanc=nbBlanc + i;
												ouiNon=true;
												cmpt = cmpt+1;
												break;
											} else if ( plateau[x-i][y-i].getAttri().equals("V") ){
												break;
											}
										}else{
											break;
										}
									}
								}
								direction ++;
								break;
							
							// Test vers la gauche
							case 8:
								i = 1;
								if ( testLimite(x, y, 0, -i) == true ){
									while(plateau[x][y-i].getAttri().equals("N") ){
										i++;
										if ( testLimite(x, y, 0, -i) == true ){
											if ( plateau[x][y-i].getAttri().equals("B") ){
												for ( int j=0 ; j < i ; j++ ){
													plateau[x][y-j].setAttribut("B");
												}
												nbNoir= nbNoir - i + 1;
												nbBlanc=nbBlanc + i;
												ouiNon=true;
												cmpt = cmpt+1;
												break;
											} else if ( plateau[x][y-i].getAttri().equals("V") ){
												break;
											}
										}else{
											break;
										}
									}
								}
								direction ++;
								break;	
							}
					}
					if ( ouiNon == true ){
						this.joueur="N";
						nbBlanc= nbBlanc- cmpt;
						 if (testeCaseVide()==false){  // On regarde si le prochain joueur peut jouer, s'il peut pas on saute son tour
									joueur="B"	;
									if ( testeCaseVide()==false ){
										System.out.println("La partie est terminée"); // // Et si le joueur courant lui même ne peux pas jouer non plus, la partie se termine !
										stop=true;
									}
						 }
						 
					}
				}
				if ( ouiNon == false)
					System.out.println("erreurOuiNon");
				else
					affichage(); // Et bien sur si tout ce passe bien, on affiche notre nouveaux plateau !
			}else{
				nonVide=false;
				System.out.println("erreurNonVide");
			}
	}else{
		System.out.println("erreurOrLimite");
	}	
	}

	
	/**
	 * Methode qui permet de savoir si une case vide peut être jouer celon le joueur courant <BR>
	 * Servira à savoir si un joueur ne peux pas jouer et donc si l'on doit sauter un tour. (return false)   <BR>
	 * Permettra également de terminer un partie si elle est utilisé deux fois de suite et  <BR>
	 * quel reçoit en return false. <BR>
	 * 
	 * @return True si le joueur courant peut jouer / False si le joueur courant ne peut pas jouer
	 * 
	 * @see testLimite()
	 * @author Samy Bencharef
	 * @version 1.0
	 */
	public boolean testeCaseVide() {
		for ( int i = 0 ; i<nbLign ; i++){
			for ( int j = 0 ; j<nbCol ; j++){ 
			    if ( plateau[i][j].getAttri().equals("V")){
			    	int dY;
					int dX;
					for (int vX=-1; vX<=1; vX++) {   // Voici la boucle imbriqué, bien plus efficace, plus lisible que le switch..
						for (int vY=-1; vY<=1; vY++) {
							 dY=vY;
							 dX=vX;
							if ( testLimite(i, j, dX, dY) == true ){
								if ( joueur.equals("B")){
									while (plateau[i+dX][j+dY].getAttri().equals("N")){ // on regarde si le joueur blanc peut jouer
										if ( vX==0 && vY==0 )
											break;
										dY=dY+vY;
										dX=dX+vX;
										if ( testLimite(i, j, dX, dY) == true ){
											if ( plateau[i+dX][j+dY].getAttri().equals("B") ){
												return true;
											}else
												break;
										}else
											break;
									}
								}
								if ( joueur.equals("N")){
									while (plateau[i+dX][j+dY].getAttri().equals("B")){ // On regarde si le joueur noir peut jouer
										if ( vX==0 && vY==0 )
											break;
										dY=dY+vY;
										dX=dX+vX;
										if ( testLimite(i, j, dX, dY) ==true ){
											if ( plateau[i+dX][j+dY].getAttri().equals("N") ){
												return true;
											}else
												break;
										}else
											break;
									}		
								}
							}			
						}				
					}
				}
			}
		}
		return false;
	} 		
				
	
	/**
	 * Méthode permettant de savoir si la case dont les coordonnées sont passé en paramètre <BR>
	 * peut être joué.  <BR>
	 * Si elle peut être joué elle retournera le nombre de case qu'elle peut retournée ! (Utile pour les IA)<BR>
	 * 
	 * @param x
	 * @param y
	 * @return (int) myCmpt  : Si myCmpt=0 c'est que la case ne peut pas être joué
	 * 
	 * @see testLimite()
	 * 
	 * @author Samy Bencharef
	 * @version 1.0
	 */
	 int testCasePossible(int x, int y) {
		int myCmpt=0;
		if ( plateau[x][y].getAttri().equals("V")) {
			int  dY;
			int  dX;
			for (int vX=-1; vX<=1; vX++) {
				for (int vY=-1; vY<=1; vY++) {
						  dY=vY;
						  dX=vX;
						if ( testLimite(x,y,dX,dY)  ) {
							
							if ( joueur.equals("B") ){
								while (plateau[x+dX][y+dY].getAttri().equals("N")){
									if ( vX==0 && vY==0 )
										break;
									dY=dY+vY;
									dX=dX+vX;
									myCmpt=myCmpt+1;
									if ( testLimite(x,y,dX,dY)  ) {
										if ( plateau[x+dX][y+dY].getAttri().equals("B") ){ 
											return myCmpt;
										}else if ( plateau[x+dX][y+dY].getAttri().equals("V") ){
											break;
										}
									}else{
										break;
									}
								}
									
									
							}else{
								while (plateau[x+dX][y+dY].getAttri().equals("B")){
									if ( vX==0 && vY==0 )
										break;
									dY=dY+vY;
									dX=dX+vX;
									myCmpt=myCmpt+1;
									if ( testLimite(x,y,dX,dY)  ) {
										if ( plateau[x+dX][y+dY].getAttri().equals("N") ) {
											return myCmpt;
										}else if ( plateau[x+dX][y+dY].getAttri().equals("V") ){
											break;
										}
									}else{
										break;
									}
								}
							}
							
						}
				}
			} 
		}
		return myCmpt=0;
	}
	
	 

		/**
		 * Méthode affichant notre plateau.<BR>
		 * 
		 * @return (int) myCmpt / Si myCmpt=0 c'est que la case ne peut pas être joué
		 * 
		 * @author Samy Bencharef
		 * @version 1.0
		 */
	public void affichage(){
		for ( int i = 0 ; i < nbLign ; i++){
			for ( int j = 0 ; j < nbCol ; j++){
					System.out.print(plateau[i][j].getAttri()+"  ");
			}
			System.out.println();
		}
		System.out.println("-----------------------");
		if ( this.joueur.equals("N") )
			System.out.println("Au joueur NOIR de jouer");
		else 
			System.out.println("Au joueur BLANC de jouer");
		System.out.println();
		System.out.println();
	}
	
	/**
	 * Méthode permettant de savoir si une direction amène hors de plateau<BR>
	 * @param   x      x de notre case
	 * @param   y      y de notre caset
	 * @param   vX     la variation que prendra notre x
	 * @param   vY     la variation que prendra notre y
	 * @return (boolean) False si la direction arrive hors du plateau / True si la direction n'est pas hors du plateau
	 * 
	 * @author Samy Bencharef
     * @version 1.0
	 */
	private boolean testLimite(int x, int y, int vX, int vY) {
		if (x+vX<0 || x+vX>=nbCol || y+vY<0 || y+vY>=nbLign) {
			return false;
		} else {
			return true;
		}
	}
}
