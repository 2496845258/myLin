package indi.crisp.mylin.pojo;

import indi.luzhao.summer.annotate.markclass.Serialize;

import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;


@Serialize
public class Leave {

	protected Integer vid;
	protected Integer vto;
	protected Timestamp vend;
	protected String vtype;
	protected Integer vstatus;
	protected Timestamp vtime;
	protected Timestamp vstart;
	protected String vcheck;
	protected Integer vfrom;
	protected String vreason;

	public Leave() {
	}

	public Leave(Integer vid, Integer vto, Timestamp vend, String vtype, Integer vstatus, Timestamp vtime, Timestamp vstart, String vcheck, Integer vfrom, String vreason) {
		 this.vid = vid;
		 this.vto = vto;
		 this.vend = vend;
		 this.vtype = vtype;
		 this.vstatus = vstatus;
		 this.vtime = vtime;
		 this.vstart = vstart;
		 this.vcheck = vcheck;
		 this.vfrom = vfrom;
		 this.vreason = vreason;
	}

	public Integer getVid() {
		return this.vid;
	}

	public void setVid(Integer vid) {
		 this.vid = vid;
	}

	public Integer getVto() {
		return vto;
	}

	public void setVto(Integer vto) {
		this.vto = vto;
	}

	public Timestamp getVend() {
		return this.vend;
	}

	public void setVend(Timestamp vend) {
		 this.vend = vend;
	}

	public String getVtype() {
		return this.vtype;
	}

	public void setVtype(String vtype) {
		 this.vtype = vtype;
	}

	public Integer getVstatus() {
		return this.vstatus;
	}

	public void setVstatus(Integer vstatus) {
		 this.vstatus = vstatus;
	}

	public Timestamp getVtime() {
		return this.vtime;
	}

	public void setVtime(Timestamp vtime) {
		 this.vtime = vtime;
	}

	public Timestamp getVstart() {
		return this.vstart;
	}

	public void setVstart(Timestamp vstart) {
		 this.vstart = vstart;
	}

	public String getVcheck() {
		return this.vcheck;
	}

	public void setVcheck(String vcheck) {
		 this.vcheck = vcheck;
	}

	public Integer getVfrom() {
		return vfrom;
	}

	public void setVfrom(Integer vfrom) {
		this.vfrom = vfrom;
	}

	public String getVreason() {
		return this.vreason;
	}

	public void setVreason(String vreason) {
		 this.vreason = vreason;
	}

	@Override
	public String toString() {
		return "Leave{" +
				"vid=" + vid +
				", vto=" + vto +
				", vend=" + vend +
				", vtype='" + vtype + '\'' +
				", vstatus=" + vstatus +
				", vtime=" + vtime +
				", vstart=" + vstart +
				", vcheck='" + vcheck + '\'' +
				", vfrom=" + vfrom +
				", vreason='" + vreason + '\'' +
				'}';
	}
}
