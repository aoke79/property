package com.sinoframe.common.util;

import java.io.File;
import java.net.URL;
import java.util.List;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;
//import com.sms.training.english.bean.EnExamInfo;

public class JacobUtil {
    // 代表一个word 程序
    private ActiveXComponent MsWordApp = null;
    // 代表进行处理的word 文档
    private Dispatch document = null;

    public JacobUtil() {
        // Open Word if we/'ve not done it already
        if (MsWordApp == null) {
            MsWordApp = new ActiveXComponent("Word.Application");
        }

    }

    // 设置是否在前台打开 word 程序 ，
    public void setVisible(boolean visible) {
        MsWordApp.setProperty("Visible", new Variant(visible));

        // 这一句作用相同
        // Dispatch.put(MsWordApp, "Visible", new Variant(visible));
    }

    // 创建一个新文档
    public void createNewDocument() {
        // Find the Documents collection object maintained by Word
        // documents表示word的所有文档窗口，（word是多文档应用程序）
        Dispatch documents = Dispatch.get(MsWordApp, "Documents").toDispatch();
        // Call the Add method of the Documents collection to create
        // a new document to edit
        document = Dispatch.call(documents, "Add").toDispatch();
    }

    // 打开一个存在的word文档,并用document 引用 引用它
    public void openFile(String wordFilePath) {
        // Find the Documents collection object maintained by Word
        // documents表示word的所有文档窗口，（word是多文档应用程序）
        Dispatch documents = Dispatch.get(MsWordApp, "Documents").toDispatch();
        document = Dispatch.call(documents, "Open", wordFilePath,
                new Variant(true)/* 是否进行转换ConfirmConversions */,
                new Variant(false)/* 是否只读 */).toDispatch();
        // document = Dispatch.invoke(documents, "Open", Dispatch.Method,
        // new Object[] { wordFilePath, new Variant(true),
        // new Variant(false)
        // }, new int[1]).toDispatch();
    }

    // 向 document 中插入文本内容
    public void insertText(String textToInsert) {
        // Get the current selection within Word at the moment.
        // a new document has just been created then this will be at
        // the top of the new doc 获得选 中的内容，如果是一个新创建的文件，因里面无内容，则光标应处于文件开头处
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch();
        // 取消选中,应该就是移动光标 ，否则 新添加的内容会覆盖选中的内容
        Dispatch.call(selection, "MoveRight", new Variant(1), new Variant(1));
        // Put the specified text at the insertion point
        Dispatch.put(selection, "Text", textToInsert);

        // 取消选中,应该就是移动光标
        Dispatch.call(selection, "MoveRight", new Variant(1), new Variant(1));
    }

    // 向文档中添加 一个图片，
    public void insertJpeg(String jpegFilePath) {
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch();
        Dispatch image = Dispatch.get(selection, "InLineShapes").toDispatch();

        Dispatch.call(image, "AddPicture", jpegFilePath);
    }

