package indi.crisp.mylin.pojo;

import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;

public class Notify {

	protected String ninfo;
	protected Integer nfrom;
	protected Integer nid;
	protected Integer nto;
	protected Integer nstatus;
	protected String ntype;
	protected Timestamp ntime;

	public Notify() {
	}

	public Notify(String ninfo, Integer nfrom, Integer nid, Integer nto, Integer nstatus, String ntype, Timestamp ntime) {
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


	public Integer getNid() {
		return this.nid;
	}

	public void setNid(Integer nid) {
		 this.nid = nid;
	}


	public void setNfrom(Integer nfrom) {
		this.nfrom = nfrom;
	}

	public Integer getNto() {
		return nto;
	}

	public void setNto(Integer nto) {
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
