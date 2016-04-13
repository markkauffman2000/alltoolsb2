/*
Copyright (C) 2011, Blackboard Inc.
All rights reserved.
Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

Neither the name of Blackboard Inc. nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY BLACKBOARD INC ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BLACKBOARD INC. BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.blackboard.bbdn.alltoolsb2;


import blackboard.base.FormattedText;
import blackboard.data.AbstractIdentifiable;
import blackboard.data.user.User;
import blackboard.persist.DataType;
import blackboard.persist.PersistenceException;
import blackboard.persist.impl.mapping.annotation.Column;
import blackboard.persist.impl.mapping.annotation.PrimaryKey;
import blackboard.persist.impl.mapping.annotation.Table;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Table("bbdn_adminlogbook") //the object class uses this table
public class Log extends AbstractIdentifiable {

    /* Define the DATA_TYPE for this object - works with DAO/Annotations */
    public static final DataType DATA_TYPE = new DataType(Log.class);

    public DataType getDataType() {
        return DATA_TYPE;
    }

    @PrimaryKey
    private int pk1;

    @Column(value = {"createdById"})
    private String createdById;

    @Column(value = {"issueType"})
    private String issueType;

    @Column(value = {"isResolved"})
    private String isResolved;
            
    @Column(value = { "isSubmitted" })
    private String isSubmitted;
            
    @Column(value = { "updatedById" })
    private String updatedById;
            
    @Column(value = { "ticketNo" })
    private String ticketNo;

    @Column(value = {"updatedOnDate"})
    private Calendar updatedOnDate;

    @Column(value = {"createdOnDate"})
    private Calendar createdOnDate;

    @Column(value = {"notes"}, lob = true, multiByte = true)
    private String notes;

    @Column(value = {"issue"}, lob = true, multiByte = true)
    private String issue;

    @Column(value = {"solution"}, lob = true, multiByte = true)
    private String solution;

    @Column(value = {"issueTitle"})
    private String issueTitle;
    
    @Column(value = {"uuid"})
    private String uuid;
    
    /*
     * Constructors
     */
    public Log() {
    }

    public Log(int pk1, String createdById, String issueType, String isResolved, 
               String isSubmitted, String updatedById, String ticketNo, 
               Calendar updatedOnDate, Calendar createdOnDate, String notes, String issue, 
               String solution) {
        this.pk1 = pk1;
        this.createdById = createdById;
        this.issueType = issueType;
        this.isResolved = isResolved;
        this.isSubmitted = isSubmitted;
        this.updatedById = updatedById;
        this.ticketNo = ticketNo;
        this.updatedOnDate = updatedOnDate;
        this.createdOnDate = createdOnDate;
        this.notes = notes;
        this.issue = issue;
        this.solution = solution;
    }
    
    /* 
     * <column name="createdById" data-type="nvarchar(64)" nullable="false" 
     * comment="The system administrator batch_uid who submitted the Log entry. 
     * Used to pull the admin name into the UI"/>
     */
    public String getCreatedById() {
        return createdById;
    }

    public void setCreateId(String createdById) {
        this.createdById = createdById;
    }

    /* 
     * <column name="issueType" data-type="char(1)" nullable="false" 
     *  default="'M'" comment="Maintenance, Bug, Outage, Slowdown">
     *   <value-constraint name="bbdn_issueType_constraint">
     *     <accepted-value value="M" comment="Maintenance"/>
     *     <accepted-value value="B" comment="Bug"/>
     *     <accepted-value value="O" comment="Outage"/>
     *     <accepted-value value="S" comment="Slowdown"/>
     *   </value-constraint>
     * </column> 
     */
    public String getIssueType() {
        return issueType;
    }

    public String getIssueTypeStr() {
        String it = "";
        
        if (this.issueType.equalsIgnoreCase("m")) { 
            it = "Maintenance";
        } else if (this.issueType.equalsIgnoreCase("b")) {
            it = "Bug";
        } else if (this.issueType.equalsIgnoreCase("o")) {
            it = "Outage";
        } else if (this.issueType.equalsIgnoreCase("s")) {
            it = "Slowdown";
        }
        return it;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getIssueTitle() {
        return issueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        this.issueTitle = issueTitle;
    }

  
    /* <column name="isResolved" data-type="char(1)" nullable="false" 
     *   default="'N'" comment="Is the issue resolved?">
     *     <value-constraint name="bbdn_isResolved_constraint">
     *       <accepted-value value="Y" comment="Y==resolved"/>
     *       <accepted-value value="N" comment="N==Not resolved"/>
     *     </value-constraint>
     *   </column> 
     */
    public boolean getIsResolved() {
        return isResolved.equalsIgnoreCase("Y")?true:false;
    }

    public void setIsResolved(boolean isResolved) {
        this.isResolved  = isResolved?"Y":"N";
    }
    
    /*
     * <column name="submitted" data-type="char(1)" nullable="false" 
     *  default="'N'" comment="Is the issue submitted to Support">
     *  <value-constraint name="bbdn_submitted_constraint">
     *    <accepted-value value="Y" comment="Y==resolved"/>
     *    <accepted-value value="N" comment="N==Not resolved"/>
     *  </value-constraint>
     * </column> 
     */   
    public boolean getIsSubmitted() {
        return isSubmitted.equalsIgnoreCase("Y")?true:false;
    }

    public void setIsSubmitted(boolean isSubmitted) {
        this.isSubmitted = isSubmitted?"Y":"N";
    }

    /* <column name="updatedId" data-type="nvarchar(64)" nullable="false" 
     * comment="The batch_uid of the System Admin who updated the Log last."/> 
     */
    public String getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(String updatedById) {
        this.updatedById = updatedById;
    }

    /* <column name="ticketNo" data-type="nvarchar(36)" nullable="false" 
     * comment="The support ticket number."/> 
     */
    public String getTicketNo() {
        
        return notEmpty(ticketNo)?ticketNo:"Not Available";
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    /* <column name="updated" data-type="date" nullable="true" 
     * comment="the date that the issue was updated or resolved"/> 
     */
    public Calendar getUpdatedDate() {
        return updatedOnDate;
    }

    public void setUpdatedDate(Calendar updatedOnDate) {
        this.updatedOnDate = updatedOnDate;
    }
    
    public String getUpdatedDateStr() {
        Date updateDate = updatedOnDate.getTime();
        SimpleDateFormat date_format = new SimpleDateFormat("MM/dd/yyyy");
        return date_format.format(updateDate);
    }

    /* <column name="created" data-type="date" nullable="true" 
     * comment="The date that the Log was entered."/> 
     */
    public Calendar getCreatedDate() {
        return createdOnDate;
    }
    
    public String getCreatedDateStr() {
        Date createDate = createdOnDate.getTime();
        SimpleDateFormat date_format = new SimpleDateFormat("MM/dd/yyyy");
        return date_format.format(createDate);
    }
    
    public void setCreatedDate(Calendar createdOnDate) {
        this.createdOnDate = createdOnDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    /* <column name="issue" data-type="clob" nullable="true" 
     * comment="The description of the issue."/> 
     */
    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }
    
    /* <column name="solution" data-type="clob" nullable="true" 
     *   comment="The resolution to the entered Log issue."/> 
     */
    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
    
    public String getCreatedByName() {
        String name = null;
        
        blackboard.data.user.User usr;
        try {
            usr = blackboard.persist.user.impl.UserDbLoaderImpl.Default.getInstance().loadByBatchUid(this.getCreatedById());
            name = usr.getGivenName() + " " + usr.getFamilyName();

        } catch (PersistenceException ex) {
            name = "Unknown Log Entry Author.";
        }
        
        return name;
    }
    
    public String getUpdatedByName() {
        String name = null;
        
        blackboard.data.user.User usr;
        try {
            usr = blackboard.persist.user.impl.UserDbLoaderImpl.Default.getInstance().loadByBatchUid(this.getUpdatedById());
            name = usr.getGivenName() + " " + usr.getFamilyName();

        } catch (PersistenceException ex) {
            name = "Unknown Log Entry Author.";
        }
        
        return name;
    }
    
    public boolean notEmpty(String str) {
        return (str != null && !str.isEmpty());
    }
    
    public String generateUuid() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
    }

    public void setUuid(String randomUUIDString) {
        this.uuid = randomUUIDString;
    }
    
    
    public String getUuid(){
        return this.uuid;
    }
}
