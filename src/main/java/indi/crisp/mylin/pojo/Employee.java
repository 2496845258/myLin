package indi.crisp.mylin.pojo;

import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;

public class Employee {

	protected String eloginid;
	protected String ename;
	protected Integer estatus;
	protected Integer edept;
	protected String epwd;
	protected Timestamp elasttime;
	protected Integer empno;
	protected String esalt;
	protected Integer erole;

	public Employee() {
	}

	public Employee(String eloginid, String ename, Integer estatus, Integer edept, String epwd, Timestamp elasttime, Integer empno, String esalt, Integer erole) {
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

	public String getEloginid() {
		return this.eloginid;
	}

	public void setEloginid(String eloginid) {
		 this.eloginid = eloginid;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		 this.ename = ename;
	}

	public Integer getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Integer estatus) {
		 this.estatus = estatus;
	}

	public Integer getEdept() {
		return this.edept;
	}

	public void setEdept(Integer edept) {
		 this.edept = edept;
	}

	public String getEpwd() {
		return this.epwd;
	}

	public void setEpwd(String epwd) {
		 this.epwd = epwd;
	}

	public Timestamp getElasttime() {
		return this.elasttime;
	}

	public void setElasttime(Timestamp elasttime) {
		 this.elasttime = elasttime;
	}

	public Integer getEmpno() {
		return this.empno;
	}

	public void setEmpno(Integer empno) {
		 this.empno = empno;
	}

	public String getEsalt() {
		return this.esalt;
	}

	public void setEsalt(String esalt) {
		 this.esalt = esalt;
	}

	public Integer getErole() {
		return this.erole;
	}

	public void setErole(Integer erole) {
		 this.erole = erole;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"eloginid='" + eloginid + '\'' +
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
