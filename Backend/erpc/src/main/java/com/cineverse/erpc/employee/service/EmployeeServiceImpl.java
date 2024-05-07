package com.cineverse.erpc.employee.service;

import com.cineverse.erpc.employee.aggregate.EmployeeEntity;
import com.cineverse.erpc.employee.dto.EmployeeDTO;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               ModelMapper modelMapper,
                               BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void registEmployee(EmployeeDTO employeeDTO) {

        employeeDTO.setEmpolyeeUUID(UUID.randomUUID().toString());

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);

        employeeEntity.setEmployPassword(bCryptPasswordEncoder.encode(employeeDTO.getEmployPassword()));

        employeeRepository.save(employeeEntity);
    }
}
