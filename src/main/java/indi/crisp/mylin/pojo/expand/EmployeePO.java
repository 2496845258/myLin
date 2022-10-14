package indi.crisp.mylin.pojo.expand;

import indi.crisp.mylin.pojo.Employee;
import java.lang.Integer;
import java.lang.String;

public class EmployeePO extends Employee {


	public EmployeePO() {
	}

	public EmployeePO(String eloginid, String ename, Integer estatus, Integer edept, String epwd, Integer empno, String esalt, Integer erole) {
		 this.eloginid = eloginid;
		 this.ename = ename;
		 this.estatus = estatus;
		 this.edept = edept;
		 this.epwd = epwd;
		 this.empno = empno;
		 this.esalt = esalt;
		 this.erole = erole;
	}

}