    // 段落的处理,插入格式化的文本
//    public void insertFormatStr(String text,List<EnExamInfo> list,String realPath) {
//        Dispatch wordContent = Dispatch.get(document, "Content").toDispatch(); // 取得word文件的内容
//        String examDate=list.get(0).getEnExamPlan().getExamDate().toString();
//        String date=examDate.split("-")[0]+"年"+examDate.split("-")[1]+"月"+examDate.split("-")[2]+"日";
//        String peopleNumber=list.get(0).getEnExamPlan().getPeopleNumber().toString();
//        String examRoom=list.get(0).getEnExamPlan().getExamRoom().toString();//考场
//        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch();
//        Dispatch.call(wordContent, "InsertAfter", "           关于国航PEPEC考点"+date+"考试安排的通知                                         "); 
//        Dispatch.call(selection, "TypeParagraph"); // 空一行段落
//        Dispatch selection2 = Dispatch.get(MsWordApp, "Selection").toDispatch();
//        Dispatch.call(wordContent, "InsertAfter", "各分公司、飞行总队、国货航：                                                                                           ");// 插入一个段落到最后
//        Dispatch.call(wordContent, "InsertAfter", "   经与局方协商，国航PEPEC考点将于"+date+"举行一场PEPEC考试。现将具体名单和时间安排通知如下。                                                                                  ");// 插入一个段落到最后
//        Dispatch.call(wordContent, "InsertAfter", "   考试人员如有变更，请提前至少三个工作日通知PEPEC国航考点。电子邮件：wangting@mail.airchina.com.cn。                                                          ");// 插入一个段落到最后
//        Dispatch.call(wordContent, "InsertAfter", "   考试地点：培训部地面职教中心"+list.get(0).getEnExamPlan().getExamRoom()+"教室                                                                       ");// 插入一个段落到最后
//        Dispatch.call(wordContent, "InsertAfter", "   考试等待教室：培训部地面职教中心"+list.get(0).getEnExamPlan().getWaitRoom()+"教室                                                                  ");// 插入一个段落到最后
//        Dispatch.call(wordContent, "InsertAfter", "   考试要求：                                                                   ");
//        Dispatch.call(selection, "TypeParagraph"); // 空一行段落
//        Dispatch.call(wordContent, "InsertAfter", "    1.	请考生按照时间安排提前15分钟到达考场等待教室，领取准考证。                  "); 
//        Dispatch.call(wordContent, "InsertAfter", "    2.	考试时请携带飞行执照或身份证。                                                 "); 
//        Dispatch.call(wordContent, "InsertAfter", "    3.	进入考场后，根据考试管理员的指示入座。然后根据准考证上的用户名和密码进入考试。                                                        ");// 插入一个段落到最后
//        Dispatch.call(wordContent, "InsertAfter", "    4.	因为考试将穿插进行，所有考生进出考场时请务必保持安静，并服从现场考试管理员管理。除准考证外，考场用铅笔和草稿纸一律不得带出考场。                                         ");// 插入一个段落到最后
//        Dispatch.call(wordContent, "InsertAfter", "    5.	按照局方考试规定，请所有考生在考试录音过程中，不得提及自己的姓名，否则考试作废。                                                                                            ");// 插入一个段落到最后
//        Dispatch.call(wordContent, "InsertAfter", date+" "+peopleNumber +"人数   "+examRoom+"场                                     ");
//        Dispatch.call(wordContent, "InsertAfter", "如下：国航飞行员英语培训办公室        "); 
//        for(int i=0;i<=21;i++){
//        	Dispatch.call(selection, "MoveDown");
//        }
//          
////		String examRoom=list.get(0).getEnExamPlan().getExamRoom().toString();//考场
////		String peopleNumber=list.get(0).getEnExamPlan().getPeopleNumber().toString();//人数
////		String tableTitle=date+" "+examRoom+"场"+" "+peopleNumber+"人";
////		Dispatch.call(wordContent, "InsertAfter", tableTitle);// 插入一个段落到最后
//        Dispatch paragraphs = Dispatch.get(wordContent, "Paragraphs")
//        .toDispatch(); // 所有段落
//		int paragraphCount = Dispatch.get(paragraphs, "Count").changeType(
//		Variant.VariantInt).getInt();// 一共的段落数
//		
//		// 找到刚输入的段落，设置格式
//		Dispatch lastParagraph = Dispatch.call(paragraphs, "Item",
//		new Variant(paragraphCount)).toDispatch(); // 最后一段（也就是刚插入的）
//		// Range 对象表示文档中的一个连续范围，由一个起始字符位置和一个终止字符位置定义
//		Dispatch lastParagraphRange = Dispatch.get(lastParagraph, "Range")
//		        .toDispatch();
//		
//		Dispatch font = Dispatch.get(lastParagraphRange, "Font").toDispatch();
//		Dispatch.put(font, "Bold", new Variant(false)); // 设置为黑体
//		Dispatch.put(font, "Italic", new Variant(false)); // 设置为斜体
//		Dispatch.put(font, "Name", new Variant("微软雅黑")); //
//		Dispatch.put(font, "Size", new Variant(12)); // 小四
//		Dispatch alignment = Dispatch.get(selection, "ParagraphFormat")
//		.toDispatch();// 段落格式
//		Dispatch.put(alignment, "Alignment", "0"); // (1:置中 2:靠右 3:靠左) 
//		int row=list.size()+1;
//		int column=6;
//		insertTable("", row, column, list);
//		//页眉，页脚处理////////////////////////
//		  Dispatch ActiveWindow = MsWordApp.getProperty("ActiveWindow")
//		    .toDispatch();
//		  Dispatch ActivePane = Dispatch.get(ActiveWindow, "ActivePane")
//		    .toDispatch(); 
//		  Dispatch View = Dispatch.get(ActivePane, "View").toDispatch();
//		  Dispatch.put(View, "SeekView", "9"); //9是设置页眉
//		  Dispatch.put(alignment, "Alignment", "1"); // 置中
//		  insertJpeg(realPath);
//		  Dispatch.call(selection, "MoveDown");
//		  Dispatch.put(selection, "Text", "AVIATION ENGLISH TRAINING OFFICE           NO: 236");
//		    
//
//    }

