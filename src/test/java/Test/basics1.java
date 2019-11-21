package Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class basics1 {

	@Test
public void getPlace()
{
		
		//BaseURL or Host
		RestAssured.baseURI="http://{{host}}:{{port}}";

	Response res= (Response) given().
		       when().
		       get("/Personnel/GetGroups?terminalCode=007").
		       then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		       body("results[0].Success",equalTo("true"));

	JsonPath js= ReusableMethods.rawToJson(res);
	int count=js.get("Pressonnel");
	for(int i=0;i<count;i++)
	{
		System.out.println("Number of objects in Personnel" + i);
	}
	System.out.println(count);
	
}
}
