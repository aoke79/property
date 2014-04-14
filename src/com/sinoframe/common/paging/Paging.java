package com.sinoframe.common.paging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Paging implements Serializable {
	 public Paging()
	    {
	        isSelectAll = false;
	        pageSize = -1;
	        maxCount = -1;
	        curPage = 1;
	        maxPage = -1;
	        lineCount = 0;
	    }

	    public Paging(int pageSize)
	    {
	        isSelectAll = false;
	        this.pageSize = -1;
	        maxCount = -1;
	        curPage = 1;
	        maxPage = -1;
	        lineCount = 0;
	        setPageSize(pageSize);
	    }

	    public Paging(int pageSize, int curPage)
	    {
	        isSelectAll = false;
	        this.pageSize = -1;
	        maxCount = -1;
	        this.curPage = 1;
	        maxPage = -1;
	        lineCount = 0;
	        setPageSize(pageSize);
	        setCurPage(curPage);
	    }

	    void setMaxCount(int maxCount)
	    {
	        if(isSelectAll)
	            return;
	        this.maxCount = maxCount;
	        maxPage = maxCount != 0 ? (maxCount - 1) / pageSize + 1 : 1;
	        if(curPage > maxPage)
	            curPage = maxPage;
	    }

	    public int getPriorPage()
	    {
	        return curPage > 1 ? curPage - 1 : 1;
	    }

	    public int getNextPage()
	    {
	        return curPage < maxPage ? curPage + 1 : maxPage;
	    }

	    public boolean isEmpty()
	    {
	        boolean bool = true;
	        if(maxPage != 0)
	            bool = false;
	        return bool;
	    }

	    public int getFirstPage()
	    {
	        return 1;
	    }

	    public int getLastPage()
	    {
	        return maxPage;
	    }

	    public List getList()
	    {
	        if(list == null)
	            list = new ArrayList();
	        return list;
	    }

	    public int getPageSize()
	    {
	        return pageSize;
	    }

	    public int getCurPage()
	    {
	        return curPage;
	    }

	    public int getMaxCount()
	    {
	        return maxCount;
	    }

	    public int getMaxPage()
	    {
	        return maxPage;
	    }

	    public boolean isFirstPage()
	    {
	        return curPage == 1;
	    }

	    public boolean isLastPage()
	    {
	        return curPage == maxPage;
	    }

	    public void setCurPage(int curPage)
	    {
	        if(isSelectAll)
	        {
	            return;
	        } else
	        {
	            this.curPage = curPage <= 0 ? 1 : curPage;
	            return;
	        }
	    }

	    public void setPageSize(int pageSize)
	    {
	        if(isSelectAll)
	        {
	            return;
	        } else
	        {
	            this.pageSize = pageSize <= 0 ? 1 : pageSize;
	            return;
	        }
	    }

	    public void setCurPage(Integer curPage)
	    {
	        if(isSelectAll)
	        {
	            return;
	        } else
	        {
	            setCurPage(curPage != null ? curPage.intValue() : 1);
	            return;
	        }
	    }

	    public void setPageSize(Integer pageSize)
	    {
	        if(isSelectAll)
	        {
	            return;
	        } else
	        {
	            setPageSize(pageSize != null ? pageSize.intValue() : 1);
	            return;
	        }
	    }

	    public int getLineCount()
	    {
	        return pageSize - list.size();
	    }

	    public void setList(List list)
	    {
	        this.list = list;
	        if(isSelectAll)
	        {
	            maxCount = list == null ? 0 : list.size();
	            maxPage = 1;
	        } else
	        {
	            lineCount = pageSize - list.size();
	        }
	    }

	    public final boolean isSelectAll()
	    {
	        return isSelectAll;
	    }

	    public final void setSelectAll(boolean isSelectAll)
	    {
	        this.isSelectAll = isSelectAll;
	        if(this.isSelectAll)
	        {
	            pageSize = -1;
	            curPage = 1;
	        }
	    }

	    private static final Log logger;
	    private boolean isSelectAll;
	    //每页显示条数
	    private int pageSize;
	    //总条数
	    private int maxCount;
	    //当前页码
	    private int curPage;
	    //总共多少页
	    private int maxPage;
	    private List list;
	    private int lineCount;

	    static 
	    {
	        logger = LogFactory.getLog(com.comm.web.Paging.class);
	    }
}
