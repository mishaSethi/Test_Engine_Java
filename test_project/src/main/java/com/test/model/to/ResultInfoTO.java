package com.test.model.to;

import java.sql.Timestamp;



public class ResultInfoTO {

    private int resultid;
    private Timestamp resultdate;
    private String username;
    private int testid;
    private int totalquestion;
    private int attempquestion;
    private int rightquestion;
    private int obtainedmarks;
    
    // This column is used for storing joined information and it is not linked to physical data
    private String testname;

    /**
     * @return the resultid
     */
    public int getResultid() {
        return resultid;
    }

    /**
     * @param resultid the resultid to set
     */
    public void setResultid(int resultid) {
        this.resultid = resultid;
    }

    /**
     * @return the resultdate
     */
    public Timestamp getResultdate() {
        return resultdate;
    }

    /**
     * @param resultdate the resultdate to set
     */
    public void setResultdate(Timestamp resultdate) {
        this.resultdate = resultdate;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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
     * @return the totalquestion
     */
    public int getTotalquestion() {
        return totalquestion;
    }

    /**
     * @param totalquestion the totalquestion to set
     */
    public void setTotalquestion(int totalquestion) {
        this.totalquestion = totalquestion;
    }

    /**
     * @return the attempquestion
     */
    public int getAttempquestion() {
        return attempquestion;
    }

    /**
     * @param attempquestion the attempquestion to set
     */
    public void setAttempquestion(int attempquestion) {
        this.attempquestion = attempquestion;
    }

    /**
     * @return the rightquestion
     */
    public int getRightquestion() {
        return rightquestion;
    }

    /**
     * @param rightquestion the rightquestion to set
     */
    public void setRightquestion(int rightquestion) {
        this.rightquestion = rightquestion;
    }

    /**
     * @return the obtainedmarks
     */
    public int getObtainedmarks() {
        return obtainedmarks;
    }

    /**
     * @param obtainedmarks the obtainedmarks to set
     */
    public void setObtainedmarks(int obtainedmarks) {
        this.obtainedmarks = obtainedmarks;
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
