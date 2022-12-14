package indi.crisp.mylin.pojo.expand;

import indi.crisp.mylin.pojo.Leave;
import indi.luzhao.summer.annotate.markclass.Serialize;

import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;

@Serialize
public class LeavePO extends Leave {
	public LeavePO() {
	}

	public LeavePO(Integer vid, Integer vto, Timestamp vend, String vtype, Integer vstatus, Timestamp vtime, Timestamp vstart, String vcheck, Integer vfrom, String vreason) {
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

}
