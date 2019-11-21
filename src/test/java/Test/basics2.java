package Test;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class basics2 {


	@Test
	public void createPlaceAPI()
	{
		RestAssured.baseURI=" http://{{host}}:{{port}}";
		Response res= (Response) given().header("Content-Type", "application/json").
		body("{\n" +
				"    \"From\": \"2019-02-18T00:00:00+03:00\",                       \n" +
				"    \"To\": \"2019-02-18T23:59:59+03:00\",                         \n" +
				"    \"WorkPlaceCode\": \"516551\",                                 \n" +
				"    \"WorkPlaceOid\": \"05561360-02b2-47fa-be99-0d42a92562e8\",    \n" +
				"    \"PersonnelCode\": \"00100105\",                               \n" +
				"    \"PersonnelOid\": \"0733ec7b-c618-44d9-9bc4-0b3e4caef640\",    \n" +
				"    \"TerminalCode\": \"007\"                                      \n" +
				"}\n").
		when().
		post("/Events/Get").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK"));


		JsonPath js= ReusableMethods.rawToJson(res);
		int count=js.get("Events");
		for(int i=0;i<count;i++)
		{
			System.out.println("Number of objects in Events" + i);
		}
		System.out.println(count);
		

		

	}
}
