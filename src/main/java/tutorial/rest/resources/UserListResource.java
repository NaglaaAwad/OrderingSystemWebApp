package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naglaa on 10/19/2015.
 */
public class UserListResource extends ResourceSupport{
    private List<UserResource> users = new ArrayList<UserResource>();

    public List<UserResource> getUsers() {
        return users;
    }

    public void setUsers(List<UserResource> users) {
        this.users = users;
    }
}
