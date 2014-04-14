package com.sms.training.qualificationassess.web.action.copilotTurn;

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
import com.sms.training.qualificationassess.business.ICopilotTurnBS;

/*
 * 页面跳转路径
 */
@Results({
    @Result(name="SUCCESS",location="/standard/ajaxDone.jsp"),
    @Result(name="list",location="/sms/training/qualificationassess/copilotturn/copilotturnList.jsp"),
    @Result(name="add",location="/sms/training/qualificationassess/copilotturn/copilotturnAdd.jsp"),
    @Result(name="toEdit",location="/sms/training/qualificationassess/copilotturn/copilotturnEdit.jsp")
})
public class CopilotTurnAction extends BaseAction {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 4420302467252431248L;
    
    // 参数类型设置表的service接口
    private ICopilotTurnBS copilotturnBS;
    
    // 参数类型设置表实体Bean用来接收页面参数
    private CopilotTurn copilotturn = new CopilotTurn();
    
    // 操作成功/失败提示信息
    private Message message = new Message();
    
    // 存储页面查询条件
    private String strQuery;
    
    //存放查询结果的List集
    private List<CopilotTurn> lstCopilotturn = new ArrayList<CopilotTurn>();
    
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
            copilotturn.setCreatedate(DateTool.getNow());

            // 取得当前session中的登陆者名称
            CmUser cmUser =  (CmUser)ActionContext.getContext().getSession().get("user");
            copilotturn.setOperate(cmUser.getLoginName());

            
            // 设置删除标志位默认为Y
            copilotturn.setStatus("y");
            
            // 执行保存操作
            copilotturnBS.save(copilotturn);
            
            // 操作成功信息提示
            this.message = this.getSuccessMessage("添加成功", "copilotturn", 
                    "closeCurrent", "copilot-turn/copilot-turn!search.do");
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
            String countHql = "select count(*) from CopilotTurn where 1=1 and status = 'Y' ";
            
            //设置最大条数
            this.getSysPageInfo()
                .setMaxCount(this.copilotturnBS.getCountByHQL(countHql, Util.decodeQuery(query)));
            
            //列表
            String listHql = "from CopilotTurn where 1=1 and status = 'Y' ";
            
            if(this.getSysOrderByInfo().getOrderAsc().equals("")
                    && this.getSysOrderByInfo().getOrderColumn() == null){
                this.getSysOrderByInfo().setIfDate(false);
                this.getSysOrderByInfo().setOrderAsc("asc");
                this.getSysOrderByInfo().setOrderColumn("name");
            }
            
            lstCopilotturn = copilotturnBS.findPageByQuery(this.getSysPageInfo(),
                            listHql, Util.decodeQuery(query), this.getSysOrderByInfo());
        } catch (Exception e) {
            e.printStackTrace();
            this.getSysPageInfo().setCurrentPage(1);
            
            //设置日志信息
            copilotturnBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
        }
        return "list";
    }
    
    /**
     * 跳转至编辑页面
     * @return
     */
    public String toEdit() {
        
        // 根据id查找对应记录
        copilotturn = copilotturnBS
            .findById(CopilotTurn.class, copilotturn.getId());
        return "toEdit";
    }
    
    /**
     * 编辑操作
     * @return
     */
    public String update() {
        try {
            
            // 修改日期
            copilotturn.setModifydate(DateTool.getNow());

            // 取得当前session中的登陆者名称
            CmUser cmUser =  (CmUser)ActionContext.getContext().getSession().get("user");
            copilotturn.setOperate(cmUser.getLoginName());
            
            // 更新操作
            copilotturnBS.saveOrUpdate(copilotturn);
            
            // 设定成功消息
            this.message = this.getSuccessMessage(getText("updateSuccess"),
                    "systemManage", "closeCurrent", "copilot-turn/copilot-turn!search.do");
        } catch (Exception e) {
            
            // 设置日志信息
            copilotturnBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
            
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
            copilotturn = copilotturnBS.findById(CopilotTurn.class, copilotturn.getId());
            copilotturn.setStatus("N");
            copilotturnBS.saveOrUpdate(copilotturn);
            
            //设定成功消息，判断是否记录了排序需要的条件
            if(this.getSysOrderByInfo().getOrderColumn() != null && !this.getSysOrderByInfo().getOrderColumn().equals("")){
                this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
                                    "forward", "copilot-turn/copilot-turn!search.do?orderBlock=" + this.getSysOrderByInfo().getOrderColumn() + 
                                    "&orderMethod=" + this.getSysOrderByInfo().getOrderAsc() + "&pageNum=" + this.getPageNum() +
                                    "&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
            } else {
                this.message = this.getSuccessMessage(getText("deleteSuccess"), "copilotturn",
                        "forward", "copilot-turn/copilot-turn!search.do?pageNum=" + this.getPageNum() +
                        "&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
            }
        } catch (Exception e) {
            
            //设置日志信息
            copilotturnBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
            
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
                    copilotturn = copilotturnBS.findById(CopilotTurn.class, id);
                    copilotturn.setStatus("N");
                    copilotturnBS.saveOrUpdate(copilotturn);
                }
            }
            //设定成功消息，判断是否记录了排序需要的条件
            if(this.getSysOrderByInfo().getOrderColumn() != null && !this.getSysOrderByInfo().getOrderColumn().equals("")){
                this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
                                    "forward", "copilot-turn/copilot-turn!search.do?orderBlock=" + this.getSysOrderByInfo().getOrderColumn() + 
                                    "&orderMethod=" + this.getSysOrderByInfo().getOrderAsc() + "&pageNum=" + this.getPageNum() +
                                    "&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
            } else {
                this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
                        "forward", "copilot-turn/copilot-turn!search.do?pageNum=" + this.getPageNum() +
                        "&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
            }
        } catch (Exception e) {
            
            //设置日志信息
            copilotturnBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
            
            //设定失败消息
            this.message = this.getFailMessage(getText("deleteFail"));
            e.printStackTrace();
        }
        return "SUCCESS";
    }

    /*******************************自动生成***********************************/
    public ICopilotTurnBS getCopilotturnBS() {
        return copilotturnBS;
    }

    public void setCopilotturnBS(ICopilotTurnBS copilotturnBS) {
        this.copilotturnBS = copilotturnBS;
    }

    public CopilotTurn getCopilotturn() {
        return copilotturn;
    }

    public void setCopilotturn(CopilotTurn copilotturn) {
        this.copilotturn = copilotturn;
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

    public List<CopilotTurn> getLstCopilotturn() {
        return lstCopilotturn;
    }

    public void setLstCopilotturn(List<CopilotTurn> lstCopilotturn) {
        this.lstCopilotturn = lstCopilotturn;
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
