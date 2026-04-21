
package com.product.serviceimpl;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.product.dto.AddUserDto;
import com.product.dto.EmailOtpVerifyDto;
import com.product.event.SimpleMessageEvent;
import com.product.service.MailService;
import com.product.service.UserService;
import com.product.util.EmailMessageBuilderUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private EmailMessageBuilderUtil emailMessageBuilderUtil;

	@Autowired
	private Random random;

	@Autowired
	private MailService mailService;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	@Qualifier("otpHolder")
	private Map<String, Object[]> otpHolder;

	@Override
	public String initiateUserVerificationService(AddUserDto dto) {
		System.out.println(Thread.currentThread());
		Integer otp = random.nextInt(100000, 999999);
		String otpMessage = emailMessageBuilderUtil.otpMessageBuilder(dto.getName(), otp);
		// mailService.sentEmail(dto.getEmail(), otpMessage);

		SimpleMessageEvent emailEvent = SimpleMessageEvent.builder().receiverEmail(dto.getEmail()).message(otpMessage)
				.subject("Products alert: OTP").build();
		eventPublisher.publishEvent(emailEvent);

		Object[] tempOtpData = { otp, LocalDateTime.now().plusMinutes(2), dto };
		otpHolder.put(dto.getEmail(), tempOtpData);
//		for (Entry<String, Object[]> e : otpHolder.entrySet()) {
//			System.out.println(e);
//		}

		return "OTP sent to email" + dto.getEmail();
	}

	@Override
	public String finalUserVerificationService(EmailOtpVerifyDto dto) {
		Object[] tempUserData = otpHolder.get(dto.getEmail());
		if (tempUserData == null) {
			throw new RuntimeException("Invalid email id");
		}

		LocalDateTime currDateTime = LocalDateTime.now();
		LocalDateTime expiryDateTime = (LocalDateTime) tempUserData[1];
		if (currDateTime.isAfter(expiryDateTime)) {
			throw new RuntimeException("Time over, use keypad");
		}

		String inMemoryOtp = String.valueOf(tempUserData[0]);
		String userOtp = dto.getOtp();
		if (!inMemoryOtp.equals(userOtp)) {
			throw new RuntimeException("Invalid OTP");
		}

		return "User Saved";
	}

}
