package com.sms.training.qualificationassess.web.action.argumentType;

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
import com.sms.training.qualificationassess.bean.ArgumentType;
import com.sms.training.qualificationassess.business.IArgumentTypeBS;

/*
 * 页面跳转路径
 */
@Results({
    @Result(name="SUCCESS",location="/standard/ajaxDone.jsp"),
    @Result(name="list",location="/sms/training/qualificationassess/argumenttype/argumenttypeList.jsp"),
    @Result(name="add",location="/sms/training/qualificationassess/argumenttype/argumenttypeAdd.jsp"),
    @Result(name="toEdit",location="/sms/training/qualificationassess/argumenttype/argumenttypeEdit.jsp")
})
public class ArgumentTypeAction extends BaseAction {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 4420302467252431248L;
    
    // 参数类型设置表的service接口
    private IArgumentTypeBS argumentTypeBS;
    
    // 参数类型设置表实体Bean用来接收页面参数
    private ArgumentType argumentType = new ArgumentType();
    
    // 操作成功/失败提示信息
    private Message message = new Message();
    
    // 存储页面查询条件
    private String strQuery;
    
    //存放查询结果的List集
    private List<ArgumentType> lstArgumentType = new ArrayList<ArgumentType>();
    
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
            argumentType.setCreatedate(DateTool.getNow());
            
            // 设置删除标志位默认为Y
            argumentType.setStatus("y");
            
            // 取得当前session中的登陆者名称
            CmUser cmUser =  (CmUser)ActionContext.getContext().getSession().get("user");
            argumentType.setOperate(cmUser.getLoginName());
            
            // 执行保存操作
            argumentTypeBS.save(argumentType);
            
            // 操作成功信息提示
            this.message = this.getSuccessMessage("添加成功", "argumentType", 
                    "closeCurrent", "argument-type/argument-type!search.do");
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
            String countHql = "select count(*) from ArgumentType where 1=1 and status = 'Y' ";
            
            //设置最大条数
            this.getSysPageInfo()
                .setMaxCount(this.argumentTypeBS.getCountByHQL(countHql, Util.decodeQuery(query)));
            
            //列表
            String listHql = "from ArgumentType where 1=1 and status = 'Y' ";
            
            if(this.getSysOrderByInfo().getOrderAsc().equals("")
                    && this.getSysOrderByInfo().getOrderColumn() == null){
                this.getSysOrderByInfo().setIfDate(false);
                this.getSysOrderByInfo().setOrderAsc("asc");
                this.getSysOrderByInfo().setOrderColumn("name");
            }
            
            // 查询结果集
            lstArgumentType = argumentTypeBS.findPageByQuery(this.getSysPageInfo(),
                            listHql, Util.decodeQuery(query), this.getSysOrderByInfo());
        } catch (Exception e) {
            e.printStackTrace();
            this.getSysPageInfo().setCurrentPage(1);
            
            //设置日志信息
            argumentTypeBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
        }
        return "list";
    }
    
    /**
     * 跳转至编辑页面
     * @return
     */
    public String toEdit() {
        
        // 根据id查找对应记录
        argumentType = argumentTypeBS
            .findById(ArgumentType.class, argumentType.getArgumenttypeid());
        return "toEdit";
    }
    
    /**
     * 编辑操作
     * @return
     */
    public String update() {
        try {
            
            // 修改日期
            argumentType.setModifydate(DateTool.getNow());
            
            // 取得当前session中的登陆者名称
            CmUser cmUser =  (CmUser)ActionContext.getContext().getSession().get("user");
            argumentType.setOperate(cmUser.getLoginName());
            
            // 更新操作
            argumentTypeBS.saveOrUpdate(argumentType);
            
            // 设定成功消息
            this.message = this.getSuccessMessage(getText("updateSuccess"),
                    "systemManage", "closeCurrent", "argument-type/argument-type!search.do");
        } catch (Exception e) {
            
            // 设置日志信息
            argumentTypeBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
            
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
            argumentType = argumentTypeBS.findById(ArgumentType.class, argumentType.getArgumenttypeid());
            argumentType.setStatus("N");
            argumentTypeBS.saveOrUpdate(argumentType);
            
            //设定成功消息，判断是否记录了排序需要的条件
            if(this.getSysOrderByInfo().getOrderColumn() != null && !this.getSysOrderByInfo().getOrderColumn().equals("")){
                this.message = this.getSuccessMessage(getText("deleteSuccess"), "argumentType",
                                    "forward", "argument-type/argument-type!search.do?orderBlock=" + this.getSysOrderByInfo().getOrderColumn() + 
                                    "&orderMethod=" + this.getSysOrderByInfo().getOrderAsc() + "&pageNum=" + this.getPageNum() +
                                    "&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
            } else {
                this.message = this.getSuccessMessage(getText("deleteSuccess"), "argumentType",
                        "forward", "argument-type/argument-type!search.do?pageNum=" + this.getPageNum() +
                        "&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
            }
        } catch (Exception e) {
            
            //设置日志信息
            argumentTypeBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
            
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
                    argumentType = argumentTypeBS.findById(ArgumentType.class, id);
                    argumentType.setStatus("N");
                    argumentTypeBS.saveOrUpdate(argumentType);
                }
            }
            
            //设定成功消息，判断是否记录了排序需要的条件
            if(this.getSysOrderByInfo().getOrderColumn() != null && !this.getSysOrderByInfo().getOrderColumn().equals("")){
                this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
                                    "forward", "argument-type/argument-type!search.do?orderBlock=" + this.getSysOrderByInfo().getOrderColumn() + 
                                    "&orderMethod=" + this.getSysOrderByInfo().getOrderAsc() + "&pageNum=" + this.getPageNum() +
                                    "&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
            } else {
                this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
                        "forward", "argument-type/argument-type!search.do?pageNum=" + this.getPageNum() +
                        "&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
            }
        } catch (Exception e) {
            
            //设置日志信息
            argumentTypeBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
            
            //设定失败消息
            this.message = this.getFailMessage(getText("deleteFail"));
            e.printStackTrace();
        }
        return "SUCCESS";
    }
    
    /*******************************自动生成***********************************/
    public IArgumentTypeBS getArgumentTypeBS() {
        return argumentTypeBS;
    }

    
    public void setArgumentTypeBS(IArgumentTypeBS argumentTypeBS) {
        this.argumentTypeBS = argumentTypeBS;
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

    public List<ArgumentType> getLstArgumentType() {
        return lstArgumentType;
    }

    public void setLstArgumentType(List<ArgumentType> lstArgumentType) {
        this.lstArgumentType = lstArgumentType;
    }

    public ArgumentType getArgumentType() {
        return argumentType;
    }

    public void setArgumentType(ArgumentType argumentType) {
        this.argumentType = argumentType;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public static String getModuleName() {
        return moduleName;
    }

    public static void setModuleName(String moduleName) {
        ArgumentTypeAction.moduleName = moduleName;
    }

    public HashMap<String, String> getQuery() {
        return query;
    }

    public void setQuery(HashMap<String, String> query) {
        this.query = query;
    }
}
