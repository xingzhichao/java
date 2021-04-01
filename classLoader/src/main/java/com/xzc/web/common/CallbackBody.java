package com.xzc.web.common;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 后台返回信息
 * @title CallbackBody.java
 * @author Gavin
 * @since 2018年5月22日 上午9:52:36
 * @version 1.0 Gavin 2018年5月22日 上午9:52:36
 * @param <T>
 */
public class CallbackBody<T> {
	public final static String CALLBACK_STATUS_SUCESS="ok";
	public final static String CALLBACK_STATUS_FAILURE="error";
	private String code;
	private String info;
	private T result;
	
	public CallbackBody(){
	}
	public CallbackBody<T> sucess(String info, T result){
		this.code=CALLBACK_STATUS_SUCESS;
		this.info=info;
		this.result=result;
		return this;
	}
	public CallbackBody<T> failure(String info, T result){
		this.code=CALLBACK_STATUS_FAILURE;
		this.info=info;
		this.result=result;
		return this;
	}
	public CallbackBody(String code, String info, T result){
		this.code=code;
		this.info=info;
		this.result=result;
	}
	
	/**
	 * 部分请求如果直接返回该对象会出现springmvc无法转换的异常，需要转换为json对象才能正常执行。比如文件上传的请求。
	 */
	public String toJson() {
		JSONObject json = (JSONObject) JSON.toJSON(this);
		return json.toString();
	}
	
	public T getResult() {
		return result;
	}
	
	public void setResult(T result) {
		this.result = result;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
}
