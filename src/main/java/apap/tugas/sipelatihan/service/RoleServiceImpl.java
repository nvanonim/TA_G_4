package apap.tugas.sipelatihan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.sipelatihan.model.RoleModel;
import apap.tugas.sipelatihan.repository.RoleDb;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDb roleDb;

    public List<RoleModel> findAll() {
        return roleDb.findAll();
    }
}
