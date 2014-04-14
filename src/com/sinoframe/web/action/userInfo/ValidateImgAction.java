package com.sinoframe.web.action.userInfo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.struts2.components.Param;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.sinoframe.web.action.BaseAction;
import com.sinoframe.web.action.userInfo.ImageUtil;
import com.opensymphony.xwork2.ActionContext;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.xml.internal.bind.v2.runtime.Name;

@SuppressWarnings("serial")
@Controller
@Result(type="stream" ,params={"inputName", "imageStream"})
@ParentPackage("json-default")
public class ValidateImgAction extends BaseAction {
	

	private InputStream imageStream;

	public InputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}
	public String execute(){
		Map<String, BufferedImage> map = ImageUtil.createImage();
		String key = map.keySet().iterator().next();
		ActionContext.getContext().getSession().put("code", key);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
		try {
			encoder.encode(map.get(key));
			byte[] byts = bos.toByteArray();
			imageStream = new ByteArrayInputStream(byts);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
}
