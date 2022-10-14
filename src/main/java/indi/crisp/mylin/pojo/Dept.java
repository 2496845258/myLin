package indi.crisp.mylin.pojo;

import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;

public class Dept {

	protected Integer dno;
	protected Timestamp dcreate;
	protected String ddesc;
	protected Integer dstatus;
	protected String dname;
	protected String dhost;

	public Dept() {
	}

	public Dept(Integer dno, Timestamp dcreate, String ddesc, Integer dstatus, String dname, String dhost) {
		 this.dno = dno;
		 this.dcreate = dcreate;
		 this.ddesc = ddesc;
		 this.dstatus = dstatus;
		 this.dname = dname;
		 this.dhost = dhost;
	}

	public Integer getDno() {
		return this.dno;
	}

	public void setDno(Integer dno) {
		 this.dno = dno;
	}

	public Timestamp getDcreate() {
		return this.dcreate;
	}

	public void setDcreate(Timestamp dcreate) {
		 this.dcreate = dcreate;
	}

	public String getDdesc() {
		return this.ddesc;
	}

	public void setDdesc(String ddesc) {
		 this.ddesc = ddesc;
	}

	public Integer getDstatus() {
		return this.dstatus;
	}

	public void setDstatus(Integer dstatus) {
		 this.dstatus = dstatus;
	}

	public String getDname() {
		return this.dname;
	}

	public void setDname(String dname) {
		 this.dname = dname;
	}

	public String getDhost() {
		return this.dhost;
	}

	public void setDhost(String dhost) {
		 this.dhost = dhost;
	}

}
