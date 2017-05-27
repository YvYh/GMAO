package app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InterfaceGD extends JFrame {
	/**
	 * interface of Gestion Devis 
	 */
	private static final long serialVersionUID = 1L;
	
	public InterfaceGD(){
		/**
		 * construction of interface
		 */
		
		JTabbedPane tabbed = new JTabbedPane();
		tabbed.addTab("Saisir", saisirD());
		//tabbed.addTab("Modifier", chercherD());
		//tabbed.addTab("Valider", validerD());
		this.getContentPane().add(tabbed,BorderLayout.CENTER);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Devis");
        this.setSize(500,450);
		this.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new InterfaceGD();
	}
	public Component saisirD(){
		/**
		 * panel pour saisir un devis
		 */
		JPanel p = new JPanel();
		CardLayout card = new CardLayout();
		p.setLayout(card);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JLabel labRef = new JLabel("Ref:");
		JTextField textRef = new JTextField(15);
		JButton bCher = new JButton("Chercher");
		panel.add(labRef);
		panel.add(textRef);
		panel.add(bCher);
		p.add(panel, "chercher");
		card.show(p, "chercher");
		
		bCher.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				MaintenanceDAO mDAO = new MaintenanceDAO();
				Maintenance m = mDAO.getMaintenance(Integer.parseInt( textRef.getText()));
				JPanel p1 = new JPanel();
				JLabel labRef = new JLabel("Ref:");
				JTextField textRef = new JTextField(10);
				
				JLabel labC = new JLabel("Contenu");
				JTextArea textC = new JTextArea(m.getcMaint());
				
				JLabel labCout = new JLabel("Cout:");
				JTextField textCout = new JTextField(5);
				
				JButton bEnregistrer = new JButton();
				
				p1.add(labRef);
				p1.add(textRef);
				p1.add(labC);
				p1.add(textC);
				p1.add(labCout);
				p1.add(textCout);

				p.add(p1,"M");
				card.show(p, "M");
				
				//finish the input
		        bEnregistrer.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent evt){
						JLabel labResultat = new JLabel();
						Devis d = new Devis(Integer.parseInt(textRef.getText()),m.getId(),Float.parseFloat(textCout.getText()));
						DevisDAO dDAO = new DevisDAO();
						dDAO.ajouter(d);
						labResultat.setText("Enregistre!");
				    }
		        });
		        
			}
		});
		return p;    
	}
	
	/*public JPanel Devis(Devis d, Maintenance m){
		JPanel p = new JPanel();
		JLabel labRef = new JLabel("Ref:");
		JTextField textRef = new JTextField(d.getId());
		
		JLabel labC = new JLabel("Contenu");
		JTextArea textC = new JTextArea(m.getcMaint());
		
		JLabel labCout = new JLabel("Cout:");
		JTextField textCout = new JTextField(String.valueOf(d.getCout()));
		
		p.add(labRef);
		p.add(textRef);
		p.add(labC);
		p.add(textC);
		p.add(labCout);
		p.add(textCout);
		
		return p;
	}*/
	
	
	
	



public JPanel Devis(Devis d, Maintenance m){
	JPanel p = new JPanel();
	JLabel labRef = new JLabel("Ref:");
	JTextField textRef = new JTextField(d.getId());
	
	JLabel labC = new JLabel("Contenu");
	JTextArea textC = new JTextArea(m.getcMaint());
	
	JLabel labCout = new JLabel("Cout:");
	JTextField textCout = new JTextField(String.valueOf(d.getCout()));
	
	p.add(labRef);
	p.add(textRef);
	p.add(labC);
	p.add(textC);
	p.add(labCout);
	p.add(textCout);
	
	return p;
}
}