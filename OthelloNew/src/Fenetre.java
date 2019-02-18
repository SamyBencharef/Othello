import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;



/**
 * IHM du jeu OTHELLO
 * 
 * Présentera le plateau sous la forme d'un tableau de JButton a deux dimensions
 * 
 * Les caractéristiques du jeu seront fixé par l'appel de la classe.<BR>
 * 
 * @see Plateau
 * @author Samy Bencharef 
 * @version 1.0
 */
public class Fenetre extends JFrame{
	
	// Nombre de ligne du plateau
	private int nbLine;
	
	// Nombre de colonne du plateau
	private int nbRowl;
	
	// Attribut reliant l'algorithme du jeu à la partie IHM
	private Plateau myGame;
	
	// Tableau de JButton présentant la grille
	private JButton plat[][] ;
	
	// Des JPanels utilisé pour la partie design.
	private JPanel grille;
	private JLabel joueur;
	private JPanel BlancSc;
	private JPanel NoirSc;
	
	// Des JLabel
	// Permettant de savoir le nombre de pions possédé par le joueur blanc
	private JLabel blanc;
	
	// Permettant de savoir le nombre de pions possédé par le joueur noir
	private JLabel noir;
	
	// Permettant de savoir le nombre de win fait par le joueur blanc
	private JLabel blancWin;
	
	// Permettant de savoir le nombre de win fait par le joueur noir
	private JLabel noirWin;
	
	// Nom de joueur blanc
	private String joueurBlanc;
	
	// Nom de joueur noir
	private String joueurNoir;
	
	// Identifiant du theme utilisé 
	private int Theme;
	
	// Identifiant du mode de jeux (2vs2, ia .. )
	private int mode;
	
	//Attribut servant au chrono ... Je tiens à remercier particulièrement le forum de Devellopons.com 
	// Qui ma aidé pour cette partie la. 
	private int heure=0;
	private int minute=0;
	private int seconde=0;
	private int delais=1000; // <-- Correspond à 1 seconde
	
