package steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utilities.RestAssuredExtension;

import javax.xml.crypto.Data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class GETSteps {

	public static ResponseOptions<Response> response;

	@Given("^I perform GET operation for \"([^\"]*)\"$")
	public void iPerformGETOperationFor(String url) throws Throwable {
		response = RestAssuredExtension.GetOps(url);
	}

	@Given("^I perform GET operation for \"([^\"]*)\" with body$")
	public void i_perform_GET_operation_for_with_body(String url, DataTable table) throws Throwable {
		var data = table.raw();

		HashMap<String, String> body = new HashMap<>();
		body.put("access_key", data.get(1).get(0));
		body.put("format", data.get(1).get(1));
		response = RestAssuredExtension.GetOpsQueryParamsWithMap(url, body);
	}

	@Then("^I should see the base  is  \"([^\"]*)\"$")
	public void i_should_see_the_base_is(String base) throws Throwable {
		assertThat(response.getBody().jsonPath().get("success"), equalTo(true));
		assertThat(response.getBody().jsonPath().get("base"), equalTo(base));
	}

	@Then("^the response status is (\\d+)$")
	public void the_response_status_is(int statusCode) throws Throwable {
		assertThat(response.getStatusCode(), equalTo(statusCode));
	}

	@Then("^I should see the response carrying a number of common world currencies$")
	public void i_should_see_the_response_carrying_a_number_of_common_world_currencies() throws Throwable {
		Map<String, HashMap<String, Object>> rate = response.getBody().jsonPath().getMap("rates");
		assertThat(rate.size(), equalTo(168));
		assertThat(rate.containsKey("USD"), equalTo(true));
	}

	@Then("^the response status has current date in the response$")
	public void the_response_status_has_current_date_in_the_response() throws Throwable {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		assertThat(response.getBody().jsonPath().get("date"), equalTo(formatter.format(date)));

	}

	@Then("^I should see the response carrying \"([^\"]*)\" world currencies$")
	public void i_should_see_the_response_carrying_world_currencies(String arg1) throws Throwable {
		Map<String, HashMap<String, Object>> rate = response.getBody().jsonPath().getMap("rates");
		System.out.println(rate.size());
		assertThat(rate.size(), equalTo(10));
	}

	@Then("^the response is stored in CSV$")
	public void the_response_is_stored_in_CSV() throws Throwable {

		InputStream initialStream = response.getBody().asInputStream();
		byte[] buffer = new byte[initialStream.available()];
		initialStream.read(buffer);

		File targetFile = new File("src/test/java/resource/OutputInCSV.csv");
		OutputStream outStream = new FileOutputStream(targetFile);
		outStream.write(buffer);

	}

}
