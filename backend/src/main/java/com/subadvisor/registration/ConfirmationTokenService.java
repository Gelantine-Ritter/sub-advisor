package com.subadvisor.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public
class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    public void deleteConfirmationToken(Long id){
        confirmationTokenRepository.deleteById(id);
    }

    public ConfirmationToken findConfirmationTokenByToken(String token) {
        List<ConfirmationToken> tokenList = (List<ConfirmationToken>) confirmationTokenRepository.findAll();
        for (ConfirmationToken confirmationToken : tokenList) {
            if (confirmationToken.getConfirmationToken() == token) return confirmationToken;
        }
        return null;
    }
}