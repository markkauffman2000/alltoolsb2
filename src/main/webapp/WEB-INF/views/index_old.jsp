<!--
Copyright (C) 2011, Blackboard Inc.
All rights reserved.
Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

Neither the name of Blackboard Inc. nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY BLACKBOARD INC ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BLACKBOARD INC. BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@page import="com.blackboard.bbdn.alltoolsb2.LogBook" %>
<%@page import="com.blackboard.bbdn.alltoolsb2.Log" %>

<jsp:useBean id="logbook" scope="page" class="com.blackboard.bbdn.alltoolsb2.LogBook"  />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="bbNG" uri="/bbNG"%>
<%@ taglib prefix="bbData" uri="/bbData" %>

<bbNG:learningSystemPage ctxId="ctx">
    
    <bbNG:jsFile href="/javascript/ngui/lightbox.js"/>

<bbNG:jsBlock> 
   <script type="text/javascript">
       
function openLightbox( url, pageTitle ) 
{
// create the lightbox with some options
   var myLightbox = new lightbox.Lightbox({
       ajax: { url: url },
       title: pageTitle,
       closeOnBodyClick: false,
       showCloseLink: true,
       dimensions: { w: 750, h : 525}
    });

// open the lightbox
    myLightbox.open();
}
  </script> 
</bbNG:jsBlock>
   
   
        <bbNG:pageHeader>
        <bbNG:breadcrumbBar>
            <bbNG:breadcrumb>Administrator Logbook</bbNG:breadcrumb>
        </bbNG:breadcrumbBar>
        <bbNG:pageTitleBar >Administrator Logbook</bbNG:pageTitleBar>
    </bbNG:pageHeader>
    <% String user_id = ctx.getUser().getBatchUid(); %>
    

    <h1>Hello <%=ctx.getUser().getGivenName()%>&nbsp;<%=ctx.getUser().getFamilyName()%>!</h1>
    <a href="./jsp/newLogPage.jsp">Create a New Log Entry</a><br/>
    <bbNG:inventoryList listId="listContainer1" 
                        className="com.blackboard.bbdn.alltoolsb2.Log"
                        objectVar="logEntry"
                        collection="<%=logbook.getLogs()%>"
                        description="Log entries."
                        emptyMsg="No eligible logs." 
                        showAll="true" 
                        displayPagingControls="true">
         <bbNG:listElement  name="createdDt" isRowHeader="true" label="Date Created">
             <bbNG:beanComparator property="createdDate"/>
             <a href="#open" onClick="javascript:openLightbox('./jsp/lightboxLogView.jsp?id=<%=logEntry.getUuid()%>','Log View'); return false;">
             <%=logEntry.getCreatedDateStr()%></a>
             
            <bbNG:listContextMenu order="edit,view">
                <bbNG:contextMenuItem title="View" 
                         url="javascript:openLightbox('./jsp/lightboxLogView.jsp?id=${logEntry.getUuid()}', 'Viewing: ${logEntry.getIssueTitle()}');" id="view"/>
                <bbNG:contextMenuItem title="Edit" 
                         url="./jsp/editLogPage.jsp?id=${logEntry.getUuid()}"/>
            </bbNG:listContextMenu>
         </bbNG:listElement>
         <bbNG:listElement  name="updatedDate" label="Date Updated">
             <bbNG:beanComparator property="updatedDate"/> 
             <%=logEntry.getUpdatedDateStr()%>
         </bbNG:listElement>
         <bbNG:listElement  name="createdByName" label="Created By">
            <bbNG:beanComparator property="createdByName"/>
            <%=logEntry.getCreatedByName()%>
         </bbNG:listElement>
         <bbNG:listElement  name="issueTitle" label="Issue Title">
            <bbNG:beanComparator property="issueTitle"/> 
            <%=logEntry.getIssueTitle()%>
         </bbNG:listElement>
         <bbNG:listElement  name="issueType" label="Issue Type">
            <bbNG:beanComparator property="issueType"/> 
            <%=logEntry.getIssueTypeStr()%>
         </bbNG:listElement>
         <bbNG:listElement  name="isResolved" label="Resolved?">
            <bbNG:beanComparator property="isResolved"/> 
            <%=logEntry.getIsResolved()?"Yes":"No"%>
         </bbNG:listElement>  
         <bbNG:listElement  name="isSubmitted" label="Submitted to Support?">
            <bbNG:beanComparator property="isSubmitted"/> 
            <%=logEntry.getIsSubmitted()?"Yes":"No"%>
         </bbNG:listElement>  
         <bbNG:listElement  name="ticketNo" label="Support Ticket No.">
            <bbNG:beanComparator property="ticketNo"/> 
            <%=logEntry.getTicketNo()%>
         </bbNG:listElement>  
    </bbNG:inventoryList>
    <a href="./jsp/newLogPage.jsp">Create a New Log Entry</a><br/><br/>
    <hr>
    <a rel="license" href="https://creativecommons.org/licenses/by/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="https://creativecommons.org/licenses/by/4.0/">Creative Commons Attribution 4.0 International License</a>.
</bbNG:learningSystemPage>
