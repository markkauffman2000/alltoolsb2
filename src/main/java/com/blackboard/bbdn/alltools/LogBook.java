/*
Copyright (C) 2011, Blackboard Inc.
All rights reserved.
Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

Neither the name of Blackboard Inc. nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY BLACKBOARD INC ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BLACKBOARD INC. BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package com.blackboard.bbdn.alltools;


import blackboard.base.FormattedText;
import blackboard.data.AbstractIdentifiable;
import blackboard.persist.DataType;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.impl.mapping.annotation.Column;
import blackboard.persist.impl.mapping.annotation.PrimaryKey;
import blackboard.persist.impl.mapping.annotation.Table;

import com.blackboard.bbdn.alltools.Log;
import com.blackboard.bbdn.alltools.LogDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Returns an Logbook object containing all admin Log entries
 * <p>
 * To be loaded on access to the admin Log page for retrieval of entries. 
 *
 * @param  none
 * @return      this LogBook
 * @see         logEntry
 */
public class LogBook {
    LogDAO dao = new LogDAO();
            
    public LogBook() {
    }
    
    public Log getLog(String uuid) {
        Log logEntry = null;
        //System.out.println("LogBook:getLog():uuid:" + uuid);
        
        try {
            logEntry = dao.loadByUuid(uuid);
        } catch (KeyNotFoundException e) {
            
        } catch (Exception ex) {
            System.out.print(ex.toString());
        }
        return logEntry;
    }
    
    public List<Log> getLogs() {
        List<Log> logs = null;
        
        logs = dao.loadAll();
        
        return logs;
    }
    
    public List<Log> getLogsByStatus(boolean isResolved) {
        List<Log> logs = null;
        
        logs = dao.loadByStatus(isResolved);
        
        return logs;
    }
    
/* Besides a loadAll, and by status
 * per requirements we will also want to return results on:
 *  Issue type
 *  Dates created/updated - equals, before, after, range
 *  isResolved?
 *  Support? (Submitted)
 *  Support ticket number
 *  Status
 */
    List<Log> getLogsByCreator(String batchuid) {
        List<Log> logs = null;
        
        logs = dao.loadByCreator(batchuid);
        
        return logs;
    }
    
    List<Log> getLogsByUpdator(String batchuid) {
        List<Log> logs = null;
        
        logs = dao.loadByUpdater(batchuid);
        
        return logs;
    }
    
    List<Log> getLogsByDate(Calendar date, String event ) {
        List<Log> logs = null;
 
        if (event.equalsIgnoreCase("created")) {
            logs = dao.loadByCreatedDate(date);
        } else if (event.equalsIgnoreCase("updated")) {
            logs = dao.loadByUpdatedDate(date);
        } 
        
        return logs;
    }
    
    List<Log> getLogsBeforeDate(Calendar date, String event ) {
        List<Log> logs = null;
        if (event.equalsIgnoreCase("created")) {
            logs = dao.loadBeforeCreatedDate(date);
        } else if (event.equalsIgnoreCase("updated")) {
            logs = dao.loadBeforeUpdatedDate(date);
        }
        
        return logs;
    }
    
    List<Log> getLogsAfterDate(Calendar date, String event ) {
        List<Log> logs = null;
        if (event.equalsIgnoreCase("created")) {
            logs = dao.loadAfterCreatedDate(date);
        } else if (event.equalsIgnoreCase("updated")) {
            logs = dao.loadAfterUpdatedDate(date);
        }
        
        return logs;
    }
    
    List<Log> getLogsByDateRange(Calendar startDate, Calendar endDate, String event ) {
        List<Log> logs = null;
        if (event.equalsIgnoreCase("created")) {
            logs = dao.loadByCreatedDateRange(startDate, endDate);
        } else if (event.equalsIgnoreCase("updated")) {
            logs = dao.loadByUpdatedDateRange(startDate, endDate);
        }
        
        return logs;
    }
    
    List<Log> getLogsBySubmitted(boolean isSubmitted) {
        List<Log> logs = null;
        
        logs = dao.loadBySubmitted(isSubmitted);
        
        return logs;
    }
    
