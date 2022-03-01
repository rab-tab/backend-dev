package com.practice.rest.webservices.restwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAResource {
    @Autowired
    private UserDaoService service;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers()
    {
        return userRepository.findAll();
    }

  /*  @GetMapping("/jpa/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id)
    {
        Optional<User> user=userRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException("id-"+id);

        //"all-users", SERVER_PATH + "/users"
        //retrieveAllUsers
       /* EntityModel<User> resource = EntityModel.of(user);

        WebMvcLinkBuilder linkToUsers =
                linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkToUsers.withRel("all-users"));*/
      /*  Resource<User> resource=new Resourse<User>(user.get());
        return resource;
    }*/

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
    {
        User savedUser=userRepository.save(user);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return  ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id)
    {
        userRepository.deleteById(id);
    }
}
