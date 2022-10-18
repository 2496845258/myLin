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

	protected Integer dhost;

	public Dept() {
	}

	public Dept(Integer dno, Timestamp dcreate, String ddesc, Integer dstatus, String dname, Integer dhost) {
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

	public Integer getDhost() {
		return dhost;
	}

	public void setDhost(Integer dhost) {
		this.dhost = dhost;
	}
}
