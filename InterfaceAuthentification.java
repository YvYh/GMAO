package app;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class InterfaceAuthentification extends JFrame{ //implements ActionListener{
	/**
	 * the login windows
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labID = new JLabel("Identifiant:");
	private JTextField textID;//id field
	private JLabel labPasse = new JLabel("Mot de passe:");
	private JPasswordField textMot;//password field
	private JButton bConnecter;//login button
	private Utilisateur u;

	
	public InterfaceAuthentification(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new java.awt.FlowLayout());
        this.setTitle("Connection");
        
        textID = new JTextField(15);
        textMot = new JPasswordField(15);
        bConnecter = new JButton("Se Connecter");
        this.getContentPane().add(labID);
        this.getContentPane().add(textID);
        this.getContentPane().add(labPasse);
        this.getContentPane().add(textMot);
        this.getContentPane().add(bConnecter);
        
        this.setSize(300,200);
        this.setVisible(true);
        
        bConnecter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				u = new Utilisateur("YU","Hong","abc",1,"abc"); //the return valuer of Authentiication.Connecter
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
							
						//else//password is wrong
							//JOptionPane.showMessageDialog(this, "erreur mot de passe",
									//"Erreur", JOptionPane.ERROR_MESSAGE);
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