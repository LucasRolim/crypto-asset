package com.crypto.cryptoassets.model;

public class Response {

	private String  total;
	private String  best_asset;
	private String  best_performance;
	private String  worst_asset;
	private String  worst_performance;
	
	
	public Response(String total, String best_asset, String best_performance, String worst_asset,
			String worst_performance) {
		this.total = total;
		this.best_asset = best_asset;
		this.best_performance = best_performance;
		this.worst_asset = worst_asset;
		this.worst_performance = worst_performance;
	}
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getBest_asset() {
		return best_asset;
	}
	public void setBest_asset(String best_asset) {
		this.best_asset = best_asset;
	}
	public String getBest_performance() {
		return best_performance;
	}
	public void setBest_performance(String best_performance) {
		this.best_performance = best_performance;
	}
	public String getWorst_asset() {
		return worst_asset;
	}
	public void setWorst_asset(String worst_asset) {
		this.worst_asset = worst_asset;
	}
	public String getWorst_performance() {
		return worst_performance;
	}
	public void setWorst_performance(String worst_performance) {
		this.worst_performance = worst_performance;
	}

	@Override
	public String toString() {
		return "Response [total=" + total + ", best_asset=" + best_asset + ", best_performance=" + best_performance
				+ ", worst_asset=" + worst_asset + ", worst_performance=" + worst_performance + "]";
	}


	
}