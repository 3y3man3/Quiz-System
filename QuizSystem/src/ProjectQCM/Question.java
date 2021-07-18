package ProjectQCM;

import java.io.Serializable;

public class Question implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String enonce; //Enoncé de la question
	public String[] choix; //Liste des choix
	public int reponse; //Numero de la reponse correcte
	public float note_question; //Note de question
	public int duree_question;
	
	public Question(String enonce, String[] choix, int reponse, float noteQuestion, int duree_question) {
		this.enonce = enonce;
		this.choix = choix;
		this.reponse = reponse;
		this.note_question = noteQuestion;
		this.duree_question = duree_question;
	}
	
	public Question(String enonce, String[] choix, int reponse, float noteQuestion) {
		this.enonce = enonce;
		this.choix = choix;
		this.reponse = reponse;
		this.note_question = noteQuestion;
	}
}
