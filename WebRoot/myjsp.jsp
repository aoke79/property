<%@ page language="java" contentType="text/html; charset=GBK"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>struts2UI标签示例</title>
 </head>
 <body>

  <!-- Textfield标签 -->
  <hr size="3" color="blue">
  <b>Textfield标签输出一个HTML单行文本输入控件，等价于HTML代码&lt;input type=”text”&gt;:
  </b>
  <s:form action="××××" method="post" theme="xhtml">
   <s:textfield name="username" label="用户名" value="张三" maxlength="12"
    size="20" />
  </s:form>


  <!-- Textarea标签 -->
  <hr size="3" color="blue">
  <b>Textarea标签输出一个HTML多行文本输入控件，等价于HTML代码：&lt;textarea /&gt;</b>
  <br>
  <s:textarea name="comment" label="个人简介" rows="3" cols="15" wrap="true" />

<!-- Radio标签 -->
  <hr size="3" color="blue">
  <b>Radio标签输出一个HTML多行文本输入控件，等价于HTML代码：&lt;textarea /&gt;</b>
  <br>
        <s:radio label="性别" name="gender"  list="#{'male':'男','female':'女'}" />


  <!-- checkboxlist标签 -->
  <hr size="3" color="blue">
  <b>checkboxlist标签即复选框</b>
  <br>
  <s:checkboxlist name="interest" list="#{1:'足球',2:'篮球',3:'排球',4:'游泳'}"
   label="兴趣爱好" />

 

  <!-- file标签 -->
  <hr size="3" color="blue">
  <b>file标签用于上传文件（accept属性，指出接受文件的MIME类型）</b>
  <br>
  <s:file name="pic" label="图片"
   accept="image/bmp,image/png,image/gif,image/jpeg,image/pjpeg" />

  <!-- Submit标签 -->
  <hr size="3" color="blue">
  <b>Submit标签输出一个按钮，submit标签和form标签使用可以提供异步表单提交功能(要使用的提交按钮的类型，有效值：input
   button image):</b>
  <br>
  <s:submit type="image" method="login" src="images/login.jpg" />
  <s:submit type="button" action="selectTag" method="login" label="登陆" />


  <!-- Select标签 -->
  <hr size="3" color="blue">
  <b>Select标签输出一个下拉列表框，相当于HTML代码中的&lt;select/&gt;</b>
  <br>
  <i>使用name和list属性，list属性的值是一个列表:</i>
  <s:select label="最高学历" name="education" list="{'高中','大学','硕士','博士'}" />
  <br>
  <i>使用name和list属性，list属性的值是一个Map:</i>
  <s:select label="最高学历" name="education"
   list="#{1:'高中',2:'大学',3:'硕士',4:'博士'}" />
  <br>
  <i>使用headerKey和headerValue属性设置header选项:</i>
  <s:select label="最高学历" name="education" list="{'高中','大学','硕士','博士'}"
   headerKey="-1" headerValue="请选择您的学历" />
  <br>
  <i>使用multiple属性设置多选和使用size属性设置下拉框可显示的选项个数:</i>
  <s:select label="最高学历" name="education" list="{'高中','大学','硕士','博士'}"
   multiple="true" size="3" />
  <br>
  <span style="color: red;">
   使用listKey和listValue属性，利用Action实例的属性（property）来设置选项的值和选项的内容:
   &lt;s:select label="最高学历" name="education" list="educations"
   listKey="id" listValue="name"/&gt; </span>


  <!-- doubleselect标签 -->
  <hr size="3" color="blue">
  <b>doubleselect标签输出关联的两个HTML列表框，产生联动效果</b>
  <br>
  <s:form name="doubleselect">
   <s:doubleselect label="请选择所在的省市" name="province" list="{'山西省','陕西省'}"
    doubleName="city"
    doubleList="top == '山西省' ? {'太原市','大同市','长治市','临汾市'}
                                    :{'西安市','汉中市','咸阳市','延安市'}" />
  </s:form>


  <!-- updownselect标签 -->
  <hr size="3" color="blue">
  <b>updownselect标签创建一个带有上下移动的按钮的列表框，可以通过上下移动按钮来调整列表框的选项的位置:</b>
  <br>
  <!-- 使用简单Map对象来生成可上下移动选项的下拉选择框  -->
  <s:updownselect name="books" label="请选择您想选择的书籍" labelposition="top"
   moveUpLabel="up" moveDownLabel="down" selectAllLabel="all"
   list="#{1:'Spring2.0宝典' ,2:'轻量级J2EE企业应用实战' ,3:'基于J2EE的Ajax宝典'}"
   listKey="key" listValue="value" size="3" />


  <!-- optiontransferselect标签 -->
  <hr size="3" color="blue">
  <b>optiontransferselect标签创建一个选项转移列表组建，它由两个select标签以及它们之间的用于将选项在两个select之间相互移动的按钮组成。表单提交时，将提交两个列表框中选中的选项:</b>
  <br>
  <s:optiontransferselect label="最喜爱的图书" name="javaBook"
   list="{'《Java Web开发详解》', '《Struts 2深入详解》', '《Java快速入门》'}"
   doubleName="cBook"
   doubleList="{'《VC++深入详解》', '《C++ Primer》', '《C++程序设计语言》'}" />

  <s:optiontransferselect label="最喜爱的图书" name="book1" leftTitle="Java图书"
     rightTitle="C/C++图书"
     list="{'《Java Web开发详解》', '《Struts 2深入详解》', '《Java快速入门》'}"
     headerKey="-1" headerValue="--- 请选择 ---" emptyOption="true"
     doubleName="book2"
     doubleList="{'《VC++深入详解》', '《C++ Primer》', '《C++程序设计语言》'}"
     doubleHeaderKey="-1" doubleHeaderValue="--- 请选择 ---"
     doubleEmptyOption="true" addToLeftLabel="向左移动" addToRightLabel="向右移动"
     addAllToLeftLabel="全部左移" addAllToRightLabel="全部右移"
     selectAllLabel="全部选择" leftUpLabel="向上移动" leftDownLabel="向下移动"
     rightUpLabel="向上移动" rightDownLabel="向下移动" />


 </body>
</html>
