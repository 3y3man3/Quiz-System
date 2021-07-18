package ProjectQCM;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class Professeur extends Personne implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static String chemin_fichier = "C:\\Users\\User\\eclipse-workspace\\QuizSystem\\Professeurs.ser";
	final static String chemin_fichier_temp = "C:\\Users\\User\\eclipse-workspace\\QuizSystem\\Professeurs_temp.ser";
	HashSet<String> matieres = new HashSet<String>();
		
	//Les Méthodes
	
	//Toutes les méthodes retournent 0 au cas d'erreur
	
	public Professeur(String nom, String prenom, String login, String mot_de_passe) throws ClassNotFoundException{
		//Constructeur
		super(nom, prenom, login, mot_de_passe, chemin_fichier, chemin_fichier_temp);
	}
	
	public static boolean login_existe(String login) throws ClassNotFoundException {
		// TODO retourne true si le login est déjà utilisé
		// false sinon ou au cas d'erreur
		return Personne.login_existe(login, chemin_fichier);
	}
	
	
	public int ajouter() throws ClassNotFoundException {
		// TODO Ajoute cet objet au fichier des professeurs
		return ajouter(chemin_fichier, chemin_fichier_temp); 
	}
	
	public static int vider() {
		// TODO Vide le fichier des professeurs
		return vider(chemin_fichier);
	}
	
	public static int vide() {
		// TODO retourne 1 si le fichier est vide et -1 sinon
		return vide(chemin_fichier);
	}
	
	public static ArrayList<Personne> Array_Professeurs() throws ClassNotFoundException, NullPointerException {
		// TODO Retourne l'Array contenant tous les professeurs
		return Array_Personnes(chemin_fichier);
	}
	
	public static Professeur chercher(String login) throws ClassNotFoundException {
		// TODO Retorne l'objet Professeur ayant ce login
		return (Professeur) chercher(login, chemin_fichier);
	}
	
	public static int supprimer(String login) throws ClassNotFoundException {
		// TODO Suppression du professeur ayant ce login
		return supprimer(login, chemin_fichier, chemin_fichier_temp);
	}
	
	public int supprimer() throws ClassNotFoundException {
		// TODO suppression de ce professeur dans le fichier
		return this.supprimer(chemin_fichier, chemin_fichier_temp);
	}
	
	public void modifier_nom(String nouveau_nom) throws ClassNotFoundException {
		// TODO modifier le nom
		this.modifier_nom(nouveau_nom, chemin_fichier, chemin_fichier_temp);
	}
	
	public void modifier_prenom(String nouveau_nom) throws ClassNotFoundException {
		// TODO modifier le prenom
		this.modifier_prenom(nouveau_nom, chemin_fichier, chemin_fichier_temp);
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
	
	public int ajouter_matiere(String nom_matiere)
			throws ClassNotFoundException {
		// TODO Ajouter une nouvelle matiere au professeur ayant ce login
		// Retourne 1 si la modification effectuée, et 0 sinon
		
		if (vide(chemin_fichier) == 1) {
			return 0;
		}
		else {
			
			try {
				FileInputStream fis = new FileInputStream(chemin_fichier);
				FileOutputStream fos = new FileOutputStream(chemin_fichier_temp);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Professeur prof;
				while (fis.available() > 0) {
					prof = (Professeur) ois.readObject();
					if (prof.login.equals(this.login) ) {
						HashSet<String> matieres_temp = new HashSet<String>();
						matieres_temp.add(nom_matiere);
						prof.matieres = matieres_temp;
						oos.writeObject(prof);
					}
					
					else oos.writeObject(prof);
				}
				
				fis.close();
				fos.flush();
				fos.close();
				
				fis = new FileInputStream(chemin_fichier_temp);
				fos = new FileOutputStream(chemin_fichier);
				oos = new ObjectOutputStream(fos);
				ois = new ObjectInputStream(fis);
				while (fis.available() > 0) {
					prof = (Professeur) ois.readObject();
					oos.writeObject(prof);
				}
				
				fis.close();
				fos.flush();
				fos.close();
				return 1;
			}
			
			catch (IOException e) {
				e.printStackTrace();
				return 0;
			}
			
		}
		
	}

}