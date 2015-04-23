package com.base.interfaces;

import org.json.JSONObject;

public interface IResponse {
	public void onSuccess(JSONObject response);
	public void onFailure(JSONObject response);
}
