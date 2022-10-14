package indi.crisp.mylin.pojo;

import java.lang.Integer;
import java.lang.String;

public class Role {

	protected String rname;
	protected String rdesc;
	protected Integer rid;
	protected String rnameZH;

	public Role() {
	}

	public Role(String rname, String rdesc, Integer rid, String rnameZH) {
		 this.rname = rname;
		 this.rdesc = rdesc;
		 this.rid = rid;
		 this.rnameZH = rnameZH;
	}

	public String getRname() {
		return this.rname;
	}

	public void setRname(String rname) {
		 this.rname = rname;
	}

	public String getRdesc() {
		return this.rdesc;
	}

	public void setRdesc(String rdesc) {
		 this.rdesc = rdesc;
	}

	public Integer getRid() {
		return this.rid;
	}

	public void setRid(Integer rid) {
		 this.rid = rid;
	}

	public String getRnameZH() {
		return this.rnameZH;
	}

	public void setRnameZH(String rnameZH) {
		 this.rnameZH = rnameZH;
	}

	@Override
	public String toString() {
		return "Role{" +
				"rname='" + rname + '\'' +
				", rdesc='" + rdesc + '\'' +
				", rid=" + rid +
				", rnameZH='" + rnameZH + '\'' +
				'}';
	}
}
