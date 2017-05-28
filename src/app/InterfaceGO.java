package app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class InterfaceGO extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbed;
	private CardLayout card = new CardLayout();
	private Devis d;
	private DevisDAO dDAO;

	
	public static void main(String[] args)
	{
		new InterfaceGO(1);
	}
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
		tabbed.addTab("Consultation", consultation());
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
		tabbed.addTab("consultation", consultation());
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
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		JLabel labNom = new JLabel("Nom:");
		JTextField textNom = new JTextField(10);
		JLabel labPrenom = new JLabel("Prenom:");
		JTextField textPrenom = new JTextField(10);
		JPanel pNom = new JPanel();
		pNom.add(labNom);
		pNom.add(textNom);
		pNom.add(labPrenom);
		pNom.add(textPrenom);
		
		
		JLabel labId = new JLabel("Id:");
		JTextField textId = new JTextField(20);
		JPanel pId = new JPanel();
		pId.add(labId);
		pId.add(textId);
		
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
		panel.add(pId);
		panel.add(pMot);
		panel.add(pAdresse);
		panel.add(pTel);
		panel.add(bOK);
		panel.add(labResultat);
		
		
		bOK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				Operateur o = new Operateur(textNom.getText(),
						textPrenom.getText(),
						textMot.getText(),
						textAdresse.getText(),
						textTel.getText(),
						Integer.parseInt(textId.getText()));
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
		JPanel p = new JPanel();
		d = new Devis();
		dDAO = new DevisDAO();
		CardLayout card = new CardLayout();
		
		p.setLayout(card);
		//chercher la maintenance
		JPanel panelC = new JPanel();
	    //panelC.setLayout(new BoxLayout(panelC, BoxLayout.PAGE_AXIS));
	    JLabel labRef = new JLabel("Ref:");
	    JTextField textId = new JTextField(15);
	    JButton bCher = new JButton("Chercher");
	    JLabel resultat = new JLabel();
	    panelC.add(labRef);
	    panelC.add(textId);
	    panelC.add(bCher);
	    panelC.add(resultat);
	    p.add(panelC, "Chercher");
	    
	    
	    //saisir le devis
	    JPanel pS = new JPanel();
	    pS.setLayout(new BoxLayout(pS, BoxLayout.PAGE_AXIS));
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
		p.add(pS, "Saisir");
		
		card.show(p, "Chercher");
		
		bCher.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				d = dDAO.getDevis(Integer.parseInt(textId.getText()));
				System.out.print(d.getId());
				if (d.getId() ==0)
				{
					JOptionPane.showMessageDialog(null, " Ce devis n'existe pas ", " Error ", JOptionPane.ERROR_MESSAGE);
					textId.setText(null);
					panelC.revalidate();
					panelC.repaint();
					card.show(p, "Chercher");
				}
				else
					card.show(p, "Saisir");
			}
		});
		
		
		bAjouter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				d.surcoutP.add(Float.valueOf(textP.getText()));
		    	d.surcoutRM.add(textRM.getText());
		    	dDAO.updateDevis(d);
		    	JOptionPane.showMessageDialog(null, "Devis est bien enregistre", "Réussi", JOptionPane.INFORMATION_MESSAGE);
		    	card.previous(p);
			}
		});
		
		bReturn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				card.previous(p);
			}
		});
		return p;
		
	}
	
	public JPanel Operateur(Operateur op)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JLabel labNom = new JLabel("Nom:");
		JTextField textNom = new JTextField(10);
		textNom.setText(op.getNom());
		textNom.setEditable(false);
		JLabel labPrenom = new JLabel("Prenom:");
		JTextField textPrenom = new JTextField(10);
		textPrenom.setText(op.getPrenom());
		textPrenom.setEditable(false);
		JPanel pNom = new JPanel();
		JPanel pPreNom = new JPanel();
		pNom.add(labNom);
		pNom.add(textNom);
		pPreNom.add(labPrenom);
		pPreNom.add(textPrenom);
		
		
		JLabel labId = new JLabel("Id:");
		JTextField textId = new JTextField(String.valueOf(op.getID()));
		textId.setEditable(false);
		JPanel pId = new JPanel();
		pId.add(labId);
		pId.add(textId);

		JLabel labAdresse = new JLabel("Adresse:");
		JTextField textAdresse = new JTextField();
		if (op.getAdresse()==null)
			textAdresse.setText("Null");
		else
			textAdresse.setText(op.getAdresse());
		textAdresse.setEditable(false);
		JPanel pAdresse = new JPanel();
		pAdresse.add(labAdresse);
		pAdresse.add(textAdresse);
		
		JLabel labTel = new JLabel("Tel:");
		JTextField textTel = new JTextField();
		if (op.getTel()==null)
			textTel.setText("Null");
		else
			textTel.setText(op.getTel());
		textTel.setEditable(false);
		JPanel pTel = new JPanel();
		pTel.add(labTel);
		pTel.add(textTel);
		
		
		panel.add(pNom);
		panel.add(pPreNom);
		panel.add(pId);
		panel.add(pAdresse);
		panel.add(pTel);
		
		return panel;
	}
	
	public JPanel consultation()
	{
		JPanel p = new JPanel();
		ArrayList<app.Operateur> oList = new ArrayList<Operateur>();
		OperateurDAO oDAO = new OperateurDAO();
		oList.addAll(oDAO.getListeOperateur());;

		JList list = new JList(oList.toArray());
		p.setLayout(new BorderLayout());
		list.setVisibleRowCount(10);
		list.setBorder(BorderFactory.createTitledBorder("Les operateur"));
		p.add(new JScrollPane(list), BorderLayout.CENTER);
		list.addListSelectionListener(new ListSelectionListener(){
			//quand on choisit un operateur, il affiche l'interface de cette operateur
			public void valueChanged(ListSelectionEvent e) {
				JDialog dialog = new JDialog(InterfaceGO.this,"Operateur",true);
				dialog.setContentPane(Operateur(oList.get(list.getSelectedIndex())));
		        dialog.setBounds(500,500,300,300);
		        dialog.setVisible(true);
			}
		});
		return p;
	}

}
