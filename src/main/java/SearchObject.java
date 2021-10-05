public class SearchObject {
    private String sid;
    private String rid;
    private int pages_count;
    private int page;
    private SubmissionObject[] submissions;

    public SearchObject(String sid, String rid, int pages_count, int page, SubmissionObject[] submissions) {
        this.sid = sid;
        this.rid = rid;
        this.pages_count = pages_count;
        this.page = page;
        this.submissions = submissions;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public int getPages_count() {
        return pages_count;
    }

    public void setPages_count(int pages_count) {
        this.pages_count = pages_count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public SubmissionObject[] getSubmissions() {
        return submissions;
    }

    public void setSubmissions(SubmissionObject[] submissions) {
        this.submissions = submissions;
    }
}
