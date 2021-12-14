package ir.peykasa.authaspect;

import ir.peykasa.authaspect.auth.Auth;
import ir.peykasa.authaspect.common.GlobalException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    @Auth(check = true)
    public Boolean test() throws GlobalException {
        return true;
    }
}
