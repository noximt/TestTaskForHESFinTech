package by.yauheni.service;

import by.yauheni.dto.NewUserDTO;
import by.yauheni.entity.Role;
import by.yauheni.entity.UserAccount;
import by.yauheni.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
public class UserAccountService implements UserDetailsService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserAccountService(UserAccountRepository accountRepository, PasswordEncoder encoder) {
        this.userAccountRepository = accountRepository;
        this.encoder = encoder;
    }

    public void update(NewUserDTO newUserDTO){

    }

    public void save(NewUserDTO newUserDTO) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(newUserDTO.getUsername());
        userAccount.setPassword(encoder.encode(newUserDTO.getPassword()));
        userAccount.setFirstName(newUserDTO.getFirstName());
        userAccount.setLastName(newUserDTO.getLastName());
        HashSet<Role> role = new HashSet<>();
        role.add(newUserDTO.getRole());
        userAccount.setRoles(role);
        userAccount.setState(newUserDTO.getState());
        userAccountRepository.save(userAccount);
    }

    public List<UserAccount> getAll() {
        return userAccountRepository.findAll(Sort.by("username"));
    }

    public UserAccount findById(long id) {
        UserAccount byId = userAccountRepository.getById(id);

        return byId;
    }

    public boolean existsByUsername(String username) {
        return userAccountRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount byUsername = userAccountRepository.findByUsername(username);
        return byUsername;
    }
}
