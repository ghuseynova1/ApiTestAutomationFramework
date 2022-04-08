package request;

import client.RestClient;
import io.restassured.response.Response;

public class RequestFactory {
	
	RestClient restClient;
	
	public RequestFactory() {
		restClient = new RestClient();
	}
	
	public Response getAllProducts() {
		return restClient.SendGetRequest("/products");
	}
	

	public Response addProducts (Object requestPayload) {
		return restClient.SendPostRequest("/products", requestPayload);
	}
	
	public Response updateProducts (Object requestPayload) {
		return restClient.SendPutRequest("/products", requestPayload);
	}
}
