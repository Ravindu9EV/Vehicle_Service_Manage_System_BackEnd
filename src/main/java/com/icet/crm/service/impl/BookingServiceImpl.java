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
        if (bookingDto.getVehicleId() == null || bookingDto.getBookedDate() == null || bookingDto.getBookedTime() == null || bookingDto.getRepairId() == null || bookingDto.getDescription() == null) {
            return false;
        } else if (!checkDate(bookingDto.getBookedDate())) {
            return false;

        } else if (getAvailbleBooking(bookingDto.getBookedDate(), bookingDto.getBookedTime()) == null) {
            Booking booking = mapper.map(bookingDto, Booking.class);

            try {
                if (getAvailbleBooking(booking.getBookedDate(), booking.getBookedTime()) == null) {
                    repository.save(booking);
                    isAvailable = true;
                    if (isAvailable) {
                        EmailDto emailDto = repairBookingEmailService.sendRepairBookingEmail(booking);
                        System.out.println(emailDto);
                        return isAvailable;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }

        } else {
            return false;
        }


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
        else if (!checkDate(bookingDto.getBookedDate())) {
            return false;

        }
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
        Integer bookedDay=Integer.parseInt(date.substring(8,10));
        Integer currentDay=Integer.parseInt(formatDate.substring(8,10));
        System.out.println(currentYear + "---" + bookedYear);
        System.out.println(currentMonth + "M---M" + bookedMonth);
        System.out.println(currentDay + "D---D" + bookedDay);
        if (bookedMonth<currentMonth  | currentYear == bookedYear ) {
            int m=currentMonth + 1;
            System.out.println(m);
            return false;




        } else if(bookedYear>currentYear+1) {
            return false;
        } else if((bookedYear==currentYear+1) & currentMonth==12) {
            if(bookedMonth==1){
                return true;
            }
            return false;
        }else if(bookedYear==currentYear & bookedMonth==currentMonth | bookedDay<currentDay){
            return false;
        }else if(bookedYear==currentYear & bookedMonth==currentMonth | bookedDay>=currentDay){
            return true;
        }else if(bookedYear==currentYear | bookedMonth>currentMonth ){
            if(bookedMonth>currentMonth+1){
                return false;
            }
            return true;
        }else if(bookedYear<currentYear){

            return false;
        }else {
            return false;
        }


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
