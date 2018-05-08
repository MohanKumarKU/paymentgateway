package com.innovativein48.paymentgateway.impl;

import com.innovativein48.paymentgateway.bean.CcsInMessage;
import com.innovativein48.paymentgateway.bean.CcsOutMessage;
import com.innovativein48.paymentgateway.server.CcsClient;
import com.innovativein48.paymentgateway.server.MessageHelper;
import com.innovativein48.paymentgateway.service.PayloadProcessor;
import com.innovativein48.paymentgateway.util.Util;

/**
 * Handles an echo request
 */
public class EchoProcessor implements PayloadProcessor {

	@Override
	public void handleMessage(CcsInMessage inMessage) {
		CcsClient client = CcsClient.getInstance();
		String messageId = Util.getUniqueMessageId();
		String to = inMessage.getFrom();

		// Send the incoming message to the the device that made the request
		CcsOutMessage outMessage = new CcsOutMessage(to, messageId, inMessage.getDataPayload());
		String jsonRequest = MessageHelper.createJsonOutMessage(outMessage);
		client.send(jsonRequest);
	}

}