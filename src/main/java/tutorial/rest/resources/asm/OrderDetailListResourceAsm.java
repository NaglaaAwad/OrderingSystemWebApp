package tutorial.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.services.util.BlogEntryList;
import tutorial.core.services.util.OrderDetailList;
import tutorial.rest.mvc.BlogController;
import tutorial.rest.mvc.OrderController;
import tutorial.rest.resources.BlogEntryListResource;
import tutorial.rest.resources.BlogEntryResource;
import tutorial.rest.resources.OrderDetailListResource;
import tutorial.rest.resources.OrderDetailResource;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Naglaa on 10/19/2015.
 */
public class OrderDetailListResourceAsm extends ResourceAssemblerSupport<OrderDetailList, OrderDetailListResource> {
    public OrderDetailListResourceAsm() {
        super(OrderController.class, OrderDetailListResource.class);
    }

    @Override
    public OrderDetailListResource toResource(OrderDetailList list) {
        List<OrderDetailResource> resources = new OrderDetailResourceAsm().toResources(list.getOrderDetails());
        OrderDetailListResource listResource = new OrderDetailListResource();
        listResource.setOrderDetails(resources);
        listResource.add(linkTo(methodOn(OrderController.class).findAllOrderDetails(list.getOrderId())).withSelfRel());
        return listResource;
    }
}
