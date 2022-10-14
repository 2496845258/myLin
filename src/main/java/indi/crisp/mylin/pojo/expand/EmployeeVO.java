package indi.crisp.mylin.pojo.expand;

import indi.crisp.mylin.pojo.Employee;
import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;

public class EmployeeVO extends Employee {


	public EmployeeVO() {
	}

	public EmployeeVO(String eloginid, String ename, Integer estatus, Integer edept, String epwd, Timestamp elasttime, Integer empno, String esalt, Integer erole) {
		 this.eloginid = eloginid;
		 this.ename = ename;
		 this.estatus = estatus;
		 this.edept = edept;
		 this.epwd = epwd;
		 this.elasttime = elasttime;
		 this.empno = empno;
		 this.esalt = esalt;
		 this.erole = erole;
	}

}
