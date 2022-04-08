package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import request.RequestFactory;

public class ProductTest extends BaseTest{
	
	
	
	@Test
	public void verifyGetRequest() {
		extentReport.createTestcase("Verify Get Product");
		Response response = requestFactory.getAllProducts();
		extentReport.addLog(Status.INFO, response.asPrettyString());
		response.then().statusCode(201);
	}
	
	@Test
	public void verifyAddProduct() {
		extentReport.createTestcase("Verify Add Product");
		String requestPayload = "{\n"
				+ "  \"name\": \"Samsung Mobile\",\n"
				+ "  \"type\": \"Mobile\",\n"
				+ "  \"price\": 100,\n"
				+ "  \"shipping\": 10,\n"
				+ "  \"upc\": \"string\",\n"
				+ "  \"description\": \"Best Mobile\",\n"
				+ "  \"manufacturer\": \"string\",\n"
				+ "  \"model\": \"string\",\n"
				+ "  \"url\": \"string\",\n"
				+ "  \"image\": \"string\"\n"
				+ "}";
		Response response = requestFactory.addProducts(requestPayload);
		extentReport.addLog(Status.INFO, response.asPrettyString());
		response.then().statusCode(201);
	}
	
	@Test
	public void verifyAddProductWithRequestPayloadMap() {
		extentReport.createTestcase("Verify Add Product with request payload as map");
		Map<String, Object> requestPayload = new HashMap<>();
		requestPayload.put("name", "Samsung Mobile1");
		requestPayload.put("type", "Mobile");
		requestPayload.put("price", 1000);
		requestPayload.put("shipping", 10);
		requestPayload.put("upc", "Samsung Mobile");
		requestPayload.put("description", "Best Samsung Mobile");
		requestPayload.put("manufacturer", "Samsung");
		requestPayload.put("model", "Samsung Mobile1");
		requestPayload.put("url", "Samsung Mobile1");
		requestPayload.put("image", "Samsung Mobile1");
		
		Response response = requestFactory.addProducts(requestPayload);
		extentReport.addLog(Status.INFO, response.asPrettyString());
		response.then().statusCode(201);
	}
	
	@Test
	public void verifyEndToEndCallFlow() {
		extentReport.createTestcase("Verify End to End call flow/add product");
		
		// 1.Send a post request to create a product
		Map<String, Object> requestPayload = new HashMap<>();
		requestPayload.put("name", "Samsung Mobile1");
		requestPayload.put("type", "Mobile");
		requestPayload.put("price", 1000);
		requestPayload.put("shipping", 10);
		requestPayload.put("upc", "Samsung Mobile");
		requestPayload.put("description", "Best Samsung Mobile");
		requestPayload.put("manufacturer", "Samsung");
		requestPayload.put("model", "Samsung Mobile1");
		requestPayload.put("url", "Samsung Mobile1");
		requestPayload.put("image", "Samsung Mobile1");
		
		/*
		 * Response response = requestFactory.addProducts(requestPayload);
		 * response.then().log().all().statusCode(201);
		 */
		
		Response response = requestFactory.addProducts(requestPayload);
		extentReport.addLog(Status.INFO, response.asPrettyString());
		response.then().statusCode(201);
		
		// Get Id of the product
		String responseBody = response.getBody().asString();
		
		JsonPath jsonPath = new JsonPath(responseBody);
		int productId = jsonPath.getInt("id");
		System.out.println("result " + productId);
		
		// 2. Send a Get Request to verify if the product is added
		extentReport.createTestcase("Verify End to End call flow/get created product");  
	    response = RestAssured.given().when().get("/products/"+productId);
		extentReport.addLog(Status.INFO, response.asPrettyString());
		response.then().statusCode(200);
		/*
		 * RestAssured.given().when().get("/products/"+productId).then().log().all()
		 * .statusCode(200);
		 */
		
		// 3. Send a Put Request to edit a product
		
		// 4. Send a Delete Request to delete the product
		
		extentReport.createTestcase("Verify End to End call flow/delete given product");
		response = RestAssured.given().when().delete("/products/" + productId);
		extentReport.addLog(Status.INFO, response.asPrettyString());
		response.then().statusCode(200);
		
		// 5. Send e GEt request to verify delete
		extentReport.createTestcase("Verify End to End call flow/verify deleted product");
		response = RestAssured.given().when().get("/products/" + productId);
		extentReport.addLog(Status.INFO, response.asPrettyString());
		response.then().statusCode(404);
		
	}
}
