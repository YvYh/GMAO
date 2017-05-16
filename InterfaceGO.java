package app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InterfaceGO extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbed;
	private CardLayout card = new CardLayout();

	
	
	public InterfaceGO(int etat){
		JPanel p = new JPanel();
		p.setLayout(card);
		p.add(panelR(),"panelR");
		p.add( panelO(),"panelO");
		if (etat==1)
		{
			card.show(p, "panelR");
		}else
		{
			card.show(p, "panelO");
		}
		this.setContentPane(p);
		this.setTitle("Opérateur");
        this.setSize(400,450);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public JPanel panelR()
	{
		JPanel panel = new JPanel();
		tabbed = new JTabbedPane();
		panel.setLayout(new BorderLayout());
		tabbed.addTab("Saisir", saisirO());
		tabbed.addTab("surcôut",surcout());
		tabbed.addTab("Consultation", consultation());
		panel.add(tabbed,BorderLayout.CENTER);
		return panel;
		
	}
	
	public JPanel panelO()
	{
		JPanel panel = new JPanel();
		tabbed = new JTabbedPane();
		panel.setLayout(new BorderLayout());
		tabbed.add("surcout", surcout());
		tabbed.addTab("Cconsultation", consultation());
		panel.add(tabbed,BorderLayout.CENTER);
		return panel;
	}
	
	public JPanel saisirO()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6,1));
		
		JLabel labNom = new JLabel("Nom:");
		JTextField textNom = new JTextField(10);
		JLabel labPrenom = new JLabel("Prenom:");
		JTextField textPrenom = new JTextField(10);
		JPanel pNom = new JPanel();
		pNom.add(labNom);
		pNom.add(textNom);
		pNom.add(labPrenom);
		pNom.add(textPrenom);
		
		JLabel labMot = new JLabel("Mot de passe:");
		JTextField textMot = new JTextField(20);
		JPanel pMot = new JPanel();
		pMot.add(labMot);
		pMot.add(textMot);
		
		JLabel labAdresse = new JLabel("Adresse:");
		JTextField textAdresse = new JTextField(20);
		JPanel pAdresse = new JPanel();
		pAdresse.add(labAdresse);
		pAdresse.add(textAdresse);
		
		JLabel labTel = new JLabel("Tel:");
		JTextField textTel = new JTextField(15);
		JPanel pTel = new JPanel();
		pTel.add(labTel);
		pTel.add(textTel);
		
		JButton bOK = new JButton("OK");
		JLabel labResultat = new JLabel();
		
		panel.add(pNom);
		panel.add(pMot);
		panel.add(pAdresse);
		panel.add(pTel);
		panel.add(bOK);
		panel.add(labResultat);
		
		
		bOK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String id = ("12345");
				Operateur o = new Operateur(textNom.getText(),
						textPrenom.getText(),
						textMot.getText(),
						textAdresse.getText(),
						textTel.getText(),
						id);
				//OperateurDAO
				labResultat.setText("Operateur "+o.nom+" est enrégistré");
			}
		});
		
		return panel;
	}
	
	public JPanel surcout()
	{
		JPanel panel = new JPanel();
		//monquer Devis
		return panel;
	}
	
	public JPanel consultation()
	{
		JPanel panel = new JPanel();
		MaintenanceDAO mDAO = new MaintenanceDAO();
		List<Maintenance> listM = new ArrayList<Maintenance>();
		JTabel table = new JTabel();
		
		return panel;
	}

}
