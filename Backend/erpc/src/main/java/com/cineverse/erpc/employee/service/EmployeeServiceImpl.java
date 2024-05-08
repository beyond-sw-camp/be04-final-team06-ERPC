package com.cineverse.erpc.employee.service;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.dto.EmployeeDTO;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        employeeDTO.setEmployeeUUID(UUID.randomUUID().toString());

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Employee employee = modelMapper.map(employeeDTO, Employee.class);

        employee.setEmployeePassword(bCryptPasswordEncoder.encode(employeeDTO.getEmployeePassword()));

        employeeRepository.save(employee);
    }

    /* UserDetailsService 인터페이스 상속 이후 DB에서 로그인 사용자 정보 조회 후 UserDetails 타입으로 반환하는 기능 */
    @Override
    public UserDetails loadUserByUsername(String employeeCode) throws UsernameNotFoundException {

        Employee employee = employeeRepository.findByEmployeeCode(employeeCode);

        if(employee == null)
            throw new UsernameNotFoundException(employeeCode + "의 사번은 존재하지 않습니다.");

        return new User(employee.getEmployeeCode(), employee.getEmployeePassword(),
                true, true, true, true,
                new ArrayList<>());
    }
}
