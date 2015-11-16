package tutorial.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.models.entities.Item;
import tutorial.core.models.entities.User;
import tutorial.rest.mvc.ItemController;
import tutorial.rest.mvc.UserController;
import tutorial.rest.resources.ItemResource;
import tutorial.rest.resources.UserResource;

import java.util.Date;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Naglaa on 10/19/2015.
 */
public class ItemResourceAsm extends ResourceAssemblerSupport<Item, ItemResource> {
    public ItemResourceAsm() {
        super(ItemController.class, ItemResource.class);
    }

    @Override
    public ItemResource toResource(Item item) {
        ItemResource res = new ItemResource();
        res.setName(item.getName());
        res.setQuantity(item.getQuantity());
        res.setCategory(item.getCategory());
        res.setImage(item.getImage());
        res.setPrice(item.getPrice());
        res.setDateAdded(item.getDateAdded());
        res.setStatus(item.getStatus());
        res.add(linkTo(methodOn(ItemController.class).getItem(item.getId())).withSelfRel());
        return res;
    }
}
