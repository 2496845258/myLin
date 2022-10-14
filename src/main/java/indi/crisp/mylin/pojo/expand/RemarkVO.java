package indi.crisp.mylin.pojo.expand;

import indi.crisp.mylin.pojo.Remark;
import java.lang.Integer;
import java.lang.String;

public class RemarkVO extends Remark {


	public RemarkVO() {
	}

	public RemarkVO(Integer kid, String kinfo, Integer kemp) {
		 this.kid = kid;
		 this.kinfo = kinfo;
		 this.kemp = kemp;
	}

}
