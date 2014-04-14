package com.sms.training.qualification.web.action.tfQuaApply;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.sinoframe.bean.CmPeople;
import com.sinoframe.bean.Message;
import com.sinoframe.web.action.BaseAction;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualPilotCheckItem;
import com.sms.training.qualification.bean.TfQualPilotLicence;
import com.sms.training.qualification.business.ITfQualCheckJobCardBS;

/*
 * 页面跳转路径
 */
@Results({
    @Result(name="success",location="/standard/ajaxDone.jsp"),
    @Result(name="add",location="/sms/training/qualification/checkJobCard/checkJobCardAdd.jsp"),
    @Result(name="before",location="/sms/training/qualification/checkJobCard/checkJobCardBefore.jsp"),
    @Result(name="access",location="/sms/training/qualification/checkJobCard/checkJobCardAddAccess.jsp"),
    @Result(name="json",type="json"),
    @Result(name="behind",location="/sms/training/qualification/checkJobCard/checkJobCardBehind.jsp")
})
public class TfQualCheckJobCardAction extends BaseAction{

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1525529429295806896L;
    
    // 操作成功/失败提示信息
    private Message message = new Message();
    
    // 参数类型设置表的service接口
    private ITfQualCheckJobCardBS checkJobCardBS;
    
    // 熟练检查工作表实体Bean用来接收页面参数
    private TfQualPilotCheckItem checkItem ;
    
    // 飞行员实体类
    private CmPeople people = new CmPeople();
    
    // 飞行员id
    private String pilotId;
    
    // 飞行员id
    private String detailsId;
    
    //申报人员明细
    private TfQualDeclaraPilot tfQualDeclaraPilot;
    
    // 飞行员名称
    private String pilotName;
    
    // 编号
    private String plcno;
    
    //定义变量来表示是before页面还是behind页面
    private String checkItemSaveFlg;
    /**
     * 跳转至添加页面
     * @return "add"
     */
    public String toAdd() {
        
        try {
            tfQualDeclaraPilot = checkJobCardBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
            if(tfQualDeclaraPilot!=null && !tfQualDeclaraPilot.equals("")){
                pilotId = tfQualDeclaraPilot.getCmPeople().getId();
                detailsId = tfQualDeclaraPilot.getDetailsid();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "add";
    }
    
	/**
     * 跳转至添加页面
     * @return "add"
     */
    public String toAddAccess() {
        
        try {
        	tfQualDeclaraPilot = checkJobCardBS.findById(TfQualDeclaraPilot.class,tfQualDeclaraPilot.getDetailsid());
        	if(tfQualDeclaraPilot!=null && !tfQualDeclaraPilot.equals("")){
        	   pilotId = tfQualDeclaraPilot.getCmPeople().getId();
        	   detailsId = tfQualDeclaraPilot.getDetailsid();
        	}
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "access";
    }
    
    /**
     * 跳转到检查工作单前半部分
     */
    public String toBefore(){
        
        // 查询一下熟练检查工作单是否有记录
        checkItem = checkJobCardBS.findCheckItemById(detailsId);
        
        // 编号
        TfQualPilotLicence licence = checkJobCardBS.findLicence(pilotId);
        plcno = licence.getPlcno();
        
        // 取得飞行员名称
        people = checkJobCardBS.findById(CmPeople.class, pilotId);
        pilotName = people.getName();
        return "before";
    }
    
    /**
     * 跳转到检查工作单后半部分
     */
    public String toBehind(){
        
        // 查询一下熟练检查工作单是否有记录
        checkItem = checkJobCardBS.findCheckItemById(detailsId);
        return "behind";
    }
    
    /**
     * 保存添加信息
     * @return
     */
    public String save() {
        try {
	        if("".equals(checkItem.getCheckid().trim())){
	        	checkItem.setCheckid(null);
			}
	        if("behind".equals(checkItemSaveFlg)){
	        	
	        	if(checkItem.getCheckid()==null){
	        		checkJobCardBS.save(checkItem);
	        	}else{
	        		dealNull(checkItem);
	        		checkJobCardBS.update(checkItem, checkItem.getCheckid());
	        	}
	        	this.message = this.getSuccessMessage(getText("addSuccess"), "checkJob", "closeCurrent", "");
	        }else{
	        	if(checkItem.getCheckid()==null){
	        		checkJobCardBS.save(checkItem);
	        	}else{
	        		if(checkItem.getCheckLevel() == null ){
	        			checkItem.setCheckLevel("");
	        		}
	        		checkJobCardBS.update(checkItem, checkItem.getCheckid());
	        	}
	        	this.message = this.getSuccessMessage(getText("addSuccess"), "checkJob", "", "");
	        }
        } catch (Exception e) {
            
            // 操作失败信息提示
            this.message = this.getFailMessage("添加失败");
            e.printStackTrace();
        }
        return "json";
    }
    
	
	private void dealNull(TfQualPilotCheckItem newObj) throws Exception{
		TfQualPilotCheckItem oldObj = checkJobCardBS.findCheckItemById(newObj.getDetailsid());
		Class<?> cs = newObj.getClass();
		Field[] fields= cs.getDeclaredFields();
		AccessibleObject.setAccessible(fields, true);
		for(Field fld : fields ){
			if(fld.getName().startsWith("checkItem")){
				fld.set(oldObj, fld.get(newObj));
			}
		}
	}	
   
    
    /****************************自动生成***************************************/
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
    @JSON(serialize = false)
    public ITfQualCheckJobCardBS getCheckJobCardBS() {
        return checkJobCardBS;
    }

    public void setCheckJobCardBS(ITfQualCheckJobCardBS checkJobCardBS) {
        this.checkJobCardBS = checkJobCardBS;
    }
    @JSON(serialize = false)
    public TfQualPilotCheckItem getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(TfQualPilotCheckItem checkItem) {
        this.checkItem = checkItem;
    }
    @JSON(serialize = false)
    public String getPilotId() {
        return pilotId;
    }

    public void setPilotId(String pilotId) {
        this.pilotId = pilotId;
    }
    @JSON(serialize = false)
    public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return tfQualDeclaraPilot;
	}

	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}
	@JSON(serialize = false)
    public String getPilotName() {
        return pilotName;
    }

    public void setPilotName(String pilotName) {
        this.pilotName = pilotName;
    }
    @JSON(serialize = false)
    public String getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(String detailsId) {
        this.detailsId = detailsId;
    }
    @JSON(serialize = false)
    public String getPlcno() {
        return plcno;
    }

    public void setPlcno(String plcno) {
        this.plcno = plcno;
    }
    @JSON(serialize = false)
    public CmPeople getPeople() {
        return people;
    }

    public void setPeople(CmPeople people) {
        this.people = people;
    }
    
    @JSON(serialize = false)
    public String getCheckItemSaveFlg() {
		return checkItemSaveFlg;
	}

	public void setCheckItemSaveFlg(String checkItemSaveFlg) {
		this.checkItemSaveFlg = checkItemSaveFlg;
	}
}
