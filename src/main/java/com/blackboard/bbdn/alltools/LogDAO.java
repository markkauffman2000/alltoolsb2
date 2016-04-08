

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

import java.util.List;

import blackboard.persist.KeyNotFoundException;
import blackboard.persist.dao.impl.SimpleDAO;
import blackboard.persist.impl.SimpleSelectQuery;
import blackboard.util.StringUtil;

import com.blackboard.bbdn.alltools.Log;
import java.util.Calendar;


public class LogDAO extends SimpleDAO<Log> {

    public LogDAO() {
        super(Log.class);
    }
    public LogDAO(Class<Log> cls) {
        super(cls);
    }
    
    public List<Log> loadAll() {
        return getDAOSupport().loadAll();
    }
    
    public List<Log> loadByCreator(String id) {
        SimpleSelectQuery query = new SimpleSelectQuery(getDAOSupport().getMap());
        //Change column name to whatever the name is in the bean
        query.addWhere("createdBy", id);
        query.addOrderBy("createdOnDate", true);

        return getDAOSupport().loadList(query);
    }
    
    public List<Log> loadByUpdater(String id) {
        SimpleSelectQuery query = new SimpleSelectQuery(getDAOSupport().getMap());
        //Change column name to whatever the name is in the bean
        query.addWhere("updatedBy", id);
        query.addOrderBy("createdOnDate", true);

        return getDAOSupport().loadList(query);
    }

/* Besides a loadAll, and by submitted
 * per requirements we will also want to return results on:
 *  Issue type
 *  Dates created/updated - equals, before, after, range
 *  isResolved?
 *  Support? (Submitted)
 *  Support ticket number
 *  Status
 */
 
    List<Log> loadByStatus(boolean isResolved) {
        SimpleSelectQuery query = new SimpleSelectQuery(getDAOSupport().getMap());
        //Change column name to whatever the name is in the bean
        query.addWhere("isResolved", isResolved?"Y":"N");
        query.addOrderBy("createdOnDate", true);

        return getDAOSupport().loadList(query);
    }
        
    List<Log> loadBySubmitted(boolean isSubmitted) {
        SimpleSelectQuery query = new SimpleSelectQuery(getDAOSupport().getMap());
        //Change column name to whatever the name is in the bean
        query.addWhere("isSubmitted", isSubmitted?"Y":"N");
        query.addOrderBy("createdOnDate", true);

        return getDAOSupport().loadList(query);
    }

    List<Log> loadByCreatedDate(Calendar date) {
        SimpleSelectQuery query = new SimpleSelectQuery(getDAOSupport().getMap());
        
        query.addWhere("createdOnDate", date);
        query.addOrderBy("createdOnDate", true);
        
        return getDAOSupport().loadList(query);
    }

    List<Log> loadByUpdatedDate(Calendar date) {
        SimpleSelectQuery query = new SimpleSelectQuery(getDAOSupport().getMap());
        
        query.addWhere("updatedOnDate", date);
        query.addOrderBy("updatedOnDate", true);
        
        return getDAOSupport().loadList(query);
    }

    List<Log> loadBeforeCreatedDate(Calendar date) {
        SimpleSelectQuery query = new SimpleSelectQuery(getDAOSupport().getMap());
        query.addDateBeforeWhere("createdOnDate", date);
        query.addOrderBy("createdOnDate", true);

        return getDAOSupport().loadList(query);
    }

    List<Log> loadBeforeUpdatedDate(Calendar date) {
        SimpleSelectQuery query = new SimpleSelectQuery(getDAOSupport().getMap());
        query.addDateBeforeWhere("updatedOnDate", date);
        query.addOrderBy("updatedOnDate", true);

        return getDAOSupport().loadList(query);
    }

    List<Log> loadAfterCreatedDate(Calendar date) {
        SimpleSelectQuery query = new SimpleSelectQuery(getDAOSupport().getMap());
        query.addDateAfterWhere("createdOnDate", date);
        query.addOrderBy("createdOnDate", true);
        
        return getDAOSupport().loadList(query);
    }    
    
    List<Log> loadAfterUpdatedDate(Calendar date) {
        SimpleSelectQuery query = new SimpleSelectQuery(getDAOSupport().getMap());
        query.addDateAfterWhere("updatedOnDate", date);
        query.addOrderBy("updatedOnDate", true);
        
        return getDAOSupport().loadList(query);
    }

    List<Log> loadByCreatedDateRange(Calendar beginDate, Calendar endDate) {
        SimpleSelectQuery query = new SimpleSelectQuery(getDAOSupport().getMap());
        query.addDateAfterWhere("createdOnDate", beginDate);
        query.addDateBeforeWhere("createdOnDate", endDate);
        query.addOrderBy("createdOnDate", true);

        return getDAOSupport().loadList(query);
    }

    List<Log> loadByUpdatedDateRange(Calendar beginDate, Calendar endDate) {
        SimpleSelectQuery query = new SimpleSelectQuery(getDAOSupport().getMap());
        query.addDateAfterWhere("updatedOnDate", beginDate);
        query.addDateBeforeWhere("updatedOnDate", endDate);
        query.addOrderBy("updatedOnDate", true);

        return getDAOSupport().loadList(query);
    }

    public Log loadByUuid(String uuid) throws KeyNotFoundException {    
        if (!StringUtil.isEmpty(uuid)) {
            //System.out.println("LogDAO:loadByUuid:in uuid:" + uuid);
        
            SimpleSelectQuery query = new SimpleSelectQuery(getDAOSupport().getMap());
            query.addWhere("uuid", uuid);   
            
            return getDAOSupport().load(query);
        }
        
        //System.out.println("LogDAO:loadByUuid:uuid not passed");

        return null;
    }
}
