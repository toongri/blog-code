package toongri.blog.springmvcrequestmapping.mapping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;import toongri.blog.springmvcrequestmapping.domain.User;

@RestController
@RequestMapping("/uri-pattern")
public class UriPatternController {

    @GetMapping("/users/{id}")
    public ResponseEntity<User> pathVariable(@PathVariable long id) {
        User user = new User(id, "이름", "email");
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/patterns/{path:[a-z]}")
    public ResponseEntity<String> pattern(@PathVariable String path) {
        return ResponseEntity.ok().body("pattern");
    }

    @GetMapping("/patterns-2/{path}/**")
    public ResponseEntity<String> patternStars(@PathVariable String path) {
        return ResponseEntity.ok().body("pattern-multi");
    }
}