package ProjectQCM;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


//IN EACH SIDE, INSERT A REPRESENTATIVE ICON 

public class HomePanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel_left = new JPanel();
	JPanel panel_right = new JPanel();
	
	JLabel label_right = new JLabel();
	JLabel label_left = new JLabel();
	
	JButton button_left = new JButton("Créez un QCM");
	JButton button_right = new JButton("Passer un QCM");
	GridBagConstraints c = new GridBagConstraints();
	
	ImageIcon icon1 = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\QuizSystem\\src\\img\\student.png");
	Image image1 = icon1.getImage();
	ImageIcon right_icon = new ImageIcon(image1.getScaledInstance(230, 230, Image.SCALE_SMOOTH));
	
	ImageIcon icon2 = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\QuizSystem\\src\\img\\exam.png");
	Image image2 = icon2.getImage();
	ImageIcon left_icon = new ImageIcon(image2.getScaledInstance(230, 230, Image.SCALE_SMOOTH));
	
	HomePanel(){
		panel_right.setBackground(new Color(0x8A4FFF));
		panel_left.setBackground(Color.WHITE);
		
		
		button_right.setBackground(Color.WHITE);
		button_left.setBackground(new Color(0x8A4FFF));
		
		button_right.setForeground(new Color(0x8A4FFF));
		button_left.setForeground(Color.WHITE);
		
		button_right.setFont(new Font("Quicksand Medium", Font.PLAIN, 24));
		button_left.setFont(new Font("Quicksand Medium",Font.PLAIN,24));
		
		button_right.setFocusable(false);
		button_left.setFocusable(false);
		
		button_right.addActionListener(this);
		
		panel_left.setLayout(new GridBagLayout());
		panel_right.setLayout(new GridBagLayout());
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(30, 5, 5, 5);
		label_right.setIcon(right_icon);
		panel_right.add(label_right, c);
		
		label_left.setIcon(left_icon);
		panel_left.add(label_left, c);
		
		c.gridy = 1;
		panel_left.add(button_left, c);
		panel_right.add(button_right, c);
		
		
		
		
		//frame.setTitle("Quiz System");
		//frame.setSize(700,700);
		//frame.setVisible(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1,2));
		this.add(panel_left);
		this.add(panel_right);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
