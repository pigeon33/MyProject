package model.entity;

public class Question {
	int questionNumber;
	String question;
	String choiceA,choiceB,choiceC,choiceD;
	String answer;
	String commentary;
	public int getQuestionNumber() {return questionNumber;}
	public void setQuestionNumber(int questionNumber) {this.questionNumber = questionNumber;}


	public String getQuestion() {return question;}
	public void setQuestion(String question) {	this.question = question;}

	public String getChoiceA() {return choiceA;}
	public void setChoiceA(String choiceA) {this.choiceA = choiceA;}

	public String getChoiceB() {return choiceB;}
	public void setChoiceB(String choiceB) {this.choiceB = choiceB;}

	public String getChoiceC() {return choiceC;}
	public void setChoiceC(String choiceC) {this.choiceC = choiceC;}

	public String getChoiceD() {return choiceD;}
	public void setChoiceD(String choiceD) {this.choiceD = choiceD;}

	public String getAnswer() {return answer;}
	public void setAnswer(String answer) {	this.answer = answer;}

	public String getCommentary() {return commentary;}
	public void setCommentary(String commentary) {this.commentary = commentary;}

}
