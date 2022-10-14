package indi.crisp.mylin.pojo;

import java.lang.Integer;
import java.lang.String;

public class Perm {

	protected String proute;
	protected String pnameZH;
	protected String picon;
	protected String pname;
	protected String pdesc;
	protected Integer pid;

	public Perm() {
	}

	public Perm(String proute, String pnameZH, String picon, String pname, String pdesc, Integer pid) {
		 this.proute = proute;
		 this.pnameZH = pnameZH;
		 this.picon = picon;
		 this.pname = pname;
		 this.pdesc = pdesc;
		 this.pid = pid;
	}

	public String getProute() {
		return this.proute;
	}

	public void setProute(String proute) {
		 this.proute = proute;
	}

	public String getPnameZH() {
		return this.pnameZH;
	}

	public void setPnameZH(String pnameZH) {
		 this.pnameZH = pnameZH;
	}

	public String getPicon() {
		return this.picon;
	}

	public void setPicon(String picon) {
		 this.picon = picon;
	}

	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		 this.pname = pname;
	}

	public String getPdesc() {
		return this.pdesc;
	}

	public void setPdesc(String pdesc) {
		 this.pdesc = pdesc;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		 this.pid = pid;
	}

	@Override
	public String toString() {
		return "Perm{" +
				"proute='" + proute + '\'' +
				", pnameZH='" + pnameZH + '\'' +
				", picon='" + picon + '\'' +
				", pname='" + pname + '\'' +
				", pdesc='" + pdesc + '\'' +
				", pid=" + pid +
				'}';
	}
}
