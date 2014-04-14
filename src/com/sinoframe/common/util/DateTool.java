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
 * <p><b>Description</b>:  日期操作共通方法</p>
 * <p><b>DATE</b>: 2011/04/11</p>
 * AUTHOR        : SinoSoft ruanruiwen
 * VERSION       : 1.0
 * <p><b>HISTORY</b>: </p>
 * 
**/
package com.sinoframe.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateTool {

    /**
     * 缺省的日期显示格式： yyyy-MM-dd
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 缺省的日期时间显示格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 私有构造方法，禁止对该类进行实例化
     */
    private DateTool() {
    }

    /**
     * 得到系统当前日期时间
     * 
     * @return 当前日期时间
     */
    public static Date getNow() {
        return Calendar.getInstance().getTime();
    }
    
    /**
     *  
     * 获得当前日期 yyyy-MM-dd
     * @return
     * Dec 9, 20113:19:32 PM
     * @author niujingwei
     */
    public static Date getNowDate(){
    	return parse(getDate(), DEFAULT_DATE_FORMAT);
    }
    
    public static Date getNowTime(){
    	return parse(getDate(), DEFAULT_DATETIME_FORMAT);
    }
    
    

    /**
     * 得到用缺省方式格式化的当前日期
     * 
     * @return 当前日期
     */
    public static String getDate() {
        return getDateTime(DEFAULT_DATE_FORMAT);
    }

    /**
     * 得到用缺省方式格式化的当前日期及时间
     * 
     * @return 当前日期及时间
     */
    public static String getDateTime() {
        return getDateTime(DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 得到系统当前日期及时间，并用指定的方式格式化
     * 
     * @param pattern 显示格式
     * @return 当前日期及时间
     */
    public static String getDateTime(String pattern) {
        Date datetime = Calendar.getInstance().getTime();
        return getDateTime(datetime, pattern);
    }

    /**
     * 得到用指定方式格式化的日期
     * 
     * @param date 需要进行格式化的日期
     * @param pattern 显示格式
     * @return 日期时间字符串
     */
    public static String getDateTime(Date date, String pattern) {
        if (null == pattern || "".equals(pattern)) {
            pattern = DEFAULT_DATETIME_FORMAT;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 得到当前年份
     * 
     * @return 当前年份
     */
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 得到当前月份
     * 
     * @return 当前月份
     */
    public static int getCurrentMonth() {
        //用get得到的月份数比实际的小1，需要加上
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * 得到当前日
     * 
     * @return 当前日
     */
    public static int getCurrentDay() {
        return Calendar.getInstance().get(Calendar.DATE);
    }

    /**
     * 取得当前日期以后若干天的日期。如果要得到以前的日期，参数用负数。 例如要得到上星期同一天的日期，参数则为-7
     * 
     * @param days 增加的日期数
     * @return 增加以后的日期
     */
    public static Date addDays(int days) {
        return add(getNow(), days, Calendar.DATE);
    }

    /**
     * 取得指定日期以后若干天的日期。如果要得到以前的日期，参数用负数。
     * 
     * @param date 基准日期
     * @param days 增加的日期数
     * @return 增加以后的日期
     */
    public static Date addDays(Date date, int days) {
        return add(date, days, Calendar.DATE);
    }

    /**
     * 取得当前日期以后某月的日期。如果要得到以前月份的日期，参数用负数。
     * 
     * @param months 增加的月份数
     * @return 增加以后的日期
     */
    public static Date addMonths(int months) {
        return add(getNow(), months, Calendar.MONTH);
    }

    /**
     * 取得指定日期以后某月的日期。如果要得到以前月份的日期，参数用负数。 注意，可能不是同一日子，例如2003-1-31加上一个月是2003-2-28
     * 
     * @param date 基准日期
     * @param months 增加的月份数
     * @return 增加以后的日期
     */
    public static Date addMonths(Date date, int months) {
        return add(date, months, Calendar.MONTH);
    }

    /**
     * 内部方法。为指定日期增加相应的天数或月数
     * 
     * @param date 基准日期
     * @param amount 增加的数量
     * @param field 增加的单位，年，月或者日
     * @return 增加以后的日期
     */
    private static Date add(Date date, int amount, int field) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(field, amount);

        return calendar.getTime();
    }

    /**
     * 计算两个日期相差天数。 用第一个日期减去第二个。如果前一个日期小于后一个日期，则返回负数
     * 
     * @param one 第一个日期数，作为基准
     * @param two 第二个日期数，作为比较
     * @return 两个日期相差天数
     */
    public static long diffDays(Date one, Date two) {
    	long dayFirst = one.getTime();
    	long dayLast = two.getTime();
    	if(dayFirst - dayLast < 0) {
    		return (dayFirst - dayLast) / (24 * 60 * 60 * 1000);
    	} else {
    		return ((dayFirst - dayLast) / (24 * 60 * 60 * 1000)) + 1;
    	}
    }

    /**
     * 计算两个日期相差月份数 如果前一个日期小于后一个日期，则返回负数
     * 
     * @param one 第一个日期数，作为基准
     * @param two 第二个日期数，作为比较
     * @return 两个日期相差月份数
     */
    public static int diffMonths(Date one, Date two) {

        Calendar calendar = Calendar.getInstance();

        //得到第一个日期的年分和月份数
        calendar.setTime(one);
        int yearOne = calendar.get(Calendar.YEAR);
        int monthOne = calendar.get(Calendar.MONDAY);

        //得到第二个日期的年份和月份
        calendar.setTime(two);
        int yearTwo = calendar.get(Calendar.YEAR);
        int monthTwo = calendar.get(Calendar.MONDAY);

        return (yearOne - yearTwo) * 12 + (monthOne - monthTwo);
    }
    
    /** 
     * 计算两个日期相差月份数 如果前一个日期小于后一个日期，则返回负数
     * 
     * @param one 第一个日期数，作为基准
     * @param two 第二个日期数，作为比较
     * @return 两个日期相差年数
     */
    public static int diffYear(Date one, Date two) {

        Calendar calendar = Calendar.getInstance();

        //得到第一个日期的年分和月份数
        calendar.setTime(one);
        int yearOne = calendar.get(Calendar.YEAR);

        //得到第二个日期的年份和月份
        calendar.setTime(two);
        int yearTwo = calendar.get(Calendar.YEAR);

        return (yearOne - yearTwo);
    }
    
    /**
     * 计算两个日期相差月份数 如果前一个日期小于后一个日期，则返回负数
     * 
     * @param one 第一个日期数，作为基准
     * @param two 第二个日期数，作为比较
     * @return 两个日期相差年数
     */
    public static int diffYear(String one, String two) {
        return diffYear(parse(one,""), parse(two,""));
    }

    /**
     * 将一个字符串用给定的格式转换为日期类型。 <br>
     * 注意：如果返回null，则表示解析失败
     * 
     * @param datestr 需要解析的日期字符串
     * @param pattern 日期字符串的格式，默认为“yyyy-MM-dd”的形式
     * @return 解析后的日期
     */
    public static Date parse(String datestr, String pattern) {
        Date date = null;

        if (null == pattern || "".equals(pattern)) {
            pattern = DEFAULT_DATE_FORMAT;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            date = dateFormat.parse(datestr);
        } catch (ParseException e) {
            //
        }

        return date;
    }

    /**
     * 返回本月的最后一天
     * 
     * @return 本月最后一天的日期
     */
    public static Date getMonthLastDay() {
        return getMonthLastDay(getNow());
    }

    /**
     * 返回给定日期中的月份中的最后一天
     * 
     * @param date 基准日期
     * @return 该月最后一天的日期
     */
    public static Date getMonthLastDay(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        //将日期设置为下一月第一天
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1);

        //减去1天，得到的即本月的最后一天
        calendar.add(Calendar.DATE, -1);

        return calendar.getTime();
    }
    /**
     *  
     * 得到本月第一天
     * @param date
     * @return
     * Oct 11, 201111:12:47 PM
     * @author niujingwei
     */
    public static Date getMonthFirstDay(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        //将日期设置为下一月第一天
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), -1);

        //加上1天，得到的即本月的最后一天
        calendar.add(Calendar.DATE, 2);

        return calendar.getTime();
    } 
    
    
    
  /**
   * 
   * 判断今天是不是本月最后一天
   * @return
   * Oct 11, 20118:39:10 AM
   * @author niujingwei
   */
  public static boolean isMonthLastDay(){
	  Date monthLastDay = getMonthLastDay();
	  long diffDays = diffDays(new Date(),monthLastDay);
	  if(diffDays==0){
		 return true; 
	  }
	  return false;
  }
    
    
    
    
    
    /**
     * 获取当前时间的前一天
     * @param args
     */
    public static String getYesterday(){
    	//获取系统当前时间
    	Calendar calendar = Calendar.getInstance();
    	//得到前一天
    	calendar.add(Calendar.DATE, -1);
    	//转换格式
    	String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    	return yesterday;
    }
    
    /**
     * 返回给定日期的当前日
     * @param args
     * Sep 14, 2011  4:32:05 PM
     * @author wangjing
     */
    public static int getDayByDate(Date date){
    	//获取系统当前时间
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	return calendar.get(Calendar.DATE);
    }
    
    /**
     * 获取两个日期之间的所有日期
     * @param d1 小日期
     * @param d2 大日期
     * @throws ParseException 
     * @author wangjing
     * Sep 19, 2011  2:18:58 PM
     */
    public static GregorianCalendar[]  getBetweenDate(Date d1,Date d2) throws ParseException     
    {     
        List<GregorianCalendar> v=new ArrayList<GregorianCalendar>();     
        GregorianCalendar gc1=new GregorianCalendar(),gc2=new GregorianCalendar();     
        gc1.setTime(d1);     
        gc2.setTime(d2);     
        do{     
            GregorianCalendar gc3=(GregorianCalendar)gc1.clone();     
            v.add(gc3);     
            gc1.add(Calendar.DAY_OF_MONTH, 1);                  
         }while(!gc1.after(gc2));     
        return v.toArray(new GregorianCalendar[v.size()]);     
    }
    
    /**
     * 
     * 将yyyy-m-d类型字符串转化为yyyy-MM-dd类型的
     * @param str yyyy-m-d类型字符串
     * @return str2 yyyy-MM-dd类型字符串
     * @author wangjing
     * Sep 20, 2011  3:06:34 PM
     */
    public static String getPattern(String str){
    	String str1[] = str.trim().split("-"); 
    	String str2=str1[0]+"-";
    	if(str1[1].trim().length()<2){
    		str2+="0"+str1[1]+"-";
    	}else{
    		str2+=str1[1]+"-";
    	}
    	if(str1[2].trim().length()<2){
    		str2+="0"+str1[2];
    	}else{
    		str2+=str1[2];
    	} 
    	return str2;
    }

    
    public static int getWeek(){
    	Calendar cal=Calendar.getInstance();
    	cal.setTime(new Date());
    	int i = cal.get(Calendar.DAY_OF_WEEK);
         System.out.println(i);
    	return i;
    }
   

    /**
     * 
     * 获取当前日期是星期几
     * @param dt
     * @return 
     * @author wangjing
     * Oct 8, 2011  12:41:48 PM
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }
    
    public static String formatDate(Date date) {
		String formDate = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		formDate = format.format(date);

		return formDate;
	}
    
    /**
     * 将日期数据按指定格式转成对应的字符串格式
     * @param date        需要要进行转化的日期
     * @param pattern     转换格式
     * @return
     */
    public static String formatDate(Date date, String pattern) {
    	String formatDate = "";
    	SimpleDateFormat format = new SimpleDateFormat(pattern);
    	formatDate = format.format(date);
    	return formatDate;
    }
    
    /**
     * 将字符串的日期数据按指定格式转成对应的日期形式
     * @param date        需要进行转化的日期字符串
     * @param pattern     转换格式
     * @throws ParseException
     * @return
     */
    public static Date formatDate(String date, String pattern) throws ParseException {
    	SimpleDateFormat format = new SimpleDateFormat(pattern);
    	Date formatDate = new Date();
    	formatDate = format.parse(date);
    	return formatDate;
    }
    
	public static Date formatDate(String date) throws ParseException {
		Date time = new Date();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		time = format.parse(date);

		return time;

	}
	public static Date formatDate2(String date) throws ParseException {
		Date time = new Date();

		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		time = format.parse(date);

		return time;

	}

	/**
	 * 计算指定年度共有多少个周。 
	 * @param year  格式 yyyy ，必须大于1900年度 小于9999年  
	 * @return 
	 * @author wangjing
	 * Oct 9, 2011  9:27:51 AM
	 */
	public static int getWeekNumByYear(final int year) {
		if (year < 1900 || year > 9999) {
			throw new NullPointerException("年度必须大于等于1900年小于等于9999年");
		}
		int result = 52;// 每年至少有52个周 ，最多有53个周。
		String date = getYearWeekFirstDay(year, 53);

		if (date.substring(0, 4).equals(year + "")) { // 判断年度是否相符，如果相符说明有53个周。
			// System.out.println(date);
			result = 53;
		}
		return result;
	}

	/**
	 * 
	 * @param yearNum
	 *                格式 yyyy ，必须大于1900年度 小于9999年  
	 * @param weekNum
	 *                 1到52或者53
	 * @return         日期，格式为yyyy-MM-dd  
	 * @author wangjing
	 * Oct 9, 2011  9:28:38 AM
	 */
	public static String getYearWeekFirstDay(int yearNum, int weekNum) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.SUNDAY); // 设置每周的第一天为星期日
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);// 每周从周日开始

		// 上面两句代码配合，才能实现，每年度的第一个周，是包含第一个星期日的那个周。

		cal.setMinimalDaysInFirstWeek(7); // 设置每周最少为7天

		cal.set(Calendar.YEAR, yearNum);
		cal.set(Calendar.WEEK_OF_YEAR, weekNum);

		// 分别取得当前日期的年、月、日
		return formatDate(cal.getTime());
	}

	public static int weeksBetweenDays(Date startDate, Date endDate) {
		int counts = 1;
		Calendar c = Calendar.getInstance();
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try { 
			c1.setTime(endDate);
			c.setTime(getFirstDayOfWeek(startDate));
			int weekofYear = c.get(Calendar.WEEK_OF_YEAR);
			int weekofYear1 = c1.get(Calendar.WEEK_OF_YEAR);

			System.out.println("start:" + weekofYear + " end week:"
					+ weekofYear1);

			if (c.get(Calendar.YEAR) == c1.get(Calendar.YEAR)) {
				counts = weekofYear1 - weekofYear + 1;

			} else {

				int difyear = c1.get(Calendar.YEAR) - c.get(Calendar.YEAR);
				//                 

				System.out.println("year diff =" + difyear + " starty="
						+ c.get(Calendar.YEAR));
				if (difyear == 1) {// 年份相差一年
					int startyearWeeks = getWeekNumByYear(c.get(Calendar.YEAR));
					String date = getYearWeekFirstDay(c.get(Calendar.YEAR),
							startyearWeeks);
                    String start = formatDate(startDate);
					System.out.println(date);
					int ds = java.sql.Date.valueOf(date).compareTo(
							java.sql.Date.valueOf(start));
					System.out.println(ds);

					if (ds <= 0) { // 这一年最后一周的开始一天小于用户选择的开始日期，比如，2009年最后一周的开始日期是2009-12-27号，所以开始日期大于或等于2009-12-27号的特殊处理。
						int yearWeeks = getWeekNumByYear(c.get(Calendar.YEAR));
						if(yearWeeks==52 ||yearWeeks==53){
							counts = 1 +weekofYear1;
						}
						
					} else {
						counts = getWeekNumByYear(c.get(Calendar.YEAR))
								- weekofYear + weekofYear1 + 1;
					}

				} else if (difyear > 1) {// 年份相差大于一年时，要考虑到
					for (int i = 0; i <= difyear; i++) {
						int startAllWeeks = getWeekNumByYear(c
								.get(Calendar.YEAR)
								+ i); // 开始日期的年份+i，如2007-12-22到2011-01-09，这里是遍历出07年，08年，09年分别有多少个周

						if (i == 0) {// 开始日期这一年的周差，如果07-12-22是第51周，07年共有52个周，那么，这里得到1.
							counts = startAllWeeks - weekofYear;
						} else if (i < difyear) {// 除开始日期与结束日期所在一年的年份的周差。也就是08年共有多少周
							counts += startAllWeeks;
						} else if (i == difyear) { // 加上09年所在的周
							counts += weekofYear1;
						}

					}
					counts += 1;
				}

			}
			// }

		} catch (Exception  e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

		return counts;
	}   
	
	/**
	* 得到某年某周的第一天
	* 
	* @param year 
	* @param week
	* @return
	*/
	public static Date getFirstDayOfWeek(int year, int week) {
	Calendar c = new GregorianCalendar();
	c.set(Calendar.YEAR, year);
	c.set (Calendar.MONTH, Calendar.JANUARY);
	c.set(Calendar.DATE, 1);

	Calendar cal = (GregorianCalendar) c.clone();
	cal.add(Calendar.DATE, week * 7);

	return getFirstDayOfWeek(cal.getTime ());
	}

	/**
	* 得到某年某周的最后一天
	* 
	* @param year
	* @param week
	* @return
	*/
	public static Date getLastDayOfWeek(int year, int week) {
	Calendar c = new GregorianCalendar(); 
	c.set(Calendar.YEAR, year);
	c.set(Calendar.MONTH, Calendar.JANUARY);
	c.set(Calendar.DATE, 1);

	Calendar cal = (GregorianCalendar) c.clone();
	cal.add(Calendar.DATE , week * 7);

	return getLastDayOfWeek(cal.getTime());
	}
	/**
	* 取得当前日期所在周的第一天
	* 
	* @param date
	* @return
	*/
	public static Date getFirstDayOfWeek(Date date) { 
	Calendar c = new GregorianCalendar();
	c.setFirstDayOfWeek(Calendar.MONDAY);
	c.setTime(date);
	c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
	return c.getTime ();
	}
	
	
	
	public static boolean isSameWeek(String date1,String date2){
		Date parse1 = parse(date1, "yyyy年MM月dd日");
		Date parse2 = parse(date2, "yyyy年MM月dd日");
		if(getFirstDayOfWeek(parse1).compareTo(getFirstDayOfWeek(parse2))==0){
			return true;
		}
		return false;
	}
	
	
	

	/**
	* 取得当前日期所在周的最后一天
	* 
	* @param date
	* @return
	*/
	public static Date getLastDayOfWeek(Date date) {
	Calendar c = new GregorianCalendar();
	c.setFirstDayOfWeek(Calendar.MONDAY);
	c.setTime(date);
	c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
	return c.getTime();
	}
   
	
	
	
	
   /**
    *  
    *  获取当前日期是第几周
    * @param date
    * @return 
    * @author wangjing
    * Oct 9, 2011  10:48:58 AM
    */
   @SuppressWarnings("static-access")
   public static int getWeekByDate(Date date){
	   Calendar c = new GregorianCalendar();
	   c.setFirstDayOfWeek(Calendar.MONDAY);
	   c.setTime(date);
	   return c.WEEK_OF_YEAR;
   } 

   
   private static int weeks = 0;// 用来全局控制 上一周，本周，下一周的周数变化   
   private static int MaxDate; // 一月最大天数   
   private static int MaxYear; // 一年最大天数   
   /**      
    *  获得上周星期一的日期      
    *  @return      
    */    
	public static Date getPreviousWeekday() {
		weeks--;
		int mondayPlus = getMondayPlus();
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DATE, mondayPlus + 7 * weeks);		
		return currentDate.getTime();
	} 
	 /**      * 获得上周星期日的日期      *        * @return      */     
	public static Date getPreviousWeekSunday() {
		weeks = 0;
		weeks--;
		int mondayPlus =  getMondayPlus();
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DATE, mondayPlus + weeks);		
		return currentDate.getTime();
	}
	
	
	 /** 
     * 上月第一天 
     *   
     * @return 
     */ 
    public static Date getPreviousMonthFirst() {
        Calendar lastDate = Calendar.getInstance();   
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号   
        lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号   
        // lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天       
        return lastDate.getTime();   
    }   
    
    
    /*** 获得上月最后一天的日期      *        * @return      */     
    public static Date getPreviousMonthEnd() {                     
    	Calendar lastDate = Calendar.getInstance();           
    	lastDate.add(Calendar.MONTH, -1);// 减一个月           
    	lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天           
    	lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天               	           
    	return lastDate.getTime();       
    }
    
    
	/**
	 * 获得上年第一天的日期 *       
	 * @return
	 */
    public static Date getPreviousYearFirst() {           
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.YEAR, -1);
    	calendar.set(Calendar.MONTH, 0);
    	calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}
    
    /**
     *  获得上年最后一天的日期      
     * @return
     */
    public static Date getPreviousYearEnd() {    	
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.YEAR, -1);
		currentDate.set(GregorianCalendar.MONTH, 12);
		currentDate.set(GregorianCalendar.DATE, 1);
		currentDate.add(GregorianCalendar.DATE, -1);
		return currentDate.getTime();
	}

    /**
     * 取上季度的第一天
     */
     public static Date getLastSeasonStart() {
 	    Calendar lastDate = Calendar.getInstance();
 	    int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
 	    int season = 1;
 	    int month = lastDate.get(Calendar.MONTH) + 1; //求当前的月份
 	    if (month >= 1 && month <= 3) {
 	    	season = 1;
 	    }
 	    if (month >= 4 && month <= 6) {
 	    	season = 2;
 	    }
 	    if (month >= 7 && month <= 9) {
 	    	season = 3;
 	    }
 	    if (month >= 10 && month <= 12) {
 	    	season = 4;
 	    }
 	    int end_month = array[season - 1][0];
 	    int years_value ;
	 	
	    int end_days = 1;
 	    String seasonDate = "";
 	    Calendar cl = Calendar.getInstance();
 	    if (end_month > 3) {
	 	    end_month = end_month - 3;
	 	    Date date = new Date();
	 	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
	 	    String years = dateFormat.format(date);
	 	    years_value = Integer.parseInt(years);
 	    } else {
	 	    end_month = end_month + 9;
	 	    Date date = new Date();
	 	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
	 	   
	 	    cl.setTime(date);
	 	    cl.add(Calendar.YEAR, -1);	 	
	 	    String years = dateFormat.format(cl.getTime());
	 	    years_value= Integer.parseInt(years);
	 	   
 	    }
 	    seasonDate = years_value + "-" + end_month + "-" + end_days;
	    cl.set(years_value, end_month-1, end_days);
	    return  cl.getTime();
     }
    
    
    /**
    * 取上季度的最后一天
    */
    public static Date getLastSeasonEnd() {
	    Calendar lastDate = Calendar.getInstance();
	    int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
	    int season = 1;
	    int month = lastDate.get(Calendar.MONTH) + 1; //求当前的月份
	    if (month >= 1 && month <= 3) {
	    	season = 1;
	    }
	    if (month >= 4 && month <= 6) {
	    	season = 2;
	    }
	    if (month >= 7 && month <= 9) {
	    	season = 3;
	    }
	    if (month >= 10 && month <= 12) {
	    	season = 4;
	    }
	    int end_month = array[season - 1][2];
	    int years_value;		
	    int end_days;
	    String seasonDate = "";
	    Calendar cl = Calendar.getInstance();
	    if (end_month > 3) {
		    end_month = end_month - 3;
		    Date date = new Date();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		    String years = dateFormat.format(date);
		    years_value = Integer.parseInt(years);
		
		    end_days = getLastDayOfMonth(years_value, end_month);
	    } else {
		    end_month = end_month + 9;
		    Date date = new Date();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		    
		    cl.setTime(date);
		    cl.add(Calendar.YEAR, -1);
		
		    String years = dateFormat.format(cl.getTime());
		    years_value = Integer.parseInt(years);
		
		    end_days = getLastDayOfMonth(years_value, end_month);
		    
	    }
	    seasonDate = years_value + "-" + end_month + "-" + end_days;
	    System.err.println(seasonDate);
	    cl.set(years_value, end_month-1, end_days-1);
	    return cl.getTime();
    }
    
    
    /**
     * 取上半年的第一天
     */
     public static Date getLastHalfYearStart() {
 	    Calendar lastDate = Calendar.getInstance();
 	    int half = 1;
 	    int month = lastDate.get(Calendar.MONTH) + 1; //求当前的月份
 	    int years_value ;
 	    int end_month = 0;
 	    Calendar cl = Calendar.getInstance();
		try {
			
			years_value = cl.get(Calendar.YEAR);
			
			if (month >= 1 && month <= 6) {
				half = 2;
				--years_value ;
				end_month = 7;
			}
			if (month >= 7 && month <= 12) {
				half = 1;
				end_month = 1;
			}
			
				 	
			cl.set(years_value, end_month-1, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return  cl.getTime();
     }
     /**
      * 取上半年的最后一天
      */
      public static Date getLastHalfYearEnd() {
    	  Calendar lastDate = Calendar.getInstance();
   	    int half = 1;
   	    int month = lastDate.get(Calendar.MONTH) + 1; //求当前的月份
   	    int years_value ;
   	    Calendar cl = Calendar.getInstance();
   	    years_value = cl.get(Calendar.YEAR);
   	    int end_month = 0;
   	    if (month >= 1 && month <= 6) {
   	    	half = 2;
   	    	--years_value ;
   	    	end_month = 12;
   	    }
   	    if (month >= 7 && month <= 12) {
   	    	half = 1;
   	    	end_month = 6;
   	    }	    
   	    cl.set(Calendar.YEAR, years_value);
   	    cl.set(Calendar.MONTH, end_month);
   	    cl.set(Calendar.DATE, 1);
  	    //cl.set(years_value, end_month, 1);
  	    cl.add(Calendar.DATE, -1);
  	    return  cl.getTime();
      }
    
    
    private static int getLastDayOfMonth(int years_values,int end_month){
        Calendar lastDate = Calendar.getInstance();   
        lastDate.set(years_values,end_month, 1);// 设为当前月的1号   
        lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号   
        lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天           
        return lastDate.get(Calendar.DATE);
    }
    
    private static int getYearPlus() {           
    	Calendar cd = Calendar.getInstance();           
	    int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 获得当天是一年中的第几天           
	    cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天           
	    cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。           
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		if (yearOfNumber == 1) {
			return -MaxYear;
		} else {
			return 1 - yearOfNumber;
		}
	}
    
	private static int getMonthPlus() {           
		Calendar cd = Calendar.getInstance();           
		int monthOfNumber = cd.get(Calendar.DAY_OF_MONTH);           
		cd.set(Calendar.DATE, 1);// 把日期设置为当月第一天          
		cd.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天         
		MaxDate = cd.get(Calendar.DATE);           
		if (monthOfNumber == 1) {               
			return -MaxDate;           
		} else {               
			return 1 - monthOfNumber;           
		}       
	}
	
	/** 
     * 获得当前日期与本周日相差的天数 
     *   
     * @return 
     */ 
    private static int getMondayPlus() {   
        Calendar cd = Calendar.getInstance();   
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......   
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1   
        if (dayOfWeek == 1) {   
            return 0;   
        } else {   
            return 1 - dayOfWeek;   
        }   
    }   
  
    public static void main(String[] args) {
        /*String test = "2011-12-14";
        Date date;*/
        try {
        	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");        	
        	System.out.println("获取上周一日期:" + sDateFormat.format(getPreviousWeekday()));   
           	System.out.println("获取上周日日期:" + sDateFormat.format(getPreviousWeekSunday())); 
            System.out.println("获取上月第一天日期:" +  sDateFormat.format(getPreviousMonthFirst()));   
            System.out.println("获取上月最后一天的日期:" +  sDateFormat.format(getPreviousMonthEnd()));
            System.out.println("获取上季度第一天:" +  sDateFormat.format(getLastSeasonStart()));
            System.out.println("获取上季度最后一天:" +  sDateFormat.format(getLastSeasonEnd()));
            System.out.println("获取上半年第一天" + sDateFormat.format(getLastHalfYearStart()));
        	System.out.println("获取上半年最后一天" + sDateFormat.format(getLastHalfYearEnd()));
            System.out.println("获取去年的第一天日期:" +  sDateFormat.format(getPreviousYearFirst()));   
            System.out.println("获取去年的最后一天日期:" +  sDateFormat.format(getPreviousYearEnd()));     

        	
/*        	
            date = parse(test, "");

            System.out.println("得到当前日期 － getDate():" + DateTool.getDate());
            System.out.println("得到当前日期时间 － getDateTime():" + DateTool.getDateTime());

            System.out.println("得到当前年份 － getCurrentYear():" + DateTool.getCurrentYear());
            System.out.println("得到当前月份 － getCurrentMonth():" + DateTool.getCurrentMonth());
            System.out.println("得到当前日子 － getCurrentDay():" + DateTool.getCurrentDay());

            System.out.println("解析 － parse(" + test + "):" + getDateTime(date, "yyyy年MM月dd日"));

            System.out.println("自增月份 － addMonths(3):" + getDateTime(addMonths(3), "yyyy-MM-dd"));
            System.out.println("增加月份 － addMonths(" + test + ",3):"
                            + getDateTime(addMonths(date, 3), "yyyy-MM-dd"));
            System.out.println("增加日期 － addDays(" + test + ",3):"
                            + getDateTime(addDays(date, 3), "yyyy-MM-dd"));
            System.out.println("自增日期 － addDays(3):" + getDateTime(addDays(3), "yyyy-MM-dd"));

            System.out.println("比较日期 － diffDays():" + DateTool.diffDays(parse(getDate(), "yyyy-MM-dd"), date));
            System.out.println("比较月份 － diffMonths():"
                            + DateTool.diffMonths(DateTool.getNow(), date));
            System.out.println("比较年份 － diffMonths():"
                    + DateTool.diffYear("2010-06-20", "2009-08-01"));
            System.out.println("得到" + test + "所在月份的最后一天:"
                            + getDateTime(getMonthLastDay(date), "yyyy-MM-dd"));
            System.out.println("将一个字符串用给定的格式转换为日期类型。"
                    + parse("2012-3-1", "yyyy-MM-dd"));

            System.out.println(diffDays(parse("2011-10-09",""), parse("2011-10-08","")));
            System.out.println(getWeekOfDate(new Date()));
            

            System.out.println(getDateTime(new Date(),"E")+"-------------------------------------");
            
            System.out.println(getWeek());
            System.out.println(isMonthLastDay());
            

            Calendar cal = Calendar.getInstance();
            System.out.println("今天的日期: " + cal.getTime());

            //int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2;
            System.out.println("每周天数: " + Calendar.DAY_OF_WEEK);
            cal.add(Calendar.DATE, -6);
            System.out.println("本周第一天: " + cal.getTime()); 
            
            System.out.println("得到" + test + "所在月份的第一天:"
                    + getDateTime(getMonthFirstDay(new Date()), "yyyy-MM-dd"));
            System.out.println(parse("2011-10-09","").after(parse("2011-10-08","")));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            date = dateFormat.parse("2011年2月2日");
            System.out.println(date); 
            Date date1=parse("2012-01-01","yyyy-MM-dd");
            Date date2=parse("2012-01-11","yyyy-MM-dd");
            System.out.println(getFirstDayOfWeek(date1)+"{[[[["); 	
            System.out.println(weeksBetweenDays(date1,date2)+"{[[[["); 	
*/            
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
