package com.innovativein48.paymentgateway.xmpp;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/**
 * Entry Point class for the XMPP Server in dev mode for debugging and testing
 * purposes
 */
public class DeviceLocator {

	public static final Logger logger = Logger.getLogger(DeviceLocator.class.getName());
	private static final String PROJECT_SENDER_ID = "362421666820";
	private static final String SERVER_KEY = "AAAAVGIDxAQ:APA91bEngyVXwe4im4g8vX-GOvW0Uw-sXgGMnyGIKXiJNw-DYWJJJvl0KX2LQNMd4WksjfSfBcbImQ6lgjmxWo2QnOkG4fso5wwrDB4xkYh3swhvUciV68ei6sBX1E32T8Xt5Z2AXqCD";

	public Map<String, String> getLocation(String deviceToken) {
		final String fcmProjectSenderId = PROJECT_SENDER_ID;
		final String fcmServerKey = SERVER_KEY;
		 final String toRegId = deviceToken;

		//final String toRegId = "eRwxyZPLPEY:APA91bHhnPHms8xrTVLv8s1U-o0Gmer0v5GsP49NydhNUmipRaAiUHp3YS2Z5VTmvP9jpvfTNC9QGv2Bbq0VE26gjLnF8MayuDHp5kf_53kgBQNEwSo5-HtqjB3-R6yLr_VpM6F8mPh_";

		ExecutorService executor = Executors.newCachedThreadPool();
		Future<String> futureCall = executor.submit(new LocatorWorker(fcmProjectSenderId, fcmServerKey, toRegId));

		String result = null;
		try {
			result = futureCall.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Here the thread will be blocked
		System.out.println(result);
		Map<String, String> devLoc = getDeviceLocation(result);
		return devLoc;
	}

	private Map<String, String> getDeviceLocation(final String result) {
		String latitude = null;
		String longitude = null;
		final String arr[] = result.split("#");
		for (String val : arr) {
			if (val.contains("Latitude")) {
				String latitudeArr[] = val.split(":");
				latitude = latitudeArr[1].trim();
			} else {
				String longitudeArr[] = val.split(":");
				longitude = longitudeArr[1].trim();
			}
		}
		Map<String, String> deviceLocation = new HashMap<>();
		deviceLocation.put(latitude, longitude);

		return deviceLocation;
	}
}