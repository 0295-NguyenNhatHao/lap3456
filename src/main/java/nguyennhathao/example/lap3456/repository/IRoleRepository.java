package nguyennhathao.example.lap3456.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository {
    @org.springframework.beans.factory.annotation.Autowired(required = false)
    Long getRoleIdByName(String roleName);


}
