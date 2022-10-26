package indi.crisp.mylin.pojo.expand;

import indi.crisp.mylin.pojo.Dept;
import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;

public class DeptVO extends Dept {


	private String hostName;
	public DeptVO() {
	}

	public DeptVO(Integer dno, Timestamp dcreate, String ddesc, Integer dstatus, String dname, Integer dhost) {
		 this.dno = dno;
		 this.dcreate = dcreate;
		 this.ddesc = ddesc;
		 this.dstatus = dstatus;
		 this.dname = dname;
		 this.dhost = dhost;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
}