    // word 中在对表格进行遍历的时候 ，是先列后行 先column 后cell
    // 另外下标从1开始
//    public void insertTable(String tableTitle, int row, int column,List<EnExamInfo> list) {
//       Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象
//        Dispatch.call(selection, "TypeText", tableTitle); // 写入标题内容 // 标题格行
//        Dispatch.call(selection, "TypeParagraph"); // 空一行段落
//        Dispatch.call(selection, "TypeParagraph"); // 空一行段落
//        Dispatch.call(selection, "MoveDown"); // 游标往下一行
//
//        // 建立表格
//        Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
//        // int count = Dispatch.get(tables,
//        // "Count").changeType(Variant.VariantInt).getInt(); // document中的表格数量
//        // Dispatch table = Dispatch.call(tables, "Item", new Variant(
//        // 1)).toDispatch();//文档中第一个表格
//        Dispatch range = Dispatch.get(selection, "Range").toDispatch();// /当前光标位置或者选中的区域
//
//        Dispatch newTable = Dispatch.call(tables, "Add", range,
//                new Variant(row), new Variant(column), new Variant(1))
//                .toDispatch(); // 设置row,column,表格外框宽度
//        Dispatch cols = Dispatch.get(newTable, "Columns").toDispatch(); // 此表的所有列，
//        int colCount = Dispatch.get(cols, "Count").changeType(
//                Variant.VariantInt).getInt();// 一共有多少列 实际上这个数==column
//        System.out.println(colCount + "列"); 
//        for (int i =1; i <=colCount; i++) { // 循环取出每一列
//            Dispatch col = Dispatch.call(cols, "Item", new Variant(i))
//                    .toDispatch();
//            Dispatch cells = Dispatch.get(col, "Cells").toDispatch();// 当前列中单元格
//            int cellCount = Dispatch.get(cells, "Count").changeType(
//                    Variant.VariantInt).getInt();// 当前列中单元格数 实际上这个数等于row
//            
//          
//            	 for (int j = 1; j <= cellCount; j++) {// 每一列中的单元格数 
//            		 EnExamInfo enExamInfo = list.get(i-1);
//             		if(i==1){
//             			 if(j==1){
//             				putTxtToCell(newTable, j,i,"序号");// 
//             			 }else{
//             				putTxtToCell(newTable, j,i,j-1+"");// 与上面四句的作用相同
//             			 } 
//                    	}else if(i==2){
//                    		if(j==1){
//                    			putTxtToCell(newTable, j,i,"单位");	
//                    		}else{
//                    			putTxtToCell(newTable, j,i,enExamInfo.getCmPeople().getSysOrganization().getName());	
//                    		} 
//                    	}else if(i==3){
//                    		if(j==1){
//                    			putTxtToCell(newTable, j,i,"姓名");
//                    		}else{
//                    			putTxtToCell(newTable, j,i,enExamInfo.getCmPeople().getName());
//                    		}
//                    	 	
//                    	}else if(i==4){
//                    		if(j==1){
//                    			putTxtToCell(newTable, j,i,"执照号");
//                    		}else{
//                    			putTxtToCell(newTable, j,i,enExamInfo.getCmPeople().getIdcard());	
//                    		} 
//                    	}else if(i==5){
//                    		if(j==1){
//                    			putTxtToCell(newTable, j,i,"考试时间");
//                    		}else{
//                    			putTxtToCell(newTable, j,i,enExamInfo.getEnExamPlan().getExamDate().toString());
//                    		}
//                    	 	
//                    	}else if(i==6){
//                    		if(j==1){
//                    			putTxtToCell(newTable, j,i,"签到");
//                    		}
//                    	}
//                 }
//             
//        }
////		 for (int i = 1; i <= colCount; i++) { // 循环取出每一列
////	            Dispatch col = Dispatch.call(cols, "Item", new Variant(i))
////	                    .toDispatch();
////	            Dispatch cells = Dispatch.get(col, "Cells").toDispatch();// 当前列中单元格
////	            int cellCount = Dispatch.get(cells, "Count").changeType(
////	                    Variant.VariantInt).getInt();// 当前列中单元格数 实际上这个数等于row
////
////	            for (int j = 1; j <= cellCount; j++) {// 每一列中的单元格数
////	                 if(i==1){
////	                	 
////	                 }
////	                putTxtToCell(newTable, j, i, "第" + j + "行，第" + i + "列");// 与上面四句的作用相同
////	            }
////
////	        }
//		 
//    }
//  

