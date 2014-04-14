package com.sinoframe.common.paging;

public abstract class PagingUtil {
	public PagingUtil()
    {
    }

    public void setDistinctColumn(String distinctColumn)
    {
        this.distinctColumn = distinctColumn;
    }

    public void setPaging(Paging paging)
    {
        this.paging = paging;
        distinctColumn = null;
    }

    String generateSQLCountString(String sSql)
    {
        StringBuffer sbSqlCnt = new StringBuffer();
        int formIndex = getFormIndex(sSql);
        int orderIndex = sSql.indexOf("order", formIndex);
        if(countSelectSql == null && distinctColumn == null)
            sbSqlCnt.append("select count(*)");
        else
        if(distinctColumn != null && !"".equals(distinctColumn))
            sbSqlCnt.append("select count(distinct " + distinctColumn + ")");
        else
        if(countSelectSql != null)
            sbSqlCnt.append("select " + countSelectSql);
        else
            sbSqlCnt.append("select count(*)");
        if(orderIndex == -1)
            sbSqlCnt.append(sSql.substring(formIndex));
        else
            sbSqlCnt.append(sSql.substring(formIndex, orderIndex));
        return sbSqlCnt.toString();
    }

    int getFirstResultNum()
    {
        return (paging.getCurPage() - 1) * paging.getPageSize();
    }

    public static void main(String args[])
    {
        String a = "select a (select a from b ) from c";
    }

    public static int getFormIndex(String sql)
    {
        int formNum = 0;
        for(int i = 0; i < sql.length(); i++)
        {
            int formIndex = sql.indexOf("from", i);
            if(formIndex == -1)
                return -1;
            formNum++;
            i = formIndex;
            int selectNum = sql.substring(0, formIndex).split("select").length - 1;
            if(selectNum == 0 && formNum == 1 || selectNum == formNum)
                return formIndex;
        }

        return -1;
    }

    protected Paging paging;
    private String distinctColumn;
    private String countSelectSql;
}
