package tutorial.rest.resources.asm;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.models.entities.OrderDetail;
import tutorial.rest.mvc.OrderController;
import tutorial.rest.mvc.OrderDetailController;
import tutorial.rest.resources.OrderDetailResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Naglaa on 10/19/2015.
 */
public class OrderDetailResourceAsm extends ResourceAssemblerSupport<OrderDetail, OrderDetailResource> {

    public OrderDetailResourceAsm()
    {
        super(OrderDetailController.class, OrderDetailResource.class);
    }

    @Override
    public OrderDetailResource toResource(OrderDetail orderDetail) {
        OrderDetailResource res = new OrderDetailResource();
        res.setItem(orderDetail.getItem());
        res.setQuantity(orderDetail.getQuantity());
        Link self = linkTo(OrderDetailController.class).slash(orderDetail.getId()).withSelfRel();
        res.add(self);
        if(orderDetail.getOrder() != null)
        {
            res.add((linkTo(OrderController.class).slash(orderDetail.getOrder().getId()).withRel("order")));
        }
        return res;
    }
}

