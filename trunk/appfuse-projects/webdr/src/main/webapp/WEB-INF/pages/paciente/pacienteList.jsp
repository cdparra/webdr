<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="pacienteList.title"/></title>
    <meta name="heading" content="<fmt:message key='pacienteList.heading'/>"/>
</head>
<s:if test="%{soloVista!='ok'}">    
    <c:set var="buttons"> 
        <input type="button" style="margin-right: 5px"
               onclick="location.href='<c:url value="editPaciente.html?from=list"/>'"
               value="<fmt:message key="button.add"/>"/>
        <input type="button" onclick="location.href='<c:url value="/mainMenu.html"/>'"
               value="<fmt:message key="button.done"/>"/>
    </c:set>
<c:out value="${buttons}" escapeXml="false" />
</s:if>


<s:set name="pacientes" value="pacientes" scope="request"/>
<display:table name="pacientes" class="table" requestURI="" id="pacienteList" export="true"
               pagesize="25">
    <s:if test="%{soloVista=='ok'}"> 
        <display:column property="cedula" sortable="true" url="viewPaciente.html?from=list"
                        paramId="id" paramProperty="id" titleKey="paciente.cedula"/>
    </s:if>
    <s:else>
        <display:column property="cedula" sortable="true" url="editPaciente.html?from=list"
                        paramId="id" paramProperty="id" titleKey="paciente.cedula"/>
    </s:else>
    
    <display:column property="firstName" sortable="true" titleKey="paciente.nombre"/>
    <display:column property="lastName" sortable="true" titleKey="paciente.apellido"/>
    <display:column property="fechaNacimientoString" sortable="true" titleKey="paciente.fechaNacimiento"/>
    <display:column property="tipoSangre.nombre" sortable="true" titleKey="paciente.tipoSangre.nombre"/>
    
    <display:setProperty name="paging.banner.item_name" value="paciente"/>
    <display:setProperty name="paging.banner.items_name" value="pacientes"/>
    <display:setProperty name="export.excel.filename" value="Pacientes.xls"/>
    <display:setProperty name="export.csv.filename" value="Pacientes.csv"/>
    <display:setProperty name="export.pdf.filename" value="Pacientes.pdf"/>
</display:table>
<c:out value="${buttons}" escapeXml="false" />
<script type="text/javascript">
    highlightTableRows("pacienteList");
</script>