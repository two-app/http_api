package com.two.http_api.api;

import com.two.http_api.model.Tokens;
import com.two.http_api.model.User;
import com.two.http_api.model.UserWithCredentials;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthenticationServiceContract {

    Tokens storeCredentialsAndGenerateTokens(
            @RequestBody @Valid UserWithCredentials credentials
    );

    Tokens authenticateCredentialsAndGenerateTokens(
            @RequestBody @Valid UserWithCredentials credentials
    );

    Tokens getToken(
            @RequestBody @Valid User user
    );

}
