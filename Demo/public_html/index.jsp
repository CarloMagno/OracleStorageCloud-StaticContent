<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="demo.staticcontent.view.Constants"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>index</title>
  </head>
  <body> 
    <h1> Using &lt;img src=&quot;url to Object Storage&quot; /&gt; </h1>
    <img src="https://em2.storage.oraclecloud.com/v1/storagetrial5906-ieoracletrial87560/static/lion.png" 
        width="350" height="290"/>
    
    <h1>Using &lt;img src=&lt;%=Constants.storageURL + element %&gt; /&gt;</h1>
    <img src=<%=Constants.storageURL + "static/lion.png" %> width="350" height="290" />
    
    <h1>Using StaticServlet Request &lt;img src=&quot;element&quot; /&gt;</h1>
    <img src="static/lion.png" width="350" height="290"/>    
  </body>
</html>