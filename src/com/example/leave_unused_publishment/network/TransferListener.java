package com.example.leave_unused_publishment.network;

import org.json.JSONObject;

public interface TransferListener {
	public void onSucceed(JSONObject obj);
	public void onFail(String desc);
}
