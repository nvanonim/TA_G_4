package apap.tugas.sipelatihan.service;

import apap.tugas.sipelatihan.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);

    UserModel getUserByUsername(String username);

    public String encrypt(String password);

}
