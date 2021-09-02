package com.crypto.cryptoassets.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Assets {

	@SerializedName("data")
	@Expose
	private List<Datum> data = null;
	@SerializedName("timestamp")
	@Expose
	private Long timestamp;

	public List<Datum> getData() {
		return data;
	}

	public void setData(List<Datum> data) {
		this.data = data;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}