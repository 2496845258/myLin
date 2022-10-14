package indi.crisp.mylin.pojo;

import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;

public class Log {

	protected Timestamp ltime;
	protected String ldname;
	protected String lrole;
	protected Integer lid;
	protected String linfo;
	protected String lename;
	protected String ldesc;
	protected String ltype;

	public Log() {
	}

	public Log(Timestamp ltime, String ldname, String lrole, Integer lid, String linfo, String lename, String ldesc, String ltype) {
		 this.ltime = ltime;
		 this.ldname = ldname;
		 this.lrole = lrole;
		 this.lid = lid;
		 this.linfo = linfo;
		 this.lename = lename;
		 this.ldesc = ldesc;
		 this.ltype = ltype;
	}

	public Timestamp getLtime() {
		return this.ltime;
	}

	public void setLtime(Timestamp ltime) {
		 this.ltime = ltime;
	}

	public String getLdname() {
		return this.ldname;
	}

	public void setLdname(String ldname) {
		 this.ldname = ldname;
	}

	public String getLrole() {
		return this.lrole;
	}

	public void setLrole(String lrole) {
		 this.lrole = lrole;
	}

	public Integer getLid() {
		return this.lid;
	}

	public void setLid(Integer lid) {
		 this.lid = lid;
	}

	public String getLinfo() {
		return this.linfo;
	}

	public void setLinfo(String linfo) {
		 this.linfo = linfo;
	}

	public String getLename() {
		return this.lename;
	}

	public void setLename(String lename) {
		 this.lename = lename;
	}

	public String getLdesc() {
		return this.ldesc;
	}

	public void setLdesc(String ldesc) {
		 this.ldesc = ldesc;
	}

	public String getLtype() {
		return this.ltype;
	}

	public void setLtype(String ltype) {
		 this.ltype = ltype;
	}

}
