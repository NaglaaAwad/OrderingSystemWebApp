package tutorial.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tutorial.core.models.entities.Blog;
import tutorial.core.models.entities.BlogEntry;
import tutorial.core.models.entities.Order;
import tutorial.core.models.entities.OrderDetail;
import tutorial.core.services.BlogService;
import tutorial.core.services.OrderService;
import tutorial.core.services.exceptions.BlogNotFoundException;
import tutorial.core.services.exceptions.OrderNotFoundException;
import tutorial.core.services.util.BlogEntryList;
import tutorial.core.services.util.BlogList;
import tutorial.core.services.util.OrderDetailList;
import tutorial.core.services.util.OrderList;
import tutorial.rest.exceptions.NotFoundException;
import tutorial.rest.resources.*;
import tutorial.rest.resources.asm.*;

import java.net.URI;

/**
 * Created by Naglaa on 10/19/2015.
 */
@Controller
@RequestMapping("/rest/orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<OrderListResource> findAllOrders()
    {
        OrderList orderList = orderService.findAllOrders();
        OrderListResource orderListRes = new OrderListResourceAsm().toResource(orderList);
        return new ResponseEntity<OrderListResource>(orderListRes, HttpStatus.OK);
    }

    @RequestMapping(value="/{orderId}",
            method = RequestMethod.GET)
    public ResponseEntity<OrderResource> getOrder(@PathVariable Long orderId)
    {
        Order order = orderService.findOrder(orderId);
        if(order != null) {
            OrderResource res = new OrderResourceAsm().toResource(order);
            return new ResponseEntity<OrderResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<OrderResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{orderId}/order-details",
            method = RequestMethod.POST)
    public ResponseEntity<OrderDetailResource> createOrderDetail(
            @PathVariable Long orderId,
            @RequestBody OrderDetailResource sentOrderDetail
    ) {
        OrderDetail createdOrderDetail = null;
        try {
            createdOrderDetail = orderService.createOrderDetail(orderId, sentOrderDetail.toOrderDetail());
            OrderDetailResource createdResource = new OrderDetailResourceAsm().toResource(createdOrderDetail);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(createdResource.getLink("self").getHref()));
            return new ResponseEntity<OrderDetailResource>(createdResource, headers, HttpStatus.CREATED);
        } catch (BlogNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @RequestMapping(value="/{orderId}/order-details")
    public ResponseEntity<OrderDetailListResource> findAllOrderDetails(
            @PathVariable Long orderId)
    {
        try {
            OrderDetailList list = orderService.findAllOrderDetails(orderId);
            OrderDetailListResource res = new OrderDetailListResourceAsm().toResource(list);
            return new ResponseEntity<OrderDetailListResource>(res, HttpStatus.OK);
        } catch(OrderNotFoundException exception)
        {
            throw new NotFoundException(exception);
        }
    }

}
