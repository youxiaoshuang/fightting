package com.youdows.fightting.service.impl;

import com.youdows.fightting.dto.User;
import com.youdows.fightting.entity.UserEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by youxiaoshuang on 16/4/11.
 */
public class ArrayListTest {
    public static void main(String[] args){
        List<UserEntity> list = new ArrayList<UserEntity>();
        UserEntity user = null;
        for (int i = 0; i <100 ; i++) {
             user = new UserEntity();
             user.setId(i);
             user.setName("name"+i);
             user.setPassword("pass"+i);
            list.add(user);
        }

        list.sort(new Comparator<UserEntity>() {
            public int compare(UserEntity o1, UserEntity o2) {
               if(o1.getId()>o2.getId()){
                   return -1;
               }else {
                   return 0;
               }
            }
        });
        for (UserEntity usere:list) {
            System.out.println(usere.getId());
        }

    }
}
