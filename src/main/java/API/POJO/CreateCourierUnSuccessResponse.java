package API.POJO;

public class CreateCourierUnSuccessResponse {

    private Integer code;
    private  String message;



    public CreateCourierUnSuccessResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CreateCourierUnSuccessResponse() {
    }

    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

