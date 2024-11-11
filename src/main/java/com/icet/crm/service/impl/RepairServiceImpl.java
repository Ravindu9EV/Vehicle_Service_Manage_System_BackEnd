package com.icet.crm.service.impl;

import com.icet.crm.dto.RepairDto;
import com.icet.crm.entity.Repair;
import com.icet.crm.repository.RepairRepository;
import com.icet.crm.service.RepairService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RepairServiceImpl implements RepairService {
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
    public void updateRepair(RepairDto repairDto) {
        if(repairDto!=null)repository.save(mapper.map(repairDto, Repair.class));
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
    public List<RepairDto> findRepairByType(String type) {
        List<RepairDto> repairDtos=new ArrayList<>();
        repository.findByType(type).forEach(repair -> {
            repairDtos.add(mapper.map(repair,RepairDto.class));
        });
        return repairDtos;
    }
}
