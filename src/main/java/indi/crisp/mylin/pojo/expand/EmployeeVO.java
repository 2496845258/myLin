package indi.crisp.mylin.pojo.expand;

import indi.crisp.mylin.pojo.Employee;
import indi.crisp.mylin.pojo.Perm;
import indi.crisp.mylin.pojo.Role;

import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;
import java.util.List;

public class EmployeeVO extends Employee {

	private Role role;
	private List<Perm> permList;

	private String deptName;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Perm> getPermList() {
		return permList;
	}

	public void setPermList(List<Perm> permList) {
		this.permList = permList;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "EmployeeVO{" +
				"role=" + role +
				", permList=" + permList +
				", eloginid='" + eloginid + '\'' +
				", ename='" + ename + '\'' +
				", estatus=" + estatus +
				", edept=" + edept +
				", epwd='" + epwd + '\'' +
				", elasttime=" + elasttime +
				", empno=" + empno +
				", esalt='" + esalt + '\'' +
				", erole=" + erole +
				'}';
	}
}
