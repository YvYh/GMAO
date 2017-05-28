package app;

import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 * Interface de Gestion Client
 * -saisir client
 * -consultation
 * (pour les responsables)
 * @author YH
 *
 */
public class InterfaceGC extends JFrame{
	JTextField textnSIRET;
	private static final long serialVersionUID = 1L;

	public InterfaceGC(int etat)
	{
		JTabbedPane tabbed = new JTabbedPane();
		tabbed.addTab("Saisir Client", saisirE());
		tabbed.addTab("Consultation", afficherListE());
		this.getContentPane().add(tabbed,BorderLayout.CENTER);
        this.setTitle("Client");
        this.setSize(500,450);
		this.setVisible(true);
	}

	/**
	 * permet d'afficher une liste de tous les entreprises
	 * quand une entreprise est select¨¦e, il y aura une fenetre
	 * qui contient les infos de cette entreprise
	 * @return JPanel p 
	 */
	private Component afficherListE() {
		JPanel p = new JPanel();
		ArrayList<Entreprise> eList = new ArrayList<Entreprise>();
		EntrepriseDAO eDAO = new EntrepriseDAO();
		eList.addAll(eDAO.getListeEntreprise());;

		@SuppressWarnings({ "rawtypes", "unchecked" })
		JList list = new JList(eList.toArray());
		p.setLayout(new BorderLayout());
		list.setVisibleRowCount(10);
		list.setBorder(BorderFactory.createTitledBorder("Les entreprises"));
		p.add(new JScrollPane(list), BorderLayout.CENTER);
		list.addListSelectionListener(new ListSelectionListener(){
			//quand on choisit un operateur, il affiche l'interface de cette entreprise
			public void valueChanged(ListSelectionEvent e) {
				JDialog dialog = new JDialog(InterfaceGC.this,"Entrepriser",true);
				dialog.setContentPane(afficherE(eList.get(list.getSelectedIndex())));
		        dialog.setBounds(500,500,300,300);
		        dialog.setVisible(true);
			}
		});
		return p;
		
	}

	

	/**
	 * afficher les info d'entreprise
	 * @param ent l'entreprise a afficher
	 * @return JPanel panel:  panel d'info 
	 */
	private JPanel afficherE(Entreprise ent) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6,2));
		
		JLabel labNom = new JLabel("Nom:");
		JTextField textNom = new JTextField();
		textNom.setText(ent.getNom());
		textNom.setEditable(false);
		
		JLabel labSIRET = new JLabel("SIRET:");
		JTextField textSiret = new JTextField(String.valueOf(ent.getnSiret()));
		textSiret.setEditable(false);
		
		JLabel labAdresse = new JLabel("Adresse:");
		JTextField textAdresse = new JTextField(ent.getAdresse());
		textAdresse.setEditable(false);
		
		JLabel labAPE = new JLabel("APE:");
		JTextField textAPE = new JTextField(ent.getApe());
		textAPE.setEditable(false);
		
		panel.add(labNom);
		panel.add(textNom);
		panel.add(labSIRET);
		panel.add(textSiret);
		panel.add(labAdresse);
		panel.add(textAdresse);
		panel.add(labAPE);
		panel.add(textAPE);
		
		return panel;
	}
	
	/**
	 * tous les panels associe a l'entreprise
	 * @param ent entreprise trouve
	 * @return la fenetre
	 */
	public JFrame frameEntreprise(Entreprise ent)
	{
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		JTabbedPane tabbed = new JTabbedPane();
		tabbed.addTab("Info", afficherE(ent));
		tabbed.addTab("Rapport d'acticite", rapport(ent));
		tabbed.addTab("Etat du dossier", etatDossier(ent));
		panel.add(tabbed,BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setSize(400, 500);
		frame.setTitle("Entreprise"+ent.getID());
		frame.setContentPane(panel);
		return frame;
	}

	private Component etatDossier(Entreprise ent) {
		// TODO Auto-generated method stub
		return null;
	}

	private Component rapport(Entreprise ent) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Ajouter une entreprise
	 * @return JPanel panel de saisie
	 */
	private Component saisirE() {
		JPanel panel = new JPanel();
		//panel.setLayout(new GridLayout(6,2));
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		JLabel labNom = new JLabel("Nom:");
		JTextField textN = new JTextField(10);
		JLabel labSIRET = new JLabel("SIRET:");
		textnSIRET = new JTextField(10);
		JLabel labMot = new JLabel("Mot de passe:");
		JTextField textMot = new JTextField(20);
		JLabel labAdresse = new JLabel("Adresse:");
		JTextField textAdresse = new JTextField(20);
		JLabel labAPE = new JLabel("APE:");
		JTextField textAPE = new JTextField(15);
		
		JButton bOK = new JButton("OK");
		JLabel labResultat = new JLabel();
		
		panel.add(labNom);
		panel.add(textN);
		panel.add(labSIRET);
		panel.add(textnSIRET);
		panel.add(labAdresse);
		panel.add(textAdresse);
		panel.add(labAPE);
		panel.add(textAPE);
		panel.add(labMot);
		panel.add(textMot);
		panel.add(bOK);
		panel.add(labResultat);	
		
		bOK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				System.out.print(Integer.parseInt(textnSIRET.getText()));
				Entreprise ent = new Entreprise(textN.getText(),
						Integer.parseInt(textnSIRET.getText()),
						textAdresse.getText(),
						textAPE.getText(),
						textMot.getText());
				EntrepriseDAO eDAO = new EntrepriseDAO();
				System.out.print(ent.getID());
				int resultat = eDAO.ajouter(ent);
				if (resultat == 0)
					labResultat.setText("Probleme d'enregistrer");
				else
					labResultat.setText("Client "+ent.getnSiret()+" est enr¨¦gistr¨¦");
				
			}
		});
		
		return panel;
	}
	
	public static void main(String[] args)
	{
		new InterfaceGC(1);
	}

}
