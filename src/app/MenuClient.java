package app;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Interface Menu pour client
 * -afficher la liste de tous les devis associ¨¦s ¨¤ ce client
 * @author YH
 *
 */
public class MenuClient extends JFrame {
	private static final long serialVersionUID = 1L;
	private EntrepriseDAO eDAO;
	private Entreprise ent;
	private JPanel panel;
	private JList<Devis> list;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MenuClient(int idEnt)
	{
		eDAO = new EntrepriseDAO();
		ent = eDAO.getEntreprise(idEnt);
		panel = new JPanel();	
        Color couleur1 = new Color(96,120,136);
        panel.setBackground(couleur1);
        panel.setLayout(new BorderLayout());

        JLabel labNom = new JLabel("Bienvenu "+ent.getNom());
        panel.add(labNom, BorderLayout.PAGE_START);;
        
        
		
		DevisDAO dDAO = new DevisDAO();
		ArrayList<Devis> listD = dDAO.getListDevisEnt(idEnt);

		list = new JList(listD.toArray());
		list.setVisibleRowCount(10);
		list.setBorder(BorderFactory.createTitledBorder("Dossiers"));
					
		list.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				JDialog dialog = new JDialog(MenuClient.this,"Devis N."+listD.get(
						list.getSelectedIndex()).getId(),true);
				dialog.setContentPane(Devis(listD.get(list.getSelectedIndex())));
				dialog.setBounds(500,500,300,300);
		        dialog.setVisible(true);
			}
		});
		
		panel.add(new JScrollPane(list), BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setTitle("Operateur");
	    this.setSize(400,450);
	    this.setContentPane(panel);
		this.setVisible(true);
	}
	
	public JPanel Devis(Devis d){
		JPanel p = new JPanel();
		MaintenanceDAO mDAO = new MaintenanceDAO();
		UtilisateurDAO uDAO = new UtilisateurDAO();
		
		JLabel labRef = new JLabel("Ref:");
		JTextField textRef = new JTextField(String.valueOf(d.getId()));
		textRef.setEditable(false);
		
		JLabel labC = new JLabel("Contenu");
		JTextArea textC = new JTextArea(mDAO.getMaintenance(d.getIdM()).getcMaint());
		textC.setEditable(false);
		
		JLabel labDuree = new JLabel("Duree");
		JTextArea textDuree = new JTextArea(mDAO.getMaintenance(d.getIdM()).getduree());
		textDuree.setEditable(false);
		
		JLabel labCout = new JLabel("Cout:");
		JTextField textCout = new JTextField(String.valueOf(d.getCout()));
		textCout.setEditable(false);
		
		JLabel labOp= new JLabel("Operateur:");
		JTextField textNOp = new JTextField("N."+mDAO.getMaintenance(d.getIdM()).getidOp());
		JTextField textOp = new JTextField(uDAO.getUtilisateur(mDAO.getMaintenance(d.getIdM()).getidOp()).getNom()
				+" "+uDAO.getUtilisateur(mDAO.getMaintenance(d.getIdM()).getidOp()).getPrenom());
		textCout.setEditable(false);
		JPanel pOp = new JPanel();
		pOp.add(labOp);
		pOp.add(textNOp);
		pOp.add(textOp);
		
		JButton bFacture = new JButton("Facture");
		
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.add(labRef);
		p.add(textRef);
		p.add(labC);
		p.add(textC);
		p.add(labDuree);
		p.add(textDuree);
		p.add(labCout);
		p.add(textCout);
		p.add(pOp);
		p.add(bFacture);
		
			
		
		bFacture.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				JDialog dialog = new JDialog(MenuClient.this,"Devis N."+d.getId(),true);
				dialog.setContentPane(facture(d));
				dialog.setBounds(500,500,300,300);
		        dialog.setVisible(true);
				
			}
			
		});
		return p;
	}
	
	public JPanel facture(Devis d){
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		MaintenanceDAO mDAO = new MaintenanceDAO();
		Maintenance m = new Maintenance();
		ArrayList<Maintenance> listM = mDAO.getListeMaintenance();
		for(int i=0; i<listM.size(); i++)
			if (listM.get(i).getId()==d.getIdM())
				m=listM.get(i);
		
		JLabel labRefD = new JLabel("Id Devis:");
		JTextField textRefD = new JTextField(String.valueOf(d.getId()));
		textRefD.setEditable(false);
		JPanel p1 = new JPanel();
		p1.add(labRefD);
		p1.add(textRefD);
		panel.add(p1);
		
		JLabel labCM = new JLabel("Maintenance:");
		JTextField textCM = new JTextField(m.getcMaint());
		textCM.setEditable(false);
		JPanel p2 = new JPanel();
		p2.add(labCM);
		p2.add(textCM);
		panel.add(p2);
		
		JLabel labCout = new JLabel("Cout:");
		JTextField textCout = new JTextField(String.valueOf(d.getCout()));
		textCout.setEditable(false);
		JPanel p3 = new JPanel();
		p3.add(labCout);
		p3.add(textCout);
		panel.add(p3);
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JList listS = new JList(d.getSurcoutP().toArray());
		listS.setVisibleRowCount(10);
		listS.setBorder(BorderFactory.createTitledBorder("Surcout"));
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JList listRM = new JList(d.getSurcoutRM().toArray());
		listRM.setVisibleRowCount(10);
		listRM.setBorder(BorderFactory.createTitledBorder("Remarque"));
		JPanel pS = new JPanel();
		pS.setLayout(new BoxLayout(pS,BoxLayout.X_AXIS));
		pS.add(new JScrollPane(listS));
		pS.add(new JScrollPane(listRM));
		panel.add(pS);
		
		float somme=d.getCout();
		for(int s=0; s<d.getSurcoutP().size();s++)
			somme=somme+d.getSurcoutP().get(s);
		JLabel labSomme = new JLabel("Somme");
		JTextField textSomme = new JTextField(String.valueOf(somme)+"¢ã");
		textSomme.setEditable(false);
		JPanel pSomme = new JPanel();
		pSomme.add(labSomme);
		pSomme.add(textSomme);
		panel.add(pSomme);
		
		return panel;
		
		
	}
	
	public static void main(String[] args)
	{
		new MenuClient(111);
	}

}
