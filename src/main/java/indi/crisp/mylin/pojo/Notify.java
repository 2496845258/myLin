package indi.crisp.mylin.pojo;

import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;

public class Notify {

	protected String ninfo;
	protected String nfrom;
	protected Integer nid;
	protected String nto;
	protected Integer nstatus;
	protected String ntype;
	protected Timestamp ntime;

	public Notify() {
	}

	public Notify(String ninfo, String nfrom, Integer nid, String nto, Integer nstatus, String ntype, Timestamp ntime) {
		 this.ninfo = ninfo;
		 this.nfrom = nfrom;
		 this.nid = nid;
		 this.nto = nto;
		 this.nstatus = nstatus;
		 this.ntype = ntype;
		 this.ntime = ntime;
	}

	public String getNinfo() {
		return this.ninfo;
	}

	public void setNinfo(String ninfo) {
		 this.ninfo = ninfo;
	}

	public String getNfrom() {
		return this.nfrom;
	}

	public void setNfrom(String nfrom) {
		 this.nfrom = nfrom;
	}

	public Integer getNid() {
		return this.nid;
	}

	public void setNid(Integer nid) {
		 this.nid = nid;
	}

	public String getNto() {
		return this.nto;
	}

	public void setNto(String nto) {
		 this.nto = nto;
	}

	public Integer getNstatus() {
		return this.nstatus;
	}

	public void setNstatus(Integer nstatus) {
		 this.nstatus = nstatus;
	}

	public String getNtype() {
		return this.ntype;
	}

	public void setNtype(String ntype) {
		 this.ntype = ntype;
	}

	public Timestamp getNtime() {
		return this.ntime;
	}

	public void setNtime(Timestamp ntime) {
		 this.ntime = ntime;
	}

}
