package ProjectQCM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;


public class LoginPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Personne user = null;
	
	boolean eleveFlag = false;
	
	JPanel panel_left = new JPanel();
	JPanel panel_right = new JPanel();
	JLabel label_left = new JLabel();
	JLabel title_label = new JLabel("Log in", SwingConstants.CENTER);
	JButton footer = new JButton("<HTML><U>S'inscrire</U></HTML>");
	JLabel message = new JLabel();
	
	
	JButton login = new JButton("Login");
	
	IconTextField username = new IconTextField();
	IconPasswordField password = new IconPasswordField();
	
	
	
	//Créer les objets ImageIcon representant les icones
	ImageIcon iconEleve = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\QuizSystem\\src\\img\\student.png");
	Image image1Eleve = iconEleve.getImage();
	ImageIcon user_icon_eleve = new ImageIcon(image1Eleve.getScaledInstance(230, 230, Image.SCALE_SMOOTH));
	
	ImageIcon iconProf = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\QuizSystem\\src\\img\\exam-white.png");
	Image image1Prof = iconProf.getImage();
	ImageIcon user_icon_prof = new ImageIcon(image1Prof.getScaledInstance(230, 230, Image.SCALE_SMOOTH));
	
	ImageIcon id = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\QuizSystem\\src\\img\\id.png");
	Image image2 = id.getImage();
	ImageIcon id_icon = new ImageIcon(image2.getScaledInstance(35, 35, Image.SCALE_SMOOTH));
	
	ImageIcon padlock = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\QuizSystem\\src\\img\\padlock.png");
	Image image3 = padlock.getImage();
	ImageIcon padlock_icon = new ImageIcon(image3.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
	
	ImageIcon check = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\QuizSystem\\src\\img\\check.png");
	Image image4 = check.getImage();
	ImageIcon check_icon = new ImageIcon(image4.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		
	ImageIcon wrong = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\QuizSystem\\src\\img\\close.png");
	Image image5 = wrong.getImage();
	ImageIcon wrong_icon = new ImageIcon(image5.getScaledInstance(20, 20, Image.SCALE_SMOOTH));	// transform it back
	
	GridBagConstraints c = new GridBagConstraints();
	
	
	
	
	LoginPanel(boolean eleveFlag){
		this.eleveFlag = eleveFlag;
		
		Border border = title_label.getBorder();
		title_label.setBorder(new CompoundBorder(border, new EmptyBorder(60,0,0,0)));
		
		
		panel_left.setBackground(new Color(0x8A4FFF));
		panel_right.setBackground(Color.WHITE);
		
		if (this.eleveFlag)
			label_left.setIcon(user_icon_eleve);
		else
			label_left.setIcon(user_icon_prof);
		
		
		c.fill = GridBagConstraints.VERTICAL;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		panel_left.setLayout(new GridBagLayout());
		panel_left.add(label_left,c);
		
		panel_right.setLayout(new GridBagLayout());
		panel_right.setFocusable(true);
		//Title
		c.ipady = 15;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		title_label.setForeground(new Color(0x8A4FFF));
		title_label.setFont(new Font("Quicksand", Font.PLAIN, 32));
		title_label.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(0x8A4FFF)));
		panel_right.add(title_label,c);
		
		//Username
		username.setPreferredSize(new Dimension(300,50));
		username.setForeground(Color.GRAY);
		username.setFont(new Font("Quicksand Medium", Font.PLAIN, 20));
		username.setBackground(new Color(0xE5ECF4));
		username.setText("Identifiant");
		username.setMargin(new Insets(12,6,10,10));
		username.setIcon(id_icon);
		username.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (username.getText().trim().equals("Identifiant"))
					username.setText("");
				username.setForeground(Color.BLACK);
			}
			public void focusLost(FocusEvent e) {
				if (username.getText().trim().equals("")) {
					username.setText("Identifiant");
					username.setForeground(Color.GRAY);
				}	
			}
		});
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(30, 5, 5, 5);
		
		panel_right.add(username, c);
	
		
		//Password
		password.setPreferredSize(new Dimension(300,50));
		password.setForeground(Color.GRAY);
		password.setFont(new Font("Quicksand Medium", Font.PLAIN, 25));
		password.setBackground(new Color(0xE5ECF4));
		password.setText("password");
		password.setMargin(new Insets(12,6,10,10));
		password.setIcon(padlock_icon);
		password.addFocusListener(new java.awt.event.FocusAdapter() {
			@SuppressWarnings("deprecation")
			public void focusGained(FocusEvent e) {
				if (password.getText().equals("password"))
					password.setText("");
				password.setForeground(Color.BLACK);
			}
			@SuppressWarnings("deprecation")
			public void focusLost(FocusEvent e) {
				if (password.getText().equals("")) {
					password.setText("password");
					password.setForeground(Color.GRAY);
				}
					
			}
		});
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(30, 5, 5, 5);
		panel_right.add(password,c);
		
		//Login Button
		login.setFont(new Font("Quicksand",Font.BOLD,24));
		login.setFocusable(false);
		login.setBackground(new Color(0x8A4FFF));
		login.setPreferredSize(new Dimension(170,40));
		login.setBorder(BorderFactory.createBevelBorder(2));
		login.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        login.setBackground(new Color(0x7558b0));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        login.setBackground(new Color(0x8A4FFF));
		    }
		});
		login.setForeground(Color.white);
		login.addActionListener(this);
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(30, 5, 5, 5);
		panel_right.add(login, c);
		
		//footer
		footer.setFont(new Font("Quicksand Medium", Font.PLAIN, 18));
		footer.setForeground(new Color(0x8A4FFF));
		footer.setFocusable(false);
		footer.setBackground(null);
		footer.setBorder(null);
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(15, 5, 5, 5);
		panel_right.add(footer, c);
		
		//Message
		message.setFont(new Font("Quicksand Medium", Font.PLAIN, 17));
		message.setIconTextGap(10);
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(15, 5, 5, 5);
		panel_right.add(message,c);
		
		
		this.setLayout(new GridLayout(1,2));
		this.add(panel_left);
		this.add(panel_right);
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == login) {
			loginUser();


		}
	}
	
	public void loginUser() {
		String id = username.getText();
		@SuppressWarnings("deprecation")
		String pswd = password.getText();
		if (this.eleveFlag) {
			try {
				if (Eleve.login_existe(id, Eleve.chemin_fichier)) {
					if (!(Eleve.Login(id, pswd, Eleve.chemin_fichier) == null)) {
						this.user = (Eleve) Eleve.Login(id, pswd, Eleve.chemin_fichier);
						displayMessage("Login Successfully !", check_icon, new Color(0x3BB54A), new Color(0xb8ffbb));
					}
					else
						displayMessage("Mot de passse incorrect !", wrong_icon, new Color(0xD7443E), new Color(0xffc8c2));
				}
				else
					displayMessage("Username introuvable !", wrong_icon, new Color(0xD7443E), new Color(0xffc8c2));
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else {
			try {
				if (Professeur.login_existe(id, Professeur.chemin_fichier)) {
					if (!(Professeur.Login(id, pswd, Professeur.chemin_fichier) == null)) {
						this.user = (Professeur) Professeur.Login(id, pswd, Professeur.chemin_fichier);
						displayMessage("Login Successfully !", check_icon, new Color(0x3BB54A), new Color(0xb8ffbb));
					}
					else
						displayMessage("Mot de passse incorrect !", wrong_icon, new Color(0xD7443E), new Color(0xffc8c2));
				}
				else
					displayMessage("Username introuvable !", wrong_icon, new Color(0xD7443E), new Color(0xffc8c2));
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		//this.eleve = null;
	}
	
	
	public void displayMessage(String text, ImageIcon icon, Color fontColor, Color bgColor) {
		message.setForeground(fontColor);
		message.setBackground(bgColor);
		message.setText(text);
		message.setIcon(icon);
		message.setOpaque(true);
		Border border = message.getBorder();
		message.setBorder(new CompoundBorder(border, new EmptyBorder(0,20,0,20)));
}

	
	
	
	
}
