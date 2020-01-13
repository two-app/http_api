package com.two.http_api.api;

import com.two.http_api.model.Tokens;
import com.two.http_api.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.two.http_api.model.PublicApiModel.UserLogin;
import static com.two.http_api.model.PublicApiModel.UserRegistration;

public class PublicApiContracts {

    public interface PostLogin {
        String loginPostMapping = "/login";

        @PostMapping(loginPostMapping)
        Tokens login(@RequestBody @Valid UserLogin userLogin);
    }

    public interface PostConnect {
        String postConnectPath = "/connect/{connectCode}";
        String connectCodePathVariable = "connectCode";

        @PostMapping(postConnectPath)
        Tokens connect(HttpServletRequest request, @PathVariable(connectCodePathVariable) String partnerConnectCode);
    }

    public interface PostSelf {
        String postSelfPath = "/self";

        @PostMapping(postSelfPath)
        Tokens registerUser(@RequestBody @Valid UserRegistration user);
    }

    public interface GetPartner {
        String getPartnerPath = "/partner";

        @GetMapping(getPartnerPath)
        User getPartner(HttpServletRequest request);
    }

}
