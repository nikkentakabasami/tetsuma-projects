<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}!</title>
</head>
<body>

<#-- include -->

<#include "/lib/copyright.ftl">



<#-- interpolation - плейсхолдеры -->

<h2>Hello ${user.name.first} ${user.name.last}!</h2>
 
t1: ${notExist!'default1'}
t2: ${notExist!}



<#-- Missing value test operator -->
	
<#if mouse??>
  Mouse found
<#else>
  No mouse found
</#if>


calc: ${(5 + 8)/2}

${title?upper_case}
${title?cap_first}
${1.3?int}
${employees?size}


<#-- добавленная функция -->

<#assign x = "something">
${indexOf("met", x)}
${indexOf("foo", x)}


<#-- list -->

<ul>
  <#list employees as emp>
    <li>${emp.id} - ${emp.firstName}
  </#list>
</ul>


<#-- if -->

<#if user.gender=='MALE'>
  MALE
<#else>
  not MALE
</#if>


<#-- перезапись глобальной переменной -->
	
<#assign user = "Joe">
${user} 
age:${.globals.user.age}  
		
  
  
  
   
   
</body>
</html>

