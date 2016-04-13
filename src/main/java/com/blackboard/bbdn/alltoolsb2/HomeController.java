/*
Copyright (C) 2015, Blackboard Inc.
All rights reserved.
Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

Neither the name of Blackboard Inc. nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY BLACKBOARD INC ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BLACKBOARD INC. BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package com.blackboard.bbdn.alltoolsb2;

import blackboard.data.course.Course;
import blackboard.data.course.CourseMembership;
import blackboard.data.course.CourseMembership.Role;
import blackboard.data.course.Organization;
import blackboard.data.user.User;
import blackboard.persist.Id;

import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.persist.course.CourseMembershipDbPersister;

import blackboard.admin.data.course.Enrollment;
import blackboard.admin.persist.course.EnrollmentLoader;
import blackboard.admin.persist.course.EnrollmentPersister;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

        @RequestMapping(value = "/course_tool", method = RequestMethod.GET)
	public String courseTool(Locale locale, Model model) {
                CourseDbLoader crsLoader;
                List<CourseMembership> memberships = new ArrayList<CourseMembership>();
              
                Course theCourse = null;
                Id theCourseId =null;
                String theCourseIdExternalString ="";
                
                String theCourseTitle = "no course title";
                
                try {
                    crsLoader = CourseDbLoader.Default.getInstance();
                    theCourse = crsLoader.loadByCourseId("mbk-test-two");
                    theCourseTitle = theCourse.getTitle();
                    theCourseId = theCourse.getId();
                    theCourseIdExternalString = theCourse.getId().toExternalString();
                    if (theCourseIdExternalString == null)
                    {
                        theCourseIdExternalString = "result was a null";
                    } else {
                        if (theCourseIdExternalString.equals(""))
                        theCourseIdExternalString = "result was an empty string";
                    }                           
                   
                }
                catch ( Exception e )
                {
                    logger.info("Exception:", e.getMessage());
                    e.printStackTrace();
                }
                
		logger.info("Requested /system_tool! The client locale is {}.", locale);
                
                try {
                    memberships = CourseMembershipDbLoader.Default.getInstance().loadByCourseId(theCourse.getId()); 
                
                }catch (Exception e)
                {
                    logger.info("Exception:", e.getMessage());
                    e.printStackTrace();
                }
                
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("theCourseTitle",theCourseTitle);
                model.addAttribute("theCourse",theCourse);
                model.addAttribute("theCourseIdExternalString", theCourseIdExternalString);
		return "course_tool";
	} // courseTool
        
	@RequestMapping(value = "/system_tool", method = RequestMethod.GET)
	public String systemTool(Locale locale, Model model) {
                CourseDbLoader crsLoader;
                List<CourseMembership> memberships = new ArrayList<CourseMembership>();
              
                Course theCourse = null;
                Id theCourseId =null;
                String theCourseIdExternalString ="";
                
                String theCourseTitle = "no course title";
                
                try {
                    crsLoader = CourseDbLoader.Default.getInstance();
                    theCourse = crsLoader.loadByCourseId("mbk-test-two");
                    theCourseTitle = theCourse.getTitle();
                    theCourseId = theCourse.getId();
                    theCourseIdExternalString = theCourse.getId().toExternalString();
                    if (theCourseIdExternalString == null)
                    {
                        theCourseIdExternalString = "result was a null";
                    } else {
                        if (theCourseIdExternalString.equals(""))
                        theCourseIdExternalString = "result was an empty string";
                    }                           
                   
                }
                catch ( Exception e )
                {
                    logger.info("Exception:", e.getMessage());
                    e.printStackTrace();
                }
                
		logger.info("Requested /system_tool! The client locale is {}.", locale);
                
                try {
                    memberships = CourseMembershipDbLoader.Default.getInstance().loadByCourseId(theCourse.getId()); 
                
                }catch (Exception e)
                {
                    logger.info("Exception:", e.getMessage());
                    e.printStackTrace();
                }
                
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("theCourseTitle",theCourseTitle);
                model.addAttribute("theCourse",theCourse);
                model.addAttribute("theCourseIdExternalString", theCourseIdExternalString);
		return "system_tool";
	} // systemTool


	@RequestMapping(value = "/learnhello", method = RequestMethod.GET)
	public String learnhello(Locale locale, Model model) {
		logger.info("Requested /learnhello! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "learnhello";
	}
	
}
