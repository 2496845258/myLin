package indi.crisp.mylin.pojo.expand;

import indi.crisp.mylin.pojo.Notify;
import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;

public class NotifyPO extends Notify {


	public NotifyPO() {
	}

	public NotifyPO(String ninfo, Integer nfrom, Integer nid, Integer nto, Integer nstatus, String ntype, Timestamp ntime) {
		 this.ninfo = ninfo;
		 this.nfrom = nfrom;
		 this.nid = nid;
		 this.nto = nto;
		 this.nstatus = nstatus;
		 this.ntype = ntype;
		 this.ntime = ntime;
	}

}
