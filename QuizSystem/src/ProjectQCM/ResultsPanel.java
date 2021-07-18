package ProjectQCM;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class ResultsPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Header, Section, Footer
	
	JFrame frame = new JFrame();
	
	JPanel panel = new JPanel();
	JPanel header = new JPanel();
	JPanel section = new JPanel();
	JPanel footer = new JPanel();
	JLabel h1 = new JLabel("Excellent !", SwingConstants.CENTER);
	JLabel h2 = new JLabel("", SwingConstants.CENTER);
	JLabel h3 = new JLabel("Votre note est : ", SwingConstants.CENTER);
	JLabel note_label = new JLabel("",SwingConstants.CENTER);
	
	JLabel correct_label = new JLabel("", SwingConstants.CENTER);
	JLabel false_label = new JLabel("", SwingConstants.CENTER);
	JButton profile = new JButton("Retour");
	GridBagConstraints c = new GridBagConstraints();
	
	//Correct answers icon
	ImageIcon check = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\QuizSystem\\src\\img\\check.png");
	Image image1 = check.getImage();
	ImageIcon check_icon = new ImageIcon(image1.getScaledInstance(22, 25, Image.SCALE_SMOOTH));
	
	//Wrong answers icon 
	ImageIcon wrong = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\QuizSystem\\src\\img\\close.png");
	Image image2 = wrong.getImage();
	ImageIcon wrong_icon = new ImageIcon(image2.getScaledInstance(22, 25, Image.SCALE_SMOOTH));	// transform it back
	
	
	ResultsPanel(int correct, int faux,float note, Examen exam){
		//Margins
		Border border = h1.getBorder();
		h1.setBorder(new CompoundBorder(border, new EmptyBorder(20,100,20,100)));
		h3.setBorder(new CompoundBorder(border, new EmptyBorder(20,0,0,0)));
		footer.setBorder(new CompoundBorder(border, new EmptyBorder(0,0,50,0)));
		
		
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(600,500));
		panel.setBorder(BorderFactory.createLineBorder(new Color(0xaeb5bd), 3));
		panel.setLayout(new BorderLayout(20,20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		
		
		h1.setForeground(new Color(0x8A4FFF));
		h2.setForeground(new Color(0xBFBEF7));
		h3.setForeground(new Color(0x8A4FFF));
		h1.setFont(new Font("Quicksand Medium",Font.PLAIN,24));
		h2.setFont(new Font("Quicksand",Font.PLAIN,26));
		h3.setFont(new Font("Quicksand Medium",Font.PLAIN,23));
		
		h2.setText(exam.matiere);
		
		header.setLayout(new BorderLayout());
		header.setBackground(Color.WHITE);
		header.add(h1, BorderLayout.NORTH);
		header.add(h2, BorderLayout.CENTER);
		header.add(h3, BorderLayout.SOUTH);
		
		
		note_label.setForeground(new Color(0x8A4FFF));
		note_label.setFont(new Font("Quicksand",Font.PLAIN,30));
		note_label.setText(String.valueOf((float) Math.round(note))+" /20");
		correct_label.setForeground(new Color(0x61b15a));
		correct_label.setFont(new Font("Quicksand Medium",Font.PLAIN,25));
		correct_label.setIcon(check_icon);
		correct_label.setText("  "+String.valueOf(correct) + " réponses correcte parmis "+exam.questions.length);
		false_label.setForeground(new Color(0xFF4F4F));
		false_label.setFont(new Font("Quicksand Medium",Font.PLAIN,25));
		false_label.setIcon(wrong_icon);
		false_label.setText("  "+String.valueOf(faux) + " réponses fausses parmis "+exam.questions.length);

		
		section.setBackground(Color.WHITE);
		section.setLayout(new GridLayout(3,1));
		section.add(note_label);
		section.add(correct_label);
		section.add(false_label);
		
		profile.setFont(new Font("Quicksand",Font.BOLD,24));
		profile.setFocusable(false);
		profile.setBackground(new Color(0x8A4FFF));
		profile.setPreferredSize(new Dimension(160,60));
		profile.setBorder(BorderFactory.createBevelBorder(2));
		profile.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        profile.setBackground(new Color(0x7558b0));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        profile.setBackground(new Color(0x8A4FFF));
		    }
		});
		profile.setForeground(Color.white);
		footer.setBackground(Color.WHITE);
		footer.add(profile);
		
		panel.add(header, BorderLayout.NORTH);
		panel.add(section, BorderLayout.CENTER);
		panel.add(footer, BorderLayout.SOUTH);
		
		
		/*frame.setLayout(new GridBagLayout());
		frame.getContentPane().setBackground(new Color(0x8A4FFF));
		frame.add(panel, c);
		
		frame.setTitle("Quiz System");
		frame.setSize(700,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(0x8A4FFF));
		this.add(panel, c);
		

	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
