package indi.crisp.mylin.pojo;

import java.lang.Integer;
import java.lang.String;

public class Remark {

	protected Integer kid;
	protected String kinfo;
	protected Integer kemp;

	public Remark() {
	}

	public Remark(Integer kid, String kinfo, Integer kemp) {
		 this.kid = kid;
		 this.kinfo = kinfo;
		 this.kemp = kemp;
	}

	public Integer getKid() {
		return this.kid;
	}

	public void setKid(Integer kid) {
		 this.kid = kid;
	}

	public String getKinfo() {
		return this.kinfo;
	}

	public void setKinfo(String kinfo) {
		 this.kinfo = kinfo;
	}

	public Integer getKemp() {
		return this.kemp;
	}

	public void setKemp(Integer kemp) {
		 this.kemp = kemp;
	}

}
