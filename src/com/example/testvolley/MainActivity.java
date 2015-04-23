package com.example.testvolley;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.base.interfaces.IResponse;

public class MainActivity extends Activity {

	// public final static String URL = "http://192.168.1.97/AppServer3/";
	public final static String URL = "http://192.168.1.97/appservice180/appservice.ashx";
	// public final static String URL =
	// "http://192.168.1.59/Appserver3/appservice.ashx";
	// public static final String URL =
	// "http://192.168.1.81:8080/TestServlet/demo";
	// public static String URL = "http://service.9yiban.com/appservice/";
	// public static final String URL = "http://www.baidu.com";

	public RequestQueue mQueue;
	private TextView tv;
	private JSONObject reqJson = new JSONObject();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tv = new TextView(this);
		mQueue = Volley.newRequestQueue(this);
		JSONObject json = new JSONObject();
		try {
			json.put("UserFlag", "");
			json.put("Content", "test content");
			
			postForJson("15001", json, new  IResponse() {
				@Override
				public void onSuccess(JSONObject response) {
					tv.setText("我回调的成功的结果是:"+response);
				}

				@Override
				public void onFailure(JSONObject response) {
					tv.setText("我回调的失败的结果是:"+response);
				}
			});
			
			
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

		
		
		
		// try {
		// JSONObject json = new JSONObject();
		// json.put("UserID", "13790114025");
		// json.put("UserPwd","123456");
		// postForJSONObject("10001",json);
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		setContentView(tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

//	public void postForJSONObject(String type, JSONObject json)
//			throws JSONException {
//
//		// JSONObject json = new JSONObject();
//		// json.put("UserFlag", "");
//		// json.put("Content", "advices to improve");
//		reqJson.put("json", json.toString());
//		reqJson.put("code", type);
//		//
//		JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
//				URL, reqJson, new Listener<JSONObject>() {
//
//					@Override
//					public void onResponse(JSONObject response) {
//						Log.i("Response", "结果是:" + response);
//						tv.setText("结果" + response.toString());
//					}
//				}, new Response.ErrorListener() {
//
//					@Override
//					public void onErrorResponse(VolleyError error) {
//						// TODO Auto-generated method stub
//						Log.i("Response", "错误:" + error.getMessage());
//					}
//
//				});
//		mQueue.add(request);
//
//	}

	public  void postForJson(final String code,final JSONObject json) {
		String url = URL;
		StringRequest postRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						// response
						// tv.setText("string响应结果是:"+response);
//						try {
//							JSONObject resJson = new JSONObject(response);
//							tv.setText(resJson.toString());
//
//						} catch (JSONException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//
//						Log.d("Response", response);
						
//					callback(response);
						
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// error
						Log.d("Error.Response", error.getMessage());
					}
				}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("code", code);
				params.put("json", json.toString());
				return params;
			}

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("Charset", "UTF-8");
				return headers;
			}
		};
		mQueue.add(postRequest);
	}

	
	
	public  void postForJson(final String code,final JSONObject json,final IResponse iResp) {
		String url = URL;
		StringRequest postRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						callback(response,iResp);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// error
						Log.d("Error.Response", error.getMessage());
					}
				}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("code", code);
				params.put("json", json.toString());
				return params;
			}

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("Charset", "UTF-8");
				return headers;
			}
		};
		mQueue.add(postRequest);
	}

	
	/**
	 * 处理响应的回调
	 * @param response
	 */
	protected void callback(String response,IResponse irsp) {
		try {
			JSONObject resJson = new JSONObject(response);
			String reqResult = resJson.optString("ReqResult");
			String ReqMsg = resJson.optString("ReqMsg");
			
			if("0".equals(reqResult)){
				//处理成功的逻辑
				irsp.onSuccess(resJson);
			}else{
				//处理错误码的逻辑
				irsp.onFailure(resJson);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

//	public interface IResp{
//		public static final String SUCCESS="0";
//		public void onSuccess();
//		public void onFailure();
//	}
//	
//	
//	private void onFailure() {
//		System.out.println("处理失败的情况");
//	}
//
//	private void onSuccess() {
//			System.out.println("处理成功的情况");
//	}

}
