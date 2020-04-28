package ru.func.takiwadai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.func.takiwadai.entity.user.User;
import ru.func.takiwadai.repository.UserRepository;

/**
 * @author func 23.04.2020
 * @project Takiwadai
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByUsername(userName).get();
    }

    public Long getWorldPlace(User user) {
        return userRepository.findPlaceByPerfectPoints(user.getPerfectPoints());
    }
}

