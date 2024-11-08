package com.icet.crm.service.impl.email;

import com.icet.crm.dto.EmailDto;
import com.icet.crm.entity.User;
import com.icet.crm.entity.Vehicle;

public interface RegistrationEmailService {
    EmailDto sendRegistrationEmail(User user, Vehicle vehicle);
}
