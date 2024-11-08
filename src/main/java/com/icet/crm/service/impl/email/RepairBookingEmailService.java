package com.icet.crm.service.impl.email;

import com.icet.crm.dto.EmailDto;
import com.icet.crm.entity.Booking;

public interface RepairBookingEmailService {
   EmailDto sendRepairBookingEmail(Booking booking);
}
