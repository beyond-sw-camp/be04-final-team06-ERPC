package com.cineverse.erpc.order;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.account.account.repository.AccountRepository;
import com.cineverse.erpc.contract.aggregate.ContractCategory;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.order.order.aggregate.OrderProduct;
import com.cineverse.erpc.order.order.aggregate.ShipmentStatus;
import com.cineverse.erpc.order.order.dto.*;
import com.cineverse.erpc.order.order.repo.OrderRepository;
import com.cineverse.erpc.order.order.service.OrderService;
import com.cineverse.erpc.product.aggregate.Product;
import com.cineverse.erpc.quotation.quotation.aggregate.Transaction;
import com.cineverse.erpc.quotation.quotation.repo.QuotationRepository;
import com.cineverse.erpc.quotation.quotation.service.QuotationService;
import com.cineverse.erpc.shipment.repo.ShipmentRepository;
import com.cineverse.erpc.shipment.service.ShipmentService;
import com.cineverse.erpc.warehouse.aggregate.Warehouse;
import com.cineverse.erpc.warehouse.repository.WarehouseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderTests {
    private final EmployeeRepository employeeRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final ShipmentService shipmentService;
    private final AccountRepository accountRepository;
    private final WarehouseRepository warehouseRepository;
    private final ShipmentRepository shipmentRepository;
    private final QuotationRepository quotationRepository;
    private final QuotationService quotationService;

    @Autowired
    public OrderTests(EmployeeRepository employeeRepository,
                      OrderRepository orderRepository,
                      OrderService orderService,
                      ShipmentService shipmentService,
                      AccountRepository accountRepository,
                      WarehouseRepository warehouseRepository,
                      ShipmentRepository shipmentRepository,
                      QuotationRepository quotationRepository,
                      QuotationService quotationService) {
        this.employeeRepository = employeeRepository;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.shipmentService = shipmentService;
        this.accountRepository = accountRepository;
        this.warehouseRepository = warehouseRepository;
        this.shipmentRepository = shipmentRepository;
        this.quotationRepository = quotationRepository;
        this.quotationService = quotationService;
    }


    @Test
    @Transactional
    @DisplayName("수주 단일조회 성공 테스트")
    public void successFindOrderTest() {
        OrderDTO order = orderService.findOrderById(3);

        assertThat(order.getOrderDueDate()).isNotNull();
        assertThat(order.getOrderProduct()).isNotNull();
        assertThat(order.getOrderDate()).isNotNull();
        assertThat(order.getAccount()).isNotNull();
        assertThat(order.getEmployee()).isNotNull();
        assertThat(order.getOrderTotalPrice()).isNotNull();
        assertThat(order.getShipmentStatus()).isNotNull();
        assertThat(order.getShipmentStatus()).isNotNull();
        assertThat(order.getTransaction()).isNotNull();
        assertThat(order.getWarehouse()).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("수주 등록 성공 테스트")
    public void successResistOrderTest() {


        Employee employee = employeeRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다"));
        Account account = accountRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 거래처입니다."));
        Warehouse warehouse = warehouseRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 창고입니다."));


        Product product = Product.builder()
                .productId(1)
                .build();
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrderSupplyPrice(10000);
        orderProduct.setOrderProductCount(10);
        orderProduct.setProduct(product);
        orderProduct.setOrderProductionNote("참고사항");
        List<OrderProduct> orderProducts = new ArrayList<>();
        orderProducts.add(orderProduct);

        ShipmentStatus shipmentStatus = new ShipmentStatus();
        shipmentStatus.setShipmentStatusId(1);
        shipmentStatus.setShipmentStatus("출하요청");

        Transaction transaction = new Transaction();
        transaction.setTransactionId(2);
        transaction.setTransactionCode("테스트 거래 코드");

        ContractCategory contractCategory = new ContractCategory();
        contractCategory.setContractCategoryId(1);
        contractCategory.setContractCategory("즉시납부");

        MultipartFile[] files = new MultipartFile[0];

        RequestRegistOrderDTO order = RequestRegistOrderDTO.builder()
                .employee(employee)
                .account(account)
                .warehouse(warehouse)
                .orderProduct(orderProducts)
                .orderTotalPrice(100000)
                .orderNote("비고란")
                .transaction(transaction)
                .contactDate("2024-05-23")
                .contractCategory(contractCategory)
                .orderDueDate("2024-06-10")
                .build();


        ResponseRegistOrderDTO testOrder = orderService.registOrder(order, files);

        assertThat(testOrder.getOrderDate()).isNotNull();
        assertThat(testOrder.getContactDate()).isEqualTo(order.getContactDate());
        assertThat(testOrder.getOrderTotalPrice()).isEqualTo(order.getOrderTotalPrice());
        assertThat(testOrder.getOrderDueDate()).isEqualTo(order.getOrderDueDate());
        assertThat(testOrder.getOrderNote()).isEqualTo(order.getOrderNote());
        assertThat(testOrder.getAccount()).isEqualTo(order.getAccount());
        assertThat(testOrder.getEmployee()).isEqualTo(order.getEmployee());
        assertThat(testOrder.getWarehouse()).isEqualTo(order.getWarehouse());
        assertThat(testOrder.getTransaction().getTransactionId()).isEqualTo(order.getTransaction().getTransactionId());
        assertThat(testOrder.getOrderProduct()).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("수주 전체조회 성공 테스트")
    public void successFindOrderListsTest() {
        List<ResponseOrderLists> orders = orderService.findAllOrders();

        assertThat(orders).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("수주 수정 성공 테스트")
    public void successModifyOrderTest() {
        ContractCategory contractCategory = new ContractCategory();
        contractCategory.setContractCategoryId(2);
        contractCategory.setContractCategory("분할납부");

        MultipartFile[] files = new MultipartFile[0];

        Product product = Product.builder()
                .productId(1)
                .build();
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrderSupplyPrice(10000);
        orderProduct.setOrderProductCount(10);
        orderProduct.setProduct(product);
        orderProduct.setOrderProductionNote("참고사항");
        List<OrderProduct> orderProducts = new ArrayList<>();
        orderProducts.add(orderProduct);

        RequestModifyOrder order = RequestModifyOrder.builder()
                .orderRegistrationId(2)
                .orderTotalPrice(100000)
                .orderNote("비고란")
                .contactDate("2024-05-23")
                .contractCategory(contractCategory)
                .orderDueDate("2024-06-10")
                .orderProduct(orderProducts)
                .build();

        ResponseModifyOrder testOrder = orderService.modifyOrder(order, files);

        assertThat(testOrder.getOrderTotalPrice()).isEqualTo(order.getOrderTotalPrice());
        assertThat(testOrder.getOrderNote()).isEqualTo(order.getOrderNote());
        assertThat(testOrder.getContactDate()).isEqualTo(order.getContactDate());
        assertThat(testOrder.getContractCategory()).isEqualTo(order.getContractCategory());
        assertThat(testOrder.getOrderDueDate()).isEqualTo(order.getOrderDueDate());
    }

    @Test
    @Transactional
    @DisplayName("수주 삭제요청 성공 테스트")
    public void successOrderDeleteRequestTest() {
        Order order = orderRepository.findById(Long.valueOf(2))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 수주입니다."));

        RequestDeleteOrder requestDeleteOrder = RequestDeleteOrder.builder()
                .orderDeleteRequestReason("삭제사유")
                .order(order)
                .build();

        ResponseDeleteOrder responseDeleteOrder = orderService.deleteOrder(requestDeleteOrder);

        assertThat(requestDeleteOrder.getOrderDeleteRequestReason())
                .isEqualTo(responseDeleteOrder.getOrderDeleteRequestReason());
        assertThat(requestDeleteOrder.getOrder())
                .isEqualTo(responseDeleteOrder.getOrder());
        assertThat(responseDeleteOrder.getOrderDeleteRequestStatus()).isNotNull();
    }
}
