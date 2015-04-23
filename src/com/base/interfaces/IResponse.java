package com.base.interfaces;

import org.json.JSONObject;

/**
 * 响应接口的回调
 * @author Administrator
 */
public interface IResponse {
	public void onSuccess(JSONObject response);
	public void onFailure(JSONObject response);
}
