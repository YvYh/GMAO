package app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
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
	private ArrayList<Maintenance> mList;
	private MaintenanceDAO mDAO;
	private JList list;
	private JPanel p;

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
		tabbed.addTab("Modifier", modifierM());
		tabbed.addTab("Valider", validerM());
		tabbed.addTab("Affectation",affecter());
		this.getContentPane().add(tabbed,BorderLayout.CENTER);
        this.setTitle("Maintenance");
        this.setSize(500,450);
		this.setVisible(true);
	}
	
	public InterfaceGM(int i){
		this.setContentPane(ConsultationM());
		this.setTitle("Maintenance");
        this.setSize(500,450);
		this.setVisible(true);
		
	}
	
	/**
	 * permet d'ajouter une maintenance
	 * @return panel de saisir
	 */
	public Component saisirM(){
		/**
		 * panel of inputting a maintenance
		 */
		panel = new JPanel();
		panel.setLayout(new java.awt.GridLayout(6,1));
		
		labRef = new JLabel("Ref:");
		textRef = new JTextField(15);
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
        
        bR= new JButton("Clear all");
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
				
				Maintenance m = new Maintenance(Integer.parseInt(textRef.getText()),textC.getText(),type,textD.getText());
				System.out.println(textRef.getText());
				System.out.println(m.getcMaint()+m.getType()+m.getduree());
				MaintenanceDAO mDAO = new MaintenanceDAO();
				int resultat = mDAO.ajouter(m);
				if (resultat == 1)
				{
					JOptionPane.showMessageDialog(InterfaceGM.this, "article ajout¨¦ !");
					textRef.setText(null);
					textC.setText(null);
					textD.setText(null);
					panel.revalidate();
					panel.repaint();
				}
					
				else
					JOptionPane.showMessageDialog(InterfaceGM.this, "erreur ajout article",
							"Erreur", JOptionPane.ERROR_MESSAGE);
				labResultat.setText("Enregistre!");
		    }
        });
        
        bR.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		textRef.setText(null);
    			textC.setText(null);
    			textD.setText(null);
    			panel.revalidate();
    			panel.repaint();
        	}
        });
        
       return panel;        
	}
	
	/**
	 * parmet d'afficher une maintenance
	 * @param m maintenance a afficher
	 */
	public JPanel maintenance(Maintenance m){
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		labRef = new JLabel("R¨¦f:");
		JTextField textRef = new JTextField(Integer.toString(m.getId()));
		textRef.setEditable(false);
		pRef = new JPanel();
		pRef.add(labRef);
		pRef.add(textRef);
		
		labC = new JLabel("Contenu");
		textC = new JTextArea(5,15);
		textC.setText(m.getcMaint());
		textC.setEditable(false);
		pC = new JPanel();
		pC.add(labC);
		pC.add(textC);
		
		labType = new JLabel("Type");
		JTextField textType = new JTextField(m.getType());
		textType.setEditable(false);
		pType = new JPanel();
        pType.add(labType);
        pType.add(textType);
        
        labD = new JLabel("Dur¨¦e:");
        textD = new JTextField(m.getduree());
        textD.setEditable(false);
        labJour = new JLabel("jour(s)");
        pDuree = new JPanel();
        pDuree.add(labD);
        pDuree.add(textD);
        pDuree.add(labJour);
        
        JLabel labEtat = new JLabel("Etat");
        JTextField textEtat;
        if (m.getEtat()==1)
        	textEtat=new JTextField("Valide");
        else
        	textEtat=new JTextField("Non valide");
        textEtat.setEditable(false);
        
        JLabel labEnt = new JLabel("Entreprise");
        JTextField textEnt=new JTextField(m.getIdEnt());
        textEnt.setEditable(false);
        
        JLabel labOp = new JLabel("Operateur");
        JTextField textOp=new JTextField(m.getidOp());
        textOp.setEditable(false);
        
		
        panel.add(pRef);
        panel.add(pC);
        panel.add(pType);
        panel.add(pDuree);
        panel.add(labEtat);
        panel.add(textEtat);
        panel.add(labEnt);
        panel.add(textEnt);
        panel.add(labOp);
        panel.add(textOp);
        
        System.out.println("0000");
        return panel;
	}
	
	
	/**
	 * parmet d'afficher une maintenance d'apres son id
	 * @return
	 */
	public Component afficherM(){
		JPanel pA = new JPanel();
		cardlay = new CardLayout();
		JPanel pC = new JPanel();
		pC.setLayout(new java.awt.GridLayout(6,1));
		
		JLabel labId = new JLabel("R¨¦f:");
		JTextField textId = new JTextField(15);
		pRef = new JPanel();
		pRef.add(labId);
		pRef.add(textId);
		bCher = new JButton("Chercher");
		pC.add(pRef);
		pC.add(bCher);
		
		pA.setLayout(cardlay);
		pA.add(pC, "chercher");
		cardlay.show(pA, "chercher");

		//action of "chercher" button
		bCher.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				mDAO = new MaintenanceDAO();
				m = mDAO.getMaintenance(Integer.parseInt(textId.getText()));
				JPanel p2 = new JPanel();
				p2.add(maintenance(m));
				JButton bReturn = new JButton("Return");
				p2.add(bReturn);
				pA.add(p2, "maintenance");
				cardlay.show(pA, "maintenance");
				
				bReturn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						cardlay.previous(pA);
					}
				});
			}
		});
		
		
		return pA;
		
	}
	

	public Component modifierM(){
		JPanel p = new JPanel();
		cardlay = new CardLayout();
		JPanel pCher = new JPanel();
		
		pCher.setLayout(new java.awt.GridLayout(6,1));
		JLabel labId = new JLabel("R¨¦f:");
		JTextField textId = new JTextField(15);
		pRef = new JPanel();
		pRef.add(labId);
		pRef.add(textId);
		bCher = new JButton("Chercher");
		pCher.add(pRef);
		pCher.add(bCher);
		
		p.setLayout(cardlay);
		p.add(pCher, "chercher");
		cardlay.show(p, "chercher");
		
		//action of "chercher" button
		bCher.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				m = mDAO.getMaintenance(Integer.parseInt(textId.getText()));
				JPanel pM = new JPanel();
				pM.setLayout(new java.awt.GridLayout(6,1));
				
				labRef = new JLabel("R¨¦f:"+m.getId());
				
				labC = new JLabel("Contenu");
				textC = new JTextArea(10, getDefaultCloseOperation());
				textC.setText(m.getcMaint());
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
		        
		        labD = new JLabel("Dur¨¦e:");
		        textD = new JTextField(m.getduree());
		        labJour = new JLabel("jour(s)");
		        pDuree = new JPanel();
		        pDuree.add(labD);
		        pDuree.add(textD);
		        pDuree.add(labJour);
		        
		        bOK = new JButton("Modifier");
		        bR = new JButton("Return");
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
						Maintenance mN = new Maintenance(m.getId(),textC.getText(),type,textD.getText());
						mDAO = new MaintenanceDAO();
						if (mDAO.ajouter(mN)==1)
						{
							JOptionPane.showMessageDialog(null, "Enregistr¨¦");
							cardlay.previous(p);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Probleme!", "Erreur", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				
				bR.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						cardlay.previous(p);
					}
				});
			}
		});
		return p;
	}
	
	@SuppressWarnings("rawtypes")
	public JPanel ConsultationM(){
		JPanel p = new JPanel();
		mList = new ArrayList<Maintenance>();
		mDAO = new MaintenanceDAO();
		mList.addAll(mDAO.getListeMaintenance());
		
		list = new JList(mList.toArray());
		p.setLayout(new BorderLayout());
		list.setVisibleRowCount(10);
		list.setBorder(BorderFactory.createTitledBorder("Les maintenance exsistes"));
		list.addListSelectionListener(new ListSelectionListener(){
			//quand on choisit un maintenance, il affiche l'interface de ce maintenance
			public void valueChanged(ListSelectionEvent e) {
				JDialog dialog = new JDialog(InterfaceGM.this,"Maintenance",true);
				dialog.setContentPane(maintenance(mList.get(list.getSelectedIndex())));
		        dialog.setBounds(500,500,300,300);
		        dialog.setVisible(true);
			}
		});
		p.add(new JScrollPane(list), BorderLayout.CENTER);
		
		
		
		return p;
	}
	
	public JPanel validerM()
	{
		JPanel pV = new JPanel();
		bOK = new JButton("Valider");
		JLabel lab = new JLabel();
		pV.setLayout(new BorderLayout());
		pV.add(ConsultationM());
		pV.add(lab,BorderLayout.PAGE_END);
		pV.add(bOK, BorderLayout.PAGE_END);
		bOK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				mList.get(list.getSelectedIndex()).validerMaint();
				lab.setText(mList.get(list.getSelectedIndex()).getId()+"valid¨¦");
				pV.revalidate();
				pV.repaint();
		    }
		});
		return pV;
	}
	
	@SuppressWarnings("rawtypes")
	public JPanel affecter(){
		JPanel p = new JPanel();
		JPanel p2 = new JPanel();
		p.setLayout(new BorderLayout());
		p2.setLayout(new BorderLayout());
		JButton consulter = new JButton("Affecter");
		

		ArrayList<Maintenance> mList = new ArrayList<Maintenance>();
		ArrayList<Maintenance> mList2 = new ArrayList<Maintenance>();
		MaintenanceDAO mDAO = new MaintenanceDAO();
		mList2.addAll(mDAO.getListeMaintenance());
		for (int i=0; i<mList2.size();i++)
		{
			if (mList2.get(i).getidOp()==0)
				mList.add(mList2.get(i));
		}
		for (int i=0; i<mList.size();i++)
		{
			System.out.print(mList.get(i).getidOp());
		}
		
		JList listM = new JList(mList.toArray());

		listM.setVisibleRowCount(10);
		listM.setBorder(BorderFactory.createTitledBorder("Les maintenance non affect¨¦"));

		ArrayList<Operateur> oList = new ArrayList<Operateur>();
		OperateurDAO oDAO = new OperateurDAO();
		oList.addAll(oDAO.getListeOperateur());
		JList listO =new JList(oList.toArray());
		listO.setVisibleRowCount(10);
		listO.setBorder(BorderFactory.createTitledBorder("Les op¨¦rateur disponible"));
		
		p.add(new JScrollPane(listM), BorderLayout.WEST);
		p.add(new JScrollPane(listO), BorderLayout.EAST);
		p2.add(p, BorderLayout.NORTH);
		p2.add(consulter, BorderLayout.SOUTH);
		
		
		consulter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				mList2.get(listM.getSelectedIndex()).setidOp(oList.get(listO.getSelectedIndex()).getID());
				JOptionPane.showMessageDialog(null, oList.get(listO.getSelectedIndex()).getNom()+""+oList.get(listO.getSelectedIndex()).getPrenom()+
						"->Maintenance R¨¦f:"+mList2.get(listM.getSelectedIndex()).getId(),"R¨¦ussi",JOptionPane.INFORMATION_MESSAGE);
		    }
		});
		return p2;
	}
	
	




	

}
