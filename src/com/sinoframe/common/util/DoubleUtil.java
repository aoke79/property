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
 * <p><b>Description</b>:  计算操作共通方法</p>
 * <p><b>DATE</b>: 2011/04/11</p>
 * AUTHOR        : SinoSoft ruanruiwen
 * VERSION       : 1.0
 * <p><b>HISTORY</b>: </p>
 * 
**/
package com.sinoframe.common.util;

import java.math.BigDecimal;

public class DoubleUtil {
	
	    private static final int DEF_DIV_SCALE = 10;
	    
	    /**
	     * 两个Double数相加
	     * @param v1
	     * @param v2
	     * @return Double
	     */
	    public static Double add(Double v1,Double v2){
	        BigDecimal b1 = new BigDecimal(v1.toString());
	        BigDecimal b2 = new BigDecimal(v2.toString());
	        return b1.add(b2).doubleValue();
	    }
	    
	    /**
	     * 两个Double数相减
	     * @param v1
	     * @param v2
	     * @return Double
	     */
	    public static Double sub(Double v1,Double v2){
	        BigDecimal b1 = new BigDecimal(v1.toString());
	        BigDecimal b2 = new BigDecimal(v2.toString());
	        return b1.subtract(b2).doubleValue();
	    }
	    
	    /**
	     * 两个Double数相乘
	     * @param v1
	     * @param v2
	     * @return Double
	     */
	    public static Double mul(Double v1,Double v2){
	        BigDecimal b1 = new BigDecimal(v1.toString());
	        BigDecimal b2 = new BigDecimal(v2.toString());
	        return b1.multiply(b2).doubleValue();
	    }
	    
	    /**
	     * 两个Double数相除
	     * @param v1
	     * @param v2
	     * @return Double
	     */
	    public static Double div(Double v1,Double v2){
	        BigDecimal b1 = new BigDecimal(v1.toString());
	        BigDecimal b2 = new BigDecimal(v2.toString());
	        return b1.divide(b2,DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP).doubleValue();
	    }
	    
	    /**
	     * 两个Double数相除，并保留scale位小数
	     * @param v1
	     * @param v2
	     * @param scale
	     * @return Double
	     */
	    public static Double div(Double v1,Double v2,int scale){
	        if(scale<0){
	            throw new IllegalArgumentException(
	            "The scale must be a positive integer or zero");
	        }
	        BigDecimal b1 = new BigDecimal(v1.toString());
	        BigDecimal b2 = new BigDecimal(v2.toString());
	        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	    }

	} 

