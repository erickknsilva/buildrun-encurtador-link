    package com.buildrun.encurtadorlinkfbr.core.usecase;


    import com.buildrun.encurtadorlinkfbr.core.domain.User;
    import com.buildrun.encurtadorlinkfbr.core.port.in.CreateUserPortIn;
    import com.buildrun.encurtadorlinkfbr.core.port.out.UserRepositoryPortOut;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.stereotype.Service;

    @Service
    public class CreateUserCase implements CreateUserPortIn {

        private static final Logger logger = LoggerFactory.getLogger(CreateUserCase.class);
        private final UserRepositoryPortOut userRepositoryPortOut;
        private final BCryptPasswordEncoder bCryptPasswordEncoder;

        public CreateUserCase(UserRepositoryPortOut userRepositoryPortOut) {
            this.userRepositoryPortOut = userRepositoryPortOut;
            this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        }

        @Override
        public User execute(User userRequest) {

            logger.info("Creating user {}", userRequest.getEmail());
            userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
            User userCreated = userRepositoryPortOut.save(userRequest);
            logger.info("User created {}", userCreated.getUserId());

            return userCreated;
        }


    }
