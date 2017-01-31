package com.stock.fileio;

import java.util.ArrayList;
import java.util.List;

public class SharedListResult<T> {

	private boolean isDone;
	private List<T> sharedList = new ArrayList<T>();

	public List<T> getSharedList() {
		return sharedList;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
}
