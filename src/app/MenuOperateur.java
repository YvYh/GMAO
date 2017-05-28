package app;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuOperateur extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panel;//basic panel
	private JPanel pMenuMacro;//panel of macro menu
	private JLabel labNom;//label of user name
	private JButton bConsultationM;
	private JButton boutonGO;//Gestion Operateur
	private GridLayout gridBouton;
	private Operateur op;
	
	
	public MenuOperateur(int idOp){
		panel = new JPanel();	
        pMenuMacro = new JPanel();
        gridBouton = new GridLayout(4,1,0,10);
        Color couleur1 = new Color(96,120,136);
        OperateurDAO oDAO = new OperateurDAO();
        op = oDAO.getOperateur(idOp);
        labNom = new JLabel("Bienvenu"+op.getNom()+" "+op.getPrenom());
        
        //pMenuMacro.setLayout(gridBouton);
        pMenuMacro.setBackground(null);
        panel.setLayout(new java.awt.GridLayout(2,1));
        panel.setBackground(couleur1);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Operateur");
        this.setSize(400,450);

		//instantiation des composants graphique
		
        Color couleur2 = new Color(242,242,242);
		pMenuMacro.setLayout(gridBouton);
        pMenuMacro.setBackground(null);
		bConsultationM = new JButton("Consultation Maintenance");
		bConsultationM.setBackground(couleur2);
		boutonGO = new JButton("Gestion Operateur");
		boutonGO.setBackground(couleur2);
		
		
		pMenuMacro.add(bConsultationM);
		pMenuMacro.add(boutonGO);
		
		panel.add(labNom);
		panel.add(pMenuMacro);
	
		
		//add ActionListener to boutonGM
		bConsultationM.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new InterfaceGM(2);
			}
		});
		
		boutonGO.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new InterfaceGO(2);
			}
		});
		
		
		//allow turn off the app when we close the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(panel);
		this.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new MenuOperateur(111);
	}

}
