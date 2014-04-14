package com.sinoframe.common.paging;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

public class HPagination3 extends PagingUtil{
	public static HPagination3 getInstance(Paging paging)
    {
        if(instance == null)
            instance = new HPagination3();
        instance.setCache(isCache);
        instance.setPaging(paging);
        return instance;
    }

    private HPagination3()
    {
    }

    public void query(String sSql, Session sess)
    {
    	System.out.println(isCache+"=================");
        if(!paging.isSelectAll())
        {
            String sCnt = generateSQLCountString(sSql);
            
            System.out.println(sCnt+"------------------");
            if(sCnt.indexOf(" group by ") == -1)
                paging.setMaxCount(Integer.parseInt(sess.createQuery(sCnt).iterate().next().toString()));
            else
                paging.setMaxCount(sess.createQuery(sCnt).list().size());
        }
        Query query = sess.createQuery(sSql);
        
        if(!paging.isSelectAll())
        {
            query.setFirstResult(getFirstResultNum());
            query.setMaxResults(paging.getPageSize());
        }
        System.out.println(query.getQueryString()+"#######################");
        query.setCacheable(isCache);
        paging.setList(query.list());
    }

    public void query(String sSql, Object para, Type type, Session sess)
    {
    	System.out.println(isCache+"=================");
        if(para == null)
        {
            query(sSql, sess);
            return;
        }
        if(!paging.isSelectAll())
        {
            String sCnt = generateSQLCountString(sSql);
            if(sCnt.indexOf(" group by ") == -1)
                paging.setMaxCount(Integer.parseInt(sess.createQuery(sCnt).setParameter(0, para, type).iterate().next().toString()));
            else
                paging.setMaxCount(sess.createQuery(sCnt).setParameter(0, para, type).list().size());
        }
        Query query = sess.createQuery(sSql);
        query.setParameter(0, para, type);
       
        if(!paging.isSelectAll())
        {
            query.setFirstResult(getFirstResultNum());
            query.setMaxResults(paging.getPageSize());
        }
        query.setCacheable(isCache);
        paging.setList(query.list());
    }

    public void query(String sSql, Object para[], Type type[], Session sess)
    {
    	System.out.println(isCache+"=================");
        if(para == null)
        {
            query(sSql, sess);
            return;
        }
        if(!paging.isSelectAll())
        {
            String sCnt = generateSQLCountString(sSql);
            if(sCnt.indexOf(" group by ") == -1)
                paging.setMaxCount(Integer.parseInt(sess.createQuery(sCnt).setParameters(para, type).iterate().next().toString()));
            else
                paging.setMaxCount(sess.createQuery(sCnt).setParameters(para, type).list().size());
        }
        Query query = sess.createQuery(sSql);
        query.setParameters(para, type);
        if(!paging.isSelectAll())
        {
            query.setFirstResult(getFirstResultNum());
            query.setMaxResults(paging.getPageSize());
        }
        query.setCacheable(isCache);
        paging.setList(query.list());
    }

    private static HPagination3 instance = null;
    private static boolean isCache=false;
	public static  boolean isCache() {
		return isCache;
	}

	public void setCache(boolean isCache) {
		System.err.println(isCache+"*******************");
		this.isCache = isCache;
	}

}
