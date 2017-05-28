package app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class InterfaceGC extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InterfaceGC(int etat)
	{
		if (etat==1)
		{
			JTabbedPane tabbed = new JTabbedPane();
			tabbed.addTab("Saisir Client", saisirE());
			tabbed.addTab("Consultationr", afficherListE());
			this.getContentPane().add(tabbed,BorderLayout.CENTER);
	        this.setTitle("Client");
	        this.setSize(500,450);
			this.setVisible(true);
		}
		
	}

	private Component afficherListE() {
		// TODO Auto-generated method stub
		return null;
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
	 * afficher les info d'entreprise
	 * @param ent l'entreprise a afficher
	 * @return la panel d'info 
	 */
	private JPanel afficherE(Entreprise ent) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6,2));
		
		JLabel labNom = new JLabel("Nom:");
		JTextField textNom = new JTextField(ent.getNom());
		JLabel labSIRET = new JLabel("SIRET:");
		JTextField textSIRET = new JTextField(ent.getnSiret());
		JLabel labAdresse = new JLabel("Adresse:");
		JTextField textAdresse = new JTextField(ent.getAdresse());
		JLabel labAPE = new JLabel("APE:");
		JTextField textAPE = new JTextField(ent.getApe());
		
		panel.add(labNom);
		panel.add(textNom);
		panel.add(labSIRET);
		panel.add(textSIRET);
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

	/**
	 * Ajouter une entreprise
	 * @return panel de saisie Entreprise
	 */
	private Component saisirE() {
		JPanel panel = new JPanel();
		//panel.setLayout(new GridLayout(6,2));
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		JLabel labNom = new JLabel("Nom:");
		JTextField textNom = new JTextField(10);
		JLabel labSIRET = new JLabel("SIRET:");
		JTextField textSIRET = new JTextField(10);
		JLabel labMot = new JLabel("Mot de passe:");
		JTextField textMot = new JTextField(20);
		JLabel labAdresse = new JLabel("Adresse:");
		JTextField textAdresse = new JTextField(20);
		JLabel labAPE = new JLabel("APE:");
		JTextField textAPE = new JTextField(15);
		
		JButton bOK = new JButton("OK");
		JLabel labResultat = new JLabel();
		
		panel.add(labNom);
		panel.add(textNom);
		panel.add(labSIRET);
		panel.add(textSIRET);
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
				Entreprise ent = new Entreprise(textNom.getText(),
						Integer.parseInt(textSIRET.getText()),
						textAdresse.getText(),
						textAPE.getText(),
						textMot.getText());
				EntrepriseDAO eDAO = new EntrepriseDAO();
				int resultat = eDAO.ajouter(ent);
				if (resultat != 0)
					labResultat.setText("Client "+ent.getnSiret()+" est enr¨¦gistr¨¦");
				else
					labResultat.setText("Probleme d'enregistrer");
				
			}
		});
		
		return panel;
	}
	
	public static void main(String[] args)
	{
		new InterfaceGC(1);
	}

}
