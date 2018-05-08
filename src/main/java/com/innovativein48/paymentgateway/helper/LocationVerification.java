package com.innovativein48.paymentgateway.helper;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovativein48.paymentgateway.bean.Reason;
import com.innovativein48.paymentgateway.bean.Status;
import com.innovativein48.paymentgateway.bean.TransactionType;
import com.innovativein48.paymentgateway.dto.ReservationRequest;
import com.innovativein48.paymentgateway.entities.TransactionLogs;
import com.innovativein48.paymentgateway.repos.TransactionLogRepository;

@Service	
public class LocationVerification {
	private static final double MAX_KM = 50;

	private static double devLat = Double.MIN_VALUE;
	private static double devLong = Double.MIN_VALUE;

	private static double tranLat = Double.MIN_VALUE;
	private static double tranLong = Double.MIN_VALUE;
	private final ReservationRequest reservationRequest;

	@Autowired
    TransactionLogRepository transactionLogRepo;
	
	public LocationVerification(final ReservationRequest reservationRequest) {
		this.reservationRequest = reservationRequest;
	}

	public Status verifyLocation(final Map<String, String> deviceLocation, final Map<String, String> transactionLocation) {

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

		TransactionLogs transactionLogs = prepareTransactionLogs(reservationRequest, devLat, devLong, tranLat,
				tranLong);

		if (dist > MAX_KM) {

			transactionLogs.setReason(Reason.GIOLOCATIONMISMATCH);
			transactionLogs.setStatus(Status.FAILURE);
			transactionLogRepo.save(transactionLogs);

			return Status.FAILURE;
		}

		transactionLogs.setStatus(Status.SUCCESS);
		transactionLogRepo.save(transactionLogs);

		return Status.SUCCESS;

	}

	private TransactionLogs prepareTransactionLogs(ReservationRequest reservationRequest, double devLat, double devLong,
			double tranLat, double tranLong) {
		TransactionLogs transactionLogs = new TransactionLogs();
		transactionLogs.setAccountNumber(111111L);
		transactionLogs.setDeviceId("qwereerrrr");
		transactionLogs.setDevLat(devLat);
		transactionLogs.setDevLong(devLong);
		transactionLogs.setMerchantID("merchant111");
		transactionLogs.setReferenceId(01010101l);
		transactionLogs.setTimeStamp("2018-02-05 03:14:23");
		transactionLogs.setTranLat(tranLat);
		transactionLogs.setTranLong(tranLong);
		transactionLogs.setTransDate("2018-02-05");
		transactionLogs.setTransactionSource("source");
		transactionLogs.setTranType(TransactionType.ATM);

		return transactionLogs;
	}

	public static double deg2rad(final double deg) {
		return (deg * Math.PI / 180.0);
	}

	public static double rad2deg(final double rad) {
		return (rad * 180.0 / Math.PI);
	}
}
