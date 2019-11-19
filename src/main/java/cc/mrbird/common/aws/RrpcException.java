package cc.mrbird.common.aws;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



public class RrpcException extends Exception {
    String errorCode = "default";

    public RrpcException(String errCode, String message) {
        super(message);
        this.errorCode = errCode;
    }

    public RrpcException() {
    }

    public RrpcException(Throwable e) {
        super(e);
    }

    public RrpcException(String message) {
        super(message);
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
