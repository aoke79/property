///////////////////////////////////////////////////////////////////////////////
// COPYRIGHT(C) 2011 SINOSOFT  CO., LTD.                                     //
//                                                                           //
// ALL RIGHTS RESERVED BY SINOSOFT  CO., LTD.                                //
// THIS PROGRAM MUST BE USED SOLELY FOR THE PURPOSE FOR WHICH                //
// IT WAS FURNISHED BY SINOSOFT  CO., LTD.                                   //
// NO PART OF THIS PROGRAM MAY BE REPRODUCED OR DISCLOSED TO OTHERS,         //
// IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF SINOSOFT              //
//(CHINA) CO., LTD.                                                          //
//                                                                           //
// SINOSOFT CO., LTD. CONFIDENTIAL AND PROPRIETARY                           //
///////////////////////////////////////////////////////////////////////////////

/**
 * <p><b>Title</b>:  DateTool</p>
 * <p><b>Description</b>:  格式化字符串</p>
 * <p><b>DATE</b>: 2011/04/11</p>
 * AUTHOR        : SinoSoft ruanruiwen
 * VERSION       : 1.0
 * <p><b>HISTORY</b>: </p>
 * 
**/
package com.sinoframe.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class FormatString {

	/***************************************************************************
	 * 函数名： GetFormatedDate <BR>
	 * 机能概要： 按照指定的格式转换日期 <BR>
	 * <BR>
	 * 参数： strYear有效的年份 <BR>
	 * strMonth有效的月份 <BR>
	 * strDate有效的日 <BR>
	 * nFormat指定的返回格式 <BR>
	 * 0："YYYYMMDD" 7："DD/MM/YYYY" <BR>
	 * 1："YYYY/MM/DD" 8："DD-MM-YYYY" <BR>
	 * 2："YYYY-MM-DD" 9："DDMMYY" <BR>
	 * 3："YYMMDD" 10："DD/MM/YY" <BR>
	 * 4："YY/MM/DD" 11："DD-MM-YY" <BR>
	 * 5："YY-MM-DD" 12："YYYY年MM月DD日" <BR>
	 * 6："DDMMYYYY" 13："YY年MM月DD日" <BR>
	 * 返回值： 指定格式的日期 <BR>
	 **************************************************************************/
	public static String GetFormatedDate(String strYear, String strMonth,
			String strDate, int nFormat) {

		String strReturnValue = "";

		switch (nFormat) {
		case 0:
			strReturnValue = SupplyLength(strYear, 4)
					+ SupplyLength(strMonth, 2) + SupplyLength(strDate, 2);
			break;
		case 1:
			strReturnValue = SupplyLength(strYear, 4) + "/"
					+ SupplyLength(strMonth, 2) + "/"
					+ SupplyLength(strDate, 2);
			break;
		case 2:
			strReturnValue = SupplyLength(strYear, 4) + "-"
					+ SupplyLength(strMonth, 2) + "-"
					+ SupplyLength(strDate, 2);
			break;
		case 3:
			strReturnValue = SupplyLength(strYear, 4).substring(2)
					+ SupplyLength(strMonth, 2) + SupplyLength(strDate, 2);
			break;
		case 4:
			strReturnValue = SupplyLength(strYear, 4).substring(2) + "/"
					+ SupplyLength(strMonth, 2) + "/"
					+ SupplyLength(strDate, 2);
			break;
		case 5:
			strReturnValue = SupplyLength(strYear, 4).substring(2) + "-"
					+ SupplyLength(strMonth, 2) + "-"
					+ SupplyLength(strDate, 2);
			break;
		case 6:
			strReturnValue = SupplyLength(strDate, 2)
					+ SupplyLength(strMonth, 2) + SupplyLength(strYear, 4);
			break;
		case 7:
			strReturnValue = SupplyLength(strDate, 2) + "/"
					+ SupplyLength(strMonth, 2) + "/"
					+ SupplyLength(strYear, 4);
			break;
		case 8:
			strReturnValue = SupplyLength(strDate, 2) + "-"
					+ SupplyLength(strMonth, 2) + "-"
					+ SupplyLength(strYear, 4);
			break;
		case 9:
			strReturnValue = SupplyLength(strDate, 2)
					+ SupplyLength(strMonth, 2)
					+ SupplyLength(strYear, 4).substring(2);
			break;
		case 10:
			strReturnValue = SupplyLength(strDate, 2) + "/"
					+ SupplyLength(strMonth, 2) + "/"
					+ SupplyLength(strYear, 4).substring(2);
			break;
		case 11:
			strReturnValue = SupplyLength(strDate, 2) + "-"
					+ SupplyLength(strMonth, 2) + "-"
					+ SupplyLength(strYear, 4).substring(2);
			break;
		case 12:
			strReturnValue = SupplyLength(strYear, 4) + "年"
					+ SupplyLength(strMonth, 2) + "月"
					+ SupplyLength(strDate, 2) + "日";
			break;
		case 13:
			strReturnValue = SupplyLength(strYear, 4).substring(2) + "年"
					+ SupplyLength(strMonth, 2) + "月"
					+ SupplyLength(strDate, 2) + "日";
			break;
		}

		return strReturnValue;
	}

	/***************************************************************************
	 * 函数名： SupplyLength <BR>
	 * 机能概要： 将输入的字符串前加0变成指定长的字符串 <BR>
	 * <BR>
	 * 参数： strin 待转换的字符串 <BR>
	 * nLength 指定的长度 <BR>
	 * 返回值： 处理后的字符串 <BR>
	 **************************************************************************/
	public static String SupplyLength(String strin, int nLength) {
		String strReturnValue = "";
		int nNum;

		nNum = nLength - strin.length();
		strReturnValue = strin;

		for (int i = 1; i <= nNum; i++) {
			strReturnValue = "0" + strReturnValue;
		}

		return strReturnValue;
	}

	/**
	 * 数字格式化(保留两位小数，整数部分每隔三位用逗号区分)
	 * 
	 * @param str
	 * @return
	 */
	public String formatNumber(String str) {
		DecimalFormat fmt = new DecimalFormat("##,###,###,###,##0.00");
		if (str == "" || str == null) {
			return "0.00";
		} else {

			BigDecimal d = new BigDecimal(str);

			return fmt.format(d);
		}
	}
}
