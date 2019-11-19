package cc.mrbird.common.aws;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



public class UserException extends Exception {
    String errorCode = "default";
    Long uid = null;

    public UserException(String errCode, String message) {
        super(message);
        this.errorCode = errCode;
    }

    public UserException(Long uid, String errCode, String message) {
        super(message);
        this.uid = uid;
        this.errorCode = errCode;
    }

    public UserException() {
    }

    public UserException(Throwable e) {
        super(e);
    }

    public UserException(String message) {
        super(message);
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Long getUid() {
        return this.uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}
