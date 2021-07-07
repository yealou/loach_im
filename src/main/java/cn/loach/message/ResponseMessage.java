package cn.loach.message;

import cn.loach.util.MessageIdGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ResponseMessage extends Message{
    /**
     * 返回请求结果码
     */
    private int code;

    /**
     * 请求结果说明
     */
    private String responseMessage;

    /**
     * 请求成功与否表示
     */
    private boolean requestFlag;

    /**
     * 返回结果体
     */
    private Message responseContent;

    private ResponseMessage(int code, String responseMessage, boolean requestFlag, Message responseContent) {
        this.code = code;
        this.responseMessage = responseMessage;
        this.requestFlag = requestFlag;
        this.responseContent = responseContent;
        setMessageId(MessageIdGenerator.getMessageId());
        setTimeStamp(System.currentTimeMillis());
        if (null != responseContent) {
            setMessageRequestTypeType(responseContent.getMessageRequestTypeType());
        }
    }

    private static ResponseMessage success(int code, String responseMessage, RequestMessage responseContent) {
        return new ResponseMessage(code, responseMessage, true, responseContent);
    }

    public static ResponseMessage success(int code, String responseMessage) {
        return success(code, responseMessage, null);
    }

    public static ResponseMessage success() {
        return success(200, "request success", null);
    }

    public static ResponseMessage error(int code, String responseMessage) {
        return new ResponseMessage(code, responseMessage, false,  null);
    }

    public static ResponseMessage error() {
        return error(500, "request error");
    }


}
