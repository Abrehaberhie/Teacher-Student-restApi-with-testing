package com.binary.jpaMappingOneToMany.config;

import com.binary.jpaMappingOneToMany.model.Member;
import com.binary.jpaMappingOneToMany.service.MemberServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MemberUserDetailService implements UserDetailsService {
    @Autowired
   private MemberServiceImple memberServiceImple;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String memberuserEmail=null;
        String memberuserPassword=null;
        List<GrantedAuthority> authorities =null;
        Member member =memberServiceImple.getMembersByEmail(username);
        if(member==null)
        {
           throw new UsernameNotFoundException("member not found with email" +username) ;
        }else{
            memberuserEmail=member.getEmail();
            memberuserPassword=member.getPassword();
            authorities=new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(member.getRole()));

        }


        return new User(memberuserEmail,memberuserPassword,authorities);
    }
}
