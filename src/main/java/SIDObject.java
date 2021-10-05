public class SIDObject {
    private String sid;
    private String user_id;
    private String ratingsmask;


    public SIDObject(String sid, String user_id, String ratingsmask) {
        this.sid = sid;
        this.user_id = user_id;
        this.ratingsmask = ratingsmask;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRatingsmask() {
        return ratingsmask;
    }

    public void setRatingsmask(String ratingsmask) {
        this.ratingsmask = ratingsmask;
    }
}