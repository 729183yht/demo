package cn.itcats.exception;

/**
 * 自定义异常类
 */
public class SysException extends Exception {

    private String message;

    @Override
    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }
    //构造方法，必须提供异常
    public SysException(String message) {
        this.message = message;
    }
}
