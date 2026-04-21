
package com.product.util;

import org.springframework.stereotype.Component;

@Component
public class EmailMessageBuilderUtil {
	public String otpMessageBuilder(String name, Integer otp) {
		StringBuilder message = new StringBuilder();
		message.append("Dear " + name + ",\n");
		message.append("Thank you for your interest in Pinda DELIVERY service.\n");
		message.append("For your new account registration the 6 digit OTP is here : " + otp);
		message.append("\nNote: OTP valid only for one minute");
		return message.toString();
	}

	public String userRegisterMessageBuilder(String name) {
		StringBuilder message = new StringBuilder();
		message.append("Dear " + name + ",\n");
		message.append("Thank you for your interest for our services, Pinda DELIVERY.\n");
		message.append("You have been registered as user.\n");
		message.append("Note: This is a system generated email. Do not reply.");
		return message.toString();
	}
}
