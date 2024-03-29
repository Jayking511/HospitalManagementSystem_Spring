package com.project.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.project.model.Permission.ADMIN_CREATE;
import static com.project.model.Permission.ADMIN_DELETE;
import static com.project.model.Permission.ADMIN_READ;
import static com.project.model.Permission.ADMIN_UPDATE;
import static com.project.model.Permission.DOCTOR_UPDATE;
import static com.project.model.Permission.DOCTOR_READ;

import static com.project.model.Permission.RECEPTIONIST_UPDATE;
import static com.project.model.Permission.RECEPTIONIST_READ;
import static com.project.model.Permission.RECEPTIONIST_DELETE;
import static com.project.model.Permission.RECEPTIONIST_CREATE;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {

  USER(Collections.emptySet()),
  ADMIN(
          Set.of(
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  ADMIN_CREATE
                 
                
          )
  ),
  DOCTOR(
          Set.of(
        		DOCTOR_READ,
        		DOCTOR_UPDATE
          )
  ),
  RECEPTIONIST(
		  Set.of(
        		 RECEPTIONIST_READ,
        		 RECEPTIONIST_UPDATE,
        		 RECEPTIONIST_DELETE,
        		 RECEPTIONIST_CREATE
                
          )
  )

  ;

  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
