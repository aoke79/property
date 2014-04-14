package com.sms.training.qualification.business.service;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.KSGZDFormData;
import com.sms.training.qualification.bean.TfQualBaseWorkOrder;
import com.sms.training.qualification.bean.TfQualBaseWorkOrderItem;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.bean.TfQualPilotFlyrecordbook;
import com.sms.training.qualification.bean.TfQualPilotLicence;
import com.sms.training.qualification.business.ITfQualBaseWorkOrderBS;

@Service("tfQualBaseWorkOrderBS")
public class TfQualBaseWorkOrderBS extends BaseBS implements
		ITfQualBaseWorkOrderBS {

	/**
	 * 获取驾驶员执照编号
	 * @param detail 申报人员明细
	 * @return
	 */
	@Override
	public String findLicenceNo(TfQualDeclaraPilot detail) {
		String hql = " from TfQualPilotLicence lc where lc.pilotid='"
				+ detail.getCmPeople().getId() + "' " + "and lc.atrid='"
				+ detail.getTfQualBaseType().getTargetatrid().getId() + "' ";
		List<TfQualPilotLicence> list = this.findPageByQuery(hql);
		if (list != null && list.size() != 0) {
			return list.get(0).getPlcno();
		}
		return null;
	}

	/**
	 * //将对象form中封装的数据保存
	 * @param order 考试工作单
	 * @param form 封装了表单数据“考试项目”
	 * @throws Exception
	 */
	@Override
	public void saveItems(TfQualBaseWorkOrder order, KSGZDFormData form)throws Exception {
		String hql=" from TfQualBaseWorkOrderItem it where it.tfQualBaseWorkOrder.id='"+order.getId()+"' ";
		List<TfQualBaseWorkOrderItem> items=this.findPageByQuery(hql);
		//如果成绩list不空，根据form中的更新数据来更新，使用反射
		if(items!=null && items.size()!=0){
			for(TfQualBaseWorkOrderItem it : items){
				String fname=it.getExamid();
				if(fname!=null && !"".equals(fname)){
					Field field=KSGZDFormData.class.getDeclaredField(fname);
					field.setAccessible(true);
					TfQualBaseWorkOrderItem fItem=(TfQualBaseWorkOrderItem) field.get(form);
					if(fItem==null)
						continue;
					it.setExamResult(fItem.getExamResult());
					it.setExamComment(fItem.getExamComment());
					it.setItemDesc(fItem.getItemDesc());//////
					this.update(it,it.getId());
				}
			}
			return;
		}
		//如果成绩list为空，根据form中的数据来保存，使用反射
		Field[] fields=KSGZDFormData.class.getDeclaredFields();
		for(Field f : fields){
			f.setAccessible(true);
			TfQualBaseWorkOrderItem fItem=(TfQualBaseWorkOrderItem) f.get(form);
			if(fItem == null){
				fItem=new TfQualBaseWorkOrderItem();
			}
			fItem.setExamid(f.getName());
			fItem.setTfQualBaseWorkOrder(order);
			this.save(fItem);
		}
	}

	/**
	 * 填充对象form,将数据库中数据查询出来，填充进form中，以供页面显示
	 * @param order 考试工作单
	 * @param form 封装了表单数据“考试项目”
	 * @throws Exception
	 */
	@Override
	public void fillForm(TfQualBaseWorkOrder order,KSGZDFormData form) throws Exception {
		String hql=" from TfQualBaseWorkOrderItem it where it.tfQualBaseWorkOrder.id='"+order.getId()+"' ";
		List<TfQualBaseWorkOrderItem> items=this.findPageByQuery(hql);
		//如果成绩list不空，根据form中的更新数据来更新成绩，使用反射
		if(items==null || items.size()==0){
			return;
		}
		for(TfQualBaseWorkOrderItem it : items){
			String fname=it.getExamid();
			if(fname!=null && !"".equals(fname)){
				Field f= KSGZDFormData.class.getDeclaredField(fname);
				f.setAccessible(true);
				f.set(form, it);
			}
		}
	}

	/**
	 * 根据申报人员明细id和当前登录人  查找对应的 考试工作单
	 * @param detailId 申报人员明细id
	 * @param modifier 最后修改人
	 * @return 考试工作单
	 */
	@Override
	public TfQualBaseWorkOrder findByDetailIdAndModifier(String detailId,String modifier) {
		String hql = "from TfQualBaseWorkOrder c where c.tfQualDeclaraPilot.detailsid='"
			+ detailId + "' and modifier='" + modifier + "'";
	List<TfQualBaseWorkOrder> list = findPageByQuery(hql);
	if (list != null && list.size() != 0) {
		return list.get(0);
	}
		return null;
	}

}
