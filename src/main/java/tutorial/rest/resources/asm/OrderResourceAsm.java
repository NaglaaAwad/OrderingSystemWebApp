package tutorial.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.models.entities.Order;
import tutorial.rest.mvc.OrderController;
import tutorial.rest.mvc.UserController;
import tutorial.rest.resources.OrderResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Naglaa on 10/19/2015.
 */
public class OrderResourceAsm extends ResourceAssemblerSupport<Order, OrderResource> {
    public OrderResourceAsm() {
        super(OrderController.class, OrderResource.class);
    }

    @Override
    public OrderResource toResource(Order order) {
        OrderResource resource = new OrderResource();
        resource.setDate(order.getDate());
        resource.add(linkTo(OrderController.class).slash(order.getId()).withSelfRel());
        resource.add(linkTo(OrderController.class).slash(order.getId()).slash("order-detais").withRel("details"));
        if(order.getOwner() != null)
            resource.add(linkTo(UserController.class).slash(order.getOwner().getUserId()).withRel("owner"));
        return resource;
    }
}