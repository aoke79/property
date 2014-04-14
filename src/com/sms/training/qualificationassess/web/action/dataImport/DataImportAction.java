package com.sms.training.qualificationassess.web.action.dataImport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionContext;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.Message;
import com.sinoframe.common.util.DateTool;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualificationassess.bean.CopilotTurn;
import com.sms.training.qualificationassess.bean.DataImport;
import com.sms.training.qualificationassess.business.IDataImportBS;

/*
 * 页面跳转路径
 */
@Results({
    @Result(name="SUCCESS",location="/standard/ajaxDone.jsp"),
    @Result(name="list",location="/sms/training/qualificationassess/dataImport/dataImportList.jsp"),
    @Result(name="add",location="/sms/training/qualificationassess/dataImport/dataImportAdd.jsp"),
    @Result(name="toEdit",location="/sms/training/qualificationassess/dataImport/dataImportEdit.jsp")
})
public class DataImportAction extends BaseAction {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 4420302467252431248L;
    
    // 参数类型设置表的service接口
    private IDataImportBS dataImportBS;
    
    // 参数类型设置表实体Bean用来接收页面参数
    private DataImport dataImport = new DataImport();
    
    // 操作成功/失败提示信息
    private Message message = new Message();
    
    // 存储页面查询条件
    private String strQuery;
    
    //存放查询结果的List集
    private List<DataImport> lstDataImport = new ArrayList<DataImport>();
    
    // 当前模块名称
    private static String moduleName = "";
    
    // 存放查询对象的HashMap集
    private HashMap<String, String> query  = new HashMap<String, String>();
    
    //存放批量删除的ID
    private String ids;
    
    /**
     * 跳转至添加页面
     * @return "add"
     */
    public String toAdd() {
        return "add";
    }
    
    /**
     * 保存添加信息
     * @return
     */
    public String save() {
        try {
            
            // 创建时间
            dataImport.setCreatedate(DateTool.getNow());
            
            // 取得当前session中的登陆者名称
            CmUser cmUser =  (CmUser)ActionContext.getContext().getSession().get("user");
            dataImport.setOperate(cmUser.getLoginName());

            // 设置删除标志位默认为Y
            dataImport.setStatus("y");
            
            // 执行保存操作
            dataImportBS.save(dataImport);
            
            // 操作成功信息提示
            this.message = this.getSuccessMessage("添加成功", "dataImport", 
                    "closeCurrent", "data-import/data-import!search.do");
        } catch (Exception e) {
            
            // 操作失败信息提示
            this.message = this.getFailMessage("添加失败");
            e.printStackTrace();
        }
        return "SUCCESS";
    }
    
    /**
     * 查询
     * @return
     */
    public String search() {
        
        try {
            if (strQuery != null && !"".equals(strQuery)) {
                query = Util.toMap(strQuery);
            }
            
            //查询列表中的条数信息
            String countHql = "select count(*) from DataImport where 1=1 and status = 'Y' ";
            
            //设置最大条数
            this.getSysPageInfo()
                .setMaxCount(this.dataImportBS.getCountByHQL(countHql, Util.decodeQuery(query)));
            
            //列表
            String listHql = "from DataImport where 1=1 and status = 'Y' ";
            
            if(this.getSysOrderByInfo().getOrderAsc().equals("")
                    && this.getSysOrderByInfo().getOrderColumn() == null){
                this.getSysOrderByInfo().setIfDate(false);
                this.getSysOrderByInfo().setOrderAsc("asc");
                this.getSysOrderByInfo().setOrderColumn("name");
            }
            
            lstDataImport = dataImportBS.findPageByQuery(this.getSysPageInfo(),
                            listHql, Util.decodeQuery(query), this.getSysOrderByInfo());
        } catch (Exception e) {
            e.printStackTrace();
            this.getSysPageInfo().setCurrentPage(1);
            
            //设置日志信息
            dataImportBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
        }
        return "list";
    }
    
    /**
     * 跳转至编辑页面
     * @return
     */
    public String toEdit() {
        
        // 根据id查找对应记录
        dataImport = dataImportBS
            .findById(DataImport.class, dataImport.getId());
        return "toEdit";
    }
    
