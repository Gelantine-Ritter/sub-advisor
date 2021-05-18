package com.subadvisor.auth;

import com.subadvisor.user.UserRepository;
import com.subadvisor.venue.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class RegistrationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VenueRepository venueRepository;

    public RegistrationResponse registrateUser(RegistrationRequest registrationRequest) {
        if (registrationRequest.isVenue()){
            if(!userExists(venueRepository, registrationRequest)){
                registrateByRepository(venueRepository, registrationRequest);
            }
        }else if(!registrationRequest.isVenue()) {
            if(!userExists(userRepository, registrationRequest)) {
                registrateByRepository(userRepository, registrationRequest);
            }
        }
        return null;
    }

    private boolean userExists (IUserAccount repository, RegistrationRequest registrationRequest) {
        return repository.findByUsername(registrationRequest.getUsername()).isPresent();
    }

    private Optional<RegistrationResponse> registrateByRepository(IUserAccount repository, RegistrationRequest registrationRequest){
        return repository.createAccount(registrationRequest)
                .map(account -> new RegistrationResponse());
    }
}
