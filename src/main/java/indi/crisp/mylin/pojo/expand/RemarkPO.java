package indi.crisp.mylin.pojo.expand;

import indi.crisp.mylin.pojo.Remark;
import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;

public class RemarkPO extends Remark {


	public RemarkPO() {
	}

	public RemarkPO(Timestamp ktime, Integer kid, String kinfo, Integer kemp) {
		 this.ktime = ktime;
		 this.kid = kid;
		 this.kinfo = kinfo;
		 this.kemp = kemp;
	}

}
