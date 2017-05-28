package app;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuResponsable extends JFrame{
	/**
	 * windows of director menu
	 */
	
	private static final long serialVersionUID = 1L;
	private JPanel panel;//basic panel
	private JPanel pMenuMacro;//panel of macro menu
	private JLabel labNom;//label of user name
	private JButton boutonGM;//Gestion Maintenance
	private JButton boutonGD;//Gestion Devis
	private JButton boutonGO;//Gestion Operateur
	private JButton boutonGC;//Gestion Client
	private GridLayout gridBouton;
	int retour;
	
	public static void main(String[] args)
	{
		new MenuResponsable("YU", "Hong");
	}

	
	/**
	 * construction of MenuResponsable
	 * @nom @prenom user's name
	 */
	public MenuResponsable(String nom, String prenom){
		
		panel = new JPanel();	
        pMenuMacro = new JPanel();
        gridBouton = new GridLayout(4,1,0,10);
        Color couleur1 = new Color(96,120,136);
        labNom = new JLabel("Bienvenu"+nom+" "+prenom);
        
        pMenuMacro.setLayout(gridBouton);
        pMenuMacro.setBackground(null);
        panel.setLayout(new java.awt.GridLayout(2,1));
        panel.setBackground(couleur1);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Responsable");
        this.setSize(400,450);

		//instantiation des composants graphique
		
        Color couleur2 = new Color(242,242,242);
		pMenuMacro.setLayout(gridBouton);
        pMenuMacro.setBackground(null);
		boutonGM = new JButton("Gestion Maintenance");
		boutonGM.setBackground(couleur2);
		boutonGD = new JButton("Gestion Devis");
		boutonGD.setBackground(couleur2);
		boutonGO = new JButton("Gestion Operateur");
		boutonGO.setBackground(couleur2);
		boutonGC = new JButton("Gestion Client");
		boutonGC.setBackground(couleur2);
		
		
		pMenuMacro.add(boutonGM);
		pMenuMacro.add(boutonGD);
		pMenuMacro.add(boutonGO);
		pMenuMacro.add(boutonGC);
		
		panel.add(labNom);
		panel.add(pMenuMacro);
	

		boutonGM.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new InterfaceGM();
			}
		});
		
		boutonGO.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new InterfaceGO(1);
			}
		});
		
		boutonGD.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new InterfaceGD();
			}
		});
		
		boutonGC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new InterfaceGC(1);
			}
		});
		
	
		//allow turn off the app when we close the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(panel);
		this.setVisible(true);
	}
	
	
	public JPanel actionMenuMacro(){
		/**
		 * show the panel of MenuMacro
		 * @return the panel of MenuMacro 
		 */
		pMenuMacro = new JPanel();
		Color couleur2 = new Color(242,242,242);
		
		pMenuMacro.setLayout(gridBouton);
        pMenuMacro.setBackground(null);
		boutonGM = new JButton("Gestion Maintenance");
		boutonGM.setBackground(couleur2);
		boutonGD = new JButton("Gestion Devis");
		boutonGD.setBackground(couleur2);
		boutonGO = new JButton("Gestion Operateur");
		boutonGO.setBackground(couleur2);
		boutonGC = new JButton("Gestion Client");
		boutonGC.setBackground(couleur2);
		
		pMenuMacro.add(boutonGM);
		pMenuMacro.add(boutonGD);
		pMenuMacro.add(boutonGO);
		pMenuMacro.add(boutonGC);
		return pMenuMacro;
		
	}

}
