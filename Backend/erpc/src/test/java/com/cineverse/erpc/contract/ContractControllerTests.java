package com.cineverse.erpc.contract;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.account.account.repository.AccountRepository;
import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.contract.aggregate.ContractCategory;
import com.cineverse.erpc.contract.aggregate.ContractDeleteRequest;
import com.cineverse.erpc.contract.dto.ContractDTO;
import com.cineverse.erpc.contract.dto.ContractDeleteRequestDTO;
import com.cineverse.erpc.contract.dto.ContractProductDTO;
import com.cineverse.erpc.contract.repository.ContractRepository;
import com.cineverse.erpc.contract.service.ContractService;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import com.cineverse.erpc.product.aggregate.Product;
import com.cineverse.erpc.product.repository.ProductRepository;
import com.cineverse.erpc.quotation.quotation.aggregate.Transaction;
import com.cineverse.erpc.warehouse.aggregate.Warehouse;
import com.cineverse.erpc.warehouse.repository.WarehouseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ContractControllerTests {

    private final EmployeeRepository employeeRepository;
    private final ContractRepository contractRepository;
    private final ContractService contractService;
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    @Autowired
    public ContractControllerTests(EmployeeRepository employeeRepository,
                                   ContractRepository contractRepository,
                                   ContractService contractService,
                                   AccountRepository accountRepository,
                                   ProductRepository productRepository,
                                   WarehouseRepository warehouseRepository) {
        this.employeeRepository = employeeRepository;
        this.contractRepository = contractRepository;
        this.contractService = contractService;
        this.accountRepository = accountRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Test
    @Transactional
    @DisplayName("계약서 등록 테스트")
    public void registContract() {
        // 기존에 설정된 모의 데이터 사용
        Employee employee = employeeRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다"));

        Account account = accountRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 거래처입니다."));

        Warehouse warehouse = warehouseRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 창고입니다."));

        Product product = productRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 제품입니다."));

        ContractProductDTO contractProductDTO = ContractProductDTO.builder()
                .contractSupplyPrice(1000)
                .contractProductCount(10)
                .product(product) // Product 객체 대신 DTO 사용 예정
                .contractProductionNote("계약서 상품 비고")
                .build();
        List<ContractProductDTO> contractProducts = new ArrayList<>();
        contractProducts.add(contractProductDTO);

        Transaction transaction = new Transaction();
        transaction.setTransactionId(2);
        transaction.setTransactionCode("테스트 거래 코드");

        ContractCategory contractCategory = new ContractCategory();
        contractCategory.setContractCategoryId(2);
        contractCategory.setContractCategory("분할납부");

        MultipartFile[] files = new MultipartFile[0];

        ContractDTO contractDTO = ContractDTO.builder()
                .employee(employee)
                .account(account)
                .warehouse(warehouse)
                .contractProduct(contractProducts)
                .contractTotalPrice(100000L)
                .contractNote("계약서 비고")
                .transaction(transaction)
                .contractCategory(contractCategory)
                .downPayment(100000L)
                .progressPayment(100000L)
                .balance(100000L)
                .contractDueDate("2024-06-31")
                .build();

        Contract testContract = contractService.registContract(contractDTO, files);

        assertThat(testContract.getContractDate()).isEqualTo(contractDTO.getContractDate());
        assertThat(testContract.getContractCategory()).isEqualTo(contractDTO.getContractCategory());
        assertThat(testContract.getContractTotalPrice()).isEqualTo(contractDTO.getContractTotalPrice());
        assertThat(testContract.getDownPayment()).isEqualTo(contractDTO.getDownPayment());
        assertThat(testContract.getProgressPayment()).isEqualTo(contractDTO.getProgressPayment());
        assertThat(testContract.getBalance()).isEqualTo(contractDTO.getBalance());
        assertThat(testContract.getContractNote()).isEqualTo(contractDTO.getContractNote());
        assertThat(testContract.getContractProduct()).isNotEmpty();
        assertThat(testContract.getEmployee()).isEqualTo(contractDTO.getEmployee());
        assertThat(testContract.getAccount()).isEqualTo(contractDTO.getAccount());
        assertThat(testContract.getWarehouse()).isEqualTo(contractDTO.getWarehouse());
        assertThat(testContract.getTransaction()).isEqualTo(contractDTO.getTransaction());
    }


    @Test
    @Transactional
    @DisplayName("계약서 수정 테스트")
    public void modifyContract() {
        ContractCategory contractCategory = new ContractCategory();
        contractCategory.setContractCategoryId(1);
        contractCategory.setContractCategory("일시납부");

        MultipartFile[] files = new MultipartFile[0];

        Product product = productRepository.findById(Long.valueOf(2))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 제품입니다."));
        ContractProductDTO contractProductDTO = ContractProductDTO.builder()
                .contractSupplyPrice(1000)
                .contractProductCount(10)
                .product(product)
                .contractProductionNote("계약서 상품 비고")
                .build();
        List<ContractProductDTO> contractProducts = new ArrayList<>();
        contractProducts.add(contractProductDTO);

        ContractDTO contract = ContractDTO.builder()
                .contractProduct(contractProducts)
                .contractTotalPrice(1234L)
                .contractNote("계약서 비고 수정")
                .contractCategory(contractCategory)
                .downPayment(12345L)
                .progressPayment(12341234L)
                .balance(12341234L)
                .contractDueDate("2024-06-31")
                .build();

        Contract testContract = contractService.modifyContract(2, contract, files);

        assertThat(testContract.getContractCategory()).isEqualTo(contract.getContractCategory());
        assertThat(testContract.getContractTotalPrice()).isEqualTo(contract.getContractTotalPrice());
        assertThat(testContract.getDownPayment()).isEqualTo(contract.getDownPayment());
        assertThat(testContract.getProgressPayment()).isEqualTo(contract.getProgressPayment());
        assertThat(testContract.getBalance()).isEqualTo(contract.getBalance());
        assertThat(testContract.getContractNote()).isEqualTo(contract.getContractNote());
        assertThat(testContract.getContractProduct()).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("계약서 삭제 요청 테스트")
    public void deleteContract() {
        Contract contract = contractRepository.findById(Long.valueOf(2))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 계약서입니다."));

        ContractDeleteRequestDTO requestDeleteContract = ContractDeleteRequestDTO.builder()
                .contractDeleteRequestReason("테스트 삭제 사유")
                .contract(contract)
                .build();

        ContractDeleteRequest deleteContract = contractService.requestDeleteContract(requestDeleteContract);

        assertThat(requestDeleteContract.getContractDeleteRequestReason())
                .isEqualTo(deleteContract.getContractDeleteRequestReason());
        assertThat(deleteContract.getContract()).isEqualTo(contract);
    }

    @Test
    @Transactional
    @DisplayName("계약서 전체조회 테스트")
    public void findContractList() {
        List<Contract> actualContractList = contractRepository.findByContractDeleteDateIsNullOrderByContractIdDesc();
        List<Contract> testContractList = contractService.findContractList();

        assertThat(actualContractList.size()).isEqualTo(testContractList.size());
        assertThat(actualContractList).isEqualTo(testContractList);
    }

    @Test
    @Transactional
    @DisplayName("계약서 단일조회 테스트")
    public void findContractById() {
        Contract testContract = contractRepository.findById(Long.valueOf(2))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 계약서입니다."));

        ContractDTO contract = contractService.findContractById(2);

        assertThat(contract.getContractDate()).isEqualTo(testContract.getContractDate());
        assertThat(contract.getContractNote()).isEqualTo(testContract.getContractNote());
        assertThat(contract.getContractCategory()).isEqualTo(testContract.getContractCategory());
        assertThat(contract.getContractTotalPrice()).isEqualTo(testContract.getContractTotalPrice());
        assertThat(contract.getProgressPayment()).isEqualTo(testContract.getProgressPayment());
        assertThat(contract.getBalance()).isEqualTo(testContract.getBalance());
        assertThat(contract.getContractDueDate()).isEqualTo(testContract.getContractDueDate());
        assertThat(contract.getContractCode()).isEqualTo(testContract.getContractCode());
        assertThat(contract.getEmployee()).isEqualTo(testContract.getEmployee());
        assertThat(contract.getTransaction()).isEqualTo(testContract.getTransaction());
        assertThat(contract.getAccount()).isEqualTo(testContract.getAccount());
        assertThat(contract.getWarehouse()).isEqualTo(testContract.getWarehouse());
    }
}