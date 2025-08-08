package com.test.model.to;

public class TestInfoTO {

    private int testid;
    private String testname;
    private String subjectid;
    private String testlevel;
    private int totalquestion;
    private String username;
    private String subjectname;

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

    /**
     * @return the subjectid
     */
    public String getSubjectid() {
        return subjectid;
    }

    /**
     * @param subjectid the subjectid to set
     */
    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    /**
     * @return the testlevel
     */
    public String getTestlevel() {
        return testlevel;
    }

    /**
     * @param testlevel the testlevel to set
     */
    public void setTestlevel(String testlevel) {
        this.testlevel = testlevel;
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
     * @return the subjectname
     */
    public String getSubjectname() {
        return subjectname;
    }

    /**
     * @param subjectname the subjectname to set
     */
    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }
    
    public String toString(){
        return testname;
    }
}
