package nguyennhathao.example.lap3456.services;

import nguyennhathao.example.lap3456.repository.User;
import nguyennhathao.example.lap3456.repository.IRoleRepository;
import nguyennhathao.example.lap3456.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired(required = false)
    private IRoleRepository roleRepository;
    public void save(User user){
        userRepository.save(user);
        Long userId = userRepository.getUserIdByUsername(user.getUsername());
        Long roleId= roleRepository.getRoleIdByName("USER");
        if(roleId !=0 && userId !=0){
            userRepository.addRoleToUser(userId,roleId);
        }
    }

}
