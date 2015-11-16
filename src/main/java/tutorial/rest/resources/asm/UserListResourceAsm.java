package tutorial.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.services.util.UserList;
import tutorial.rest.mvc.UserController;
import tutorial.rest.resources.UserListResource;
import tutorial.rest.resources.UserResource;

import java.util.List;

/**
 * Created by Naglaa on 10/19/2015.
 */
public class UserListResourceAsm extends ResourceAssemblerSupport<UserList, UserListResource> {


    public UserListResourceAsm() {
        super(UserController.class, UserListResource.class);
    }

    @Override
    public UserListResource toResource(UserList userList) {
        List<UserResource> resList = new UserResourceAsm().toResources(userList.getUsers());
        UserListResource finalRes = new UserListResource();
        finalRes.setUsers(resList);
        return finalRes;
    }
}