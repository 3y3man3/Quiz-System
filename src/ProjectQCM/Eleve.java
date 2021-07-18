package ProjectQCM;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;


public class Eleve extends Personne implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Le chemin du fichier contenant les objets Eleves
	final static String chemin_fichier = "C:\\Users\\User\\eclipse-workspace\\QuizSystem\\Eleves.ser";
	
	//Un fichier temporaire pour l'ajout et les modifications
	final static String chemin_fichier_temp = "C:\\Users\\User\\eclipse-workspace\\QuizSystem\\Eleves_temp.ser";
	
	final static String chemin_fichier_examens = "C:\\Users\\User\\eclipse-workspace\\QuizSystem\\Examens.ser";

	//Un dictionnaire contenant les examens et les notes obtenus en chacun
	public Hashtable<String, Float> notes = new Hashtable<String, Float>();
	
	
	//Les Méthodes
	
	//Toutes les méthodes retournent 0 au cas d'erreur
	
	public Eleve(String nom, String prenom, String login, String mot_de_passe) throws ClassNotFoundException {
		//Constructeur
		super(nom, prenom, login, mot_de_passe, chemin_fichier, chemin_fichier_temp);		
	}
	
	public ArrayList<Examen> listeExamens() throws ClassNotFoundException {
		//Retourne un ArrayList contenant les examens disponibles pour l'eleve
		ArrayList<Examen> examens = new ArrayList<Examen>();
		try {
			FileInputStream fis = new FileInputStream(Eleve.chemin_fichier_examens);
			ObjectInputStream ois = new ObjectInputStream(fis);
			if (vide(Eleve.chemin_fichier_examens) == 1 || fis.available() == 0) {
				System.out.println("Le fichier d'Examens est vide");
			}
			else {
				Examen examen;
				while (fis.available() > 0) {
					examen = (Examen) ois.readObject();
					if (Arrays.asList(examen.logins_Eleves).contains(this.login))
						examens.add(examen);
				}
			}
			fis.close();
			return examens;
			
		} catch (IOException e) {
			System.out.println("Erreur produite dans la création du liste des exams !");
			return null;
		}
	}
	
	
	public static boolean login_existe(String login) throws ClassNotFoundException {
		// TODO retourne true si le login est déjà utilisé
		// false sinon ou au cas d'erreur
		return Personne.login_existe(login, chemin_fichier);
	}
	
	
	public int ajouter() throws ClassNotFoundException {
		return ajouter(chemin_fichier, chemin_fichier_temp); 
	}
	
	public static int vider() {
		// TODO Vide le fichier des objets Eleves complètement
		return vider(chemin_fichier);
	}
	
	public static int vide() {
		// TODO retourne 1 si le fichier est vide et -1 sinon
		return vide(chemin_fichier);
	}
	
	public static ArrayList<Personne> Array_Eleves() throws ClassNotFoundException, NullPointerException {
		// TODO Retourne l'Array contenant tous les eleves
		return Array_Personnes(chemin_fichier);
	}
	
	public static Eleve chercher(String login) throws ClassNotFoundException {
		// TODO Retorne l'objet Eleve ayant ce login
		return (Eleve) chercher(login, chemin_fichier);
	}
	
	public static int supprimer(String login) throws ClassNotFoundException {
		// TODO Suppression de l'eleve ayant ce login
		return supprimer(login, chemin_fichier, chemin_fichier_temp);
	}
	
	public int supprimer() throws ClassNotFoundException {
		// TODO suppression de cet Eleve dans le fichier
		return this.supprimer(chemin_fichier, chemin_fichier_temp);
	}
	
	public void modifier_nom(String nouveau_nom) throws ClassNotFoundException {
		// TODO modifier le nom
		modifier_nom(nouveau_nom, chemin_fichier, chemin_fichier_temp);
	}
	
	public void modifier_prenom(String nouveau_nom) throws ClassNotFoundException {
		// TODO modifier le prenom
		modifier_prenom(nouveau_nom, chemin_fichier, chemin_fichier_temp);
	}
	
	public int changer_mot_de_passe(String mot_de_passe) throws ClassNotFoundException {
		// TODO change le mot de passe
		// Retourne 1 si le changement est effectué et 0 au cas d'erreur
		return this.changer_mot_de_passe(mot_de_passe, chemin_fichier, chemin_fichier_temp);
	}
	
	public int changer_login(String login) throws ClassNotFoundException {
		// TODO change le login
		// Retourne 1 si le changement est effectué et 0 au cas d'erreur
		return this.changer_login(login, chemin_fichier, chemin_fichier_temp);
	}
	
	public void noter(String identifiant_exam, float note) {
		// TODO noter l'élève
		Hashtable<String, Float> notes_temp = new Hashtable<String, Float>();
		if (!(this.notes == null))
			notes_temp = this.notes;
		notes_temp.put(identifiant_exam, note);
		this.notes = notes_temp;
		//Attention : this.notes.put() ne fonctionne pas dans le cas ou le hashtable notes est null,
		//d'ou la necessite de creer une copie;
	}
	
	public Eleve noter(String login, String identifiant_exam, float note)
			throws ClassNotFoundException {
		// TODO Modifier la note de l'élève ayant ce login
		// Retourne 1 si la modification effectuée, et 0 sinon
		Eleve returnedElv = null;
		if (vide(chemin_fichier) == 1) {
			System.out.println("Aucun eleve n'est enregistré, le fichier est vide !");
		}
		else {
			
			try {
				FileInputStream fis = new FileInputStream(chemin_fichier);
				FileOutputStream fos = new FileOutputStream(chemin_fichier_temp);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Eleve elv;
				while (fis.available() > 0) {
					elv = (Eleve) ois.readObject();
					if (elv.login.equals(login) ) {
						elv.noter(identifiant_exam, note);
						oos.writeObject(elv);
						returnedElv = elv;
					}
					
					else oos.writeObject(elv);
				}
				
				fis.close();
				fos.flush();
				fos.close();
				
				fis = new FileInputStream(chemin_fichier_temp);
				fos = new FileOutputStream(chemin_fichier);
				oos = new ObjectOutputStream(fos);
				ois = new ObjectInputStream(fis);
				while (fis.available() > 0) {
					elv = (Eleve) ois.readObject();
					oos.writeObject(elv);
				}
				
				fis.close();
				fos.flush();
				fos.close();
				
			}
			
			catch (IOException e) {
				System.out.println("Exception survenue lors de noter methode !");
			}
			
		}
		return returnedElv;
				
	}

}