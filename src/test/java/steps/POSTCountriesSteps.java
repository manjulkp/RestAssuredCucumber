package steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.core.IsNot;
import utilities.RestAssuredExtension;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class POSTCountriesSteps {

    public static ResponseOptions<Response> response;

    
    @Given("^I perform GET operation for \"([^\"]*)\" with with countries$")
    public void i_perform_GET_operation_for_with_with_countries(String url, DataTable table) throws Throwable {
    	var data = table.raw();

        HashMap<String, String> body = new HashMap<>();
        body.put("access_key", data.get(1).get(0));
        body.put("format", data.get(1).get(1));
       
        body.put("symbols", data.get(1).get(2));
        System.out.println(body.get("symbols"));
       response = RestAssuredExtension.PostOpsWithBody(url, body);
    }



}
