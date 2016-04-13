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


<%@page import="blackboard.data.course.Course" %>

<jsp:useBean id="logbook" scope="page" class="com.blackboard.bbdn.alltoolsb2.LogBook"  />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="bbNG" uri="/bbNG"%>
<%@ taglib prefix="bbData" uri="/bbData" %>

<bbNG:learningSystemPage ctxId="ctx">
    
    <bbNG:pageHeader>
        <bbNG:breadcrumbBar environment="course" >
            <bbNG:breadcrumb>alltoolsb2-course_tool</bbNG:breadcrumb>
        </bbNG:breadcrumbBar>
        <bbNG:pageTitleBar >alltoolsb2-course_tool</bbNG:pageTitleBar>
    </bbNG:pageHeader>
    <% String user_id = ctx.getUser().getBatchUid(); %>
    

    <h1>Hello <%=ctx.getUser().getGivenName()%>&nbsp;<%=ctx.getUser().getFamilyName()%>!</h1>
    <h2>The course: ${theCourseTitle}  </h2>
    <h2>theCourseIdExternalString: ${theCourseIdExternalString}</h2>
    <h2>The theCourse.getId().toExternalString(): ${theCourse.getId().toExternalString()} </h2>
    <hr>
    <a rel="license" href="https://creativecommons.org/licenses/by/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="https://creativecommons.org/licenses/by/4.0/">Creative Commons Attribution 4.0 International License</a>.
</bbNG:learningSystemPage>
