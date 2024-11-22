package com.icet.crm.service.impl.email;

import com.icet.crm.dto.EmailDto;
import com.icet.crm.entity.User;

public interface OTPEmailService {
    EmailDto sendOTP(User user,Integer otp);
}
