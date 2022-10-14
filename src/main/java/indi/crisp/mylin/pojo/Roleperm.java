package indi.crisp.mylin.pojo;

import java.lang.Integer;

public class Roleperm {

	protected Integer rprid;
	protected Integer rppid;

	public Roleperm() {
	}

	public Roleperm(Integer rprid, Integer rppid) {
		 this.rprid = rprid;
		 this.rppid = rppid;
	}

	public Integer getRprid() {
		return this.rprid;
	}

	public void setRprid(Integer rprid) {
		 this.rprid = rprid;
	}

	public Integer getRppid() {
		return this.rppid;
	}

	public void setRppid(Integer rppid) {
		 this.rppid = rppid;
	}

}
