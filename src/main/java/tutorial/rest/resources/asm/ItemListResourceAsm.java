package tutorial.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.services.util.ItemList;
import tutorial.core.services.util.UserList;
import tutorial.rest.mvc.ItemController;
import tutorial.rest.mvc.UserController;
import tutorial.rest.resources.ItemListResource;
import tutorial.rest.resources.ItemResource;
import tutorial.rest.resources.UserListResource;
import tutorial.rest.resources.UserResource;

import java.util.List;

/**
 * Created by Naglaa on 10/19/2015.
 */
public class ItemListResourceAsm extends ResourceAssemblerSupport<ItemList, ItemListResource> {


    public ItemListResourceAsm() {
        super(ItemController.class, ItemListResource.class);
    }

    @Override
    public ItemListResource toResource(ItemList itemList) {
        List<ItemResource> resList = new ItemResourceAsm().toResources(itemList.getItems());
        ItemListResource finalRes = new ItemListResource();
        finalRes.setItems(resList);
        return finalRes;
    }
}