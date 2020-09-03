package com.travel.common.Code;

import java.util.ArrayList;
import java.util.List;

public enum CommonCode {
	//已在前端使用中
	SUCCESS(200,"服务调用成功"),
	FILE_SAVE_SUCCESS(201,"文件上传成功!"),
    //前端错误
	LOGIN_FAILED(400,"用户名或密码错误"),
	TOKEN_FAILED(401,"token无效"),
	ERROR(500,"服务异常"),
	USER_EXIST(501, "登录名称已存在"),
	USER_LOGOUT(502,"用户未登录"),


	BATCH_UPDATE(20001,"批量更新失败！"),
	ERROR_NULL(20005,"空指针异常"),

	ERROR_IO(20010,"IO异常！"),

	FILE_SAVE_FAIL(20011,"文件上传失败!"),
	AUDIT_ERROR(20002,"审批失败"),

	CODE_INVALID(30000,"传入参数为空"),
	 DATA_NULL(30001,"数据为空"),
	PARAMETER_INVALID(30014,"非法的参数"),
	DATA_REPEAT(30002,"非法的参数"),

	//已在前端使用中，接口未登陆
	ISV_INVALID_LOGIN(40000,"登陆超时"),
	TOKEN_INVALID(40001,"此用户无访问权限"),
	ISV_INVALID_SIGNATURE(40002,"无效签名"),
	ISV_INVALID_ENCRYPT(40002,"解密异常"),
	ISV_INVALID_APP_ID(40002,"无效的APPID参数");


	
	
	
	
	private int key;
	private String value;


	public static CommonCode get(int  key) {
		for (CommonCode dot : CommonCode.values()) {
			if (key == dot.getKey()) {
				return dot;
			}
		}
		return CODE_INVALID;
	}


	public static CommonCode get(String value) {
		for (CommonCode dot : CommonCode.values()) {
			if (value.equals(dot.getValue())) {
				return dot;
			}
		}
		return CODE_INVALID;
	}

	public static List<Integer> getAllKey() {
		List<Integer> list =new ArrayList<Integer>();
		for (CommonCode dot : CommonCode.values()) {
			list.add(dot.getKey());
		}
		return list;
	}

	public static List<String> getAllValue() {
		List<String> list =new ArrayList<String>();
		for (CommonCode dot : CommonCode.values()) {
			list.add(dot.getValue());
		}
		return list;
	}



	CommonCode(int key, String value) {
		this.key = key;
		this.value = value;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}


}
