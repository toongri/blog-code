package toongri.blog.springmvcrequestmapping.mapping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/param-header/message")
public class ParamController {

    @GetMapping
    public ResponseEntity<String> message() {
        return ResponseEntity.ok().body("message");
    }

    @GetMapping(params = "name")
    public ResponseEntity<String> messageForParam() {
        return ResponseEntity.ok().body("hello");
    }

    @GetMapping(value = "/multi", params = {"name=hello", "message"})
    public ResponseEntity<String> messageForMultiParam(@RequestParam String name) {
        return ResponseEntity.ok().body("hello message");
    }
}