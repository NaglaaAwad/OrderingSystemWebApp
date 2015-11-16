package tutorial.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tutorial.core.models.entities.Account;
import tutorial.core.models.entities.Blog;
import tutorial.core.models.entities.Order;
import tutorial.core.models.entities.User;
import tutorial.core.services.AccountService;
import tutorial.core.services.UserService;
import tutorial.core.services.exceptions.*;
import tutorial.core.services.util.AccountList;
import tutorial.core.services.util.BlogList;
import tutorial.core.services.util.OrderList;
import tutorial.core.services.util.UserList;
import tutorial.rest.exceptions.ConflictException;
import tutorial.rest.exceptions.ForbiddenException;
import tutorial.rest.exceptions.NotFoundException;
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
@RequestMapping("/rest/users")
public class UserController {
    @Autowired
    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<UserListResource> findAllUsers(@RequestParam(value="name", required = false) String name) {
        UserList list = null;
        if(name == null) {
            list = userService.findAllUsers();
        } else {
            User user = userService.findByUserName(name);
            if(user == null) {
                list = new UserList(new ArrayList<User>());
            } else {
                list = new UserList(Arrays.asList(user));
            }
        }
        UserListResource res = new UserListResourceAsm().toResource(list);
        return new ResponseEntity<UserListResource>(res, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<UserResource> createUser(
            @RequestBody UserResource sentUser
    ) {
        try {
            User createdUser = userService.createUser(sentUser.toUser());
            UserResource res = new UserResourceAsm().toResource(createdUser);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<UserResource>(res, headers, HttpStatus.CREATED);
        } catch(UserExistsException exception) {
            throw new ConflictException(exception);
        }
    }

    @RequestMapping( value="/{userId}",
            method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<UserResource> getUser(
            @PathVariable Long userId
    ) {
        User user = userService.findUser(userId);
        if(user != null)
        {
            UserResource res = new UserResourceAsm().toResource(user);
            return new ResponseEntity<UserResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<UserResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{userId}/orders",
            method = RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<OrderResource> createOrder(
            @PathVariable Long userId,
            @RequestBody OrderResource res)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            UserDetails details = (UserDetails)principal;
            User loggedIn = userService.findByUserName(details.getUsername());
            if(loggedIn.getUserId() == userId) {
                try {
                    Order createdOrder = userService.createOrder(userId, res.toOrder());
                    OrderResource createdOrderRes = new OrderResourceAsm().toResource(createdOrder);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(URI.create(createdOrderRes.getLink("self").getHref()));
                    return new ResponseEntity<OrderResource>(createdOrderRes, headers, HttpStatus.CREATED);
                } catch(AccountDoesNotExistException exception)
                {
                    throw new NotFoundException(exception);
                } catch(BlogExistsException exception)
                {
                    throw new ConflictException(exception);
                }
            } else {
                throw new ForbiddenException();
            }
        } else {
            throw new ForbiddenException();
        }
    }

    @RequestMapping(value="/{userId}/orders",
            method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<OrderListResource> findAllOrders(
            @PathVariable Long userId) {
        try {
            OrderList orderList = userService.findOrdersByUser(userId);
            OrderListResource orderListRes = new OrderListResourceAsm().toResource(orderList);
            return new ResponseEntity<OrderListResource>(orderListRes, HttpStatus.OK);
        } catch(UserDoesNotExistException exception)
        {
            throw new NotFoundException(exception);
        }
    }



}
