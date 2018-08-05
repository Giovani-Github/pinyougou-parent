package entity;

/**
 * 返回信息封装类
 *
 * @Author Giovani
 * @Create: 2018/8/5 16:04
 */
public class Result {

    private boolean success; // 是否成功
    private String message; // 信息

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
