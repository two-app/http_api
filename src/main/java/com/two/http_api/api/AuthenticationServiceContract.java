package com.two.http_api.api;

import com.two.http_api.model.Tokens;
import com.two.http_api.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;

public interface AuthenticationServiceContract {

    ResponseEntity<Tokens> storeCredentialsAndGenerateTokens(
            @RequestBody @NotNull(message = "You must provide credentials.") @Validated
                    User.Credentials credentials
    );

}
