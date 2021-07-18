package ProjectQCM;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Personne implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String nom;
	public String prenom;
	public String login;
	public String mot_de_passe;
	public boolean no_problem = true;
	
	public Personne(String nom, String prenom, String login, String mot_de_passe,
			String chemin_fichier, String chemin_fichier_temp) throws ClassNotFoundException {
		this.nom = nom;
		this.prenom = prenom;
		
		
		if (vide(chemin_fichier) == 1) {
			vider(chemin_fichier);
			this.login = login;
			this.mot_de_passe = mot_de_passe;
			try {
				FileOutputStream fos = new FileOutputStream(chemin_fichier);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(this);
				oos.close();
			}
			catch (IOException e) {
				System.out.println("Erreur de création du fichier");
			}
		}
		
		else {
			if (login_existe(login, chemin_fichier)) {
				this.no_problem = false;
			}
			else {
				this.login = login;
				
				this.mot_de_passe = mot_de_passe;
				
				if (ajouter(chemin_fichier, chemin_fichier_temp) == 1) {
					System.out.println("SignUp Successful");
				};
			}
			
		}
		
	}
	
	@SuppressWarnings("resource")
	static boolean login_existe(String login, String chemin_fichier) 
			throws ClassNotFoundException {
		// TODO retourne true si le login est déjà utilisé
		// false sinon ou au cas d'erreur
		try {
			FileInputStream fis = new FileInputStream(chemin_fichier);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Personne p;
			while (fis.available() > 0) {
				p = (Personne) ois.readObject();
				if (!(p.login == null)) {
					if ( p.login.equals(login) ) {
						fis.close();
						return true;
					}
				}
			}
			return false;
			
		}
		catch(IOException e) {
			return false;
		}
	}
	
	
	public static Personne Login(String login, String password, String chemin_fichier) {

		//Access to the personal profile using the both login and password

		try {
			FileInputStream f = new FileInputStream(chemin_fichier);
			ObjectInputStream ois = new ObjectInputStream(f);

			Personne personne;
			while (f.available() > 0) {
				personne = (Personne) ois.readObject();
				if (!(personne.login == null)) {
					if (personne.login.equals(login) && personne.mot_de_passe.equals(password)) {
						System.out.println("Vous êtes identifié");
						ois.close();
						return personne;
					}
				}
			}
			ois.close();
			return null;
		} 
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static int vider(String chemin_fichier) {
		// TODO Créer de nouveau le fichier ou écraser son contenu ancien
		// Retourne 0 au cas d'erreur, et 1 sinon
		try {
			FileOutputStream fos = new FileOutputStream(chemin_fichier);
			fos.flush();
			fos.close();
			return 1;
		} catch( IOException e) {
			return 0;
		}
	}
	
	@SuppressWarnings("resource")
	public static int vide(String chemin_fichier) {
		// TODO retourne 1 si le fichier est vide,-1 sinon, et 0 ou au cas d'erreur
		try {
		FileInputStream fis = new FileInputStream(chemin_fichier);
		if (fis.available() == 0) return 1;
		if (fis.available() > 0) return -1;
		fis.close();
		} 
		catch( IOException e) {
			e.printStackTrace();
			return 0;
		}
		vider(chemin_fichier);
		return 1;
	}
	
	public int ajouter(String chemin_fichier, String chemin_fichier_temp) 
			throws ClassNotFoundException {
		
		// TODO insertion de la personne p dans le fichier
		// Retourne 1 si l'ajout est effectué, et 0 sinon
		
		if (vide(chemin_fichier) == 1 || vide(chemin_fichier) == 0) {
			//Verifier tout d'abord si le fichier était vide
			vider(chemin_fichier);
			try {
				FileOutputStream fos = new FileOutputStream(chemin_fichier);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(this);
				fos.flush();
				fos.close();
				return 1;
			}
			catch (IOException e) {
				return 0;
			}
		}
		
		else {
			try {
				FileOutputStream fos = new FileOutputStream(chemin_fichier_temp);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(this);
				FileInputStream fis = new FileInputStream(chemin_fichier);
				ObjectInputStream ois = new ObjectInputStream(fis);
				while ( fis.available() > 0 ) {
					oos.writeObject((Personne) ois.readObject());
				}
				
				fos.close();
				fos.flush();
				fis.close();
				
				fos = new FileOutputStream(chemin_fichier);
				fis = new FileInputStream(chemin_fichier_temp);
				oos = new ObjectOutputStream(fos);
				ois = new ObjectInputStream(fis);
				while ( fis.available() > 0 ) {
					oos.writeObject((Personne) ois.readObject());
				}
				
				fos.close();
				fos.flush();
				fis.close();
				return 1;
			}
			catch (IOException e) {
				return 0;
			}
		}
	}
	
	public static void toString(String chemin_fichier) 
			throws ClassNotFoundException, NullPointerException {
		
		// TODO Lister dans la Console les informations des personnes
		try {
			FileInputStream fis = new FileInputStream(chemin_fichier);
			ObjectInputStream ois = new ObjectInputStream(fis);
			if (vide(chemin_fichier) == 1 || fis.available() == 0) {
				System.out.println("Le fichier est vide");
			}
			else {
				Personne p;
				while (fis.available() > 0) {
					p = (Personne) ois.readObject();
					System.out.println(p.toString());
				}
			}
			fis.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichier vide ou inexistant");
		}
		
	}

	public static ArrayList<Personne> Array_Personnes(String chemin_fichier) 
			throws ClassNotFoundException, NullPointerException {
		
		// TODO Retourne l'Array des objets Personne enregistrés dans le fichier
		ArrayList<Personne> lst = new ArrayList<Personne>();
		
		try {
			FileInputStream fis = new FileInputStream(chemin_fichier);
			ObjectInputStream ois = new ObjectInputStream(fis);
			if (vide(chemin_fichier) == 1 || fis.available() == 0) {
				System.out.println("Le fichier est vide");
			}
			else {
				Personne p;
				while (fis.available() > 0) {
					p = (Personne) ois.readObject();
					lst.add(p);
				}
			}
			fis.close();
			return lst;
			
		} catch (IOException e) {
			return null;
		}
		
	}
	
	public static boolean existe(String login, String chemin_fichier) 
			throws ClassNotFoundException, NullPointerException {
			
		// TODO Retourne true si une personne ayant ce login existe dans le fichier
		// Retourne false sinon ou au cas d'erreur
		try {
			FileInputStream fis = new FileInputStream(chemin_fichier);
			ObjectInputStream ois = new ObjectInputStream(fis);
			if (vide(chemin_fichier) == 1 || fis.available() == 0) {
				System.out.println("Le fichier est vide");
				ois.close();
				return false;
			}
			else {
				Personne p;
				while (fis.available() > 0) {
					p = (Personne) ois.readObject();
					if (p.login.equals(login)) {
						ois.close();
						return true;
					}
				}
			}
			fis.close();
			ois.close();
			return false;
			
		} catch (IOException e) {
			return false;
		}
		
	}
	
	public static Personne chercher(String login, String chemin_fichier) 
			throws ClassNotFoundException {
		
		// TODO Retourne l'objet Personne ayant le login donné
		// Retourne null s'il n'existe pas ou au cas d'erreur
		try {
			FileInputStream fis = new FileInputStream(chemin_fichier);
			ObjectInputStream ois = new ObjectInputStream(fis);
			if ( !(existe(login, chemin_fichier) )) {
				System.out.println("Ce login n'existe pas");
				ois.close();
				return null;
			}
			else {
				Personne p;
				while (fis.available() > 0) {
					p = (Personne) ois.readObject();
					if (p.login.equals(login)) {
						ois.close();
						return p;
					}
				}
			}
			fis.close();
			ois.close();
			return null;
			
		} catch (IOException e) {
			return null;
		}
		
	}
	
	
	public int supprimer(String chemin_fichier, String chemin_fichier_temp) 
			throws ClassNotFoundException {
		
		// TODO Suppression de l'objet au fichier
		// Retourne 0 si la suppression n'est pas effectuée
		
		if (vide(chemin_fichier) == 1) {
			return 0;
		}
		else {
			
			try {
				FileInputStream fis = new FileInputStream(chemin_fichier);
				FileOutputStream fos = new FileOutputStream(chemin_fichier_temp);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Personne p;
				while (ois.available() > 0) {
					p = (Personne) ois.readObject();
					if ( !p.equals(this) ) oos.writeObject(p);
				}
				
				fis.close();
				fos.flush();
				fos.close();
				
				fis = new FileInputStream(chemin_fichier_temp);
				fos = new FileOutputStream(chemin_fichier);
				while (ois.available() > 0) {
					p = (Personne) ois.readObject();
					oos.writeObject(p);
				}
				
				fis.close();
				fos.flush();
				fos.close();
				return 1;
				
			}
			
			catch (IOException e) {
				return 0;
			}
			
		}
		
	}
	
	public static int supprimer(String login, String chemin_fichier,
			String chemin_fichier_temp) throws ClassNotFoundException {
		
		if (vide(chemin_fichier) == 1) {
			return 0;
		}
		
		else {
			Personne p = chercher(login, chemin_fichier);
			return p.supprimer(chemin_fichier, chemin_fichier_temp);
		}
		
	}
	
	public int modifier_nom(String nom, String chemin_fichier,
			String chemin_fichier_temp) throws ClassNotFoundException {
		
		// TODO Modifier le nom de l'objet
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
				Personne p;
				while (ois.available() > 0) {
					p = (Personne) ois.readObject();
					if ( !p.equals(this) ) {
						p.nom = nom;
						oos.writeObject(p);
					}
					
					else oos.writeObject(p);
				}
				
				fis.close();
				fos.flush();
				fos.close();
				
				fis = new FileInputStream(chemin_fichier_temp);
				fos = new FileOutputStream(chemin_fichier);
				while (ois.available() > 0) {
					p = (Personne) ois.readObject();
					oos.writeObject(p);
				}
				
				fis.close();
				fos.flush();
				fos.close();
				return 1;
			}
			
			catch (IOException e) {
				return 0;
			}
			
		}
		
	}
	
	public int modifier_prenom(String nom, String chemin_fichier,
			String chemin_fichier_temp) throws ClassNotFoundException {
		
		// TODO Modifier le nom de l'objet
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
				Personne p;
				while (ois.available() > 0) {
					p = (Personne) ois.readObject();
					if ( !p.equals(this) ) {
						p.prenom = prenom;
						oos.writeObject(p);
					}
					
					else oos.writeObject(p);
				}
				
				fis.close();
				fos.flush();
				fos.close();
				
				fis = new FileInputStream(chemin_fichier_temp);
				fos = new FileOutputStream(chemin_fichier);
				while (ois.available() > 0) {
					p = (Personne) ois.readObject();
					oos.writeObject(p);
				}
				
				fis.close();
				fos.flush();
				fos.close();
				return 1;
				
			}
			
			catch (IOException e) {
				return 0;
			}
			
		}
		
	}
	
	public int changer_mot_de_passe(String mot_de_passe, String chemin_fichier,
			String chemin_fichier_temp) throws ClassNotFoundException {
		
		// TODO Change le mot de passe
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
				Personne p;
				while (ois.available() > 0) {
					p = (Personne) ois.readObject();
					if ( !p.equals(this) ) {
						p.mot_de_passe = mot_de_passe;
						oos.writeObject(p);
					}
					
					else oos.writeObject(p);
				}
				
				fis.close();
				fos.flush();
				fos.close();
				
				fis = new FileInputStream(chemin_fichier_temp);
				fos = new FileOutputStream(chemin_fichier);
				while (ois.available() > 0) {
					p = (Personne) ois.readObject();
					oos.writeObject(p);
				}
				
				fis.close();
				fos.flush();
				fos.close();
				return 1;
				
			}
			
			catch (IOException e) {
				return 0;
			}
			
		}
		
	}
	
	public int changer_login(String login, String chemin_fichier,
			String chemin_fichier_temp) throws ClassNotFoundException {
		
		// TODO Change le mot de passe
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
				Personne p;
				while (ois.available() > 0) {
					p = (Personne) ois.readObject();
					if ( !p.equals(this) ) {
						p.login = login;
						oos.writeObject(p);
					}
					
					else oos.writeObject(p);
				}
				
				fis.close();
				fos.flush();
				fos.close();
				
				fis = new FileInputStream(chemin_fichier_temp);
				fos = new FileOutputStream(chemin_fichier);
				while (ois.available() > 0) {
					p = (Personne) ois.readObject();
					oos.writeObject(p);
				}
				
				fis.close();
				fos.flush();
				fos.close();
				return 1;
				
			}
			
			catch (IOException e) {
				return 0;
			}
			
		}
		
	}

	public String toString() {
		return this.nom+"\t"+this.prenom;
	}
	
}