package com.cineverse.erpc.employee.service;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.dto.*;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    @Transactional
    public ResponseRegistDTO registEmployee(EmployeeDTO employeeDTO) {

        employeeDTO.setEmployeeUUID(UUID.randomUUID().toString());

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Employee employee = modelMapper.map(employeeDTO, Employee.class);

        employee.setEmployeePassword(bCryptPasswordEncoder.encode(employeeDTO.getEmployeePassword()));

        employeeRepository.save(employee);
        return modelMapper.map(employee, ResponseRegistDTO.class);
    }

    @Override
    public List<ResponseEmployeesDTO> findEmployeeList() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map(employee -> modelMapper
                .map(employee, ResponseEmployeesDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEmployeeDTO findEmployeeById(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다."));

        return modelMapper.map(employee, ResponseEmployeeDTO.class);
    }

    /* security */

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

    /* 토큰의 재료인 userId 조회를 위해 만들어진 메소드 */
    @Override
    public EmployeeDTO getEmployeeDetailsByEmployeeCode(String employeeCode) {

        Employee employee = employeeRepository.findByEmployeeCode(employeeCode);

        if(employee == null)
            throw new UsernameNotFoundException(employeeCode + "의 사번은 존재하지 않습니다.");

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);

        return employeeDTO;
    }

    @Override
    public ResponseModifyPassword modifyPassword(RequestModifyPassword requestModifyPassword) {
        Employee employee = employeeRepository.findById(requestModifyPassword.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다."));

        employee.setEmployeePassword(bCryptPasswordEncoder.encode(requestModifyPassword.getEmployeePassword()));

        employeeRepository.save(employee);

        return modelMapper.map(employee, ResponseModifyPassword.class);
    }

    @Override
    public ResponseModifyEmployee modifyEmployee(RequestModifyEmployee requestModifyEmployee) {
        Employee employee = employeeRepository.findById(requestModifyEmployee.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다."));

        if (requestModifyEmployee.getEmployeeCode() != null) {
            employee.setEmployeeCode(requestModifyEmployee.getEmployeeCode());
        }

        if (requestModifyEmployee.getEmployeeName() != null) {
            employee.setEmployeeName(requestModifyEmployee.getEmployeeName());
        }

        if (requestModifyEmployee.getEmployeePassword() != null) {
            employee.setEmployeePassword(
                    bCryptPasswordEncoder.encode(requestModifyEmployee.getEmployeePassword()));
        }

        if (requestModifyEmployee.getEmployeeEmail() != null) {
            employee.setEmployeeEmail(requestModifyEmployee.getEmployeeEmail());
        }

        if (requestModifyEmployee.getEmployeeHp() != null) {
            employee.setEmployeeHp(requestModifyEmployee.getEmployeeHp());
        }

        if (requestModifyEmployee.getEmployeeNumber() != null) {
            employee.setEmployeeNumber(requestModifyEmployee.getEmployeeNumber());
        }

        if (requestModifyEmployee.getEmploymentDate() != null) {
            employee.setEmploymentDate(requestModifyEmployee.getEmploymentDate());
        }

        if (requestModifyEmployee.getResignationDate() != null) {
            employee.setResignationDate(requestModifyEmployee.getResignationDate());
        }

        if (requestModifyEmployee.getEmployeeRank() != null) {
            employee.setEmployeeRank(requestModifyEmployee.getEmployeeRank());
        }

        if (requestModifyEmployee.getTeamCode() != null) {
            employee.setTeamCode(requestModifyEmployee.getTeamCode());
        }

        employeeRepository.save(employee);

        return modelMapper.map(employee, ResponseModifyEmployee.class);
    }
}
