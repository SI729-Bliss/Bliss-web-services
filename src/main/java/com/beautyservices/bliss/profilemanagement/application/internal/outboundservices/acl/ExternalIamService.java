package com.beautyservices.bliss.profilemanagement.application.internal.outboundservices.acl;

import com.beautyservices.bliss.iam.domain.model.aggregates.User;
import com.beautyservices.bliss.iam.interfaces.acl.IamContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalIamService {

    private final IamContextFacade iamContextFacade;

    public ExternalIamService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }

    public Optional<User> fetchUserIdByUsername(String email) {
        String username = String.valueOf(iamContextFacade.fetchUserIdByUsername(email));
        if (username.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of( new User(username));
    }

    public boolean existsByUsername(String username) {
        return this.iamContextFacade.existsByUsername(username);
    }


}