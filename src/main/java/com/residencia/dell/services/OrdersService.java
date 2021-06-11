package com.residencia.dell.services;

import com.residencia.dell.dto.OrderlinesDTO;
import com.residencia.dell.dto.OrderlinesReceiptDTO;
import com.residencia.dell.dto.OrdersDTO;
import com.residencia.dell.dto.ReceiptDTO;
import com.residencia.dell.entities.Orderlines;
import com.residencia.dell.entities.Orders;
import com.residencia.dell.repositories.CustomersRepository;
import com.residencia.dell.repositories.OrderlinesRepository;
import com.residencia.dell.repositories.OrdersRepository;
import com.residencia.dell.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {

    @Autowired
    public OrdersRepository ordersRepository;

    @Autowired
    public CustomersRepository customersRepository;

    @Autowired
    public OrderlinesRepository orderlinesRepository;

    @Autowired
    public ProductsRepository productsRepository;

    //Delete
    public boolean delete (Integer id) {
        if (id != null) {
            ordersRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    //Find by ID
    public Orders findById (Integer id) {
        return ordersRepository.findById(id).get();
    }

    //Find by ID DTO
    public OrdersDTO findByIdDto (Integer id) {
        Orders order = ordersRepository.findById(id).get();
        return entityToDto(order);
    }

    //Find all
    public List<Orders> findAll(Integer page, Integer registersQuantity) throws Exception {
        PageRequest pages = null;
        List<Orders> listOrders = null;
        List<Orders> listOrdersPageable = null;

        try {
            if (null != page && null != registersQuantity){
                pages = PageRequest.of(page, registersQuantity);
                listOrdersPageable = ordersRepository.findAll(pages).getContent();

                return  listOrdersPageable;
            }
            else {
                listOrders = ordersRepository.findAll();

                return listOrders;
            }
        }
        catch (Exception e) {
            throw new Exception("Não foi possível recuperar a lista de pedidos ::" + e.getMessage());
        }
    }

    //Count
    public Long count() {
        return ordersRepository.count();
    }

    //Receipt
    public ReceiptDTO receipt (Integer orderID) {

        Orders orders = ordersRepository.getById(orderID);
        List<Orderlines> orderlinesList = orders.getOrderlines();
        ReceiptDTO receiptDTO = new ReceiptDTO();

        receiptDTO.setFirstName(orders.getCustomerId().getFirstName());
        receiptDTO.setLastName(orders.getCustomerId().getLastName());
        receiptDTO.setNetAmount(orders.getNetAmount());
        receiptDTO.setOrderDate(orders.getOrderDate());
        receiptDTO.setOrderId(orders.getOrderId());
        receiptDTO.setTotalAmount(orders.getTotalAmount());

        List<OrderlinesReceiptDTO> orderlineItemsList = new ArrayList<>();

        for (Orderlines orderlines : orderlinesList){

            OrderlinesReceiptDTO orderlinesReceiptDTO = new OrderlinesReceiptDTO();

            orderlinesReceiptDTO.setProductId(orderlines.getProdId().getProductId());
            orderlinesReceiptDTO.setQuantity(orderlines.getQuantity());
            orderlinesReceiptDTO.setTitle(orderlines.getProdId().getTitle());

            orderlineItemsList.add(orderlinesReceiptDTO);
        }

        receiptDTO.setOrderlinesDTOList(orderlineItemsList);
        return receiptDTO;
    }

    //Entity to DTO
    private OrdersDTO entityToDto (Orders order) {

        OrdersDTO orderDTO = new OrdersDTO();
        List<Orderlines> orderlinesList = order.getOrderlines();

        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setCustomerId(order.getCustomerId().getCustomerId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setNetAmount(order.getNetAmount());
        orderDTO.setTax(order.getTax());
        orderDTO.setTotalAmount(order.getTotalAmount());

        List<OrderlinesDTO> orderlineItemsList = new ArrayList<>();

        for (Orderlines orderlines : orderlinesList){

            OrderlinesDTO orderlinesDTO = new OrderlinesDTO();

            orderlinesDTO.setProductId(orderlines.getProdId().getProductId());
            orderlinesDTO.setQuantity(orderlines.getQuantity());
            orderlinesDTO.setOrderDate(order.getOrderDate());
            orderlinesDTO.setOrderId(order.getOrderId());
            orderlinesDTO.setOrderlineId(orderlines.getOrderlineId());
            orderlineItemsList.add(orderlinesDTO);
        }

        orderDTO.setOrderlines(orderlineItemsList);
        return orderDTO;
    }

    //DTO to Entity
    private Orders dtoToEntity (OrdersDTO orderDTO, Integer id){

        Orders order = new Orders();
        List<OrderlinesDTO> orderlinesList = orderDTO.getOrderlines();

        order.setOrderId( (null == id) ? orderDTO.getOrderId() : id);
        order.setCustomerId(customersRepository.getById(orderDTO.getCustomerId()));
        order.setOrderDate(orderDTO.getOrderDate());
        order.setNetAmount(orderDTO.getNetAmount());
        order.setTax(orderDTO.getTax());
        order.setTotalAmount(orderDTO.getTotalAmount());

        List<Orderlines> orderlineItemsList = new ArrayList<>();

        for (OrderlinesDTO orderlinesDTO : orderlinesList){

            Orderlines orderlines = new Orderlines();

            orderlines.setProdId(productsRepository.getById(orderlinesDTO.getProductId()));
            orderlines.setQuantity(orderlinesDTO.getQuantity());
            //orderlines.setOrderId(ordersRepository.getById(orderlinesDTO.getOrderId()));
            orderlines.setOrderlineId(orderlinesDTO.getOrderlineId());
            orderlines.setOrderDate(orderlinesDTO.getOrderDate());
            orderlineItemsList.add(orderlines);
        }

        order.setOrderlines(orderlineItemsList);
        return order;
    }

    //Create
    public OrdersDTO create (OrdersDTO orderDTO){

        Orders newOrder = dtoToEntity(orderDTO, null);
        List<Orderlines> orderlinesList = new ArrayList<>();
        orderlinesList = newOrder.getOrderlines();

        Orders order = ordersRepository.save(newOrder);


        for (Orderlines orderline : orderlinesList){
            orderline.setOrderId(order);
            orderlinesRepository.save(orderline);
        }

        return  entityToDto(newOrder);
    }

    //Update
    public OrdersDTO update (OrdersDTO orderDTO, Integer id){
        Orders order = dtoToEntity(orderDTO, id);
        Orders newOrder = ordersRepository.save(order);
        return  entityToDto(newOrder);
    }






}
