package tutorial.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.models.entities.Account;
import tutorial.core.models.entities.User;
import tutorial.rest.mvc.AccountController;
import tutorial.rest.mvc.UserController;
import tutorial.rest.resources.AccountResource;
import tutorial.rest.resources.UserResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Naglaa on 10/19/2015.
 */
public class UserResourceAsm extends ResourceAssemblerSupport<User, UserResource> {
    public UserResourceAsm() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User user) {
        UserResource res = new UserResource();
        res.setUserName(user.getUserName());
        res.setEmail(user.getEmail());
        res.setPassword(user.getPassword());
        res.add(linkTo(methodOn(UserController.class).getUser(user.getUserId())).withSelfRel());
        res.add(linkTo(methodOn(UserController.class).findAllOrders(user.getUserId())).withRel("orders"));
        return res;
    }
}
