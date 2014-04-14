package com.sinoframe.common.util;

import java.util.UUID;

import bsh.This;

public class UUIDTools {
	
	private static UUIDTools uuidTools = null;
	
	private UUIDTools(){
		
	}
	
	public static synchronized UUIDTools getInstance() {
		if(uuidTools == null){
			return new UUIDTools();
		}
		return uuidTools;
	}
	
	/**
	 * ��ȡUUID
	 * @return UUID.tostring
	 */

	public String getUUID()
	{
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 陈蕾蕾测试
	 * @param args    91a69c9e-3df7-4997-ac6d-1e131fb792bf   3b9a968e-0271-4332-866c-2c689953ef5e
	 */
	public static void main(String[] args) {
		System.out.println(UUIDTools.getInstance().getUUID());
	}
}
