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

public class SignUpPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel_left = new JPanel();
	JPanel panel_right = new JPanel();
	JLabel label_left = new JLabel();
	JLabel title_label = new JLabel("Sign Up", SwingConstants.CENTER);
	JButton footer = new JButton("<HTML>Vous avez déja un compte ? <U>Login</U></HTML>");
	JLabel message = new JLabel();
	
	
	JButton signup = new JButton("Sign Up");
	
	IconTextField nom = new IconTextField();
	IconTextField prenom = new IconTextField();
	IconTextField username = new IconTextField();
	IconPasswordField password = new IconPasswordField();
	
	boolean eleveFlag = false;
	
	
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
	
	ImageIcon name = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\QuizSystem\\src\\img\\name.png");
	Image imageName = name.getImage();
	ImageIcon name_icon = new ImageIcon(imageName.getScaledInstance(35, 35, Image.SCALE_SMOOTH));
	
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
	
	
	SignUpPanel(boolean eleveFlag){
		
		this.eleveFlag = eleveFlag;
		
		if (this.eleveFlag)
			label_left.setIcon(user_icon_eleve);
		else
			label_left.setIcon(user_icon_prof);
		
		
		Border border = title_label.getBorder();
		title_label.setBorder(new CompoundBorder(border, new EmptyBorder(60,0,0,0)));
		
		
		panel_left.setBackground(new Color(0x8A4FFF));
		panel_right.setBackground(Color.WHITE);
		
		if (eleveFlag)
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
		

		//Nom
		nom.setPreferredSize(new Dimension(300,40));
		nom.setForeground(Color.GRAY);
		nom.setFont(new Font("Quicksand Medium", Font.PLAIN, 18));
		nom.setBackground(new Color(0xE5ECF4));
		nom.setText("Nom");
		nom.setMargin(new Insets(8,6,10,10));
		nom.setIcon(name_icon);
		nom.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (nom.getText().trim().equals("Nom"))
					nom.setText("");
					nom.setForeground(Color.BLACK);
					}
			public void focusLost(FocusEvent e) {
				if (nom.getText().trim().equals("")) {
					nom.setText("Nom");
					nom.setForeground(Color.GRAY);
					}	
			}
		});
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(30, 5, 5, 5);
				
		panel_right.add(nom, c);
		
		//Prenom
		prenom.setPreferredSize(new Dimension(300,40));
		prenom.setForeground(Color.GRAY);
		prenom.setFont(new Font("Quicksand Medium", Font.PLAIN, 18));
		prenom.setBackground(new Color(0xE5ECF4));
		prenom.setText("Prénom");
		prenom.setMargin(new Insets(8,6,10,10));
		prenom.setIcon(name_icon);
		prenom.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (prenom.getText().trim().equals("Prénom"))
					prenom.setText("");
				prenom.setForeground(Color.BLACK);
			}
			public void focusLost(FocusEvent e) {
				if (prenom.getText().trim().equals("")) {
					prenom.setText("Prénom");
					prenom.setForeground(Color.GRAY);
				}	
			}
		});
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(30, 5, 5, 5);

		panel_right.add(prenom, c);
		
		//Username
		username.setPreferredSize(new Dimension(300,40));
		username.setForeground(Color.GRAY);
		username.setFont(new Font("Quicksand Medium", Font.PLAIN, 18));
		username.setBackground(new Color(0xE5ECF4));
		username.setText("Username");
		username.setMargin(new Insets(8,6,10,10));
		username.setIcon(id_icon);
		username.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (username.getText().trim().equals("Username"))
					username.setText("");
				username.setForeground(Color.BLACK);
			}
			public void focusLost(FocusEvent e) {
				if (username.getText().trim().equals("")) {
					username.setText("Username");
					username.setForeground(Color.GRAY);
				}	
			}
		});
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(30, 5, 5, 5);

		panel_right.add(username, c);
		
	
		
		//Password
		password.setPreferredSize(new Dimension(300,40));
		password.setForeground(Color.GRAY);
		password.setFont(new Font("Quicksand Medium", Font.PLAIN, 23));
		password.setBackground(new Color(0xE5ECF4));
		password.setText("password");
		password.setMargin(new Insets(8,6,10,10));
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
		c.gridy = 4;
		c.insets = new Insets(30, 5, 5, 5);
		panel_right.add(password,c);
		
		//signup Button
		signup.setFont(new Font("Quicksand",Font.BOLD,24));
		signup.setFocusable(false);
		signup.setBackground(new Color(0x8A4FFF));
		signup.setPreferredSize(new Dimension(170,40));
		signup.setBorder(BorderFactory.createBevelBorder(2));
		signup.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        signup.setBackground(new Color(0x7558b0));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        signup.setBackground(new Color(0x8A4FFF));
		    }
		});
		signup.setForeground(Color.white);
		signup.addActionListener(this);
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(30, 5, 5, 5);
		panel_right.add(signup, c);
		
		//footer
		footer.setFont(new Font("Quicksand Medium", Font.PLAIN, 18));
		footer.setForeground(new Color(0x8A4FFF));
		footer.setFocusable(false);
		footer.setBackground(null);
		footer.setBorder(null);
		c.gridx = 0;
		c.gridy = 6;
		c.insets = new Insets(15, 5, 5, 5);
		panel_right.add(footer, c);
		
		//Message
		message.setFont(new Font("Quicksand Medium", Font.PLAIN, 16));
		message.setIconTextGap(10);
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 7;
		c.insets = new Insets(15, 5, 5, 5);
		panel_right.add(message,c);
		
		this.setLayout(new GridLayout(1,2));
		this.add(panel_left);
		this.add(panel_right);
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == signup) {
			String nom_user = nom.getText();
			String prenom_user = prenom.getText();
			String login_user = username.getText();
			String password_user = password.getText();
			
			if (this.eleveFlag){
				if (!(nom_user.equals("Nom")) && !(nom_user.equals(""))) {
					if (!(prenom_user.equals("Prénom")) && !(prenom_user.equals(""))) {
						if (!(login_user.equals("Username")) && !(login_user.equals(""))) {
							if (!(password_user.equals("password")) && !(password_user.equals(""))) {
								Eleve eleve = null;
								try {
									eleve = new Eleve(nom_user, prenom_user, login_user, password_user);
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								if (eleve.no_problem)
									displayMessage("Inscription réussite !", check_icon, new Color(0x3BB54A), new Color(0xb8ffbb));
								else 
									displayMessage("Username existe déja !", wrong_icon, new Color(0xD7443E), new Color(0xffc8c2));
							}
							else 
								displayMessage("Veuillez choisir un mot de passe !", wrong_icon, new Color(0xD7443E), new Color(0xffc8c2));
						}
						else
							displayMessage("Veuillez choisir un username !", wrong_icon, new Color(0xD7443E), new Color(0xffc8c2));
					}
					else
						displayMessage("Veuillez saisir votre prénom !", wrong_icon, new Color(0xD7443E), new Color(0xffc8c2));
				}
				else 
					displayMessage("Veuillez saisir votre nom !", wrong_icon, new Color(0xD7443E), new Color(0xffc8c2));
			}
			
			else {
				if (!(nom_user.equals("Nom")) && !(nom_user.equals(""))) {
					if (!(prenom_user.equals("Prénom")) && !(prenom_user.equals(""))) {
						if (!(login_user.equals("Username")) && !(login_user.equals(""))) {
							if (!(password_user.equals("password")) && !(password_user.equals(""))) {
								Professeur prof = null;
								try {
									prof = new Professeur(nom_user, prenom_user, login_user, password_user);
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								if (prof.no_problem)
									displayMessage("Inscription réussite !", check_icon, new Color(0x3BB54A), new Color(0xb8ffbb));
								else 
									displayMessage("Username existe déja !", wrong_icon, new Color(0xD7443E), new Color(0xffc8c2));
							}
							else 
								displayMessage("Veuillez choisir un mot de passe !", wrong_icon, new Color(0xD7443E), new Color(0xffc8c2));
						}
						else
							displayMessage("Veuillez choisir un username !", wrong_icon, new Color(0xD7443E), new Color(0xffc8c2));
					}
					else
						displayMessage("Veuillez saisir votre prénom !", wrong_icon, new Color(0xD7443E), new Color(0xffc8c2));
				}
				else 
					displayMessage("Veuillez saisir votre nom !", wrong_icon, new Color(0xD7443E), new Color(0xffc8c2));
			}
			
		}
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
	
	
	


/*try {

ObjectInputStream o1 = new ObjectInputStream(new FileInputStream("./nbEleves.ser"));
int nbEleves = (int) o1.readObject();
o1.close();

FileOutputStream f = new FileOutputStream("./eleves.ser",true);
ObjectOutputStream o = new ObjectOutputStream(f);
o.writeObject(eleve);
nbEleves++;
o.close();
f.close();


ObjectOutputStream o2 = new ObjectOutputStream(new FileOutputStream("./nbEleves.ser"));
o2.writeObject(nbEleves);
o2.close();
}
catch (FileNotFoundException e1) {
System.out.println("File not found");
} catch (IOException e1) {
System.out.println("Error initializing stream");
} catch (ClassNotFoundException e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
*/