package indi.crisp.mylin.pojo.expand;

import indi.crisp.mylin.pojo.Leave;
import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;

public class LeaveVO extends Leave {

	private String empName;
	private String spStatus;

	public LeaveVO() {
	}

	public LeaveVO(Integer vid, Integer vto, Timestamp vend, String vtype, Integer vstatus, Timestamp vtime, Timestamp vstart, String vcheck, Integer vfrom, String vreason) {
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

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getSpStatus() {
		return spStatus;
	}

	public void setSpStatus(String spStatus) {
		this.spStatus = spStatus;
	}
}
