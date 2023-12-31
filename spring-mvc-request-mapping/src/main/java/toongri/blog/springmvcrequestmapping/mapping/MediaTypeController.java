package toongri.blog.springmvcrequestmapping.mapping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toongri.blog.springmvcrequestmapping.domain.User;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/media-type")
public class MediaTypeController {

    @PostMapping(value = "/users", consumes = "application/json")
    public ResponseEntity createUser(@RequestBody User user) {
        Long id = 1L;
        return ResponseEntity.created(URI.create("/users/" + id)).build();
    }

    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<List<User>> showUser() {
        List<User> users = Arrays.asList(
                new User("이름", "email"),
                new User("이름", "email")
        );
        return ResponseEntity.ok().body(users);
    }
    @GetMapping(value = "/users", produces = "text/html")
    public String userPage() {
        return "user page";
    }
}