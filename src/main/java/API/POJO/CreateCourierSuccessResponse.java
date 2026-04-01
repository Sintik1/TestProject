package API.POJO;

public class CreateCourierSuccessResponse {
    private Boolean ok;


    public CreateCourierSuccessResponse(Boolean ok) {
        this.ok = ok;

    }

    public CreateCourierSuccessResponse(){

    }

    public boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

}
