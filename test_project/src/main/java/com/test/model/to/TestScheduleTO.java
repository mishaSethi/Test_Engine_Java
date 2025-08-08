package com.test.model.to;

import java.sql.Date;

public class TestScheduleTO {

    private int scheduleid;
    private String fusername;
    private String susername;
    private Date testdate;
    private int testid;
    private String testname;

    /**
     * @return the scheduleid
     */
    public int getScheduleid() {
        return scheduleid;
    }

    /**
     * @param scheduleid the scheduleid to set
     */
    public void setScheduleid(int scheduleid) {
        this.scheduleid = scheduleid;
    }

    /**
     * @return the fusername
     */
    public String getFusername() {
        return fusername;
    }

    /**
     * @param fusername the fusername to set
     */
    public void setFusername(String fusername) {
        this.fusername = fusername;
    }

    /**
     * @return the susername
     */
    public String getSusername() {
        return susername;
    }

    /**
     * @param susername the susername to set
     */
    public void setSusername(String susername) {
        this.susername = susername;
    }

    /**
     * @return the testdate
     */
    public Date getTestdate() {
        return testdate;
    }

    /**
     * @param testdate the testdate to set
     */
    public void setTestdate(Date testdate) {
        this.testdate = testdate;
    }

    /**
     * @return the testid
     */
    public int getTestid() {
        return testid;
    }

    /**
     * @param testid the testid to set
     */
    public void setTestid(int testid) {
        this.testid = testid;
    }

    /**
     * @return the testname
     */
    public String getTestname() {
        return testname;
    }

    /**
     * @param testname the testname to set
     */
    public void setTestname(String testname) {
        this.testname = testname;
    }
}
