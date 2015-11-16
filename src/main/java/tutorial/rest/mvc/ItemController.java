package tutorial.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tutorial.core.models.entities.Item;
import tutorial.core.services.ItemService;
import tutorial.core.services.exceptions.*;
import tutorial.core.services.util.ItemList;
import tutorial.rest.exceptions.ConflictException;
import tutorial.rest.resources.*;
import tutorial.rest.resources.asm.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Naglaa on 10/19/2015.
 */
@Configuration
@Controller
@RequestMapping("/rest/items")
public class ItemController {
    @Autowired
    private ItemService itemService;


    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ItemListResource> findAllItems(@RequestParam(value="name", required = false) String name) {
        ItemList list = null;
        if(name == null) {
            list = itemService.findAllItems();
        } else {
            Item item = itemService.findByName(name);
            if(item == null) {
                list = new ItemList(new ArrayList<Item>());
            } else {
                list = new ItemList(Arrays.asList(item));
            }
        }
        ItemListResource res = new ItemListResourceAsm().toResource(list);
        return new ResponseEntity<ItemListResource>(res, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ItemResource> createItem(
            @RequestBody ItemResource sentItem
    ) {
        try {
            Item createdItem = itemService.createItem(sentItem.toItem());
            ItemResource res = new ItemResourceAsm().toResource(createdItem);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<ItemResource>(res, headers, HttpStatus.CREATED);
        } catch(ItemExistsException exception) {
            throw new ConflictException(exception);
        }
    }

    @RequestMapping( value="/{itemId}",
            method = RequestMethod.GET)
    public ResponseEntity<ItemResource> getItem(
            @PathVariable Long itemId
    ) {
        Item item = itemService.findItem(itemId);
        if(item != null)
        {
            ItemResource res = new ItemResourceAsm().toResource(item);
            return new ResponseEntity<ItemResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<ItemResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{itemId}",
            method = RequestMethod.DELETE)
    public ResponseEntity<ItemResource> deleteItem(
            @PathVariable Long itemId) {
        Item entry = itemService.deleteItem(itemId);
        if(entry != null)
        {
            ItemResource res = new ItemResourceAsm().toResource(entry);
            return new ResponseEntity<ItemResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<ItemResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{itemId}",
            method = RequestMethod.PUT)
    public ResponseEntity<ItemResource> updateItem(
            @PathVariable Long itemId, @RequestBody ItemResource sentItem) {
        Item updatedEntry = itemService.updateItem(itemId, sentItem.toItem());
        if(updatedEntry != null)
        {
            ItemResource res = new ItemResourceAsm().toResource(updatedEntry);
            return new ResponseEntity<ItemResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<ItemResource>(HttpStatus.NOT_FOUND);
        }
    }
}
