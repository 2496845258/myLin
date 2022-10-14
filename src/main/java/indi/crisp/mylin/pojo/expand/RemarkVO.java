package indi.crisp.mylin.pojo.expand;

import indi.crisp.mylin.pojo.Remark;
import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;

public class RemarkVO extends Remark {


	public RemarkVO() {
	}

	public RemarkVO(Timestamp ktime, Integer kid, String kinfo, Integer kemp) {
		 this.ktime = ktime;
		 this.kid = kid;
		 this.kinfo = kinfo;
		 this.kemp = kemp;
	}

}
