package com.sonkabin.repository;

import com.sonkabin.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {

    @Modifying
    @Query(value = "INSERT INTO tb_authority(role_id,menu_id) VALUES(?1,?2)",nativeQuery = true)
    void saveAuthority(Integer roleId,Integer menuId);

    @Query(value = "SELECT role_name,menu_name,a.role_id,a.menu_id FROM tb_user_role r INNER JOIN tb_menu m INNER JOIN tb_authority a" +
            " WHERE r.id = a.role_id AND m.id = a.menu_id",nativeQuery = true)
    Object[] findAllAuthority();

    @Modifying
    @Query(value = "DELETE FROM tb_authority WHERE role_id=?1 AND menu_id = ?2",nativeQuery = true)
    void deleteAuthority(Integer roleId, Integer menuId);
}
