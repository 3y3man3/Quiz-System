package ProjectQCM;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class QuizPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Examen examen; // L'Examen à passer;
	Eleve eleve; // L'eleve qui va passer l'Examen;
	
	int reponse;
	float noteExam;
	float noteTotale = 0;
	
	int corrects = 0; //Nb de reponses correctes;
	int faux = 0; //Nb de reponses fausses;
	
	int temps_dispo ;
	int counter = 0;
	int temps_restant = temps_dispo;
	
	int i = 0; //Numero de la question;
	
	Timer timer; //Progress Time Bar timer;
	
	JFrame frame = new JFrame();
	
	JPanel header = new JPanel();
	JPanel section = new JPanel();
	JPanel footer = new JPanel();
	JPanel buttons = new JPanel();
	JPanel suivant_panel = new JPanel();
	JLabel timer_label = new JLabel(); // Timer Label
	JLabel header_label = new JLabel();
	JLabel question_label = new JLabel();
	JButton Option1 = new JButton();
	JButton Option2 = new JButton();
	JButton Option3 = new JButton();
	JButton Option4 = new JButton();
	JButton suivant = new JButton();
	JProgressBar bar = new JProgressBar(0,10); // la barre de temps
	
	JPanel profile = new JPanel();
	JPanel container = new JPanel();
	
	Color correctColor = new Color(0x3ed420);
	Color wrongColor = new Color(0xfc563d);
	
	CardLayout cl = new CardLayout();
	
	WindowListener listener;
	
	public QuizPanel(Examen examen, Eleve eleve) throws InterruptedException{
		this.examen = examen;
		this.eleve = eleve;
		
		if (this.examen.mode_sequentiel)
			temps_dispo = this.examen.questions[i].duree_question;
		else
			temps_dispo = this.examen.duree_totale;
		
		//Set margins
		Border border = header_label.getBorder();
		Border margin1 = new EmptyBorder(40,50,20,50);
		Border margin2 = new EmptyBorder(0,60,20,60);
		header_label.setBorder(new CompoundBorder(border, margin1));
		question_label.setBorder(new CompoundBorder(border, margin2));
		buttons.setBorder(new CompoundBorder(border, new EmptyBorder(10,100,20,100)));
		suivant_panel.setBorder(new CompoundBorder(border, new EmptyBorder(0,0,20,60)));
		timer_label.setBorder(new CompoundBorder(border, new EmptyBorder(15,0,0,80)));
		
		//Header
		header.setPreferredSize(new Dimension(100,100));
		header.setBackground(Color.white);
		header_label.setText("Question "+(i+1)+" sur "+this.examen.questions.length);
		header_label.setForeground(new Color(0xaeb5bd));
		header_label.setBackground(Color.WHITE);
		header_label.setFont(new Font("Quicksand Medium",Font.PLAIN,25));
		header_label.setOpaque(true);		
		header.setLayout(new BorderLayout());
		header.add(header_label, BorderLayout.WEST);
		
		//Timer
		timer_label.setForeground(new Color(0x8A4FFF));
		timer_label.setFont(new Font("Quicksand",Font.PLAIN,30));
		header.add(timer_label,BorderLayout.EAST);

		//Barre de temps
		bar.setValue(0);
		bar.setBounds(0,0,100,50);
		bar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		bar.setForeground(new Color(0x8A4FFF));
		
		header.add(bar,BorderLayout.SOUTH);
				
		//Questions Section
		section.setPreferredSize(new Dimension(100,100));
		section.setBackground(Color.white);
		
		question_label.setText("<HTML>"+this.examen.questions[i].enonce+"</HTML>");
		question_label.setForeground(new Color(0x8A4FFF));
		question_label.setFont(new Font("Quicksand",Font.PLAIN,22));
		question_label.setBackground(Color.white);
		question_label.setOpaque(true);		
		
		//Les buttons de choix de réponse
		Option1.setText(this.examen.questions[i].choix[0]);
		Option2.setText(this.examen.questions[i].choix[1]);
		Option3.setText(this.examen.questions[i].choix[2]);
		Option4.setText(this.examen.questions[i].choix[3]);
		suivant.setText("Suivant");
		
		Option1.setFont(new Font("Quicksand Medium",Font.PLAIN,21));
		Option2.setFont(new Font("Quicksand Medium",Font.PLAIN,21));
		Option3.setFont(new Font("Quicksand Medium",Font.PLAIN,21));
		Option4.setFont(new Font("Quicksand Medium",Font.PLAIN,21));
		suivant.setFont(new Font("Quicksand",Font.BOLD,20));

		Option1.setOpaque(true);
		Option2.setOpaque(true);
		Option3.setOpaque(true);
		Option4.setOpaque(true);
		suivant.setOpaque(true);
						
		Option1.setFocusable(false);
		Option2.setFocusable(false);
		Option3.setFocusable(false);
		Option4.setFocusable(false);
		suivant.setFocusable(false);
				
		Option1.setBackground(new Color(0xE5ECF4));
		Option2.setBackground(new Color(0xE5ECF4));
		Option3.setBackground(new Color(0xE5ECF4));
		Option4.setBackground(new Color(0xE5ECF4));
		suivant.setBackground(new Color(0x8A4FFF));
		
		Option1.addActionListener(this);
		Option2.addActionListener(this);
		Option3.addActionListener(this);
		Option4.addActionListener(this);
		suivant.addActionListener(this);
		
		suivant.setPreferredSize(new Dimension(160,60));
		suivant.setBorder(BorderFactory.createBevelBorder(2));
		suivant.setForeground(Color.white);
		suivant.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        suivant.setBackground(new Color(0x7558b0));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        suivant.setBackground(new Color(0x8A4FFF));
		    }
		});
				
		buttons.setBounds(0,0,100,100);


		section.setLayout(new BorderLayout());
		section.add(question_label, BorderLayout.NORTH);
		section.add(buttons, BorderLayout.CENTER);
		section.setBackground(Color.WHITE);
		buttons.setLayout(new GridLayout(4,1,20,20));
		buttons.setBackground(Color.WHITE);
		buttons.add(Option1);
		buttons.add(Option2);
		buttons.add(Option3);
		buttons.add(Option4);		 
		
		//Footer Section
		footer.setPreferredSize(new Dimension(100,100));
		footer.setBackground(Color.WHITE);
		footer.setLayout(new BorderLayout());
		suivant_panel.setBackground(Color.WHITE);
		suivant_panel.add(suivant);
		footer.add(suivant_panel, BorderLayout.EAST);
		
		container.setBackground(Color.WHITE);
		container.setLayout(new BorderLayout(20,20));
		
		container.add(header, BorderLayout.NORTH);
		container.add(section, BorderLayout.CENTER);
		container.add(footer, BorderLayout.SOUTH);
		
		this.setLayout(cl);
		this.add(container, "Quiz");
		cl.show(this,"Quiz");
		
		listener = new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
			      Frame frame = (Frame) evt.getSource();
			      System.out.println("Closing = "+frame.getTitle());
			      float note = (noteExam*20)/examen.noteTotale();
			      try {
					eleve.noter(eleve.login,examen.identifiant, (float) Math.round(note));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
			};
		
		
		count(); //Lancez le countdown dés que le QuizPanel instance est crée

		
	}
	
	public void count() {
		counter = 0;
		temps_restant = temps_dispo - counter;
		timer_label.setText(Integer.toString(temps_restant));
		bar.setMaximum(temps_dispo);
		bar.setValue(counter);
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				counter++;
				System.out.println(counter);
				temps_restant = temps_dispo - counter;
				timer_label.setText(Integer.toString(temps_restant));
				bar.setValue(counter);
				if (counter == temps_dispo) {
					displayCorrectAnswer();
				}
				else if (temps_restant < 0) {
					((Timer) e.getSource()).stop();
					counter = 0;
					temps_restant = temps_dispo - counter;
					timer_label.setText(Integer.toString(temps_restant));
					bar.setValue(counter);
					try {
						faux++;
						nextQuestion();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}							
			}
		});
		timer.start();		
	}
	
	public void nextQuestion() throws ClassNotFoundException, InterruptedException {
		noteTotale += this.examen.questions[i].note_question; 
		if (i == this.examen.questions.length-1) {
			float note = (noteExam*20)/this.examen.noteTotale();
			System.out.println("Votre note est: "+noteExam+" sur "+noteTotale);
			System.out.println("Votre note sur 20 est: " +(float) Math.round(note));
			System.out.println("Vous avez "+corrects+" questions correctes sur "+this.examen.questions.length);
			System.out.println("Vous avez "+faux+" questions fausses sur "+this.examen.questions.length);
			Eleve eleve = this.eleve.noter(this.eleve.login,this.examen.identifiant, (float) Math.round(note));
			ResultsPanel resultsPanel = new ResultsPanel(corrects,faux,note,this.examen);
			this.add(resultsPanel, "Quiz");
			JPanel parent = this;
			
			cl.show(this,"Quiz");
			resultsPanel.profile.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						ProfilePanel profilePanel = new ProfilePanel(eleve);
						parent.add(profilePanel, "Profile");
						cl.show(parent,"Profile");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}		
			});
			timer.stop();
		}
		else {
			Option1.setEnabled(true);
	  		Option2.setEnabled(true);
	  		Option3.setEnabled(true);
	  		Option4.setEnabled(true);
	  		suivant.setEnabled(true);
			i++;
			header_label.setText("Question "+(i+1)+" sur "+this.examen.questions.length);
			question_label.setText("<HTML>" + this.examen.questions[i].enonce + "</HTML>");
			Option1.setText(this.examen.questions[i].choix[0]);
			Option2.setText(this.examen.questions[i].choix[1]);
			Option3.setText(this.examen.questions[i].choix[2]);
			Option4.setText(this.examen.questions[i].choix[3]);
				
			Option1.setBackground(new Color(0xE5ECF4));
			Option2.setBackground(new Color(0xE5ECF4));
			Option3.setBackground(new Color(0xE5ECF4));
			Option4.setBackground(new Color(0xE5ECF4));
			
			if (this.examen.mode_sequentiel)
				count();
				
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Option1.setEnabled(false);
		Option2.setEnabled(false);
		Option3.setEnabled(false);
		Option4.setEnabled(false);
		suivant.setEnabled(false);
		
		if (this.examen.mode_sequentiel)
			timer.stop();
		
		if (e.getSource() == Option1) {
			this.reponse = 1;
			if (this.examen.questions[i].reponse == this.reponse) {
				Option1.setBackground(correctColor);
				noteExam += this.examen.questions[i].note_question;
				corrects++;
			} 
			else {
				Option1.setBackground(wrongColor);
				faux++;
			}
				
		}
		else if (e.getSource() == Option2) {
			this.reponse = 2;
			if (this.examen.questions[i].reponse == this.reponse) {
				Option2.setBackground(correctColor);
				noteExam += this.examen.questions[i].note_question;
				corrects++;
			} 
			else {
				Option2.setBackground(wrongColor);
				faux++;
			}
			
		}
		else if (e.getSource() == Option3) {
			this.reponse = 3;
			if (this.examen.questions[i].reponse == this.reponse) {
				Option3.setBackground(correctColor);
				noteExam += this.examen.questions[i].note_question;
				corrects++;
			}
				  
			else {
				Option3.setBackground(wrongColor);
				faux++;
			}
		}
		else if (e.getSource() == Option4) {
			this.reponse = 4;
			if (this.examen.questions[i].reponse == this.reponse) {
				Option4.setBackground(correctColor);
				noteExam += this.examen.questions[i].note_question;
				corrects++;
			}
			else {
				Option4.setBackground(wrongColor);
				faux++;
			}
		}
		else if (e.getSource() == suivant) {
			switch(this.examen.questions[i].reponse) {
			  case 1:
				  Option1.setBackground(correctColor);
			    break;
			  case 2:
				  Option2.setBackground(correctColor);
			    break;
			  case 3:
				  Option3.setBackground(correctColor);
			    break;
			  case 4:
				  Option4.setBackground(correctColor);
			    break;
			}
			faux++;
		}
				
		displayCorrectAnswer();
		
		Timer timer1 = new Timer(900, new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		    	try {
		  			nextQuestion();
		  		} catch (ClassNotFoundException e1) {
		  			// TODO Auto-generated catch block
		  			e1.printStackTrace();
		  		} catch (InterruptedException e1) {
		  			// TODO Auto-generated catch block
		  			e1.printStackTrace();
		  		}
		      }
		    });
		timer1.setRepeats(false); 
		timer1.start();		  
	}

		
	public void displayCorrectAnswer() {
		Option1.setEnabled(false);
		Option2.setEnabled(false);
		Option3.setEnabled(false);
		Option4.setEnabled(false);
		suivant.setEnabled(false);
		switch(this.examen.questions[i].reponse) {
		  case 1:
			  Option1.setBackground(correctColor);
		    break;
		  case 2:
			  Option2.setBackground(correctColor);
		    break;
		  case 3:
			  Option3.setBackground(correctColor);
		    break;
		  case 4:
			  Option4.setBackground(correctColor);
		    break;
		}	
	}
	
}
