package cheanxin.service.impl;

import cheanxin.constant.ConfigConstants;
import cheanxin.data.UserRepository;
import cheanxin.domain.Dept;
import cheanxin.domain.Post;
import cheanxin.domain.User;
import cheanxin.domain.UserPost;
import cheanxin.service.DeptService;
import cheanxin.service.PostService;
import cheanxin.service.UserPostService;
import cheanxin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * Created by 273cn on 16/12/21.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    DeptService deptService;

    @Autowired
    UserPostService userPostService;

    @Autowired
    PostService postService;

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsernameIgnoringCase(username);
    }

    @Override
    public User getByMobileNumber(String mobileNumber) {
        return userRepository.findByMobileNumber(mobileNumber);
    }

    @Override
    public User save(User user) {
        // encode password.
        user.setPassword(new StandardPasswordEncoder(ConfigConstants.PASSWORD_SECRET).encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Page<User> list(long deptId, String realName, String mobileNumber, String email, int status, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        SearchUser searchUser = new SearchUser();
        searchUser.setDeptId(deptId);
        searchUser.setRealName(realName);
        searchUser.setMobileNumber(mobileNumber);
        searchUser.setEmail(email);
        searchUser.setStatus(status);
        Specification<User> specification = this.getWhereClause(searchUser);
        Page<User> userPage =  userRepository.findAll(specification, pageable);

        // set user dept info and post info.
        Map<Long, Dept> deptMap = new HashMap<>();
        Set<String> usernames = new HashSet<>();
        for (User user : userPage) {
            deptMap.put(user.getDeptId(), null);
            usernames.add(user.getUsername());
        }
        List<Dept> deptList = deptService.list(deptMap.keySet());
        for (Dept dept : deptList) {
            deptMap.put(dept.getId(), dept);
        }

        Map<String, Collection<Post>> userPostListMap = userPostService.listUserPostListMap(usernames);
        for (User user : userPage) {
            user.setPosts(userPostListMap.get(user.getUsername()));
        }

        return userPage;
    }

    @Override
    public List<User> list(long deptId, long postId, boolean enabled) {
        List<User> userList = userRepository.findAllByDeptIdAndEnabled(deptId, enabled);
        if (postId <= 0L) return userList;
        Set<String> usernames = new HashSet<>();
        for (User user : userList) {
            usernames.add(user.getUsername());
        }
        List<UserPost> userPostList = userPostService.list(usernames, postId);
        usernames.clear();
        for (UserPost userPost : userPostList) {
            usernames.add(userPost.getUsername());
        }

        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (!usernames.contains(user.getUsername())) {
                iterator.remove();
            }
        }

        return userList;
    }

    @Override
    public boolean isUsernameExists(String username) {
        return getByUsername(username) != null;
    }

    @Override
    public boolean isMobileNoExists(String mobileNo) {
        return getByMobileNumber(mobileNo) != null;
    }

    private class SearchUser {
        private Long deptId;
        private String realName;
        private String mobileNumber;
        private String email;
        private Integer status;

        public SearchUser() {}

        public Long getDeptId() {
            return deptId;
        }

        public void setDeptId(Long deptId) {
            this.deptId = deptId;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }
    }

    /**
     * generate where clause dynamically.
     * @param searchUser
     * @return
     */
    private Specification<User> getWhereClause(final SearchUser searchUser){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                if (searchUser.getDeptId() > 0L) {
                    predicate.add(cb.equal(root.get("deptId").as(Long.class), searchUser.getDeptId()));
                }
                if (searchUser.getRealName() != null && !searchUser.getRealName().trim().isEmpty()) {
                    predicate.add(cb.equal(root.get("realName").as(String.class), searchUser.getRealName() + "%"));
                }
                if (searchUser.getMobileNumber() != null && !searchUser.getMobileNumber().trim().isEmpty()) {
                    predicate.add(cb.like(root.get("mobileNumber").as(String.class), searchUser.getMobileNumber() + "%"));
                }
                if (searchUser.getEmail() != null && !searchUser.getEmail().trim().isEmpty()) {
                    predicate.add(cb.like(root.get("email").as(String.class), searchUser.getEmail() + "%"));
                }
                if (searchUser.getStatus() >= 0) {
                    predicate.add(cb.equal(root.get("enabled").as(Integer.class), searchUser.getStatus()));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}
