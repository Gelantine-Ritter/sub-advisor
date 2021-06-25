package com.subadvisor.api.venue;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import com.subadvisor.api.auth.dto.IRegistrationRequestDto;
import com.subadvisor.api.auth.IUserId;
import com.subadvisor.api.event.Event;
import com.subadvisor.api.venue.dto.IVenueDto;
import lombok.*;
import lombok.experimental.Accessors;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "venue")
public class Venue implements UserDetails, Serializable, IUserId, IRegistrationRequestDto, IVenueDto {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    private String password;

    @CreatedDate
    @Column(updatable = false)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime created;

    @LastModifiedDate
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime modifiedAt;

    @NotNull
    private String name;
    @Column(unique = true)
    private String email;
    @Lob
    private String info;
    private String mobile;
    @ElementCollection
    private Map<String, String> hours;
    private String website;

    @ElementCollection
    private Map<String, String> address;

    @ElementCollection
    @Getter
    @Setter
    private Set<String> tags;

    @NotNull
    @Builder.Default
    private String ROLE = "VENUE";

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] pic;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    private Set<Event> events = new HashSet<>();

    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
        //return AuthorityUtils.createAuthorityList(role());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Long getUserId() {
        return this.id;
    }
}