    /** */
    /**
     * 在指定的单元格里填写数据
     *
     * @param tableIndex
     * @param cellRowIdx
     * @param cellColIdx
     * @param txt
     */
    public void putTxtToCell(Dispatch table, int cellRowIdx, int cellColIdx,
            String txt) {
        Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx),
                new Variant(cellColIdx)).toDispatch();
        Dispatch.call(cell, "Select");
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象
        Dispatch.put(selection, "Text", txt);
    }

    /** */
    /**
     * 在指定的单元格里填写数据
     *
     * @param tableIndex
     * @param cellRowIdx
     * @param cellColIdx
     * @param txt
     */
    public void putTxtToCell(int tableIndex, int cellRowIdx, int cellColIdx,
            String txt) {
        // 所有表格
        Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
                .toDispatch();
        Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx),
                new Variant(cellColIdx)).toDispatch();
        Dispatch.call(cell, "Select");
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象
        Dispatch.put(selection, "Text", txt);
    }

    // 合并两个单元格
    public void mergeCell(Dispatch cell1, Dispatch cell2) {
        Dispatch.call(cell1, "Merge", cell2);
    }

    public void mergeCell(Dispatch table, int row1, int col1, int row2, int col2) {
        Dispatch cell1 = Dispatch.call(table, "Cell", new Variant(row1),
                new Variant(col1)).toDispatch();
        Dispatch cell2 = Dispatch.call(table, "Cell", new Variant(row2),
                new Variant(col2)).toDispatch();
        mergeCell(cell1, cell2);
    }

    public void mergeCellTest() {
        Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
        int tableCount = Dispatch.get(tables, "Count").changeType(
                Variant.VariantInt).getInt(); // document中的表格数量
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableCount))
                .toDispatch();// 文档中最后一个table
        mergeCell(table, 1, 1, 1, 2);// 将table 中x=1,y=1 与x=1,y=2的两个单元格合并
    }

    // ========================================================

    /** */
    /**
     * 把选定的内容或光标插入点向上移动
     *
     * @param pos
     *            移动的距离
     */
    public void moveUp(int pos) {
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象
        for (int i = 0; i < pos; i++) {
            // MoveDown MoveLeft moveRight
            // moveStart ( Dispatch.call(selection, "HomeKey", new Variant(6));
            // )
            // moveEnd Dispatch.call(selection, "EndKey", new Variant(6));
            Dispatch.call(selection, "MoveUp");
        }
    }

    /** */
    /**
     * 从选定内容或插入点开始查找文本
     *
     * @param toFindText
     *            要查找的文本
     * @return boolean true-查找到并选中该文本，false-未查找到文本
     */
    public boolean find(String toFindText) {
        if (toFindText == null || toFindText.equals(""))
            return false;
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象
        // 从selection所在位置开始查询
        Dispatch find = Dispatch.call(selection, "Find").toDispatch();
        // 设置要查找的内容
        Dispatch.put(find, "Text", toFindText);
        // 向前查找
        Dispatch.put(find, "Forward", "True");
        // 设置格式
        Dispatch.put(find, "Format", "True");
        // 大小写匹配
        Dispatch.put(find, "MatchCase", "True");
        // 全字匹配
        Dispatch.put(find, "MatchWholeWord", "True");
        // 查找并选中
        return Dispatch.call(find, "Execute").getBoolean();
    }

    /** */
    /**
     * 把选定选定内容设定为替换文本
     *
     * @param toFindText
     *            查找字符串
     * @param newText
     *            要替换的内容
     * @return
     */
    public boolean replaceText(String toFindText, String newText) {
        if (!find(toFindText))
            return false;
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象
        Dispatch.put(selection, "Text", newText);
        return true;
    }

    public void printFile() {
        // Just print the current document to the default printer
        Dispatch.call(document, "PrintOut");
    }

    // 保存文档的更改
    public void save() {
        Dispatch.call(document, "Save");
    }

    public void saveFileAs(String filename) {
        Dispatch.call(document, "SaveAs", filename);
    }

    public void closeDocument() {
        // Close the document without saving changes
        // 0 = wdDoNotSaveChanges
        // -1 = wdSaveChanges
        // -2 = wdPromptToSaveChanges
        Dispatch.call(document, "Close", new Variant(0));
        document = null;
    }

    public void closeWord() {
        Dispatch.call(MsWordApp, "Quit");
        MsWordApp = null;
        document = null;
    }

    // 设置wordApp打开后窗口的位置
    public void setLocation() {
        Dispatch activeWindow = Dispatch.get(MsWordApp, "Application")
                .toDispatch();
        Dispatch.put(activeWindow, "WindowState", new Variant(1)); // 0=default
        // 1=maximize
        // 2=minimize
        Dispatch.put(activeWindow, "Top", new Variant(0));
        Dispatch.put(activeWindow, "Left", new Variant(0));
        Dispatch.put(activeWindow, "Height", new Variant(600));
        Dispatch.put(activeWindow, "width", new Variant(800));
    }
    public static void main(String[] args) {
		JacobUtil word = new JacobUtil();
		word.setVisible(true); //是否前台打开word 程序，或者后台运行
		word.createNewDocument();// 创建一个新文档
		String str="你好，中国" +
				"中华人民共和国" +
				"成立啦！";
		word.insertText(str);// 插入一个段落，对其中的字体进行了设置
		String s = File.separator;
		word.saveFileAs("D:\\upLoadFile" + s +"考试通知.doc");
	}

}
 