    /**
     * 编辑操作
     * @return
     */
    public String update() {
        try {
            
            // 修改日期
            dataImport.setModifydate(DateTool.getNow());
            
            // 取得当前session中的登陆者名称
            CmUser cmUser =  (CmUser)ActionContext.getContext().getSession().get("user");
            dataImport.setOperate(cmUser.getLoginName());
            
            // 更新操作
            dataImportBS.saveOrUpdate(dataImport);
            
            // 设定成功消息
            this.message = this.getSuccessMessage(getText("updateSuccess"),
                    "systemManage", "closeCurrent", "data-import/data-import!search.do");
        } catch (Exception e) {
            
            // 设置日志信息
            dataImportBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
            
            // 设定失败消息
            this.message = this.getFailMessage(getText("updateFail"));
            e.printStackTrace();
        }
        return "SUCCESS";
    }
    
    /**
     * 删除一条记录
     * @return
     */
    public String delete() {
        try {
            
            // 根据id删除一条记录（逻辑删除）
            dataImport = dataImportBS.findById(DataImport.class, dataImport.getId());
            dataImport.setStatus("N");
            dataImportBS.saveOrUpdate(dataImport);
            
            //设定成功消息，判断是否记录了排序需要的条件
            if(this.getSysOrderByInfo().getOrderColumn() != null && !this.getSysOrderByInfo().getOrderColumn().equals("")){
                this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
                                    "forward", "data-import/data-import!search.do?orderBlock=" + this.getSysOrderByInfo().getOrderColumn() + 
                                    "&orderMethod=" + this.getSysOrderByInfo().getOrderAsc() + "&pageNum=" + this.getPageNum() +
                                    "&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
            } else {
                this.message = this.getSuccessMessage(getText("deleteSuccess"), "dataImport",
                        "forward", "data-import/data-import!search.do?pageNum=" + this.getPageNum() +
                        "&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
            }
        } catch (Exception e) {
            
            //设置日志信息
            dataImportBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
            
            //设定失败消息
            this.message = this.getFailMessage(getText("deleteFail"));
            e.printStackTrace();
        }
        return "SUCCESS";
    }
    
    /**
     * 删除多条记录
     * @return
     */
    public String multipleDelete() {
        try {
            
            if(null != this.getIds()&&!"".equals(this.getIds())) {
                
                // 批量删除
                String[] strIds = this.getIds().split(",");
                for(String id :strIds) {
                    dataImport = dataImportBS.findById(DataImport.class, id);
                    dataImport.setStatus("N");
                    dataImportBS.saveOrUpdate(dataImport);
                }
            }
            //设定成功消息，判断是否记录了排序需要的条件
            if(this.getSysOrderByInfo().getOrderColumn() != null && !this.getSysOrderByInfo().getOrderColumn().equals("")){
                this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
                                    "forward", "data-import/data-import!search.do?orderBlock=" + this.getSysOrderByInfo().getOrderColumn() + 
                                    "&orderMethod=" + this.getSysOrderByInfo().getOrderAsc() + "&pageNum=" + this.getPageNum() +
                                    "&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
            } else {
                this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
                        "forward", "data-import/data-import!search.do?pageNum=" + this.getPageNum() +
                        "&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
            }
        } catch (Exception e) {
            
            //设置日志信息
            dataImportBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
            
            //设定失败消息
            this.message = this.getFailMessage(getText("deleteFail"));
            e.printStackTrace();
        }
        return "SUCCESS";
    }

    /*******************************自动生成***********************************/
    public IDataImportBS getDataImportBS() {
        return dataImportBS;
    }

    public void setDataImportBS(IDataImportBS dataImportBS) {
        this.dataImportBS = dataImportBS;
    }

    public DataImport getDataImport() {
        return dataImport;
    }

    public void setDataImport(DataImport dataImport) {
        this.dataImport = dataImport;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getStrQuery() {
        return strQuery;
    }

    public void setStrQuery(String strQuery) {
        this.strQuery = strQuery;
    }

    public List<DataImport> getLstDataImport() {
        return lstDataImport;
    }

    public void setLstDataImport(List<DataImport> lstDataImport) {
        this.lstDataImport = lstDataImport;
    }

    public HashMap<String, String> getQuery() {
        return query;
    }

    public void setQuery(HashMap<String, String> query) {
        this.query = query;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
