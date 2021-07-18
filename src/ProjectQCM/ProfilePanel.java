package ProjectQCM;

import java.awt.CardLayout;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;

public class ProfilePanel extends JPanel {	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Eleve eleve;
	Examen[] nextExamens;
	Examen selectedExamen;
	
	int[] selectedCell = new int[2];

	
	JPanel all = new JPanel();
	JPanel container = new JPanel();
	
	JPanel info = new JPanel();
	JLabel nomLabel = new JLabel();
	JLabel prenomLabel = new JLabel();
	JLabel usernameLabel = new JLabel();
	
	JPanel iconPanel = new JPanel();
	JLabel iconLabel = new JLabel();
	
	JPanel tablePanel = new JPanel();
	JPanel navPanel = new JPanel();
	JButton previousExamsButton = new JButton("Vos Notes");
	JButton nextExamsButton = new JButton("QCMs Disponibles");
	
	
	
	String[] header = {"Matiere", "Professeur", "Note sur 20"};
	String[] nextHeader = {"Matiere", "Professeur", "Date"};

	
	
	GridBagConstraints c = new GridBagConstraints();
	
	
	JTable nextTable;
	
	JScrollPane scrollPane;
	JScrollPane nextScrollPane;

	
	Object[][] donnees;
	Object[][] nextDonnees;
	
	
	
	ImageIcon icon = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\QuizSystem\\src\\img\\user.png");
	Image image1 = icon.getImage();
	ImageIcon user_icon = new ImageIcon(image1.getScaledInstance(90, 90, Image.SCALE_SMOOTH));
	
	JButton button = new JButton("Commencer le QCM");
	
	CardLayout cl = new CardLayout();
	CardLayout clGlobal = new CardLayout();
	
