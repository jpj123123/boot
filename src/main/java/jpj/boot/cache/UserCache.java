package jpj.boot.cache;

import jpj.boot.entity.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/15
 */
public class UserCache {
    public static Set<User> CACHE_SALES;
    public static void UPCACHE(User user){
        if(user == null || user.getId() == null)
            return;

        if(CACHE_SALES == null)
            CACHE_SALES = new HashSet<>();

        CACHE_SALES = CACHE_SALES.stream().filter(u -> {
            return u.getId() != user.getId();
        }).collect(Collectors.toSet());

        if(user.getIssale())
            CACHE_SALES.add(user);
    };

    public static boolean Contain(Long userId){
        if(userId == null || userId == 0){
            return false;
        }
        if(CACHE_SALES == null)
            return false;
        for(User u : CACHE_SALES){
            if(userId.equals(u.getId())){
                return true;
            }
        }
        return false;
    }

}