    public void saveLogEntry(Map entry) {
        
        Log newLog = new Log();
        String createdOnDateStr = null;
        Calendar createdOnDate = null;
        UUID uuid = UUID.randomUUID();
        String uuidStr = "";
        
        /* note - updatedById and updatedOnDate not captured as this is a new Log entry... */
        newLog.setCreateId(((String[]) entry.get("createdById"))[0]);
        newLog.setIsResolved(((String[]) entry.get("isResolved"))[0].equalsIgnoreCase("y"));
        newLog.setIssueType(((String[]) entry.get("issueType"))[0]);
        newLog.setIsSubmitted(((String[]) entry.get("isSubmitted"))[0].equalsIgnoreCase("y"));
        newLog.setIssueTitle(((String[]) entry.get("issueTitle"))[0]);
        newLog.setTicketNo(((String[]) entry.get("ticketNo"))[0]);
        newLog.setIssue(((String[]) entry.get("issuetext"))[0]);
        newLog.setSolution(((String[]) entry.get("solutiontext"))[0]);
        newLog.setNotes(((String[]) entry.get("notestext"))[0]);
        newLog.setUpdatedById(((String[]) entry.get("createdById"))[0]);
        uuidStr = uuid.toString();
        newLog.setUuid(uuidStr);
        
        /* handle dates 
            passed value is in:
                createdOnDate_date
        */
        createdOnDateStr = ((String[]) entry.get("createdOnDate_date"))[0];
        createdOnDate = Calendar.getInstance();
        
        if (!isEmpty(createdOnDateStr)) {
            //parse the incoming string into a date
            createdOnDateStr = createdOnDateStr.replaceAll("%2F", "/");
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            try {
                createdOnDate.setTime(sdf.parse(createdOnDateStr));
            } catch (ParseException ex) {
                Logger.getLogger(LogBook.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        newLog.setUpdatedDate(createdOnDate);
        //System.out.println("setUpdatedDate:"+newLog.getUpdatedDate().toString());

        newLog.setCreatedDate(createdOnDate);
        //System.out.println("setCreatedDate:"+newLog.getCreatedDate().toString());
        
        dao.persist(newLog);
        
    }
    
    public void updateLogEntry(Map entry) {
        
        
        String updatedOnDateStr = null;
        Calendar updatedOnDate = null;
        //UUID uuid = UUID.randomUUID();
        String uuidStr = ((String[]) entry.get("uuid"))[0];
        Log newLog = getLog(uuidStr);
        newLog.setUpdatedById(((String[]) entry.get("updatedById"))[0]);
        newLog.setIsResolved(((String[]) entry.get("isResolved"))[0].equalsIgnoreCase("y"));
        newLog.setIssueType(((String[]) entry.get("issueType"))[0]);
        newLog.setIsSubmitted(((String[]) entry.get("isSubmitted"))[0].equalsIgnoreCase("y"));
        newLog.setIssueTitle(((String[]) entry.get("issueTitle"))[0]);
        newLog.setTicketNo(((String[]) entry.get("ticketNo"))[0]);
        newLog.setIssue(((String[]) entry.get("issuetext"))[0]);
        newLog.setSolution(((String[]) entry.get("solutiontext"))[0]);
        newLog.setNotes(((String[]) entry.get("notestext"))[0]);
        
        /* handle dates 
            passed value is in:
                updatedOnDate_date
        */
        updatedOnDateStr = ((String[]) entry.get("updatedOnDate_date"))[0];
        updatedOnDate = Calendar.getInstance();
        
        if (!isEmpty(updatedOnDateStr)) {
            //parse the incoming string into a date
            updatedOnDateStr = updatedOnDateStr.replaceAll("%2F", "/");
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            try {
                updatedOnDate.setTime(sdf.parse(updatedOnDateStr));
            } catch (ParseException ex) {
                Logger.getLogger(LogBook.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        newLog.setUpdatedDate(updatedOnDate);
        //System.out.println("setUpdatedDate:"+newLog.getUpdatedDate().toString());
        
        dao.persist(newLog);
        
    }
    
    public static boolean isEmpty(String s) {
        return (s == null || s.length() == 0);
    }
}
