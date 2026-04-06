#web.views.<m.entityLowerCase>Journal.jsp

\<script type="module" src="tm/js/tsc-<m.entity>-journal.js">\</script>


\<form:form commandName="mainFilter" class="form-horizontal" role="form" action=".">

  \<div id="filterContainer" style="display: none;">
    \<form:hidden path="currentPage" />

	<m.propertyList:{p|

		<if(p.typePrimitiveBoolean || p.typeBoolean)>
		    \<form:select path="<p.propertyName>" class="form-control">
		      \<form:option value="" label="-" />
		      \<form:option value="1" label="да" />
		      \<form:option value="0" label="нет" />
		    \</form:select>
		
		<elseif(p.typeDate)>
		   \<form:input path="<p.propertyName>" cssClass="date-input" />
		
		<else>
		   \<form:input path="<p.propertyName>" />
		
		<endif>	
	
	}>









  \</div>

  \<div id="myGrid"/>


\</form:form>

