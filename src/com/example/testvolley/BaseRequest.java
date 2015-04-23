package com.example.testvolley;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;

public class BaseRequest extends Request<String> {

	public BaseRequest(int method, String url, ErrorListener listener) {
		super(method, url, listener);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void deliverResponse(String response) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("Charset", "UTF-8");
		return super.getHeaders();
	}
	
	
	

}
