package indi.crisp.mylin.pojo.expand;

import indi.crisp.mylin.pojo.Perm;
import java.lang.Integer;
import java.lang.String;

public class PermPO extends Perm {


	public PermPO() {
	}

	public PermPO(String proute, String pnameZH, String picon, String pname, String pdesc, Integer pid) {
		 this.proute = proute;
		 this.pnameZH = pnameZH;
		 this.picon = picon;
		 this.pname = pname;
		 this.pdesc = pdesc;
		 this.pid = pid;
	}

}
