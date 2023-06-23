package toongri.blog.springmvcrequestmapping.mapping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;import org.springframework.web.bind.annotation.RestController;import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/param-header")
public class HeaderController {

    @RequestMapping(
            headers = { "key1=val1", "key2=val2" }, method = GET)
    @ResponseBody
    public ResponseEntity<String> getFoosWithHeaders() {
        return ResponseEntity.ok().body("headers");
    }
}