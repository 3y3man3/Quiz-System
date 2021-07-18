package ProjectQCM;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	
	JFrame frame = new JFrame();
	JPanel container = new JPanel();
	HomePanel homePanel = new HomePanel();
	
	SignUpPanel signUpPanelEleve = new SignUpPanel(true);
	SignUpPanel signUpPanelProf = new SignUpPanel(false);
	
	LoginPanel loginPanelEleve = new LoginPanel(true);
	LoginPanel loginPanelProf = new LoginPanel(false);
	
	CardLayout cl = new CardLayout();
	
	ProfilePanel profilePanel;
	QuizPanel quizPanel;
	
	Examen selectedExamen;
	
	SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
    
	
	Main(){
		/*Le constructeur Main est divisé en deux parties : la premiere presente l'espace Etudiant,
		 * et la deuxieme presente l'espace Prof*/
				
		container.setLayout(cl);
		container.add(homePanel, "Home Panel");
		container.add(signUpPanelEleve, "SignUp Panel Eleve");
		container.add(signUpPanelProf, "SignUp Panel Prof");
		container.add(loginPanelEleve, "Login Panel Eleve");
		container.add(loginPanelProf, "Login Panel Prof");
		
		
		
		cl.show(container, "Home Panel");
		
		homePanel.button_right.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(container, "Login Panel Eleve");
			}
		});
		
		
		//Espace Etudiant: "Passer le QCM"
		signUpPanelEleve.footer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg1) {
				cl.show(container, "Login Panel Eleve");
			}
		});
		
		loginPanelEleve.footer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg1) {
				
				cl.show(container, "SignUp Panel Eleve");
			}
		});
		
		
		loginPanelEleve.login.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent arg1) {
				loginPanelEleve.loginUser();
				Eleve elv = (Eleve) loginPanelEleve.user;
				
				if (!(elv == null)) {
						/*String[] choix1 = {"A Gas Giant (like Jupiter)", "An Exoplanet (like HD 80606b)",
								"A Dwarf Planet (like Ceres)", "A Terrestrial Planet (like Earth)"};
						String[] choix2 = {"Jupiter", "Saturn", "Earth", "Venus"};
						String[] choix3 = {"Rust", "Chili Powder", "Chlorophyll", "Lava"};
						String[] choix4 = {"Carbon Monoxide", "Iron Oxide", "Carbon Dioxide", "Water"};
						String[] choix5 = {"Sojourner","Spirit","Curiosity","Endeavour"};
						String[] choix6 = {"Perseverance","Insight","Pathfinder","Viking"};
						
						String[] logins = {"aymane.ouahbi"};
						
						Question[] questions = {new Question("What type of planet is Mars ?", choix1, 4, (float) 1),
								new Question("What planet's orbit is closest to the orbit of Mars ?", choix2, 3, (float) 1),
								new Question("Which substance is primarily responsible for the color of Mars ?", choix3, 1, (float) 1),
								new Question("The chemical name for rust is ....", choix4, 2, (float) 1),
								new Question("Which of the following is NOT a name of a Mars rover ?", choix5, 4, (float) 1),
								new Question("Which NASA mission most recently landed on Mars?", choix6, 1, (float) 1),
						};
						
						try {
							new Examen("Planet Mars", "Jack", "Exam 1", "mars",
									 false, 100,
									6, questions,
									logins,"08/06/2021","22:58");
							elv.noter(elv.login, "mars", (float) -1);
						} catch (ClassNotFoundException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}*/
						
			
											
						try {
							profilePanel = new ProfilePanel(elv);
							container.add(profilePanel, "Profile Panel");
							cl.show(container, "Profile Panel");
							
							String date = String.valueOf(java.time.LocalDate.now()).substring(8, 10)+"/"+
									String.valueOf(java.time.LocalDate.now()).substring(5, 7)+"/"+
									String.valueOf(java.time.LocalDate.now()).substring(0, 4);
							System.out.println(date);

							
							
							profilePanel.nextTable.addMouseListener(new MouseListener() {
								@Override
								public void mouseClicked(MouseEvent e) {
									// TODO Auto-generated method stub
									System.out.println(profilePanel.nextTable.getSelectedRow());
									selectedExamen = profilePanel.selectedExam(profilePanel.nextTable.getSelectedRow());
									System.out.println(selectedExamen.titre);
									
									Date d1 = null;
									Date d2 = null;
									try {
										d1 = sdformat.parse(date);
										d2 = sdformat.parse(selectedExamen.date);
									} catch (ParseException e2) {
										// TODO Auto-generated catch block
										e2.printStackTrace();
									}
									
									LocalTime time1 = LocalTime.now();
							        LocalTime time2 = LocalTime.of(Integer.valueOf(selectedExamen.heure.substring(0, 2)), Integer.valueOf(selectedExamen.heure.substring(3, 5)), 00);
									
									if ((!(selectedExamen == null)) && (d1.compareTo(d2)>0 || (d1.compareTo(d2)== 0 && time1.isAfter(time2)))) {		
										System.out.println(selectedExamen.matiere);
										profilePanel.button.addActionListener(new ActionListener() {
											@Override
											public void actionPerformed(ActionEvent e) {
												// TODO Auto-generated method stub
												try {
													quizPanel = new QuizPanel(selectedExamen, elv);
													container.add(quizPanel, "Quiz Panel");
													cl.show(container, "Quiz Panel");
													frame.addWindowListener(quizPanel.listener);
												} catch (InterruptedException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
											}
											});
									}
								}

								@Override
								public void mousePressed(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void mouseReleased(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void mouseEntered(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void mouseExited(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
								
							});
							
							
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				
				}
			}
		});
		
		
		
		
		//Espace Prof: "Creer" un QCM
		signUpPanelProf.footer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg1) {
				cl.show(container, "Login Panel Prof");
			}
		});
		homePanel.button_left.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(container, "Login Panel Prof");
			}
		});
		loginPanelProf.footer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg1) {
				
				cl.show(container, "SignUp Panel Prof");
			}
		});
		
		
		
		
		
		frame.setTitle("Quiz System");
		frame.setSize(700,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(container);
	}
	

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub	
		new Main();		
	}

}
