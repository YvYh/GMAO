package app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class InterfaceGM extends JFrame {
	/**
	 * interface of Gestion Maintenance 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbed;
	private JPanel panel;//basic panel
	private JPanel pRef;//panel of reference
	private JLabel labRef;
	private JTextField textRef;
	private JPanel pC;//panel of content
	private JLabel labC;
	private JTextArea textC;
	private JPanel pType;//panel of type
	private JLabel labType;
	private JRadioButton type1;
	private JRadioButton type2;
	private JRadioButton type3;
	private JPanel pDuree;//panel of duration
	private JLabel labD;
	private JLabel labJour;
	private JTextField textD;
	private JPanel pBouton;//panel of button
	private JButton bR;//return button
	private JButton bOK;//validation button
	private JLabel labResultat;
	private JButton bCher;
	private CardLayout cardlay;
	private Maintenance m;

	private MaintenanceDAO mDAO;
	
	public static void main(String[] args)
	{
		new InterfaceGM();
	}
	
	public InterfaceGM(){
		/**
		 * construction of maintenance interface
		 */
		
		tabbed = new JTabbedPane();
		tabbed.addTab("Saisir", saisirM());
		tabbed.addTab("Afficher", afficherM());
		tabbed.addTab("Modifier", chercherM());
		tabbed.addTab("Valider", validerM());
		//tabbed.addTab("Affectation",affecter());
		this.getContentPane().add(tabbed,BorderLayout.CENTER);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Maintenance");
        this.setSize(500,450);
		this.setVisible(true);
	}
	
	public Component saisirM(){
		/**
		 * panel of inputting a maintenance
		 */
		panel = new JPanel();
		panel.setLayout(new java.awt.GridLayout(6,1));
		
		labRef = new JLabel("Ref:");
		JTextField textRef = new JTextField(15);
		pRef = new JPanel();
		pRef.add(labRef);
		pRef.add(textRef);
		
		labC = new JLabel("Contenu");
		textC = new JTextArea(5,15);
		pC = new JPanel();
		pC.add(labC);
		pC.add(textC);
		
		labType = new JLabel("Type");
		type1 = new JRadioButton("T1");
		type2 = new JRadioButton("T2");
		type3 = new JRadioButton("T3");
        ButtonGroup group = new ButtonGroup();
        pType = new JPanel();
        group.add(type1);
        group.add(type2);
        group.add(type3);
        pType.add(labType);
        pType.add(type1);
        pType.add(type2);
        pType.add(type3);
        
        labD = new JLabel("Duree:");
        textD = new JTextField(15);
        labJour = new JLabel("jour(s)");
        pDuree = new JPanel();
        pDuree.add(labD);
        pDuree.add(textD);
        pDuree.add(labJour);
        
        bR= new JButton("Retour");
        bOK = new JButton("OK");
        pBouton = new JPanel();
        pBouton.add(bR);
        pBouton.add(bOK);
        labResultat = new JLabel();
        
        panel.add(pRef);
        panel.add(pC);
        panel.add(pType);
        panel.add(pDuree);
        panel.add(pBouton);
        panel.add(labResultat);
        
        //finish the input
        bOK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				String type;
				JLabel labResultat = new JLabel();
				if(type1.isSelected()){
					type="T1";
				}else if (type2.isSelected()){
					type="T2";
				}else if (type3.isSelected()){
					type="T3";
				}else
					type=null;
				
				Maintenance m = new Maintenance(textRef.getText(),textC.getText(),type,textD.getText());
				System.out.println(textRef.getText());
				System.out.println(m.getcMaint()+m.getType()+m.getduree());
				MaintenanceDAO mDAO = new MaintenanceDAO();
				mDAO.ajouter(m);
				labResultat.setText("Enregistre!");
		    }
        });
        
       return panel;        
	}
	
	public JPanel maintenance(Maintenance m){
		/**
		 * panel of showing a maintenance
		 * @param m maintenance target
		 */
		panel = new JPanel();
		panel.setLayout(new java.awt.GridLayout(6,1));
		
		labRef = new JLabel("Réf:");
		textRef = new JTextField(Integer.toString(m.getId()));
		pRef = new JPanel();
		pRef.add(labRef);
		pRef.add(textRef);
		
		labC = new JLabel("Contenu");
		textC = new JTextArea(5,15);
		textC.setText(m.getcMaint());
		pC = new JPanel();
		pC.add(labC);
		pC.add(textC);
		
		labType = new JLabel("Type");
		JTextField textType = new JTextField(m.getType());
		pType = new JPanel();
        pType.add(labType);
        pType.add(textType);
        
        labD = new JLabel("Durée:");
        textD = new JTextField(m.getduree());
        labJour = new JLabel("jour(s)");
        pDuree = new JPanel();
        pDuree.add(labD);
        pDuree.add(textD);
        pDuree.add(labJour);
        
		
        panel.add(pRef);
        panel.add(pC);
        panel.add(pType);
        panel.add(pDuree);
        
        System.out.println("0000");
        return panel;
	}
	
	public Component chercherM(){
		/**
		 * panel of researching a maintenance
		 * @return panel
		 */
		panel = new JPanel();
		panel.setLayout(new java.awt.GridLayout(6,1));
		
		labRef = new JLabel("Réf:");
		textRef = new JTextField(15);
		pRef = new JPanel();
		pRef.add(labRef);
		pRef.add(textRef);
		
		bCher = new JButton("Chercher");
		panel.add(pRef);
		panel.add(bCher);
		
		return panel;
	}
	
	
	
	public Component afficherM(){
		/**
		 * panel of showing a maintenance
		 */
		JPanel p = new JPanel();
		cardlay = new CardLayout();
		p.setLayout(cardlay);
		p.add(chercherM(), "chercher");
		cardlay.show(p, "chercher");
		//Maintenance m = new Maintenance(123,"abcdef","T2","23");
		mDAO = new MaintenanceDAO();
		m = new Maintenance();
		
		//action of "chercher" button
		bCher.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				mDAO = new MaintenanceDAO();
				m = mDAO.getMaintenance(textRef.getText());
				p.add(maintenance(m),"M");
				cardlay.show(p, "M");
			}
		});
		return p;
		
	}
	
	@SuppressWarnings({ "unchecked", "unchecked", "unchecked" })
	public Component modifierM(){
		JPanel p = new JPanel();
		cardlay = new CardLayout();
		p.setLayout(cardlay);
		p.add(chercherM(), "chercher");
		cardlay.show(p, "chercher");
		//Maintenance m = new Maintenance(123,"abcdef","T2","23");
		mDAO = new MaintenanceDAO();
		m = new Maintenance();
		
		//action of "chercher" button
		bCher.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				m = mDAO.getMaintenance(textRef.getText());
				JPanel pM = new JPanel();
				pM.setLayout(new java.awt.GridLayout(6,1));
				
				labRef = new JLabel("Réf:"+m.getId());
				
				labC = new JLabel("Contenu");
				textC = new JTextArea(m.getcMaint());
				pC = new JPanel();
				pC.add(labC);
				pC.add(textC);
				
				labType = new JLabel("Type");
				type1 = new JRadioButton("T1");
				type2 = new JRadioButton("T2");
				type3 = new JRadioButton("T3");
		        ButtonGroup group = new ButtonGroup();
		        pType = new JPanel();
		        group.add(type1);
		        group.add(type2);
		        group.add(type3);
		        pType.add(labType);
		        pType.add(type1);
		        pType.add(type2);
		        pType.add(type3);
		        
		        labD = new JLabel("Durée:");
		        textD = new JTextField(m.getduree());
		        labJour = new JLabel("jour(s)");
		        pDuree = new JPanel();
		        pDuree.add(labD);
		        pDuree.add(textD);
		        pDuree.add(labJour);
		        
		        bOK = new JButton("Modifier");
		        pBouton = new JPanel();
		        pBouton.add(bR);
		        pBouton.add(bOK);
		        labResultat = new JLabel();
		        
		        pM.add(labRef);
		        pM.add(pC);
		        pM.add(pType);
		        pM.add(pDuree);
		        pM.add(pBouton);
		        pM.add(labResultat);
				
				p.add(pM,"M");
				cardlay.show(p, "M");
				bOK.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String type;
						if(type1.isSelected()){
							type="T1";
						}else if (type2.isSelected()){
							type="T2";
						}else if (type3.isSelected()){
							type="T3";
						}else
							type=null;
						m = new Maintenance(textRef.getText(),textC.getText(),type,textD.getText());
						mDAO = new MaintenanceDAO();
						mDAO.ajouter(m);
						labResultat.setText("Enregisté!");
					}
				});
			}
		});
		return p;
	}
	
	@SuppressWarnings("rawtypes")
	public JPanel validerM(){
		JPanel p = new JPanel();
		ArrayList<Maintenance> mList = new ArrayList<Maintenance>();
		MaintenanceDAO mDAO = new MaintenanceDAO();
		mList.addAll(mDAO.getListeMaintenance());
		JLabel lab = new JLabel();
		
		@SuppressWarnings("unchecked")
		JList list = new JList(mList.toArray());
		bOK = new JButton("Valider");
		p.setLayout(new BorderLayout());
		list.setVisibleRowCount(10);
		list.setBorder(BorderFactory.createTitledBorder("Les maintenance sans validation"));
		list.addListSelectionListener(new ListSelectionListener(){
			//quand on choisit un maintenance, il affiche l'interface de ce maintenance
			public void valueChanged(ListSelectionEvent e) {
				JDialog dialog = new JDialog(InterfaceGM.this,"Maintenance",true);
				dialog.setContentPane(maintenance(mList.get(list.getSelectedIndex())));
		        dialog.setBounds(300,300,200,200);
		        dialog.setVisible(true);
			}
		});
		p.add(new JScrollPane(list), BorderLayout.CENTER);
		p.add(lab,BorderLayout.PAGE_END);
		p.add(bOK, BorderLayout.PAGE_END);
		bOK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				mList.get(list.getSelectedIndex()).validerMaint();
				lab.setText(mList.get(list.getSelectedIndex()).getId()+"validé");
		    }
		});
		return p;

	}
	
	/*public JPanel affecter(){
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		JButton consulter = new JButton("Affecter");
		JLabel lab = new JLabel();
		

		ArrayList<Maintenance> mList = new ArrayList<Maintenance>();
		ArrayList<Maintenance> mList2 = new ArrayList<Maintenance>();
		MaintenanceDAO mDAO = new MaintenanceDAO();
		mList2.addAll(mDAO.getListeMaintenance());
		for (int i=0; i<mList2.size();i++)
		{
			if (mList2.get(i).getidOp()!=0)
				mList.add(mList2.get(i));
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JList listM = new JList(mList.toArray());

		listM.setVisibleRowCount(10);
		listM.setBorder(BorderFactory.createTitledBorder("Les maintenance sans validation"));

		ArrayList<Operateur> oList = new ArrayList<Operateur>();
		OperateurDAO oDAo = new OperateurDAO();
		oList.addAll(oDAO.getLisateOperateur());
		JList listO new JList(oList.toArray());
		listO.setVisibleRowCount(10);
		listO.setBorder(BorderFactory.createTitledBorder("Les opérateur disponible"));
		
		p.add(new JScrollPane(listM), BorderLayout.WEST);
		p.add(new JScrollPane(listO), BorderLayout.EAST);
		p.add(consulter, BorderLayout.SOUTH);
		p.add(lab, BorderLayout.PAGE_END);
		
		
		consulter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				mList2.get(listM.getSelectedIndex()).setidOp(oList.get(listO.getSelectedIndex()).getID());
				lab.setText(oList.get(listO.getSelectedIndex()).getNom()+""+oList.get(listO.getSelectedIndex()).getPrenom()+
						"->Maintenance Réf:"+mList2.get(listM.getSelectedIndex()).getRef());
		    }
		});
		return p;
	}*/
	
	




	

}