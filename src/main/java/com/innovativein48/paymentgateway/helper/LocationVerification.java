package com.innovativein48.paymentgateway.helper;

import java.sql.Timestamp;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovativein48.paymentgateway.bean.Reason;
import com.innovativein48.paymentgateway.bean.Status;
import com.innovativein48.paymentgateway.dto.ReservationRequest;
import com.innovativein48.paymentgateway.entities.Transactionlog;
import com.innovativein48.paymentgateway.repos.TransactionLogRepository;

@Service
public class LocationVerification implements VerificationService {
	private static final double MAX_KM = 50;

	private static double devLat = Double.MIN_VALUE;
	private static double devLong = Double.MIN_VALUE;

	private static double tranLat = Double.MIN_VALUE;
	private static double tranLong = Double.MIN_VALUE;

	@Autowired
	TransactionLogRepository transactionLogRepo;

	@Override
	public Transactionlog verifyLocation(final Map<String, String> deviceLocation,
			final Map<String, String> transactionLocation, final ReservationRequest reservationRequest) {

		for (Map.Entry<String, String> deviceLocat : deviceLocation.entrySet()) {
			devLat = Double.valueOf(deviceLocat.getKey());
			devLong = Double.valueOf(deviceLocat.getValue());
		}

		for (Map.Entry<String, String> transtionLoc : transactionLocation.entrySet()) {
			tranLat = Double.valueOf(transtionLoc.getKey());
			tranLong = Double.valueOf(transtionLoc.getValue());
		}

		final double theta = devLong - tranLong;

		double dist = Math.sin(deg2rad(devLat)) * Math.sin(deg2rad(tranLat))
				+ Math.cos(deg2rad(devLat)) * Math.cos(deg2rad(tranLat)) * Math.cos(deg2rad(theta));

		dist = rad2deg(Math.acos(dist)) * 60 * 1.1515 * 1.609344;

		Transactionlog transactionLogs = prepareTransactionLogs(reservationRequest, devLat, devLong, tranLat, tranLong);

		if (dist > MAX_KM) {

			transactionLogs.setReason("OTHER");
			transactionLogs.setStatus("FAILURE");
			Transactionlog logFailure = transactionLogRepo.save(transactionLogs);

			return logFailure;
		}

		transactionLogs.setStatus("SUCCESS");
		Transactionlog logSuccess = transactionLogRepo.save(transactionLogs);

		return logSuccess;

	}

	private Transactionlog prepareTransactionLogs(ReservationRequest reservationRequest, double devLat, double devLong,
			double tranLat, double tranLong) {
		Transactionlog transactionLogs = new Transactionlog();
		transactionLogs.setAccountnumber(reservationRequest.getAccountNumber());
		transactionLogs.setDevlat(devLat);
		transactionLogs.setDevlong(devLong);
		transactionLogs.setTimestamp(new Timestamp(System.currentTimeMillis()));
		transactionLogs.setTranlat(tranLat);
		transactionLogs.setTranlong(tranLong);
		transactionLogs.setTransdate(java.time.LocalDate.now().toString());
		transactionLogs.setTrantype("ATM");

		return transactionLogs;
	}

	public static double deg2rad(final double deg) {
		return (deg * Math.PI / 180.0);
	}

	public static double rad2deg(final double rad) {
		return (rad * 180.0 / Math.PI);
	}

}
