package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.NotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRoleRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IRoleEntityMapper;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class RoleMysqlAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;
    @Override
    public List<Role> getAllRoles() {
        List<RoleEntity> roleEntityList = roleRepository.findAll();
        if (roleEntityList.isEmpty()) {
            throw new NotFoundException("No roles for the user");
        }
        return roleEntityMapper.toRoleList(roleEntityList);
    }

    @Override
    public Role getRole(Long id) {
        return roleEntityMapper.toRole(roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role not found with the id provided")));
    }
}
