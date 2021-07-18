package ProjectQCM;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Examen implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String matiere;
	public String login_professeur;
	public String titre;
	public String identifiant;
	public boolean mode_sequentiel;
	public int duree_totale; //Durée en minutes
	public int nombre_questions;
	public Question[] questions;
	public String[] logins_Eleves;
	float noteTotale;
	String date;
	String heure;
	public static final String fichier_examens = "C:\\Users\\User\\eclipse-workspace\\QuizSystem\\Examens.ser";
	public static final String fichier_examens_temp = "C:\\Users\\User\\eclipse-workspace\\QuizSystem\\Examens_temp.ser";
	
	private static boolean Id_exists(String identifiant) throws ClassNotFoundException {
		try {
			FileInputStream fis = new FileInputStream(fichier_examens);
			ObjectInputStream oos = new ObjectInputStream(fis);
			Examen examen = (Examen) oos.readObject();
			while (fis.available() > 0) {
				examen = (Examen) oos.readObject();
				if (examen.identifiant.equals(identifiant) ) {
					fis.close();
					oos.close();
					return true;
				}
			}
			fis.close();
			return false;
		}
		catch(IOException e) {
			System.out.println("Erreur de lecture d'identifiants");
		}
		
		return false;
		
	}
	
	
	
	public static void add_exam(Examen examen) throws ClassNotFoundException {
		
		
		if (vide(fichier_examens)) {
			//Verifier tout d'abourd si le fichier était vide
			vider(fichier_examens);
			try {
				FileOutputStream fos = new FileOutputStream(fichier_examens);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(examen);
				fos.flush();
				fos.close();
			
			}
			catch (IOException e) {
			
			}
		}
		
		else {
			try {
				FileOutputStream fos = new FileOutputStream(fichier_examens_temp);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				if (!(Id_exists(examen.identifiant)))
					oos.writeObject(examen);
				FileInputStream fis = new FileInputStream(fichier_examens);
				ObjectInputStream ois = new ObjectInputStream(fis);
				while ( fis.available() > 0 ) {
					oos.writeObject((Examen) ois.readObject());
				}
				
				fos.close();
				fos.flush();
				fis.close();
				
				fos = new FileOutputStream(fichier_examens);
				fis = new FileInputStream(fichier_examens_temp);
				oos = new ObjectOutputStream(fos);
				ois = new ObjectInputStream(fis);
				while ( fis.available() > 1 ) {
					oos.writeObject((Examen) ois.readObject());
				}
				
				fos.close();
				fos.flush();
				fis.close();
			}
			catch (IOException e) {
			}
		}	
	}
	
	//Constructor
	public Examen(String matiere, String login_professeur, String titre, String identifiant, boolean mode_sequentiel, int duree_totale,
			int nombre_questions, Question[] questions,
			String[] logins_Eleves,String date, String heure) throws ClassNotFoundException {
		
		this.matiere = matiere;
		this.login_professeur = login_professeur;
		this.date = date;
		this.heure = heure;
		
		Professeur p = Professeur.chercher(login_professeur);
		
		
		if (p.ajouter_matiere(matiere) == 1) {
			System.out.println("matiere ajoute avec succes");
		}
		
		this.titre = titre;
		this.identifiant = identifiant;
		this.mode_sequentiel = mode_sequentiel;
		this.duree_totale = duree_totale;
		this.nombre_questions = nombre_questions;
		this.questions = questions;
		this.logins_Eleves = logins_Eleves;
		add_exam(this);
	}
	
	//methods
	public static void vider(String chemin_fichier) {
		//écraser le contenu ancien
		try {
			FileOutputStream fos = new FileOutputStream(chemin_fichier);
			fos.flush();
			fos.close();
		} catch( IOException e) {
			System.out.println("ne peut pas etre vidé");
		}
	}
	
	@SuppressWarnings("resource")
	public static boolean vide(String chemin_fichier) {
		
		try {
		FileInputStream fis = new FileInputStream(chemin_fichier);
		if (fis.available() == 0) return true;
		if (fis.available() > 0) return false;
		fis.close();
		} 
		catch( IOException e) {
			e.printStackTrace();
			vider(chemin_fichier);
			return true;
		}
		vider(chemin_fichier);
		return true;
	}
	
	public static void supprimer(String identifiant) throws ClassNotFoundException {
		
		if (vide(fichier_examens)) {
			System.out.println("Le fichier des examens est vide");
		}
		else {
			
			try {
				FileInputStream fis = new FileInputStream(fichier_examens);
				FileOutputStream fos = new FileOutputStream(fichier_examens_temp);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Personne p;
				while (ois.available() > 0) {
					p = (Personne) ois.readObject();
					if ( !p.login.equals(identifiant) ) oos.writeObject(p);
				}
				
				fis.close();
				fos.flush();
				fos.close();
				
				fis = new FileInputStream(fichier_examens_temp);
				fos = new FileOutputStream(fichier_examens);
				while (ois.available() > 0) {
					p = (Personne) ois.readObject();
					oos.writeObject(p);
				}
				
				fis.close();
				fos.flush();
				fos.close();
				
			}
			
			catch (IOException e) {
				System.out.println("Erreur de suppression de l'examen");
			}
			
		}
		
	}
		
	public float noteTotale() {
		for (Question qst:this.questions) {
			noteTotale += qst.note_question;
		}
		return noteTotale;
	}
	
}