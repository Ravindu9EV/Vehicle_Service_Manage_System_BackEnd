package com.icet.crm.service.impl;

import com.icet.crm.dto.RepairDto;
import com.icet.crm.entity.Repair;
import com.icet.crm.repository.RepairRepository;
import com.icet.crm.service.RepairService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class RepairServiceImpl implements RepairService {
    private static final Logger log = LoggerFactory.getLogger(RepairServiceImpl.class);
    private final RepairRepository repository;
    private final ModelMapper mapper;
    @Override
    public boolean addRepair(RepairDto repairDto) {
        System.out.println(repairDto.equals(null));
        Repair repair=null;
//        if((repairDto.getType()==null)){
//           return false;
//        } else if (repairDto.getDuration()==null) {
//            return false;
//        } else if (repairDto.getDescription()==null){
//            return false;
//        }else if(repair.getCost()==null){
//            return false;
//        } else
//           try{
//               if(repairDto!=null ){
//
//                   repair=repository.save(mapper.map(repairDto, Repair.class));
//
//               }
//               System.out.println(repair);
//
//           }catch (Exception e){
//               System.out.println(e);
//               return false;
//           }

        if(repairDto.getType()==null | repairDto.getDuration()==null | repairDto.getCost()==null | repairDto.getDescription()==null){
            return false;
        }else if(repairDto!=null ){
               System.out.println(repairDto);
               repair=repository.save(mapper.map(repairDto, Repair.class));

        }

           return repair!=null;
    }

    @Override
    public RepairDto findReapairById(Integer id) {
        return mapper.map(repository.findById(id),RepairDto.class);
    }

    @Override
    public boolean deleteRepair(Integer id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateRepair(RepairDto repairDto) {
        if(repairDto.getId()==null || repairDto.getType()==null || repairDto.getCost()==null || repairDto.getDuration()==null || repairDto.getDescription()==null){
            return false;
        }
         try{

             repository.save(mapper.map(repairDto, Repair.class));
             return true;
         }catch (Exception e){
             log.info(e.toString());
             return false;
         }
    }

    @Override
    public List<RepairDto> getAll() {
        List<RepairDto> repairDtos=new ArrayList<>();

        repository.findAll().forEach(repair -> {
            repairDtos.add(mapper.map(repair,RepairDto.class));
        });
        return repairDtos;
    }

    @Override
    public List<RepairDto> findRepairsByType(String type) {
        System.out.println(type);
        List<RepairDto> repairDtos=new ArrayList<>();
        repository.findByType(type).forEach(repair -> {
            System.out.println(repair);
            repairDtos.add(mapper.map(repair,RepairDto.class));
        });
        System.out.println(repairDtos);
        return repairDtos;
    }

    @Override
    public RepairDto findByType(String type) {
        try{

            return mapper.map(repository.findRepairByType(type),RepairDto.class);

        }catch (Exception e){
            log.info(e.toString());

        }
        return null;
    }
}
