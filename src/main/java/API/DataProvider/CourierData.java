package API.DataProvider;

import API.POJO.CreateCourierRequest;

import org.apache.commons.lang3.RandomStringUtils;


public class CourierData {

    public static CreateCourierRequest createCourierData(){
        CreateCourierRequest createCourierRequest = new CreateCourierRequest();
        createCourierRequest.setFirstName(RandomStringUtils.randomAlphabetic(8));
        createCourierRequest.setPassword(RandomStringUtils.randomAlphabetic(8));
        createCourierRequest.setLogin(RandomStringUtils.randomAlphabetic(8));
        return createCourierRequest;
    }

}
