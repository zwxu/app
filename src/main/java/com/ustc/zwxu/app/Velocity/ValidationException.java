package com.ustc.zwxu.app.Velocity;



/**
 * <p>校验异常</p>
 * @author
 * @version
 */
public class ValidationException extends Exception {
    private static final long serialVersionUID = -2902025037137372147L;

    /**
     * 默认构造
     */
    public ValidationException() {
        super();
    }

    /**
     * 通过信息构造
     * @param message
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * 根据原因构造
     * @param cause
     */
    public ValidationException(Throwable cause) {
        super(cause);
    }

    /**
     * 根据信息和原因构造
     * @param message
     * @param cause
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}

