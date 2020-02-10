package model.entity;

import java.sql.Timestamp;

public class Examinees {

	private int entryNumber;//登録番号
	private int rankingNumber;//ランキング
	private String Name;
	private String Score;
	private String pass;
	private Timestamp timestamp;
	

	public Examinees() {
		super();
	}

	public Examinees(String name, String pass) {
		super();
		Name = name;
		this.pass = pass;
	}
	public int getEntryNumber() {return entryNumber;}
	public void setEntryNumber(int entryNumber) {this.entryNumber = entryNumber;}

	public String getName() {return Name;}
	public void setName(String name) {	Name = name;}

	public String getScore() {return Score;}
	public void setScore(String score) {Score = score;}

	public int getRankingNumber() {return rankingNumber;}
	public void setRankingNumber(int rankingNumber) {this.rankingNumber = rankingNumber;}

	public Timestamp getTimestamp() {return timestamp;}
	public void setTimestamp(Timestamp timestamp) {this.timestamp = timestamp;}

	public String getPass() {return pass;}
	public void setPass(String pass) {	this.pass = pass;}
}