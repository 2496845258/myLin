package indi.crisp.mylin.pojo.expand;

import indi.crisp.mylin.pojo.Dept;
import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;

public class DeptPO extends Dept {


	public DeptPO() {
	}

	public DeptPO(Integer dno, Timestamp dcreate, String ddesc, Integer dstatus, String dname, String dhost) {
		 this.dno = dno;
		 this.dcreate = dcreate;
		 this.ddesc = ddesc;
		 this.dstatus = dstatus;
		 this.dname = dname;
		 this.dhost = dhost;
	}

}
