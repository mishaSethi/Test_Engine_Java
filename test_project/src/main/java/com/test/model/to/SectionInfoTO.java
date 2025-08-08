package com.test.model.to;

public class SectionInfoTO {

    private String sectionid;
    private String sectionname;
    private String subjectid;

    /**
     * @return the sectionid
     */
    public String getSectionid() {
        return sectionid;
    }

    /**
     * @param sectionid the sectionid to set
     */
    public void setSectionid(String sectionid) {
        this.sectionid = sectionid;
    }

    /**
     * @return the sectionname
     */
    public String getSectionname() {
        return sectionname;
    }

    /**
     * @param sectionname the sectionname to set
     */
    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
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
    
    public String toString(){
        return sectionname;
    }
}
