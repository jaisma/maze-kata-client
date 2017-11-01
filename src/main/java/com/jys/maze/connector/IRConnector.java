package com.jys.maze.connector;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Handles http request to and from Impact Radius maze server. Base URL is hard
 * coded, with pass in parameters attached. This can be improved by extracting
 * out the base URL. No log implementation; everything is sysout.
 * 
 * @author Jai Sun
 *
 */
public class IRConnector {

	/**
	 * Send "start" GET http request. Creates json response and returns string
	 * representation of it
	 * 
	 * @return String format of returned json response. Empty string on fail
	 */
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

	/**
	 * Send "advance" GET request. Creates json response and returns string
	 * representation of it.
	 * 
	 * Assume valid input
	 * 
	 * @param id
	 *            current maze session id
	 * @param quickFeet
	 *            true for quickfeet
	 * @return String format of returned json response. Empty string on fail
	 */
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

	/**
	 * Send "check" GET request. Creates json response and returns string
	 * representation of it.
	 * 
	 * Assume valid input.
	 * 
	 * @param id
	 *            current maze session id
	 * @param x
	 *            coordinate
	 * @param y
	 *            coordinate
	 * @return String format of returned json response. Empty string on fail
	 */
	public String check(String id, int x, int y) {
		String url = "http://ir-interviews.herokuapp.com/maze/allowed?id=" + id + "&x=" + x + "&y=" + y;
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

	/**
	 * Send "turn" GET request. Creates json response and returns string
	 * representation of it.
	 * 
	 * Assume valid direction input.
	 * 
	 * @param id
	 *            current maze session id
	 * @param direction
	 *            string value of either "left" or "right" only
	 * @return String format of returned json response. Empty string on fail
	 */
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
