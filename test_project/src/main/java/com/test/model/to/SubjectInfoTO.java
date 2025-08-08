package com.test.model.to;

public class SubjectInfoTO {

    private String subjectid;
    private String subjectname;
    private String description;

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

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String toString(){
        return subjectname;
    }
}
