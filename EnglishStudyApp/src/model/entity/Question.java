package model.entity;

public class Question {
	int questionNumber;
	String question;//問題文
	//String choiceA,choiceB,choiceC,choiceD;
	String choices[] = new String[4];
	String answer;//正解
	String commentary;//回答解説
	String choosenAnswer;//選択された回答
	String judge;
	public int getQuestionNumber() {return questionNumber;}
	public void setQuestionNumber(int questionNumber) {this.questionNumber = questionNumber;}


	public String getQuestion() {return question;}
	public void setQuestion(String question) {	this.question = question;}

	public String getAnswer() {return answer;}
	public void setAnswer(String answer) {	this.answer = answer;}

	public String getCommentary() {return commentary;}
	public void setCommentary(String commentary) {this.commentary = commentary;}
	public String getChoosenAnswer() {
		return choosenAnswer;
	}
	public void setChoosenAnswer(String choosenAnswer) {
		this.choosenAnswer = choosenAnswer;
	}
	public String getJudge() {
		return judge;
	}
	public void setJudge(String judge) {
		this.judge = judge;
	}
	public String getChoices(int i) {
		return choices[i];
	}
	public void setChoices(int i, String choices) {
		this.choices[i] = choices;
	}

}
