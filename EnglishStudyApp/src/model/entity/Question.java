package model.entity;

public class Question {

	int orgQuestionNumber;//DBで管理されている問題ID
	int questionNumber;//アプリで表示される問題番号
	String question;//問題文
	String choices[] = new String[4];
	String Answer;//正解
	String commentary;//回答解説
	String choosenAnswer;//選択された回答
	String judge;
	boolean shuffledAnswer[] = new boolean[4];//シャッフル後の各選択肢の回答
	int conserns=0;

	public int getQuestionNumber() {return questionNumber;}
	public void setQuestionNumber(int questionNumber) {this.questionNumber = questionNumber;}

	public String getQuestion() {return question;}
	public void setQuestion(String question) {	this.question = question;}

	public String getAnswer() {return Answer;}
	public void setAnswer(String answer) {	this.Answer = answer;}

	public String getCommentary() {return commentary;}
	public void setCommentary(String commentary) {this.commentary = commentary;}

	public String getChoosenAnswer() {return choosenAnswer;}
	public void setChoosenAnswer(String choosenAnswer) {this.choosenAnswer = choosenAnswer;}

	public String getJudge() {return judge;}
	public void setJudge(String judge) {this.judge = judge;}

	public String[] getChoices() {	return choices;}
	public void setChoices(String[] choices) {	this.choices = choices;}

	public boolean[] getShuffledAnswer() {return shuffledAnswer;}
	public void setShuffledAnswer(boolean[] shuffledAnswer) {this.shuffledAnswer = shuffledAnswer;}
	public int getOrgQuestionNumber() {
		return orgQuestionNumber;
	}
	public void setOrgQuestionNumber(int orgQuestionNumber) {
		this.orgQuestionNumber = orgQuestionNumber;
	}
	public int getConserns() {
		return conserns;
	}
	public void setConserns(int conserns) {
		this.conserns = conserns;
	}
}
