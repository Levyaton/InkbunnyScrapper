public class SubmissionObject {
    private String submission_id;
    private String title;
    private String file_url_full;
    private String file_name;

    public SubmissionObject(String submission_id, String title, String file_url_full, String file_name) {
        this.submission_id = submission_id;
        this.title = title;
        this.file_url_full = file_url_full;
        this.file_name = file_name;
    }

    public String getSubmission_id() {
        return submission_id;
    }

    public void setSubmission_id(String submission_id) {
        this.submission_id = submission_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFile_url_full() {
        return file_url_full;
    }

    public void setFile_url_full(String file_url_full) {
        this.file_url_full = file_url_full;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
}
