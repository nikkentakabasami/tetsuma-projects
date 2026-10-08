<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>


<#-- простой макрос  -->
		
<#macro greet>
Hello Joe!
</#macro>  

<@greet/>
	
<#-- макрос с параметром -->
	
<#macro greet person>
 Hello ${person}!
</#macro>  

		
<@greet person="Fred"/>
<@greet person="Batman"/>

<#-- макрос с параметрами и значениями по умолчанию -->

<#macro greet person color="black">
  <font size="+2" color="${color}">Hello ${person}!</font>
</#macro>  

<@greet person="Bob"/>


<#-- Nested content -->

<#macro border>
  <table border=4 cellspacing=0 cellpadding=4><tr><td>
    <#nested>
  </tr></td></table>
</#macro>  

<@border>The bordered text</@border>

<#-- nested можно вызывать несколько раз -->

<#macro do_thrice>
  <#nested>
  <#nested>
  <#nested>
</#macro>

<@do_thrice>Anything.</@do_thrice>


   
</body>
</html>

