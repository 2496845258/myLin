package indi.crisp.mylin.pojo;

import java.lang.Integer;
import java.lang.String;

public class Dict {

	protected Integer dtid;
	protected Integer dkey;
	protected String dvalue;
	protected Integer did;
	protected Integer dis;

	public Dict() {
	}

	public Dict(Integer dtid, Integer dkey, String dvalue, Integer did, Integer dis) {
		 this.dtid = dtid;
		 this.dkey = dkey;
		 this.dvalue = dvalue;
		 this.did = did;
		 this.dis = dis;
	}

	public Integer getDtid() {
		return this.dtid;
	}

	public void setDtid(Integer dtid) {
		 this.dtid = dtid;
	}

	public Integer getDkey() {
		return this.dkey;
	}

	public void setDkey(Integer dkey) {
		 this.dkey = dkey;
	}

	public String getDvalue() {
		return this.dvalue;
	}

	public void setDvalue(String dvalue) {
		 this.dvalue = dvalue;
	}

	public Integer getDid() {
		return this.did;
	}

	public void setDid(Integer did) {
		 this.did = did;
	}

	public Integer getDis() {
		return this.dis;
	}

	public void setDis(Integer dis) {
		 this.dis = dis;
	}

}
