package app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	
	/**
	 * menu pour la version de responsable
	 * @return panel
	 */
	public JPanel panelR()
	{
		JPanel panel = new JPanel();
		tabbed = new JTabbedPane();
		panel.setLayout(new BorderLayout());
		tabbed.addTab("Saisir", saisirO());
		tabbed.addTab("surcôut",surcout());
		//tabbed.addTab("Consultation", consultation());
		panel.add(tabbed,BorderLayout.CENTER);
		return panel;
		
	}
	
	/**
	 * menu pour la version d'operateur
	 * @return panel
	 */
	public JPanel panelO()
	{
		//version pour operateur
		JPanel panel = new JPanel();
		tabbed = new JTabbedPane();
		panel.setLayout(new BorderLayout());
		tabbed.add("surcout", surcout());
		//tabbed.addTab("Cconsultation", consultation());
		panel.add(tabbed,BorderLayout.CENTER);
		return panel;
	}
	
	/**
	 * fonction permet de creer le panel pour ajouter un operateur
	 * @return panel de saisir
	 */
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
				int id = 12345;
				Operateur o = new Operateur(textNom.getText(),
						textPrenom.getText(),
						textMot.getText(),
						textAdresse.getText(),
						textTel.getText(),
						id);
				OperateurDAO oDAO = new OperateurDAO();
				int resultat = oDAO.ajouter(o);
				if (resultat != 0)
					labResultat.setText("Operateur "+o.nom+" est enrégistré");
				else
					labResultat.setText("Probleme d'enregistrer");
				
			}
		});
		
		return panel;
	}
	
	public JComponent surcout(){
		int niveau = 1;
		JPanel p = new JPanel();
		Devis d = new Devis();
		DevisDAO dDAO = new DevisDAO();
		
		//while(niveau != 0)
		{
			switch(niveau)
			{
			case 1://panel pour chercher un devis existe
			{
				JPanel panelC = new JPanel();
			    panelC.setLayout(new GridLayout(2,2));
			    JLabel labRef = new JLabel("Ref:");
			    JTextField textRef = new JTextField(15);
			    JButton bCher = new JButton("Chercher");
			    JLabel resultat = new JLabel();
			    panelC.add(labRef);
			    panelC.add(textRef);
			    panelC.add(bCher);
			    panelC.add(resultat);
			    if (bCher.isSelected())
			    {
					d = dDAO.getDevis(Integer.parseInt(textRef.getText()));
					if (d.getId() ==0)
					{
						resultat.setText("Ce devis n'existe pas");
						panelC.revalidate();
					}
					else
						niveau =2;
			    }
			    panelC.setVisible(true);
			    p.add(panelC);
			    p.revalidate();
			    p.repaint();
			    break;
			}
			
			case 2://detailler le surcout
			{
				JPanel pS = new JPanel();
				JLabel labP = new JLabel("Prix:");
				JTextField textP = new JTextField(10);
				JLabel labRM = new JLabel("Remarque:");
				JTextArea textRM = new JTextArea();
				JButton bAjouter = new JButton("Ajouter");
				JButton bReturn = new JButton("Return");
				pS.add(labP);
				pS.add(textP);
				pS.add(labRM);
				pS.add(textRM);
				pS.add(bAjouter);
				pS.add(bReturn);
				p.removeAll();
				p.add(pS);
				p.revalidate();
			    p.repaint();
			    if (bAjouter.isSelected())
			    {
			    	d.surcoutP.add(Float.valueOf(textP.getText()));
			    	d.surcoutRM.add(textRM.getText());
			    	dDAO.updateDevis(d);
			    	niveau = 3;
			    }
			    if (bReturn.isSelected())
			    {
			    	niveau = 1;
			    	d = new Devis();
			    }
			    break;
			}
			
			case 3:
				JLabel lab = new JLabel("Devis N."+d.getId()+"est renouvele");
				niveau = 1;
				d = new Devis();
		    }
		}
		return p;
		
	}
	
	
	/*public JPanel consultation()
	{
		JPanel p = new JPanel();
		ArrayList<Operateur> oList = new ArrayList<Operateur>();
		OperateurDAO oDAO = new OperateurDAO();
		oList.addAll(oDAO.getListeOperateur());
		JLabel lab = new JLabel();
		

		JList list = new JList(oList.toArray());
		p.setLayout(new BorderLayout());
		list.setVisibleRowCount(10);
		list.setBorder(BorderFactory.createTitledBorder("Les operateur"));
		p.add(new JScrollPane(list), BorderLayout.CENTER);
		p.add(lab,BorderLayout.PAGE_END);
		
		return p;
	}*/

}
