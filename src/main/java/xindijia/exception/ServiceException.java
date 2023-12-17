package xindijia.exception;

/**
 * 自定义业务异常
 * @author xia
 * @since 2023/12/17/15:40
 */
public class ServiceException extends RuntimeException{
    public ServiceException(String message) {
        super(message);
    }
}
