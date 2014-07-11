package com.dmg.admin.rest.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class BillsJson implements Serializable {

	/**
	 * @author Kareem Jabr
	 */
	private static final long serialVersionUID = 4731187513819796122L;
	@JsonProperty("bills")
	private List<BillJson> billJsonList = new ArrayList<BillJson>();

	public List<BillJson> getBillJsonList() {
		return billJsonList;
	}

	public void setBillJsonList(List<BillJson> billJsonList) {
		this.billJsonList = billJsonList;
	}

}
