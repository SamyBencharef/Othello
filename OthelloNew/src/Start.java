import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;


/**
 * Main du projet, comportement également la fenetre de départ du jeu. <BR>
 * 
 * La fenetre sera composé d'un menu comportant un accès au jeux, un aux règles, un aux sauvegardes et enfin un aux sources<BR>
 * 
 * @author Samy Bencharef 
 * @version 1.0
 */
public class Start{
	//ArrayList de sauvegardes des anciennes parties
	static ArrayList<Save> saves = new ArrayList<>();
	
	// Menu de notre fenetre
	private JMenuBar menu = new JMenuBar();

	//Les differents boutons utilisés dans notre menus
	private JButton menu1 = new JButton("Jouer");
	private JButton menu2 = new JButton("Règles");
	private JButton menu3 = new JButton("Resultats");
	private JButton menu4 = new JButton("Sources");
	
	private JPanel Centre = new JPanel();
	private JLabel copy = new JLabel("©SamyBencharef 2017");
	
	// Une JFrame static pour pouvoir la manipulé dans la classe fenetre.
	static JFrame prin = new JFrame("Othello");
	
	//Ainsi que les deux compteurs de win en static pour aussi les manipulés dans fenetres.
    static int joueur1Win;
    static int joueur2Win;
    
    
    /**
	 * Constructeur de la fenetre de départ <BR>
	 * 
	 * @author Samy bencharef
	 * @version 1.0
	 */
	Start(){
		 
		// On initialise nos compteurs de win à 0 au lancement du jeux
		joueur1Win=0;
		joueur2Win=0;
		 
		// On met un petit fond pour l'accueil
		 JLabel fond = new JLabel(new ImageIcon(getClass().getResource("/images/Reversi2.png")));
		 Centre.setBackground(new Color(101,78,54));
		 Centre.add(fond);
		 
		 // on place tous nos boutons dans notre menu
		 prin.add(Centre, BorderLayout.CENTER);
		 menu.add(menu1);
		 menu.add(menu2);
		 menu.add(menu3);
		 menu.add(menu4);
		 menu.add(copy);
		 
		 JPanel haut = new JPanel();
		 haut.add(menu);
		 
		 haut.setBackground(new Color(101,78,54));
		 
		 prin.add(haut, BorderLayout.NORTH);
		 
		 prin.setBackground(new Color(101,78,54));
		 menu.setBackground(new Color(101,78,54));
		 menu1.setBackground(Color.WHITE);
		 menu2.setBackground(Color.WHITE);
		 menu3.setBackground(Color.WHITE);
		 menu4.setBackground(Color.WHITE);
		 copy.setForeground(Color.BLACK);
		 
		 
		 // Si on clique sur le menu4 --> les sources 
		 menu4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prin.setVisible(false); // On cache notre fenetre accueil
				JFrame fene4 = new JFrame("Sources"); // Et on crée un nouvelle fenetre comportant nos sources
				JButton retour4 = new JButton("Retour"); // On crée un bouton retour pour revenir sur l'accueil
				fene4.setLayout(new GridLayout(4,1) );
				
				// On crée nos trois JLabel comportant les sources
				JLabel musique = new JLabel("<html>Musique:  <FONT color=\"blue\"><u>http://www.moviewavs.com/Movies.html</u><FONT></html>");
				
				JLabel icone = new JLabel("<html>Les icones : <FONT color=\"blue\"><u>http://www.flaticon.com</u><FONT></html>");
				
				JLabel imageOthello = new JLabel("<html>Image du début (othello) : <FONT color=\"blue\"><u>https://www.google.com/imghp?hl=FR</u><FONT></html>");
				
				// Et pour chaque sources on mets un MousseListener
				musique.addMouseListener(new MouseListener() {
					public void mouseReleased(java.awt.event.MouseEvent e) {
					}
					public void mousePressed(java.awt.event.MouseEvent e) {
					}
					public void mouseExited(java.awt.event.MouseEvent e) {
					}
					public void mouseEntered(java.awt.event.MouseEvent e) { // Qui au moment du passage de la souris sur le lieux on aura un curseur en forme de main
						Toolkit tk = Toolkit.getDefaultToolkit();
						Cursor Curseur = new Cursor(Cursor.HAND_CURSOR);
						musique.setCursor(Curseur);
					}
					public void mouseClicked(java.awt.event.MouseEvent arg0) { // et au moment du clique on a une redirection vers les sites proprietaires des sources
						if(Desktop.isDesktopSupported())
						{
						  try {
							Desktop.getDesktop().browse(new URI("http://www.moviewavs.com/Movies.html"));
						} catch (IOException e) {
							e.printStackTrace();
						} catch (URISyntaxException e) {
							e.printStackTrace();
						}
						}
					}
				});
				
				icone.addMouseListener(new MouseListener() {
					public void mouseReleased(java.awt.event.MouseEvent e) {
					}
					public void mousePressed(java.awt.event.MouseEvent e) {
					}
					public void mouseExited(java.awt.event.MouseEvent e) {
					}
					public void mouseEntered(java.awt.event.MouseEvent e) {
						Toolkit tk = Toolkit.getDefaultToolkit();
						Cursor Curseur = new Cursor(Cursor.HAND_CURSOR);
						musique.setCursor(Curseur);
					}
					public void mouseClicked(java.awt.event.MouseEvent arg0) {
						if(Desktop.isDesktopSupported())
						{
						  try {
							Desktop.getDesktop().browse(new URI("http://www.flaticon.com"));
						} catch (IOException e) {
							e.printStackTrace();
						} catch (URISyntaxException e) {
							e.printStackTrace();
						}
						}
					}
				});
				
				imageOthello.addMouseListener(new MouseListener() {
					public void mouseReleased(java.awt.event.MouseEvent e) {
					}
					public void mousePressed(java.awt.event.MouseEvent e) {
					}
					public void mouseExited(java.awt.event.MouseEvent e) {
					}
					public void mouseEntered(java.awt.event.MouseEvent e) {
						Toolkit tk = Toolkit.getDefaultToolkit();
						Cursor Curseur = new Cursor(Cursor.HAND_CURSOR);
						musique.setCursor(Curseur);
					}
					public void mouseClicked(java.awt.event.MouseEvent arg0) {
						if(Desktop.isDesktopSupported())
						{
						  try {
							Desktop.getDesktop().browse(new URI("https://www.google.com/imghp?hl=FR"));
						} catch (IOException e) {
							e.printStackTrace();
						} catch (URISyntaxException e) {
							e.printStackTrace();
						}
						}
					}
				});
				
				
				// On ajoute tous ça dans notre fenetre
				fene4.add(musique);
				fene4.add(icone);
				fene4.add(imageOthello);
				fene4.add(retour4);
				
				retour4.addActionListener(new ActionListener() {// et si on clique sur le bouton retour
					public void actionPerformed(ActionEvent e) {
						fene4.dispose(); // On dipose() notre fenetre de sources
						prin.setVisible(true); // on fait re apparaitre notre fenetre d'accueil
					}
				});
				
				fene4.setSize(400,250); // Taille prédéfinit de notre fenetre de source
				fene4.setLocationRelativeTo(null); // On la place au centre de notre écran
				fene4.setVisible(true); // evident.. 
				fene4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pareil
			}
		});
		 
		// Si on clique sur le menu3 --> les résultats des précedents partie
		 menu3.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				prin.setVisible(false);// on cache la fenetre d'accueil
				JFrame fene3 = new JFrame("Résultats"); // et on crée la fenetre de résultat
				JButton retour3 = new JButton("Retour"); // on crée notre bouton retour
				fene3.setLayout(new BorderLayout() );
				fene3.add(retour3, BorderLayout.SOUTH);
				
				if (saves.size()>0 ){ // Si notre arrayList de sauvegarde n'est pas de vide on aura ce contenu: 
					// On crée un tableau de titre pour notre JTable
					String title[] = {"Partie", "Score Blanc", "Score Noir", "Gagnant"}; 
					
					int cmpt = 1;				
				
					// On crée un tableau d'Object qui sera composé des données récupéré dans notre sauvegarde
					Object[][] data = new Object[saves.size()][4];
					for ( int i=0 ; i<saves.size() ; i++){
							data[i][0] = "Partie"+Integer.toString(cmpt);
							data[i][1] = saves.get(i).getJN()+" : "+Integer.toString(saves.get(i).getScN());
							data[i][2] = saves.get(i).getJB()+" : "+Integer.toString(saves.get(i).getScB());
							data[i][3] = saves.get(i).getGG();
							
							cmpt++;
					}
					
					// enfin on crée notre JTable.
					JTable mySav = new JTable(data, title);
					
					// et on l'ajoute à notre fenetre.. en faisant attention d'aussi ajouté la tête de notre JTable.
					fene3.add(mySav.getTableHeader(), BorderLayout.NORTH);
					fene3.add(new JScrollPane(mySav), BorderLayout.CENTER);
					
				}else{ // si on a fait aucune partie avant, alors on a aucune sauvegarde et donc : 
					JLabel txt = new JLabel("Aucune partie n'a été faite");
					fene3.add(txt, BorderLayout.CENTER);
				}
					
				retour3.addActionListener(new ActionListener() { // Pareil que les autres retour.. 
					public void actionPerformed(ActionEvent e) {
						fene3.dispose();
						prin.setVisible(true);
					}
				});
				
				fene3.setSize(400,250); // Pareil qu'avant.. je ne re écrierai pas pour la suite.. 
				fene3.setLocationRelativeTo(null);
				fene3.setVisible(true);
				fene3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
				
			}
		});
		 
		// Si on clique sur le menu2 --> les règles 
		 menu2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prin.setVisible(false); //on cache la fenetre.. 
				JFrame fene1 = new JFrame("Règles"); // crée la nouvelle fenetre 
				JButton retour = new JButton("Retour"); // notre bouton retour
				
				// ++ Oui j'ai redigéré tout dans des JLabels puisque faire un simple screenshot du sujet procurer un image trop volumineuse.. (200KO :O)
				
				
				// Les Jpanel comportant nos règles, pourquoi deux ? 
				JPanel reg1 = new JPanel(); // Car celui ci redigera vers le site officel de othello :D 
				JPanel resu1 = new JPanel(); // et celui sera le résumé que Fabrice Pelleau à bien rédiger.. 
				
				fene1.setLayout(new BorderLayout() ); 
				// J'utilise les balises HTML qui sont bien utile pour manipulé la forme du texte à l'intérieur d'un JLabel :d 
				       	JLabel reg = new JLabel("<html><dt>Vous trouverez des explications très détaillées sur le site officiel de la Fédération Française <br>"+
										        "d’Othello (FFO) : <FONT color=\"blue\"><u>http://www.ffothello.org/othello/regles-du-jeu/</u><FONT></dt></html>");
							JLabel resu =  new JLabel("<html><br><dt>Voici une version résumée :<br></dt>"+
												 "<li>On joue :<br>"+
												 " <ol>Sur une grille 8x8<br>"+
												 " Avec des pions noir et blanc</ol><br>"+
												 " <li>Au départ :<br>"+
												 " <ol>La position est fixée<br>"+
												 " Les noirs commencent</ol><br>"+
												 " <li>A chaque coup :<br>"+
												 " <ol>On doit jouer sur une case vide collée à une case adverse<br>"+
												 " On doit encadrer un ou plusieurs pions adverse à l’aide du nouveau pion et d’un (ou plusieurs) pions déjà posé<br>"+
												 " Les pions encadrés sont retournés et changent de couleur</ol><br>"+
												 " <li>Si on ne peut pas jouer (aucune case ne permettant d’encadrer des pions adverse)<br>"+
												 " <ol>on passe son tour</ol><br>"+
												 " <li>Le jeu est terminé<br>"+
												 " <ol>Quand plus aucun joueur ne peut jouer  </ol></html>");
				
		 			
					// On mets tous ça sur un fond blanc comme sur le sujet.. 
				    reg1.setBackground(Color.WHITE);
				    resu1.setBackground(Color.WHITE);
				    
				    // on ajoute tous ça dans nos jpanel
				    reg1.add(reg);
				    resu1.add(resu);
				    
				    // et on place nos jpanel dans notre fenetre 
				    fene1.add(resu1,BorderLayout.CENTER );
					fene1.add(reg1, BorderLayout.NORTH );
					
					// on crée un mouseListener pour la redirection vers le lien du site officiel de othello
					reg.addMouseListener(new MouseListener() {
								public void mouseReleased(java.awt.event.MouseEvent e) {
								}
								public void mousePressed(java.awt.event.MouseEvent e) {
								}
								public void mouseExited(java.awt.event.MouseEvent e) {
								}
								public void mouseEntered(java.awt.event.MouseEvent e) {
									Toolkit tk = Toolkit.getDefaultToolkit();
									Cursor Curseur = new Cursor(Cursor.HAND_CURSOR); // Pour que le curseur devienne une main 
									reg.setCursor(Curseur);
								}
								public void mouseClicked(java.awt.event.MouseEvent arg0) {
									if(Desktop.isDesktopSupported())
									{
									  try {
										Desktop.getDesktop().browse(new URI("http://www.ffothello.org/othello/regles-du-jeu/")); // la redirection.. 
									} catch (IOException e) {
										e.printStackTrace();
									} catch (URISyntaxException e) {
										e.printStackTrace();
									}
									}
								}
							});
							
				JLabel im = new JLabel(new ImageIcon(getClass().getResource("/images/RegleJeux.jpg"))); // On mets l'image d'un plateau othello
				JPanel im1 = new JPanel(new BorderLayout());
				im1.add(im, BorderLayout.CENTER);
				im1.setBackground(Color.WHITE);	
				fene1.add(im1, BorderLayout.EAST);
				fene1.add(retour, BorderLayout.SOUTH);
				
				retour.addActionListener(new ActionListener() { // et le retour comme d'habitude.. 
					public void actionPerformed(ActionEvent e) {
						fene1.dispose();
						prin.setVisible(true);
					}
				});
				
				fene1.setSize(new Dimension(1000, 600)); 
				fene1.setLocationRelativeTo(null);
				fene1.setVisible(true);
				fene1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
			}
		});
		 
		 // Si on clique sur le menu1 --> la redirection dans le menu de parametrage de la partie.. 
		 menu1.addActionListener( new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				prin.setVisible(false); // On cache notre menu d'accueil
				JFrame fene = new JFrame("Paramètres"); // On crée notre fenetre de paramètre
				JPanel param = new JPanel();
				JPanel NomJoueur = new JPanel();
				ButtonGroup groupTaille = new ButtonGroup(); // Les buttons group qu'on utilisera dans notre fenetre.. 
				ButtonGroup groupAppa = new ButtonGroup();
				ButtonGroup groupVs = new ButtonGroup();
				
				param.setBorder(new CompoundBorder(param.getBorder(), new EmptyBorder(8, 8, 8, 8))); // la bordure de la fenetre design.. 
				
				fene.add(param);
				
				//Initialisaion partie nom et confirmer 
				JPanel Bas = new JPanel();
				JPanel BasHaut = new JPanel();
				JTextArea joue1 = new JTextArea("Noir");
				joue1.setPreferredSize(new Dimension(100, 20));
				JTextArea joue2 = new JTextArea("Blanc");
				joue2.setPreferredSize(new Dimension(100, 20));
				JLabel joueurBlanc = new JLabel("Joueur Noir");
				JLabel joueurNoir = new JLabel("Joueur Blanc");
				JPanel butBot = new JPanel();
				JButton confi = new JButton("Confirmer");
				JButton ret = new JButton("Retour");
				
				
				//Initialisation partie taille
				JPanel Gauche = new JPanel();
				JPanel tailleP = new JPanel();
				JRadioButton quatre = new JRadioButton();
				quatre.setSelected(true);
				JRadioButton six = new JRadioButton();
				JRadioButton huit = new JRadioButton();
				JRadioButton choisi = new JRadioButton();
				JLabel quatresL = new JLabel("4x4");
				JLabel sixL = new JLabel("6x6");
				JLabel huitL = new JLabel("8x8");
				JLabel choisiL = new JLabel("Choisissez");
				
				JPanel petigauche = new JPanel();
				JPanel petidroit = new JPanel();
				JTextArea larg = new JTextArea("4");
				larg.setPreferredSize(new Dimension(10, 20));
				JTextArea longe = new JTextArea("4");
				longe.setPreferredSize(new Dimension(10, 20));
				JLabel longueur = new JLabel("Largeur");
				JLabel largeur = new JLabel("Longueur");
				
				groupTaille.add(quatre);
				groupTaille.add(six);
				groupTaille.add(huit);
				groupTaille.add(choisi);
				
				//Initalisation mode
				JPanel Mid = new JPanel();
				Mid.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE),"Adversaire"));
				JRadioButton deVSde = new JRadioButton();
				JRadioButton botId = new JRadioButton();
				JRadioButton botR = new JRadioButton();
				JRadioButton botBR = new JRadioButton();
				
				JLabel deVSdeL = new JLabel("2 joueurs");
				JLabel botIdL = new JLabel("IA faible");
				JLabel botRL = new JLabel("IA moyen");
				JLabel botBRL = new JLabel("IA fort");
				
				deVSde.setSelected(true);
				groupVs.add(deVSde);
				groupVs.add(botId);
				groupVs.add(botR);
				groupVs.add(botBR);
				
				//Initialisaiton partie apparence
				JPanel Droite = new JPanel();
				Droite.setPreferredSize(new Dimension(150,150));
				JPanel tailleD = new JPanel();
				JRadioButton ap1 = new JRadioButton();
				JPanel choix = new JPanel();
				ap1.setSelected(true);
				JRadioButton ap2 = new JRadioButton();
				JRadioButton ap3 = new JRadioButton();
				JRadioButton ap4 = new JRadioButton();
				JLabel ap1L = new JLabel("Par défault");
				JLabel ap2L = new JLabel("Reversi");
				JLabel ap3L = new JLabel("Donut");
				JLabel ap4L = new JLabel("Pokemon");
				groupAppa.add(ap1);
				groupAppa.add(ap2);
				groupAppa.add(ap3);
				groupAppa.add(ap4);
								
				param.setLayout( new BorderLayout() );			
				param.add(NomJoueur, BorderLayout.SOUTH);
				param.add(Gauche, BorderLayout.WEST);
				param.add(Droite, BorderLayout.EAST);
				param.add(BasHaut, BorderLayout.SOUTH);
				
				
				//PartieBas ( nom des joueurs)
				BasHaut.setLayout(new BorderLayout() );
				BasHaut.add(Bas, BorderLayout.NORTH);
				Bas.setLayout(new FlowLayout() );
				Bas.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE),"Nom")); 
				Bas.add(joueurBlanc);
				Bas.add(joue1);
				Bas.add(joueurNoir);
				Bas.add(joue2);
				
				butBot.add(ret);
				butBot.add(confi);
				BasHaut.add(butBot, BorderLayout.SOUTH);
				
				
				//Partie apparence (choix du theme)
				Droite.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE),"Apparences"));
				Droite.setLayout(new BorderLayout() );
				tailleD.setLayout(new GridLayout(6, 2));
				Droite.add(tailleD, BorderLayout.CENTER);
				tailleD.add(ap1);
				tailleD.add(ap1L);
				tailleD.add(ap2);
				tailleD.add(ap2L);
				tailleD.add(ap3);
				tailleD.add(ap3L);
				tailleD.add(ap4);
				tailleD.add(ap4L);
				
				//Partie Adversaire(choix de l'ia ou pas)
				JPanel tailleMid = new JPanel();
				Mid.setLayout(new GridLayout(4, 1));
				
				JPanel de = new JPanel(new FlowLayout());
				de.add(deVSde);
				de.add(deVSdeL);
				Mid.add(de);
				
				JPanel de1 = new JPanel(new FlowLayout());
				de1.add(botId);
				de1.add(botIdL);
				Mid.add(de1);
				
				JPanel de2 = new JPanel(new FlowLayout());
				de2.add(botR);
				de2.add(botRL);
				Mid.add(de2);
				
				JPanel de3 = new JPanel(new FlowLayout());
				de3.add(botBR);
				de3.add(botBRL);
				Mid.add(de3);

				
				param.add(Mid, BorderLayout.CENTER);
				
				//Partie Taille ( choix de la taille de la fenetre)
				choix.setLayout(new BorderLayout() );
				Gauche.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE),"Taille"));
				Gauche.setLayout(new BorderLayout() );
				tailleP.setLayout(new GridLayout(5, 2));
				Gauche.add(tailleP, BorderLayout.CENTER);
				Gauche.add(choix, BorderLayout.SOUTH);
				tailleP.add(quatre);
				tailleP.add(quatresL);
				tailleP.add(six);
				tailleP.add(sixL);
				tailleP.add(huit);
				tailleP.add(huitL);
				tailleP.add(choisi);
				tailleP.add(choisiL);
				choix.add(petidroit, BorderLayout.WEST);
				choix.add(petigauche, BorderLayout.EAST);
				petidroit.add(longueur);
				petidroit.add(longe);
				petigauche.add(largeur);
				petigauche.add(larg);

				
				ret.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) { // On revient à l'accueil.. 
						fene.dispose(); 
						prin.setVisible(true);
					}
				});
				
				confi.addActionListener(new ActionListener() { // si on confirme notre choix.. 
					public void actionPerformed(ActionEvent e) {
						
						// On récupere les données celon les choix de chaques partie.. 
						
						// celon le choix de la taille 
						int largeu; 
						int longue;
						if (quatre.isSelected()==true){ 
							largeu = 4;
							longue = 4;
						}else if (six.isSelected() == true ){
							largeu = 6;
							longue = 6;
						} else if (huit.isSelected() == true ){
							largeu = 8;
							longue = 8;
						} else {
							largeu = Integer.parseInt( larg.getText() );
							longue = Integer.parseInt( longe.getText() );
							if ( largeu%2!=0 )
								largeu = largeu + 1;
							longue = largeu;
						}
						
						
						// celon le choix du theme (de l'apparence) du jeu
						int thm ;
						if (ap1.isSelected()==true){
							 thm = 1;
						}else if (ap2.isSelected() == true ){
							 thm = 2;
						} else if (ap3.isSelected() == true ){
							 thm = 3;
						} else {
							 thm = 4;
						}
						
						// celon le choix du mode de jeux ( contre un vrai joueur ou un ia.. )
						int vs=0;
						if ( botId.isSelected())
							vs=1;
						else if (botR.isSelected())
							vs=2;
						else if (botBR.isSelected())
							vs=3;
						
						// On récupere le nom attribué aux joueurs 
						String joueur1;
						String joueur2;
						joueur1 = joue1.getText();
						joueur2 = joue2.getText();
						
						// et enfin on crée notre jeux ! 
						Fenetre myGame = new Fenetre(longue, largeu, joueur1, joueur2, thm, vs );
						fene.dispose();
						
					}
				});
				
				fene.pack(); // On adapte la taille de la fenetre de parametre automatiquement
				fene.setLocationRelativeTo(null); // on la place au milieu de l'écran
				fene.setVisible(true); 
				fene.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		 
		 prin.pack(); //  On adapte la taille de la fenetre d'accueil automatiquement
		 prin.setLocationRelativeTo(null); // on la place au milieu de l'écran
		 prin.setVisible(true);
		 prin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/** Méthode setSave qui permet de donné une nouvelle sauvegarde à notre arrayList de sauvegarde <BR>
	 * 
	 * @param (Save) e
	 * @author Samy Bencharef
	 * @version 1.0
	 * 
	 */
	public void setSave(Save e){
		saves.add(e);
	}
	
	/** Main de notre application !! <BR>
	 * 
	 * @param (String[]) a
	 * 
	 * @author Samy Bencharef
	 * @version 1.0
	 * 
	 */
	public static void main(String[] args) {
		Start fenetre = new Start();
		
	}

}
