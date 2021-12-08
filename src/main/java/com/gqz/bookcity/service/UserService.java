package com.gqz.bookcity.service;


import com.github.pagehelper.PageInfo;
import com.gqz.bookcity.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Add.
     *
     * @param user the user
     */
    void add(User user);

    /**
     * Gets user list.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return the user list
     */
    PageInfo getUserList(Integer pageNumber, Integer pageSize);

    /**
     * Delete user by id.
     *
     * @param id the id
     */
    void deleteUserById(Integer id);

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    User getUserById(Integer id);

    /**
     * Update.
     *
     * @param user the user
     */
    void update(User user);

    /**
     * Gets list count.
     *
     * @return the list count
     */
    Integer getListCount();

    /**
     * Find user by username boolean.
     *
     * @param username the username
     * @return the boolean
     */
    Boolean findUserByUsername(String username);

    /**
     * Sign in user.
     *
     * @param user the user
     * @return the user
     */
    User signIn(User user);

    /**
     * Find user roles map.
     *
     * @param username the username
     * @return the map
     */
    Map<String, Object> findUserRoles(String username);

    /**
     * Find users list.
     *
     * @return the list
     */
    List<User> findUsers();

}
