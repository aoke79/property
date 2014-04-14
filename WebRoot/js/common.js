	/****************************************************
	* ����:   check E-mail�Ƿ�Ϸ�
	* ����ֵ: �Ϸ�ʱ����True;���򷵻�False
	* ����:   HaoFeng
	* ����:   2009/03/13
	*****************************************************/
	function IsEmail(userMail) {
		if ((userMail==null) || (userMail.length==0)) return true;
		if (userMail.indexOf("@")==-1) return false;
	    var NameList=userMail.split("@");
	    if (NameList.length!=2) return false;
	    if (NameList[0].length<1 ) return false;
	    if (NameList[1].indexOf(".")<=0) return false;
	    if (userMail.indexOf("@")>userMail.indexOf(".")) return false; 
	    if (userMail.indexOf(".")==userMail.length-1) return false;
	    return true;
	}
	/****************************************************
	* ����:   check �����Ƿ�Ϊ 2009/03/13��ʽ
	* ����ֵ: 2009/02/01ʱ����True;2009/2/1����False;֧������2��29���ж�.
	* ����:   HaoFeng
	* ����:   2009/03/13
	*****************************************************/
	function check(str){
		var a=/^((((1[6-9]|[2-9]\d)\d{2})\/(0?[13578]|1[02])\/(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})\/(0?[13456789]|1[012])\/(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})\/0?2\/(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))\/0?2\/29\/))$/
		if (!a.test(str)){			
			return false;
		} else {			
			return true;
		}
	}
	
	/****************************************************
	* ����:   ����һ����ť״̬Ϊ����
	* ����ֵ: void
	* ����:   HaoFeng
	* ����:   2009/03/13
	*****************************************************/
	function setBtnEnabled (btnSubmit) {		
		btnSubmit.disabled = false;		
	}
	
	/****************************************************
	* ����:   ����һ����ť״̬Ϊ������
	* ����ֵ: void
	* ����:   HaoFeng
	* ����:   2009/03/13
	*****************************************************/
	function setBtnDisabled (btnSubmit) {		 
		btnSubmit.disabled = true;
	}
	
	/****************************************************
	* ����:   Ϊ�ı������ù��
	* ����ֵ: void
	* ����:   HaoFeng
	* ����:   2009/03/13
	*****************************************************/			
	function setBtnEnabled (textParam) {		
		textParam.focus();
	}
	/****************************************************
	* ����:   ȡ���ı�����
	* ����ֵ: �ı�����
	* ����:   HaoFeng
	* ����:   2009/03/15
	******************************************************/
	function getTextLength(str){
     	return str.replace(/[^\x00-\xff]/g,"**").length;
    }
	/****************************************************
	* ����:   �ж��ı������Ƿ���� n
	* ����ֵ: ����nʱ����True ���򷵻�false
	* ����:   HaoFeng
	* ����:   2009/03/15
	******************************************************/
	function checkTextLength(TextValue,lengthValue){
	
		//�ж��ı�����
		if (getTextLength(TextValue) > 
								lengthValue){
			return true;
		}else{
			return false;
		}
	}	
	
	
	
	
	
	
	
	