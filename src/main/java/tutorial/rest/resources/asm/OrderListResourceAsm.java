package tutorial.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.services.util.BlogList;
import tutorial.core.services.util.OrderList;
import tutorial.rest.mvc.BlogController;
import tutorial.rest.mvc.OrderController;
import tutorial.rest.resources.BlogListResource;
import tutorial.rest.resources.OrderListResource;

/**
 * Created by Naglaa on 10/19/2015.
 */
public class OrderListResourceAsm extends ResourceAssemblerSupport<OrderList, OrderListResource> {

    public OrderListResourceAsm()
    {
        super(OrderController.class, OrderListResource.class);
    }

    @Override
    public OrderListResource toResource(OrderList orderList) {
        OrderListResource res = new OrderListResource();
        res.setOrders(new OrderResourceAsm().toResources(orderList.getOrders()));
        return res;
    }
}