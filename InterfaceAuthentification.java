package app;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class InterfaceAuthentification extends JFrame{ //implements ActionListener{
	/**
	 * the login windows
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField textID;//id field
	private JPasswordField textMot;//password field
	private JButton bConnecter;//login button
	private JLabel lab;
	private JPanel panel;
	

	
	public InterfaceAuthentification(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = new JPanel();
        panel.setLayout(null);
        this.setTitle("Connection");
        JLabel labID = new JLabel("Identifiant:");
        labID.setBounds(10,20,80,25);
        textID = new JTextField(15);
        textID.setBounds(100,20,165,25);
        
        
        JLabel labPasse = new JLabel("Mot de passe:");
        labPasse.setBounds(10,50,120,25);
        textMot = new JPasswordField(15);
        textMot.setBounds(110,50,165,25);
        bConnecter = new JButton("Se Connecter");
        bConnecter.setBounds(10, 80, 120, 25);
        lab = new JLabel();
        panel.add(labID);
        panel.add(textID);
        panel.add(labPasse);
        panel.add(textMot);
        panel.add(bConnecter);
        panel.add(lab);
        this.setContentPane(panel);
        
        this.setSize(350,200);
        this.setVisible(true);
        
        bConnecter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				AuthentificationDAO aDAO = new AuthentificationDAO();
				Utilisateur u = new Utilisateur();//the return valuer of Authentiication.Connecter
				try {
					if (ae.getSource() == bConnecter) {
						u = aDAO.Connecter(textID.getText(), String.valueOf(textMot.getPassword()));
						System.out.print(u.getEtat());
						if (u.getEtat() > 0)//password is right
						{
							JOptionPane.showMessageDialog(panel, "Bienvenu!");
							if (u.getEtat() == 1)//this is a director
							{
								new MenuResponsable(u.getNom(), u.getPrenom());//turn into director's menu
							}
						}
							
						else//password is wrong
						{
							lab.setText("ID/Mot de passe incorrect");
							//JOptionPane.showMessageDialog(this, "erreur mot de passe",
									//"Erreur", JOptionPane.ERROR_MESSAGE);
						}
							
					}
				  
				}catch (Exception e) {
					//JOptionPane.showMessageDialog(this,
							//"Veuillez contrôler vos saisies", "Erreur",
							//JOptionPane.ERROR_MESSAGE);
					System.err.println("Veuillez contrôler vos saisies");
				}
				
			}
		});
	}
	
	
	/*public void actionPerformed(ActionEvent ae) {
		Utilisateur u = new Utilisateur("YU","Hong","abc",1,"abc"); //the return valuer of Authentiication.Connecter
		try {
			if (ae.getSource() == bConnecter) {
				//u = Authentification.Connecter(textID.getText(), textMot.getText());
				System.out.print(u.getEtat());
				if (u.getEtat() > 0)//password is right
				{
					//JOptionPane.showMessageDialog(this, "Bienvenu!");
					if (u.getEtat() == 1)//this is a director
					{
						new MenuResponsable(u.getNom(), u.getPrenom());//turn into director's menu
					}
				}
					
				else//password is wrong
					JOptionPane.showMessageDialog(this, "erreur mot de passe",
							"Erreur", JOptionPane.ERROR_MESSAGE);
			}
		  
		}catch (Exception e) {
			JOptionPane.showMessageDialog(this,
					"Veuillez contrôler vos saisies", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			System.err.println("Veuillez contrôler vos saisies");
		}
			
	}*/

	
	public static void main(String[] args)
	{
		new InterfaceAuthentification();
	}
	
	
	
}