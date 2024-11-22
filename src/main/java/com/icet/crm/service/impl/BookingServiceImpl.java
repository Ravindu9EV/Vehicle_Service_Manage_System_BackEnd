package com.icet.crm.service.impl;

import com.icet.crm.dto.BookingDto;
import com.icet.crm.dto.EmailDto;
import com.icet.crm.entity.Booking;
import com.icet.crm.repository.BookingRepository;
import com.icet.crm.service.BookingService;
import com.icet.crm.service.impl.email.RepairBookingEmailService;
import com.icet.crm.util.EmailServiceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Array;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {
    private final BookingRepository repository;
    private final ModelMapper mapper;
    final RepairBookingEmailService repairBookingEmailService;
    @Override
    public boolean addBooiking(BookingDto bookingDto) {
        boolean isAvailable=false;
        if(bookingDto!=null) {
            //boolean isDateCorc=checkDate(bookingDto.getBookedDate());


                if ( bookingDto.getVehicleId() == null || bookingDto.getBookedDate() == null || bookingDto.getBookedTime() == null || bookingDto.getRepairId() == null || bookingDto.getDescription() == null) {
                    return false;
                }
//                else if (!checkDate(bookingDto.getBookedDate())) {
//                    return false;
//
//                }
                else if (getAvailbleBooking(bookingDto.getBookedDate(), bookingDto.getBookedTime()) == null) {
                    Booking booking = mapper.map(bookingDto, Booking.class);

                    try{
                        if (getAvailbleBooking(booking.getBookedDate(), booking.getBookedTime()) == null) {
                            repository.save(booking);
                             isAvailable = true;
                            if (isAvailable) {
                                EmailDto emailDto = repairBookingEmailService.sendRepairBookingEmail(booking);
                                System.out.println(emailDto);
                                isAvailable= true;
                            }else{
                                isAvailable=false;
                            }
                        } else {
                            isAvailable= false;
                        }
                    }catch (Exception e){
                       isAvailable= false;
                    }

                }

        }
        return isAvailable;

    }



    @Override
    public BookingDto findBooking(Integer id) {
        return mapper.map(repository.findById(id),BookingDto.class);
    }

    @Override
    public List<BookingDto> getAll() {
        List<BookingDto> bookingDtos=new ArrayList<>();
        repository.findAll().forEach(booking -> bookingDtos.add(mapper.map(booking,BookingDto.class)));
        return bookingDtos;
    }

    @Override
    public List<BookingDto> findByDate(String date) {
        List<BookingDto> bookingDtos=new ArrayList<>();
        repository.findByBookedDate(date).forEach(booking -> bookingDtos.add(mapper.map(booking,BookingDto.class)));
        return bookingDtos;
    }

    @Override
    public List<BookingDto> findByRepairId(Integer id) {
        List<BookingDto> bookingDtos=new ArrayList<>();
        repository.findByRepairId(id).forEach(booking -> bookingDtos.add(mapper.map(booking,BookingDto.class)));
        return bookingDtos;
    }

    @Override
    public List<BookingDto> findByVehicleId(Integer id) {
        List<BookingDto> bookingDtos=new ArrayList<>();
        repository.findByVehicleId(id).forEach(booking -> bookingDtos.add(mapper.map(booking,BookingDto.class)));
        return bookingDtos;
    }

    @Override
    public boolean updateBooking(BookingDto bookingDto) {

        if(bookingDto.getId()<=0  || bookingDto.getVehicleId()==null || bookingDto.getBookedDate()==null || bookingDto.getBookedTime()==null || bookingDto.getRepairId()==null || bookingDto.getDescription()==null) {
            return false;
        }
//        if (!checkDate(bookingDto.getBookedDate())) {
//            return false;
//
//        }
        else{
            try {
                Booking booking = new Booking(bookingDto.getId(), bookingDto.getVehicleId(), bookingDto.getBookedDate(), bookingDto.getBookedTime(), bookingDto.getRepairId(), bookingDto.getDescription());
                System.out.println(booking);
                repository.save(booking);
                repairBookingEmailService.sendRepairBookingEmail(booking);
                return true;
            } catch (Exception e) {
                log.info(e.toString());
                return false;
            }
        }





    }

    @Override
    public BookingDto getAvailbleBooking(String bookedDate, String bookedTime) {
       Booking booking=repository.findByBookedDateAndBookedTime(bookedDate,bookedTime);
        return booking==null?null:mapper.map(booking,BookingDto.class);
    }

    @Override
    public boolean deleteBooking(Integer id) {
        repository.deleteById(id);
        return true;
    }

    //----------checkDate------------
    public boolean checkDate(String date) {
        LocalDate date1 = LocalDate.now();
        String formatDate = date1.getYear() + "-" + date1.getMonthValue() + "-" + date1.getDayOfMonth() + "";
        Integer currentYear = Integer.parseInt(formatDate.substring(0, 4));
        Integer bookedYear = Integer.parseInt(date.substring(0, 4));
        Integer currentMonth = Integer.parseInt(formatDate.substring(5, 7));
        Integer bookedMonth = Integer.parseInt(date.substring(5, 7));
        System.out.println(currentYear + "---" + bookedYear);
        System.out.println(currentMonth + "M---M" + bookedMonth);
        if (currentYear == bookedYear) {
            int m=currentMonth + 1;
            System.out.println(m);
            if(bookedMonth == (m)){
                System.out.println(true);

                return true;
            }else if(bookedMonth<currentMonth){
                return false;
            }else if(bookedMonth==currentMonth){
                return true;
            }else {
                return false;
            }



        } else if(bookedYear>currentYear+1) {
            return false;
        } else if((bookedYear==currentYear+1) & bookedMonth==12) {
            return true;
        }else{

            return false;
        }

        //boolean isCorrect=false;
//        if (currentYear < bookedYear) {
//            if (currentMonth == 12 & bookedMonth == 1) {
//                return true;
//            }
//            return false;
//        } else if ((currentYear == bookedYear) & (currentMonth > bookedMonth)) {
//
//
//            return false;
//
//        } else if (currentYear == bookedYear & (currentMonth + 2 <= bookedMonth)) {
//            return false;
//        } else if ((currentYear == bookedYear)) {
//
//                return bookedMonth == currentMonth + 1;
//
//
//        } else if (currentYear > bookedYear) {
//            return false;
//        } else if (currentYear + 1 == bookedYear & (currentMonth == 12 & bookedMonth == 1)) {
//
//            return true;
//        } else {
//            return false;
//        }




       // return    false;

    }




    public List<BookingDto> sortByDate(){
        List<BookingDto> unsortedBookings= getAll();
        List<String> dates=new ArrayList<>();
        List<BookingDto> sortedByTime=new ArrayList<>();
        List<String> times=new ArrayList<>();

       for(BookingDto bookingDto:unsortedBookings){
           dates.add(bookingDto.getBookedDate());
            times.add(bookingDto.getBookedTime());
       }
        dates.sort(Collections.reverseOrder());
//        Collections.sort(times);
//       Collections.sort(dates);
       unsortedBookings=new ArrayList<>();
       for(String date:dates){
           for(BookingDto bookingDto:findByDate(date)) {
               if(bookingDto!=null){

                   unsortedBookings.add(bookingDto);
               }

                break;
           };
           //------------sort by time-----------



       }


        System.out.println(unsortedBookings);
        System.out.println(times);
        System.out.println(sortedByTime);
        return sortByTime(unsortedBookings);
    }

    private List<BookingDto> sortByTime(List<BookingDto> bookingDtos){
        List<String> times=new ArrayList<>();
        List<BookingDto> sortedList=new ArrayList<>();
        for(BookingDto bookingDto:bookingDtos){
            if(times.isEmpty()){
                times.add(bookingDto.getBookedTime());
            }else{
                for(String time:times){
                    System.out.println(time);
                    if(time.equals(bookingDto.getBookedTime())){
                        continue;
                    }else {
                        times.add(bookingDto.getBookedTime());
                    }

                }
            }

        }
        for(String time:times){
            for(BookingDto bookingDto:bookingDtos){
                if(bookingDto.getBookedTime().equals(time)){
                    sortedList.add(bookingDto);
                }
            }
        }
        System.out.println(times);
        System.out.println(sortedList);
        return sortedList;
    }
}