	//Affichage de notre chrono
	private JLabel label=new JLabel(""+heure+":"+minute+":"+seconde);
	private ActionListener tache_timer;
	private Timer chrono;
	
	
	/**
	 * Constructeur de la fenetre du jeu Othello <BR>
	 * 
	 * @param nbLign sera la nombre de lignes de notre plateau
	 * @param nbColon sera la nombre de colonnes de notre plateau
	 * @param joueur1 sera le nom du premier joueur (pions noir)
	 * @param joueur2 sera le nom du second joueur (pions blanc)
	 * @param theme sera la theme qui sera présenté pour notre interface (Par défaut, reversi, donut ou pokémon) 
	 * @param modeP sera le mode de jeux ( 2vs2 , ia faible, ia moyen, ia fort)
	 * 
	 * 
	 * @author Samy bencharef
	 * @version 1.0
	 */
	 Fenetre(int nbLign, int nbColon, String joueur1, String joueur2, int theme, int modeP){
		   super("Othello "+nbLign+"x"+nbColon);
		   
		   // On donne les paramètres reçues a nos attributs
		   joueurNoir=joueur1;
		   joueurBlanc=joueur2;
		   nbLine = nbLign; 
		   nbRowl = nbColon;
		   Theme=theme;
		   mode=modeP;
		   
		   // On appelle notre algorithme en donnant en paramètre le nombre de ligne et de colonnes
		   this.myGame = new Plateau(nbLine, nbRowl);
		   
		   // On crée d'autre JPanel qui seront utile au design de la fenetre.
		   JPanel Quiquijoue = new JPanel();
		   JPanel score = new JPanel();
		   JPanel vic = new JPanel();
		   JPanel vicD = new JPanel();
		   JPanel vicG = new JPanel();
		   
		   // On initialise les JPanels qui ont déjà été crée en tant qu'attributs
		   grille = new JPanel();
		   BlancSc = new JPanel(new FlowLayout());
		   NoirSc = new JPanel(new FlowLayout());
		   
		   //On initialise les JLabel qui ont déjà été crée en tant qu'attributs
		   blanc = new JLabel();
		   noir = new JLabel();
		   blancWin = new JLabel();
		   noirWin = new JLabel();
		    
		   
		    // On donne à des JPanel un layoutManager pour le design
		    vic.setLayout(new BorderLayout());		   
		    score.setLayout(new BorderLayout()  );
			this.setLayout( new BorderLayout() );
			
			// On donne une bordure à notre JPanel de score pour le design
			score.setBorder(BorderFactory.createLoweredBevelBorder());
			
			
			//On ajoute nos JLabel à nos JPanel, ainsi que nos JPanel à notre JFrame (this)
			score.add(BlancSc, BorderLayout.WEST);
			score.add(NoirSc, BorderLayout.EAST);
			score.add(vic, BorderLayout.CENTER);
			this.add(score, BorderLayout.NORTH);
			this.add(grille, BorderLayout.CENTER);
			
			// On donne comme layoutManager à notre grille qui contiendra notre plateau 
			grille.setLayout(new GridLayout(nbLine,nbRowl));
			
			// On execute la fonction doIT(), qui permet d'initialise notre grile.
			plat = doIT();
			
			// Le prmeier joueur à jouer est le noir.
			joueur = new JLabel(joueurNoir+" à toi de jouer!");
			
			//On donne des couleurs de fond à notre Panel qui indique c'est à qui de jouer, 
			//de base noir puisque le premier à jouer et le joueur Noir
			Quiquijoue.setBackground(new Color(68,68,68));
			//On donne une couleur à notre Jpanel de score, blanc pour les blanc et noir atténué pour les noirs
			BlancSc.setBackground(Color.WHITE);
			NoirSc.setBackground(new Color(68,68,68));
			
			// On donne des couleurs à notre écriture, blanc
			noir.setForeground(Color.WHITE);
			joueur.setForeground(Color.WHITE);
			noirWin.setForeground(Color.WHITE);
			
			// On connecte notre musique de fond à notre programme
			java.net.URL url = getClass().getResource("/images/imperial.wav");
		    java.applet.AudioClip clip = Applet.newAudioClip(url);
			addWindowListener(new WindowListener() {
				@Override
				public void windowOpened(WindowEvent arg0) {
				    clip.play(); // Et on la déclenche au moment de l'ouverture de la fenetre
				}
				public void windowIconified(WindowEvent arg0) {
				}
				public void windowDeiconified(WindowEvent arg0) {	
				}
				@Override
				public void windowDeactivated(WindowEvent arg0) {
				}	
				@Override
				public void windowClosing(WindowEvent arg0) {
				}
				public void windowClosed(WindowEvent arg0) {
				}
				public void windowActivated(WindowEvent arg0) {	
				}
			});
			
			// On donne le compteur de pions à nos JLABEL
			blanc.setText(joueurBlanc+" = "+myGame.getNbBlanc()+" pions");
		    noir.setText(joueurNoir+" = "+myGame.getNbNoir()+" pions");
		    
		 // On donne le compteur de wins à nos JLABEL
		    blancWin.setText(joueurBlanc+" = "+Start.joueur2Win+" wins");
		    noirWin.setText(joueurNoir+" = "+Start.joueur1Win+" wins");
			
		    
		    // On commence un double for pour pouvoir parcourir la grille..
			 for( int i = 0 ; i < nbLine ; i++){
				for( int j = 0 ; j <nbRowl ; j++){
					//Par soucis de contrainte au sein des addXXXListener on connecte des variables à nos i et j
					int ligne=i;
					int colonne=j;
					
					if ( myGame.plateau[i][j].getAttri().equals("V")){ // Si la case i et j de notre plateau n'est pas vide
						plat[i][j].addMouseListener(new MouseListener() { // On commence un MouseListene
							public void mouseReleased(MouseEvent e) {
							}
							public void mouseExited(MouseEvent e) { // Si la souris sors de la case on redonnera à nos cases leurs allures de base
								switch (Theme){
								case 1:
									if( myGame.testCasePossible(ligne,colonne)>0 ){
										plat[ligne][colonne].setBackground(new Color(81,189,0));
										plat[ligne][colonne].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
									}
								break;
								
								case 2:
									if( myGame.testCasePossible(ligne,colonne)>0 ){
										plat[ligne][colonne].setBackground(new Color(2,98,38));
										plat[ligne][colonne].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
									}
								break;
								
								case 3:
									if( myGame.testCasePossible(ligne,colonne)>0 ){
										plat[ligne][colonne].setBackground(new Color(255,251,234));
										plat[ligne][colonne].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
									}
								break;
								
								case 4:
									if( myGame.testCasePossible(ligne,colonne)>0 ){
										plat[ligne][colonne].setBackground(new Color(255,255,172));
										plat[ligne][colonne].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
									}
								break;
							}
							}
							public void mouseEntered(MouseEvent e) { // Si la souris passe sur une case;
								Toolkit tk = Toolkit.getDefaultToolkit();
								Cursor Curseur = new Cursor(Cursor.HAND_CURSOR);//On initialise un nouveaux curseur (forme de main)
								switch (Theme){ // Celon le thème des changement auront lieux
								case 1:
									if( myGame.testCasePossible(ligne,colonne)>0 ){ // Si un pion peut être joué pour le joueur courant alors les changements ci dessous auront lieu
										plat[ligne][colonne].setBackground(new Color(24,130,50)); // Nouvelle couleur de fond 
										plat[ligne][colonne].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLineBorder(Color.black))); // nouvelle bordures
										plat[ligne][colonne].setCursor(Curseur); // et enfin l'application de notre curseur.
									}	
								break;
								                        // C'est la même chose pour tous le switch, juste les changements sont différent celon les thèmes.
								case 2:
									if( myGame.testCasePossible(ligne,colonne)>0 ){
										plat[ligne][colonne].setBackground(new Color(2,90,30));
										plat[ligne][colonne].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLineBorder(Color.black)));
										plat[ligne][colonne].setCursor(Curseur);
									}
								break;
								
								case 3:
									if( myGame.testCasePossible(ligne,colonne)>0 ){
										plat[ligne][colonne].setBackground(new Color(255,250,220));
										plat[ligne][colonne].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLineBorder(Color.black)));
										plat[ligne][colonne].setCursor(Curseur);
									}
								break;
								
								case 4:
									if( myGame.testCasePossible(ligne,colonne)>0 ){
										plat[ligne][colonne].setBackground(new Color(255,250,160));
										plat[ligne][colonne].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLineBorder(Color.black)));
										plat[ligne][colonne].setCursor(Curseur);
									}
								break;
								}
								
							}
							@Override
							public void mouseClicked(MouseEvent e) {
							}
							@Override
							public void mousePressed(MouseEvent e) {							
							}
							//Autre bug peut être que vous arriverez à m'aider, lorsque je clique sur un bouton, le bouton garde les changement qui ont lieu lorsqu'on passe la souris dessus.. 
						});
						
					}
					
					
					plat[i][j].addActionListener(new ActionListener() { // Si un clique à lieu sur une case : 
						public void actionPerformed(ActionEvent e) {
							myGame.EffectuerCoup(ligne, colonne); // On effecteur un coup sur notre plateau.. le traitement de l'erreur s'il y en a une se fera dans l'algorithme du jeu

							//Arrêt de l'ancien chrono avec lancement du nouveaux.
							chrono.stop();
							// On re initialise tous aux attributs de base
						    tache_timer=null;
							chrono=null;
						    delais=1000;
						    heure=0;
						    minute=0;
						    seconde=0;
						    chrono(); // et on relance le chrono ! 
						    
						    
						    // Bot random -- Bot faible 
						    if ( mode == 1 ){
						    	if ( myGame.getOuiNon()  && myGame.getnonVide() ){
						    		for (int i=0; i<nbLine;i++){
						    			for (int j=0; j<nbRowl;j++){
							    			if ( myGame.testCasePossible(i,j) > 0 ){  // Si une case peut être jouer quand c'est à lui il la joue 
							    				 myGame.EffectuerCoup(i, j); // Il est random puisqu'il joue la première qu'il trouve
							    				 i=nbLine-1;
							    				 break;
							    			}	
							    		}
						    		}
						    	}
					    	}
						    
						    //Bot max retournement -- Bot moyen 
						    if ( mode == 2 ){
						    	int me=0;
						    	int lo=0;
						    	int co=0;
						    	if ( myGame.getOuiNon()  && myGame.getnonVide() ){
						    		for (int i=0; i<nbLine;i++){
						    			for (int j=0; j<nbRowl;j++){
							    			if ( myGame.testCasePossible(i,j) > 0 ){ 
							    				 if ( me !=0 ) {
							    					 if ( me < myGame.testCasePossible(i,j) ){ // Ce bot prendra en mémoire toutes les possibilité qu'il a, ainsi que le nombre de case qu'il retourne par possiblité
							    						 me = myGame.testCasePossible(i,j);
							    						 lo = i;
							    						 co = j;
							    					 }
							    				 }
							    				 me= myGame.testCasePossible(i,j);
							    				 lo = i;
							    				 co = j;
							    			}	
							    		}
						    		}
						    		if (me!=0)
						    			myGame.EffectuerCoup(lo, co); // Il effectuera le coup qui retournera le maximum de case
						    	}
					    	}
						    
						    // Bot vise angle ou max retournement -- Et enfin le bot fort, pourquoi fort, car les angles sont un lieu stratégique. 
						    if ( mode == 3 ){
						    	int me=0;
						    	int lo=0;
						    	int co=0;
						    	if ( myGame.getOuiNon()  && myGame.getnonVide() ){
						    		for (int i=0; i<nbLine;i++){
						    			for (int j=0; j<nbRowl;j++){
							    			if ( myGame.testCasePossible(i,j) > 0 ){
							    				if ( i==0 && j==0 || i==0 && j==nbRowl || i==nbLine && j==0 ||  i==nbLine && j==nbRowl ){ // Si les angles peuvent être joué il le jouera sans se posé de question sur le nombre de retournement
							    					myGame.EffectuerCoup(i, co);
							    					me=0;
							    					i=nbLine-1;
								    				break;
							    				}else{
								    				 if ( me !=0 ) {
								    					 if ( me < myGame.testCasePossible(i,j) ){ 
								    						 me = myGame.testCasePossible(i,j);
								    						 lo = i;
								    						 co = j;
								    					 }
								    				 }
								    				 me= myGame.testCasePossible(i,j);
								    				 lo = i;
								    				 co = j;
							    				}
	 
							    			}	
							    		}
						    		}
						    		if (me!=0)
						    			myGame.EffectuerCoup(lo, co);// Par contre s'il n'a pas d'angles possible tout comme la précédente ia il retournera le maximum de pions 
						    	}
					    	}

						    
						    // Bien sur si j'aurai eu plus de temps, j'aurai pu crée une IA extrème , qui aurait pris en compte aussi le nombre de retournement par angles et qui aurait visé aussi les bords.
						    
						    
						    
						    // Maintenant que les retournements on eu lieu on actualise notre grille 
							for ( int i = 0 ; i < nbLine ; i++ ){ 
								for ( int j = 0 ; j < nbRowl ; j++ ){ 
									if ( myGame.plateau[i][j].getAttri().equals("N")){ // si on a un pion noir dans notre algorithme alors le bouton de la grille qui à les mêmes coordonnée aura une caractéristique
										switch (Theme){//Pour chaque thème on appliquera des changements de couleurs ou images 
											case 1:
												plat[i][j].setBackground(new Color(68,68,68));
											break;
											case 2:
												plat[i][j].setBackground(new Color(2,98,38));
												plat[i][j].setIcon( new ImageIcon(getClass().getResource("/images/ball.png")));
											break;	
											case 3:
												plat[i][j].setBackground(new Color(255,251,234));
												plat[i][j].setIcon( new ImageIcon(getClass().getResource("/images/donChoco.png")));
											break;	
											case 4:
												plat[i][j].setBackground(new Color(255,255,172));
												plat[i][j].setIcon( new ImageIcon(getClass().getResource("/images/hyBall.png")));
											break;
										}
									}else if ( myGame.plateau[i][j].getAttri().equals("B")){ // si on a un pion blanc dans notre algorithme a...
										switch (Theme){
											case 1:
												plat[i][j].setBackground(Color.WHITE);
											break;
											case 2:
												plat[i][j].setBackground(new Color(2,98,38));
												plat[i][j].setIcon( new ImageIcon(getClass().getResource("/images/ballW.png")));
											break;	
											case 3:
												plat[i][j].setBackground(new Color(255,251,234));
												plat[i][j].setIcon( new ImageIcon(getClass().getResource("/images/donRose.png")));
											break;
											case 4:
												plat[i][j].setBackground(new Color(255,255,172));
												plat[i][j].setIcon( new ImageIcon(getClass().getResource("/images/meBall.png")));
											break;
										}
										}else if( myGame.testCasePossible(i,j)>0 ){// si on a une case vide mais que la case peut être joué alors : 
											switch (Theme){
											case 1:
												plat[i][j].setBackground(new Color(81,189,0));
											break;
											case 2:
												plat[i][j].setBackground(new Color(2,98,38));
												plat[i][j].setIcon( new ImageIcon(getClass().getResource("/images/whitepiece.png")));
											break;
											case 3:
												plat[i][j].setBackground(new Color(255,251,234));
												plat[i][j].setIcon( new ImageIcon(getClass().getResource("/images/whitepiece.png")));
											break;	
											case 4:
												plat[i][j].setBackground(new Color(255,255,172));
												plat[i][j].setIcon( new ImageIcon(getClass().getResource("/images/whitepiece.png")));
											break;
										}
									}else{ // et enfin si la case est juste vide.. 
										switch (Theme){
											case 1:
												plat[i][j].setIcon(null);
												plat[i][j].setBackground(new Color(24,131,56));
											break;
											case 2:
												plat[i][j].setIcon(null);
												plat[i][j].setBackground(new Color(2,98,38));
											break;
											case 3:
												plat[i][j].setIcon(null);
												plat[i][j].setBackground(new Color(255,251,234));
											break;
											case 4:
												plat[i][j].setIcon(null);
												plat[i][j].setBackground(new Color(255,255,172));
											break;
										}
									}
							}
							}
							
							// Si le nouveaux joueur courant dans l'algorithme est le joueur blanc on modifie la le Jpanel ainsi que le JLabel associé au joueurs courant
						    if ( myGame.getJoueur().equals("B")){
								joueur.setText(joueurBlanc+" à toi de jouer!");
								joueur.setForeground(new Color(68,68,68));
								Quiquijoue.setBackground(Color.WHITE);
							}else{//Pareil pour le cas ou le joueur courant est le joueur noir
								joueur.setText(joueurNoir+" à toi de jouer!");
								joueur.setForeground(Color.WHITE);
								Quiquijoue.setBackground(new Color(68,68,68));
							}
						    
						    //On actualise également le nombre de pions de chaque joueur
						    blanc.setText(joueurBlanc+" = "+myGame.getNbBlanc());
						    noir.setText(joueurNoir+" = "+myGame.getNbNoir());
						    
						  
						    // Si l'algorithme s'arrête alors; 
						    if( myGame.getStop() ){
						    	getContentPane().removeAll();// on vide le contenue de notre fenetre
						    	clip.stop(); // On arrête la musique 
						    	
						    	//et on crée une petit interface design disant qui a gagner. 
						    	joueur.setText("Partie terminé");
						    	JPanel gagnant = new JPanel();
						    	JPanel bot = new JPanel();
						    	JPanel top = new JPanel();
						    	
						    	JLabel sc1 = new JLabel(joueurBlanc+" = "+myGame.getNbBlanc());
						    	JLabel sc2 = new JLabel(joueurNoir+" = "+myGame.getNbNoir());
						    	
						    	if ( myGame.nbBlanc > myGame.nbNoir){// le blanc win..
						    		Start.joueur2Win= Start.joueur2Win + 1; // Ici on augmente le compteur de win pour le joueur blanc
						    		Start.saves.add(new Save(myGame.getNbBlanc(), myGame.getNbNoir(),joueurBlanc, joueurNoir, joueurBlanc )); // On sauvegarde la partie dans notre classe sauvegarde..
						    	}else if ( myGame.nbBlanc < myGame.nbNoir){// le noir win..
						    		Start.joueur1Win= Start.joueur1Win + 1;// Ici on augmente le compteur de win pour le joueur noir 
						    		Start.saves.add(new Save(myGame.getNbBlanc(), myGame.getNbNoir(),joueurBlanc, joueurNoir, joueurNoir ));// On sauvegarde la partie dans notre classe sauvegarde..
						    	}else{// et s'il y a égalité.. on sait jamais.. 
						    		Start.joueur2Win= Start.joueur2Win + 1;
						    		Start.joueur1Win= Start.joueur1Win + 1;
						    		Start.saves.add(new Save(myGame.getNbBlanc(), myGame.getNbNoir(),joueurBlanc, joueurNoir, "Egalite" ));
						    		
						    	}
   					    
						    	//On ajoute le score au final  
						    	top.setLayout(new FlowLayout());
						    	top.add(sc1);
						    	top.add(sc2);
						    	
						    	// INITIALISATION DES GAGNANT.. la partie desgin de la fenetre quoi.. celon chaque thème.. 
						    	JLabel gg = new JLabel();
						    	switch (Theme){
								case 1: case 2:
							    	if ( myGame.nbBlanc > myGame.nbNoir){
							    		gg.setText(joueurBlanc+" à gagné.");
							    		gg.setIcon(new ImageIcon(getClass().getResource("/images/trophe.png")));
							    		gg.setForeground(new Color(68,68,68));
							    		sc1.setForeground(new Color(68,68,68));
							    		sc2.setForeground(new Color(68,68,68));
							    		top.setBackground(new Color(243,243,243));
							    		gagnant.setBackground(new Color(243,243,243));
							    		bot.setBackground(new Color(243,243,243));
							    	}else if ( myGame.nbBlanc < myGame.nbNoir){
							    		gg.setText(joueurNoir+" à gagné.");
							    		gg.setIcon(new ImageIcon(getClass().getResource("/images/trophe.png")));
							    		gg.setForeground(new Color(243,243,243));
							    		sc1.setForeground(new Color(243,243,243));
							    		sc2.setForeground(new Color(243,243,243));
							    		top.setBackground(new Color(68,68,68));
							    		gagnant.setBackground(new Color(68,68,68));
							    		bot.setBackground(new Color(68,68,68));
							    	}else{
							    		gg.setText(" EGALITE ");
							    		gg.setForeground(new Color(201,117,3));
							    		sc1.setForeground(new Color(201,117,3));
							    		sc2.setForeground(new Color(201,117,3));
							    		top.setBackground(new Color(141,141,141));
							    		gagnant.setBackground(new Color(141,141,141));
							    		bot.setBackground(new Color(141,141,141));
							    	}
							    	break;
									
								case 3:
						    		gg.setForeground(new Color(68,68,68));
						    		sc1.setForeground(new Color(68,68,68));
						    		sc2.setForeground(new Color(68,68,68));
						    		top.setBackground(new Color(255,251,234));
						    		gagnant.setBackground(new Color(255,251,234));
						    		bot.setBackground(new Color(255,251,234));
									if ( myGame.nbBlanc > myGame.nbNoir){
							    		gg.setText(joueurBlanc+" à gagné.");
							    		gg.setIcon(new ImageIcon(getClass().getResource("/images/donutR.png")));
							    	}else if ( myGame.nbBlanc < myGame.nbNoir){
							    		gg.setText(joueurNoir+" à gagné.");
							    		gg.setIcon(new ImageIcon(getClass().getResource("/images/donut.png")));
							    	}else{
							    		gg.setText(" EGALITE ");
							    		gg.setIcon(new ImageIcon(getClass().getResource("/images/donutE.png")));
							    	}
									break;
									
								case 4:
									gg.setIcon(new ImageIcon(getClass().getResource("/images/crown.png")));
						    		gg.setForeground(new Color(68,68,68));
						    		sc1.setForeground(new Color(68,68,68));
						    		sc2.setForeground(new Color(68,68,68));
						    		top.setBackground(new Color(255,255,172));
						    		gagnant.setBackground(new Color(255,255,172));
						    		bot.setBackground(new Color(255,255,172));
									if ( myGame.nbBlanc > myGame.nbNoir){
							    		gg.setText(joueurBlanc+" à gagné.");  
							    	}else if ( myGame.nbBlanc < myGame.nbNoir){
							    		gg.setText(joueurNoir+" à gagné.");
							    	}else{
							    		gg.setText(" EGALITE ");
							    	}
									break;
									
									
						    	}
									
						    	Font font = new Font("Arial",Font.BOLD,17);
					    		gg.setFont(font);
						    	
						    	gagnant.add(gg);
						    	
						    	//On crée et paramètre le bouton de retour vers l'accueil
						    	JButton acc = new JButton("Accueil");
						    	
						    	bot.add(acc);
						    	
						    	setSize(new Dimension(280, 200));
						    	setLocationRelativeTo(null);
						    	setLayout(new BorderLayout() );
						    	add(gagnant, BorderLayout.CENTER);
						    	add(bot, BorderLayout.SOUTH);
						    	add(top, BorderLayout.NORTH);
						    	
						    	acc.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										dispose();
										Start.prin.setVisible(true);
									}
								});
						    	
						    	
						    }						 
						}	
					});
					grille.add(plat[i][j]) ; // On ajoute notre tableau de jbutton à notre grille.
				}
			 }

			 
			 BlancSc.add(blanc);
			 NoirSc.add(noir);
			 
			 // On place le nombre de win de chaque joueurs et on le rend design.. 
			 vicD.setLayout(new BorderLayout());
			 vicG.setLayout(new BorderLayout());
			 
			 vicD.setBackground(Color.WHITE);
			 vicG.setBackground(new Color(68,68,68));
			 
			 vicD.add(blancWin, BorderLayout.CENTER);
			 vicG.add(noirWin, BorderLayout.CENTER);
			 
			 vic.add(vicD, BorderLayout.WEST);
			 vic.add(vicG, BorderLayout.EAST);
			 
			 // On ajoute le chrono dans notre fenetre
			 JPanel time = new JPanel();
			 time.add(label);
			 vic.add(time, BorderLayout.CENTER);
			 
			 // On démare le chrono 
			 chrono();
			 
			 // On crée des bordures pour les différentes parties de notre fenetre.. jolie jolie.. 
			 Border raisedbevel = BorderFactory.createRaisedBevelBorder();
			 Border loweredbevel = BorderFactory.createLoweredBevelBorder();
			 Quiquijoue.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
			 Quiquijoue.add(joueur);
			 this.add(Quiquijoue, BorderLayout.SOUTH);
			 
			 
			 // On prévoit plusieurs dimensions de fentre celon le nombre de grille.. 
			if ( nbColon<= 4 ){
				this.setSize(new Dimension(700, 700));
			}else if ( nbColon <= 6){
				this.setSize(new Dimension(1000, 1000));
			}else if ( nbColon <= 8){
				this.setSize(new Dimension(1200, 1200));
			}else
				this.setSize(new Dimension(1300, 1300));
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	 
	 /**
		 * Methode donnant une première allure à notre plateau. <BR>
		 * 
		 * 
		 * @return (JButton[][]) tab 
		 * @see class Plateau
		 * @author Samy Bencharef
		 * @version 1.0
		 */
	 JButton[][] doIT(){
		 JButton tab[][]= new JButton[nbLine][nbRowl];
		 for( int i = 0 ; i < nbLine ; i++){
			for( int j = 0 ; j < nbRowl ; j++){
				tab[i][j] = new JButton();
				tab[i][j].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
				if ( myGame.plateau[i][j].getAttri().equals("N")){
					switch (Theme){
						case 1:
							tab[i][j].setBackground(new Color(68,68,68));
						break;
						case 2:
							tab[i][j].setBackground(new Color(2,98,38));
							tab[i][j].setIcon( new ImageIcon(getClass().getResource("/images/ball.png")));
						break;
						case 3:
							tab[i][j].setBackground(new Color(255,251,234));
							tab[i][j].setIcon( new ImageIcon(getClass().getResource("/images/donChoco.png")));
						break;
						case 4:
							tab[i][j].setBackground(new Color(255,255,172));
							tab[i][j].setIcon( new ImageIcon(getClass().getResource("/images/hyBall.png")));
						break;
					}
				}else if( myGame.plateau[i][j].getAttri().equals("B")){
					switch (Theme){
						case 1:
							tab[i][j].setBackground(Color.WHITE);
						break;
						case 2:
							tab[i][j].setBackground(new Color(2,98,38));
							tab[i][j].setIcon( new ImageIcon(getClass().getResource("/images/ballW.png")));
						break;
						case 3:
							tab[i][j].setBackground(new Color(255,251,234));
							tab[i][j].setIcon( new ImageIcon(getClass().getResource("/images/donRose.png")));
						break;	
						case 4:
							tab[i][j].setBackground(new Color(255,255,172));
							tab[i][j].setIcon( new ImageIcon(getClass().getResource("/images/meBall.png")));
						break;
					}
				}else if( myGame.testCasePossible(i,j)>0 ){
					switch (Theme){
						case 1:
							tab[i][j].setBackground(new Color(81,189,0));
						break;
						case 2:
							tab[i][j].setBackground(new Color(2,98,38));
							tab[i][j].setIcon( new ImageIcon(getClass().getResource("/images/whitepiece.png")));
						break;
						case 3:
							tab[i][j].setBackground(new Color(255,251,234));
							tab[i][j].setIcon( new ImageIcon(getClass().getResource("/images/whitepiece.png")));
						break;	
						case 4:
							tab[i][j].setBackground(new Color(255,255,172));
							tab[i][j].setIcon( new ImageIcon(getClass().getResource("/images/whitepiece.png")));
						break;
					}
				}else{
					switch (Theme){
						case 1:
							tab[i][j].removeAll();
							tab[i][j].setBackground(new Color(24,131,56));
						break;
						case 2:
							tab[i][j].removeAll();
							tab[i][j].setBackground(new Color(2,98,38));
						break;	
						case 3:
							tab[i][j].removeAll();
							tab[i][j].setBackground(new Color(255,251,234));
						break;	
						case 4:
							tab[i][j].removeAll();
							tab[i][j].setBackground(new Color(255,255,172));
						break;
					}
				}
			}
		}
		return tab;
	 }	 
	 
	 
	 /**
		 * Methode qui chronomètre le temps qui passe. <BR>
		 * Permet de calculer le temps de réflexion de chanque joueur <BR>
		 * et d'affiché en temp réel le chrono de chaque joueur <BR>
		 * Je tiens particulièrement à remercier le Forum de Developpons.com qui a été d'une grande aide pour cette méthode.<BR>
		 * 
		 * @author Samy Bencharef
		 * @version 1.0
		 */
	 public void chrono(){
			tache_timer= new ActionListener()  {
			  public void actionPerformed(ActionEvent e1)  {
			    seconde++;
			     if(seconde==60)  {
			          seconde=0;
			          minute++;
			      }
			  if(minute==60) {
			      minute=0;
			      heure++;
			   }
			  //Afficher le chrono dans un JLabel
			  label.setText(heure+":"+minute+":"+seconde);
	 
			 }
	     };
	     //Action et temps execution de la tache
	     chrono=new Timer(delais,tache_timer);
	     //Demarrer le chrono
	     chrono.start();
		}

}
