package no.hvl.dat110.aciotdevice.client;

import okhttp3.*;
import com.google.gson.JsonParser;
import java.io.IOException;
import com.google.gson.Gson;

public class RestClient {

	public RestClient() {
		// TODO Auto-generated constructor stub
	}

	private static String logpath = "/accessdevice/log";

	public void doPostAccessEntry(String message) {

		// TODO: implement a HTTP POST on the service to post the message
		Gson gson = new Gson();
		OkHttpClient client = new OkHttpClient();
		AccessMessage msg = new AccessMessage(message);
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		//@SuppressWarnings("deprecation")
		RequestBody reqBody = RequestBody.create(JSON, gson.toJson(msg));
		Request req = new Request.Builder().url("http://localhost:8080" + logpath).post(reqBody).build();

		try (Response res = client.newCall(req).execute()) {

			System.out.println("Respons: " + res.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String codepath = "/accessdevice/code";

	public AccessCode doGetAccessCode() {

		AccessCode code = null;

		// TODO: implement a HTTP GET on the service to get current access code
		
		OkHttpClient client = new OkHttpClient();
		Gson gson = new Gson();
		Request req = new Request.Builder().url("http://localhost:8080" + codepath).get().build();
		System.out.println(req);
		try (Response res = client.newCall(req).execute()) {
			System.out.println("Respons:" + res.body().string());

			String resBody = res.body().string();

			code = gson.fromJson(JsonParser.parseString(resBody), AccessCode.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return code;
	}
}
