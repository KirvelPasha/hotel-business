package com.netcracker.config;

import com.netcracker.entity.Person;
import com.netcracker.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Person> optionalPersonperson = personRepository.findByLogin(login);
        if (optionalPersonperson.isPresent()) {
            Person person = optionalPersonperson.get();

            List<SimpleGrantedAuthority> authList = getAuthorities(person.getPersonRole().getRole());


            return new User(login, person.getPassword(), authList);
        }
        throw new UsernameNotFoundException("No such person");
    }

    private List<SimpleGrantedAuthority> getAuthorities(String role) {
        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        switch (role) {
            case "user":
                authList.add(new SimpleGrantedAuthority("user"));
                break;
            case "admin":
                authList.add(new SimpleGrantedAuthority("admin"));
                break;
        }
        log.info("User has been authorization with role=" + authList.toString());
        return authList;
    }
}
