package rc.bootsecurity.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rc.bootsecurity.model.User;
import rc.bootsecurity.model.UserRepository;

import java.util.Arrays;
import java.util.List;

@Service
//executesafter app starts
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    //this gets run on startup after db initialisation
    public void run(String... args) {

        // Delete all - this will do clear down on each run
        this.userRepository.deleteAll();

        // Crete users
        User adam = new User("adam",passwordEncoder.encode("adam123"),"USER","");
        User admin = new User("admin",passwordEncoder.encode("admin123"),"ADMIN","ACCESS_TEST1,ACCESS_TEST2");
        User manager = new User("manager",passwordEncoder.encode("manager123"),"MANAGER","ACCESS_TEST1");

        List<User> users = Arrays.asList(adam,admin,manager);

        // Save to db
        this.userRepository.saveAll(users);
    }
}
