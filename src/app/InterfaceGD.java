package app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 * Interface Gestion Devis
 * -saisir un devis
 * -modifier un devis
 * -valider un devis
 * @author YH
 *
 */
public class InterfaceGD extends JFrame {
	private static final long serialVersionUID = 1L;
	private ArrayList<Devis> dList;
	@SuppressWarnings("rawtypes")
	private JList list;
	
	/**
	 * menu de gestion devis
	 */
	public InterfaceGD(){
		
		JTabbedPane tabbed = new JTabbedPane();
		tabbed.addTab("Saisir", saisirD());
		tabbed.addTab("Modifier", chercherD());
		tabbed.addTab("Valider", validerD());
		this.getContentPane().add(tabbed,BorderLayout.CENTER);
        this.setTitle("Devis");
        this.setSize(500,450);
		this.setVisible(true);
	}
	
	/**
	 * permet d'afficher un devis
	 * @param d devis vis¨¦
	 */
	public InterfaceGD(Devis d)
	{
		this.getContentPane().add(Devis(d));
        this.setTitle("Devis");
        this.setSize(500,450);
		this.setVisible(true);
	}
	
	
	/**
	 * Permet de chercher un devis d'apr¨¨s ls r¨¦ference tap¨¦e
	 * @return JPanel panel de chercher
	 */
	public Component chercherD(){
		
		JPanel p = new JPanel();
		CardLayout card = new CardLayout();
		p.setLayout(card);
		
		JPanel panel = new JPanel();
		JLabel labRef = new JLabel("Ref de devis:");
		JTextField textRefD = new JTextField(15);
		JButton bCher = new JButton("Chercher");
		panel.add(labRef);
		panel.add(textRefD);
		panel.add(bCher);
		p.add(panel, "chercher");
		card.show(p, "chercher");
		
		bCher.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				DevisDAO dDAO = new DevisDAO();
				Devis d = dDAO.getDevis((Integer.parseInt( textRefD.getText())));
				
				//afficher le devis trouv¨¦
				JPanel p1 = new JPanel();
				JLabel labRef = new JLabel("Ref:");
				JTextField textR = new JTextField(String.valueOf(d.getId()));
				textR.setEditable(false);
				
				
				JLabel labC = new JLabel("Contenu");
				MaintenanceDAO mDAO = new MaintenanceDAO();
				JTextArea textC = new JTextArea(mDAO.getMaintenance(d.getIdM()).getcMaint());
				
				JLabel labCout = new JLabel("Cout:");
				JTextField textCout = new JTextField(String.valueOf(d.getCout()));
				
				p1.setLayout(new BorderLayout());
				p1.add(labRef);
				p1.add(textR);
				p1.add(labC);
				p1.add(textC);
				p1.add(labCout);
				p1.add(textCout);

				p.add(p1,"M");
				card.show(p, "M");
			}
		});
		return p;    
	}
	
	
	/**
	 * permet d'afficher un devis
	 * @param d devis vis¨¦
	 * @return JPanel p
	 */
	public JPanel Devis(Devis d){
		JPanel p = new JPanel();
		JLabel labRef = new JLabel("Ref:");
		JTextField textRef = new JTextField(String.valueOf(d.getId()));
		textRef.setEditable(false);
		
		JLabel labC = new JLabel("Contenu");
		MaintenanceDAO mDAO = new MaintenanceDAO();
		JTextArea textC = new JTextArea(mDAO.getMaintenance(d.getIdM()).getcMaint());
		textC.setEditable(false);
		
		JLabel labCout = new JLabel("Cout:");
		JTextField textCout = new JTextField(String.valueOf(d.getCout()));
		textCout.setEditable(false);
		
		p.add(labRef);
		p.add(textRef);
		p.add(labC);
		p.add(textC);
		p.add(labCout);
		p.add(textCout);
		
		return p;
	}
	
	/**
	 * permet de saisir un devis
	 * @return JPanel p
	 */
	public JPanel saisirD()
	{
		JPanel p = new JPanel();
		JLabel labRef = new JLabel("Ref:");
		JTextField textRef = new JTextField(10);
		
		JLabel labC = new JLabel("Maintenance");
		MaintenanceDAO mDAO = new MaintenanceDAO();
		ArrayList<Maintenance> list = new ArrayList<Maintenance>();
		list.addAll(mDAO.getListeMaintenance());
		JComboBox<Maintenance> comboBox=new JComboBox<Maintenance>(); 
		for(int i =0; i<list.size();i++)
			comboBox.addItem(list.get(i));

		JLabel labCout = new JLabel("Cout:");
		JTextField textCout = new JTextField(10);
		
		JButton bAjouter = new JButton("Ajouter");
		p.setLayout(new BoxLayout(p,BoxLayout.PAGE_AXIS));
		p.add(labRef);
		p.add(textRef);
		p.add(labC);
		p.add(comboBox);
		p.add(labCout);
		p.add(textCout);
		p.add(bAjouter);
		
		bAjouter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				DevisDAO dDAO = new DevisDAO();
				int idM;
				idM=list.get(comboBox.getSelectedIndex()).getId();
				System.out.print(idM);
				Devis d = new Devis(Integer.parseInt(textRef.getText()),idM,Float.valueOf(textCout.getText()));
				if (dDAO.ajouter(d)==1)
					JOptionPane.showMessageDialog(null, "Ajout¨¦", "OK", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "peobleme", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		});
		return p;
	}
	
	/**
	 * permet de valider un devis
	 * il affiche une liste de devis
	 * @return JPanel
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JPanel validerD(){
		JPanel pV = new JPanel();
		JButton bV = new JButton("Valider");
		JLabel lab = new JLabel();
		pV.setLayout(new BorderLayout());
		
		JPanel p = new JPanel();
		dList = new ArrayList<Devis>();
		DevisDAO dDAO = new DevisDAO();
		dList.addAll(dDAO.getListDevis());
		
		list = new JList( dList.toArray());
		p.setLayout(new BorderLayout());
		list.setVisibleRowCount(10);
		list.setBorder(BorderFactory.createTitledBorder("Les devis exsistes"));
		
		list.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				JDialog dialog = new JDialog(InterfaceGD.this,"Devis",true);
				dialog.setContentPane(Devis(dList.get(list.getSelectedIndex())));
		        dialog.setBounds(500,500,300,300);
		        dialog.setVisible(true);
			}
		});
		p.add(new JScrollPane(list), BorderLayout.CENTER);
		pV.add(p);
		pV.add(lab,BorderLayout.PAGE_END);
		pV.add(bV, BorderLayout.PAGE_END);
		bV.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dList.get(list.getSelectedIndex()).validerDevis();;
				DevisDAO dDAO = new DevisDAO();
				int rs=dDAO.updateDevis(dList.get(list.getSelectedIndex()));
				if (rs==1)
					lab.setText(dList.get(list.getSelectedIndex()).getId()+"valid¨¦");
				else
					lab.setText(dList.get(list.getSelectedIndex()).getId()+"non valid¨¦");
				pV.revalidate();
				pV.repaint();
				
		    }
		});
		return pV;
	}
	
	public static void test(String[] args)
	{
		new InterfaceGD();
	}
}