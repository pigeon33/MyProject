package model.entity;

public class Answer {
	private int num;
	private String ans;
	private String collectAns;
	private String judge;
	private long time;

	public String getAns() {
		return ans;
	}
	public void setAns(String string) {
		this.ans = string;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getJudge() {
		return judge;
	}
	public void setJudge(String judge) {
		this.judge = judge;
	}
	public String getCollectAns() {
		return collectAns;
	}
	public void setCollectAns(String collectAns) {
		this.collectAns = collectAns;
	}
}
