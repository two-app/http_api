package com.two.http_api.api;

import com.two.http_api.model.Tokens;
import com.two.http_api.model.User;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthenticationServiceContract {

    Tokens storeCredentialsAndGenerateTokens(
            @RequestBody @Valid
                    User.WithCredentials credentials
    );

    Tokens authenticateCredentialsAndGenerateTokens(
            @RequestBody @Valid
                    User.WithCredentials credentials
    );

}
