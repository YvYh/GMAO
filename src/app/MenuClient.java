package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MenuClient extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel panel;//basic panel
	private JPanel pMenuMacro;//panel of macro menu
	private JLabel labNom;//label of user name
	private JButton boutonD;
	private JButton boutonF;
	private GridLayout gridBouton;
	
	
	public MenuClient(int id){
		panel = new JPanel();	
        pMenuMacro = new JPanel();
        gridBouton = new GridLayout(4,1,0,10);
        Color couleur1 = new Color(96,120,136);
        EntrepriseDAO eDAO = new EntrepriseDAO();
        Entreprise e = eDAO.getEntreprise(id);
        labNom = new JLabel("Bienvenu"+e.getNom());
        
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
		boutonD = new JButton("Dossier");
		boutonD.setBackground(couleur2);
		boutonF = new JButton("Facture");
		boutonF.setBackground(couleur2);
		
		
		pMenuMacro.add(boutonD);
		pMenuMacro.add(boutonF);
		
		panel.add(labNom);
		panel.add(pMenuMacro);
	
		
		//add ActionListener to boutonGM
		boutonD.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					dossier(id);
				}catch (Exception ex) 
				{
					JOptionPane.showMessageDialog(null,
							"Veuillez contr么ler vos saisies", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					System.err.println("Veuillez contr么ler vos saisies");	
			}
		}
		});
		
		boutonF.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					facture(id);
				}catch (Exception ex) 
				{
					JOptionPane.showMessageDialog(null,
							"Veuillez contr么ler vos saisies", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					System.err.println("Veuillez contr么ler vos saisies");	
			}
		}
		});
		
		
		//allow turn off the app when we close the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(panel);
		this.setVisible(true);
	}

	
	public JFrame dossier(int idEnt){
		JFrame frame = new JFrame();
		frame.setTitle("Facture");
		frame.setSize(300, 400);
		JList list = new JList();
		list.setVisibleRowCount(10);
		list.setBorder(BorderFactory.createTitledBorder("Dossiers"));
		
		DevisDAO dDAO = new DevisDAO();
		MaintenanceDAO mDAO = new MaintenanceDAO();
		ArrayList<Devis> listD = dDAO.getListDevis();
		ArrayList<Maintenance> listM = mDAO.getListeMaintenance();
		for(int i=0; i<listM.size(); i++)
			if (listM.get(i).getIdEnt()==idEnt)
				for(int j=0; j<listD.size(); i++)
					if (listD.get(j).getIdM()==listM.get(i).getId())
					{
						list.add(frame, listD.get(j));
					}
		list.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				new InterfaceGD(listD.get(list.getSelectedIndex()));
			}
		});
		
		frame.setVisible(true);
		return frame;
	}
	
	public JFrame facture(int idEnt) {
		JFrame frame = new JFrame();
		frame.setTitle("Facture");
		frame.setSize(300, 400);
		
		DevisDAO dDAO = new DevisDAO();
		MaintenanceDAO mDAO = new MaintenanceDAO();
		ArrayList<Devis> listD1 = dDAO.getListDevis();
		ArrayList<Maintenance> listM = mDAO.getListeMaintenance();
		for(int i=0; i<listM.size(); i++)
			if (listM.get(i).getIdEnt()==idEnt)
				for(int j=0; j<listD1.size(); i++)
					if (listD1.get(j).getIdM()==listM.get(i).getId())
					{
						JLabel labRefD = new JLabel("Id Devis:");
						JTextField textRefD = new JTextField(String.valueOf(listM.get(i).getId()));
						textRefD.setEditable(false);
						
						JLabel labCM = new JLabel("Maintenance:");
						JTextField textCM = new JTextField(listM.get(i).getcMaint());
						textCM.setEditable(false);
						
						JLabel labCout = new JLabel("Cout:");
						JTextField textCout = new JTextField(String.valueOf(listD1.get(j).getCout()));
						textCout.setEditable(false);
						
						JList listS = new JList(listD1.get(j).getSurcoutP().toArray());
						listS.setVisibleRowCount(10);
						listS.setBorder(BorderFactory.createTitledBorder("Surcout"));
						JList listRM = new JList(listD1.get(j).getSurcoutRM().toArray());
						listRM.setVisibleRowCount(10);
						listRM.setBorder(BorderFactory.createTitledBorder("Remarque"));
						JPanel pS = new JPanel();
						pS.setLayout(new BorderLayout());
						pS.add(new JScrollPane(listS), BorderLayout.WEST);
						pS.add(new JScrollPane(listRM), BorderLayout.EAST);
						
						float somme=0;
						for(int s=0; s<listD1.get(j).getSurcoutP().size();s++)
							somme=somme+listD1.get(j).getSurcoutP().get(s);
						JLabel labSomme = new JLabel("Somme");
						JTextField textSomme = new JTextField(String.valueOf(somme)+"€");
						textSomme.setEditable(false);
						JPanel pSomme = new JPanel();
						pSomme.add(labSomme);
						pSomme.add(textSomme);
						
						frame.getContentPane().add(labRefD);
						frame.getContentPane().add(textRefD);
						frame.getContentPane().add(labCM);
						frame.getContentPane().add(textCM);
						frame.getContentPane().add(labCout);
						frame.getContentPane().add(textCout);
						frame.getContentPane().add(pS);
						frame.getContentPane().add(pSomme);
					}
		frame.setVisible(true);
		
		return frame;
		
	}
			
	
	
	public static void main(String[] args)
	{
		new MenuClient(123);
	}


	

}
