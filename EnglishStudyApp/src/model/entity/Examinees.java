package model.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Examinees {

	private int id;//登録番号
	private int rankingNumber;//ランキング
	private String Name;
	private int Score;
	private String pass;
	private Timestamp timestamp;
	private String timestampStr;
	private String msg;
	private String tweet;
	private int last_insert_id;


	public Examinees() {
		super();
	}

	public Examinees(String name, String pass) {
		super();
		Name = name;
		this.pass = pass;
	}
	public int getId() {return this.id;}
	public void setId(int id) {this.id = id;}

	public String getName() {return Name;}
	public void setName(String name) {	Name = name;}

	public int getScore() {return Score;}
	public void setScore(int score) {Score = score;}

	public int getRankingNumber() {return rankingNumber;}
	public void setRankingNumber(int rankingNumber) {this.rankingNumber = rankingNumber;}

	public Timestamp getTimestamp() {return timestamp;}
	public void setTimestamp(Timestamp timestamp) {this.timestamp = timestamp;}

	public String getTimestampStr() {return timestampStr;}
	public void setTimestampStr(Timestamp timestamp) {
		this.timestampStr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(timestamp);
		}

	public String getPass() {return pass;}
	public void setPass(String pass) {	this.pass = pass;}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public int getLast_insert_id() {
		return last_insert_id;
	}

	public void setLast_insert_id(int last_insert_id) {
		this.last_insert_id = last_insert_id;
	}

}