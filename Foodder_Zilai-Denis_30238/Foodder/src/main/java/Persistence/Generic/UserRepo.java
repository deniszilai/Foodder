package Persistence.Generic;

import Domain.User;

public interface UserRepo extends Repository<Long, User>{
    User FindByLoginCredentials(String email, String password);
}
