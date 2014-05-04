package com.dmg.admin.ui;

import java.util.ArrayList;
import java.util.List;

import org.tepi.filtertable.paged.PagedFilterControlConfig;

public class CustomPagedFilterControlConfig extends PagedFilterControlConfig {

	private List<Integer> pageLengths;

	@Override
	public List<Integer> getPageLengths() {
		return pageLengths;
	}

	@Override
	public void setPageLengthsAndCaptions(List<Integer> pageLengths) {
		this.pageLengths = pageLengths;
	}

	public CustomPagedFilterControlConfig() {
		pageLengths = new ArrayList<Integer>();
		pageLengths.add(25);
		pageLengths.add(50);
		pageLengths.add(100);
		pageLengths.add(150);
		pageLengths.add(200);
	}

}
