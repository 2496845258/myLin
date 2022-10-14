package indi.crisp.mylin.pojo.expand;

import indi.crisp.mylin.pojo.Dict;
import java.lang.Integer;
import java.lang.String;

public class DictVO extends Dict {


	public DictVO() {
	}

	public DictVO(Integer dtid, Integer dkey, String dvalue, Integer did, Integer dis) {
		 this.dtid = dtid;
		 this.dkey = dkey;
		 this.dvalue = dvalue;
		 this.did = did;
		 this.dis = dis;
	}

}
