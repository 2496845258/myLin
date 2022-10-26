package indi.crisp.mylin.pojo.expand;

import indi.crisp.mylin.pojo.Notify;
import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;

public class NotifyVO extends Notify {

	private String fname;
	private String tname;

	private String fteptname;
	private String tteptname;

	public NotifyVO() {
	}

	public NotifyVO(String ninfo, Integer nfrom, Integer nid, Integer nto, Integer nstatus, String ntype, Timestamp ntime) {
		 this.ninfo = ninfo;
		 this.nfrom = nfrom;
		 this.nid = nid;
		 this.nto = nto;
		 this.nstatus = nstatus;
		 this.ntype = ntype;
		 this.ntime = ntime;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTteptname() {
		return tteptname;
	}

	public void setTteptname(String tteptname) {
		this.tteptname = tteptname;
	}

	public String getFteptname() {
		return fteptname;
	}

	public void setFteptname(String fteptname) {
		this.fteptname = fteptname;
	}
}