	ProfilePanel(Eleve eleve) throws ClassNotFoundException{
		this.eleve = eleve;
		
		donnees = new Object[this.eleve.listeExamens().toArray().length][3];
		
		System.out.println(this.eleve.notes);
		
		
		int i = 0;
		for (Examen examen : this.eleve.listeExamens()) {
			if (this.eleve.notes.get(examen.identifiant) != (float) -1) {
				Object[] row = {examen.matiere, examen.login_professeur, this.eleve.notes.get(examen.identifiant)};
				donnees[i] = row;
				i++;
			}
		}
		nextDonnees = new Object[this.eleve.listeExamens().toArray().length-i][3];
		nextExamens = new Examen[this.eleve.listeExamens().toArray().length-i];
		i=0;
		for (Examen exam : this.eleve.listeExamens()) {
			if (this.eleve.notes.get(exam.identifiant) == (float) -1) {
				Object[] nextRow = {exam.matiere, exam.login_professeur, exam.date+" "+exam.heure};
				nextDonnees[i] = nextRow;
				nextExamens[i] = exam;
				i++;
			}	
		}
		
		
		c.gridx = 0;
		c.gridy = 0;
		
		nomLabel.setForeground(new Color(0x8A4FFF));
		nomLabel.setFont(new Font("Quicksand",Font.BOLD,20));
		nomLabel.setText("Nom : " + eleve.nom);
		
		prenomLabel.setForeground(new Color(0x8A4FFF));
		prenomLabel.setFont(new Font("Quicksand",Font.BOLD,20));
		prenomLabel.setText("Préom : " + eleve.prenom);
		
		usernameLabel.setForeground(new Color(0x8A4FFF));
		usernameLabel.setFont(new Font("Quicksand",Font.BOLD,20));
		usernameLabel.setText("Username : " + eleve.login);
		
		info.setBackground(Color.WHITE);
		info.setLayout(new GridLayout(3,1));
		info.add(nomLabel);
		info.add(prenomLabel);
		info.add(usernameLabel);
		
		container.setLayout(new GridBagLayout());
		container.add(info, c);
		
		
		c.gridx = 1;
		c.insets = new Insets(0,200,0,0);
		iconLabel.setIcon(user_icon);
		iconPanel.add(iconLabel);
		iconPanel.setBackground(Color.WHITE);
		container.add(iconPanel, c);
		
		
		previousExamsButton.setFocusable(false);
		nextExamsButton.setFocusable(false);
		nextExamsButton.setForeground(Color.WHITE);
		previousExamsButton.setForeground(Color.WHITE);
		previousExamsButton.setBackground(new Color(0x8A4FFF));
		nextExamsButton.setBackground(new Color(0x8A4FFF));
		previousExamsButton.setFont(new Font("Quicksand",Font.PLAIN,17));
		nextExamsButton.setFont(new Font("Quicksand",Font.PLAIN,17));
		navPanel.setBackground(Color.WHITE);
		
		
		navPanel.add(previousExamsButton);
		navPanel.add(nextExamsButton);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.insets = new Insets(6,0,0,0);
		container.add(navPanel, c);
		
		button.setFocusable(false);
		button.setBackground(new Color(0x8A4FFF));
		button.setFont(new Font("Quicksand",Font.PLAIN,18));
		button.setForeground(Color.WHITE);
		button.setPreferredSize(new Dimension(90,50));
		

		
		createExamsPrecedentsTable();
		createExamsSuivantsTable();
		
		
		tablePanel.setLayout(cl);
		tablePanel.add(scrollPane, "Previous Scroll Pane");
		tablePanel.add(nextScrollPane, "Next Scroll Pane");
		tablePanel.setPreferredSize(new Dimension(450,450));
		
		cl.show(tablePanel, "Previous Scroll Pane");
		this.previousExamsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(tablePanel, "Previous Scroll Pane");
			}
		});
		this.nextExamsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(tablePanel, "Next Scroll Pane");
			}
		});
		
		
		container.add(tablePanel, c);
		
		c.gridy = 3;
		container.add(button,c);
		
		container.setBackground(Color.WHITE);
		
		this.setBackground(Color.WHITE);
		this.add(container);			
	}
	
	
	
	public JTable createExamsPrecedentsTable() throws ClassNotFoundException {
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.ipadx = 150;
		c.insets = new Insets(0,0,0,0);
		
		JTable table = new JTable(donnees,header);
		
		scrollPane = new JScrollPane(table);

		
		
		
		table.getTableHeader().setBackground(new Color(0xE5ECF4));
		table.getTableHeader().setFont(new Font("Quicksand",Font.PLAIN,17));
		table.getTableHeader().setForeground(new Color(0x8A4FFF));
		table.setFont(new Font("Quicksand",Font.PLAIN,17));
		table.getTableHeader().setPreferredSize(new Dimension(600,60));
		table.setRowHeight(60);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);

		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); 
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.setBackground(new Color(0xEFFFFA));
       
		table.setEnabled(false);
		
		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = new Color(0x8A4FFF);
		    }
		});
		
		return table;
	}
		
	public void createExamsSuivantsTable() {
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.ipadx = 150;
		c.insets = new Insets(0,0,4,0);
		
		nextTable = new JTable(nextDonnees, nextHeader);
		
		nextScrollPane = new JScrollPane(nextTable);
		
		nextTable.getTableHeader().setBackground(new Color(0xE5ECF4));
		nextTable.getTableHeader().setFont(new Font("Quicksand",Font.PLAIN,17));
		nextTable.getTableHeader().setForeground(new Color(0x8A4FFF));
		nextTable.setFont(new Font("Quicksand",Font.PLAIN,17));
		nextTable.getTableHeader().setPreferredSize(new Dimension(600,60));
		nextTable.setRowHeight(60);
		nextTable.getColumnModel().getColumn(0).setPreferredWidth(170);
		nextTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		nextTable.getColumnModel().getColumn(2).setPreferredWidth(80);

		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		nextTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); 
		nextTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); 
		nextTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		nextTable.setBackground(new Color(0xEFFFFA));
		
		nextTable.setSelectionBackground(new Color(0x33ff44));
        
		
		
		nextScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = new Color(0x8A4FFF);
		    }
		});
		
	}
	
	public Examen selectedExam(int row) {
		if (row != -1) {
			Examen selectedExam = nextExamens[row];
			return selectedExam;
		}
		return null;
	}	
}
