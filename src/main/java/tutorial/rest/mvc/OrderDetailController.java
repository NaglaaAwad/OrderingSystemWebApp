package tutorial.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tutorial.core.models.entities.OrderDetail;
import tutorial.core.services.OrderDetailService;
import tutorial.rest.resources.OrderDetailResource;
import tutorial.rest.resources.asm.OrderDetailResourceAsm;

/**
 * Created by Naglaa on 10/19/2015.
 */
@Controller
@RequestMapping("/rest/order-details")
public class OrderDetailController {
    private OrderDetailService service;

    @Autowired
    public OrderDetailController(OrderDetailService service)
    {
        this.service = service;
    }

    @RequestMapping(value="/{orderDetailId}",
            method = RequestMethod.GET)
    public ResponseEntity<OrderDetailResource> getOrderDetail(
            @PathVariable Long orderDetailId) {
        OrderDetail entry = service.findOrderDetail(orderDetailId);
        if(entry != null)
        {
            OrderDetailResource res = new OrderDetailResourceAsm().toResource(entry);
            return new ResponseEntity<OrderDetailResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<OrderDetailResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{orderDetailId}",
            method = RequestMethod.DELETE)
    public ResponseEntity<OrderDetailResource> deleteBlogEntry(
            @PathVariable Long orderDetailId) {
        OrderDetail entry = service.deleteOrderDetail(orderDetailId);
        if(entry != null)
        {
            OrderDetailResource res = new OrderDetailResourceAsm().toResource(entry);
            return new ResponseEntity<OrderDetailResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<OrderDetailResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{orderDetailId}",
            method = RequestMethod.PUT)
    public ResponseEntity<OrderDetailResource> updateOrderDetail(
            @PathVariable Long orderDetailId, @RequestBody OrderDetailResource sentOrderDetail) {
        OrderDetail updatedEntry = service.updateOrderDetail(orderDetailId, sentOrderDetail.toOrderDetail());
        if(updatedEntry != null)
        {
            OrderDetailResource res = new OrderDetailResourceAsm().toResource(updatedEntry);
            return new ResponseEntity<OrderDetailResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<OrderDetailResource>(HttpStatus.NOT_FOUND);
        }
    }
}
