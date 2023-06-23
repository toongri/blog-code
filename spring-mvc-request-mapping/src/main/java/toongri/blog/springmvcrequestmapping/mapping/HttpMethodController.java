package toongri.blog.springmvcrequestmapping.mapping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toongri.blog.springmvcrequestmapping.domain.User;

import java.net.URI;
import java.util.Arrays;
import java.util.List;import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/http-method/users")
public class HttpMethodController {


    @RequestMapping(value = "/ex/foos")
    @ResponseBody
    public ResponseEntity<String> getFoosBySimplePath() {
        return ResponseEntity.ok().body("foos");
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) {
        Long id = 1L;
        return ResponseEntity.created(URI.create("/users/" + id)).build();
    }

    @GetMapping
    public ResponseEntity<List<User>> showUser() {
        List<User> users = Arrays.asList(
                new User("이름", "email"),
                new User("이름", "email")
        );
        return ResponseEntity.ok().body(users);
    }

}