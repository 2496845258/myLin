package indi.crisp.mylin.pojo.expand;

import indi.crisp.mylin.pojo.Log;
import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;

public class LogPO extends Log {


	public LogPO() {
	}

	public LogPO(Timestamp ltime, String ldname, String lrole, Integer lid, String linfo, String lename, String ldesc, String ltype) {
		 this.ltime = ltime;
		 this.ldname = ldname;
		 this.lrole = lrole;
		 this.lid = lid;
		 this.linfo = linfo;
		 this.lename = lename;
		 this.ldesc = ldesc;
		 this.ltype = ltype;
	}

}
