package com.jys.maze.connector;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class IRConnector {

	public String start() {
		String url = "http://ir-interviews.herokuapp.com/maze/start";
		HttpGet request = new HttpGet(url);
		try {
			request.setHeader("Accept", "application/json");
			HttpResponse response = HttpClientBuilder.create().build().execute(request);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			System.out.println("Error occurerd while calling " + request.getMethod() + " " + request.getURI() + " : "
					+ e.getMessage());
		} finally {
			request.releaseConnection();
		}
		return "";
	}

	public String advance(String id, boolean quickFeet) {
		String type = quickFeet ? "quickFeet" : "normal";
		String url = "http://ir-interviews.herokuapp.com/maze/advance?id=" + id + "&type=" + type;
		HttpGet request = new HttpGet(url);
		try {
			request.setHeader("Accept", "application/json");
			HttpResponse response = HttpClientBuilder.create().build().execute(request);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			System.out.println("Error occurerd while calling " + request.getMethod() + " " + request.getURI() + " : "
					+ e.getMessage());
		} finally {
			request.releaseConnection();
		}
		return "";
	}

	public String check(String id, int x, int y) {
		String url = "http://ir-interviews.herokuapp.com/maze/allowed?id=" + id + "&x=" + x + "&y=" + y;
		HttpGet request = new HttpGet(url);
		try {
			request.setHeader("Accept", "application/json");
			HttpResponse response = HttpClientBuilder.create().build().execute(request);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			System.out.println("Error occurerd while calling " + request.getMethod() + " " + request.getURI() + " : " + e.getMessage());
		} finally {
			request.releaseConnection();
		}
		return "";
	}

	public String turn(String id, String direction) {
		String url = "http://ir-interviews.herokuapp.com/maze/turn/" + direction + "?id=" + id;
		HttpGet request = new HttpGet(url);
		try {
			request.setHeader("Accept", "application/json");
			HttpResponse response = HttpClientBuilder.create().build().execute(request);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			System.out.println("Error occurerd while calling " + request.getMethod() + " " + request.getURI() + " : "
					+ e.getMessage());
		} finally {
			request.releaseConnection();
		}
		return "";

	}

}
