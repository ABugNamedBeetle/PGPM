package priv.pgpm.cfi.models.entities.base;

import java.util.List;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseUser {

    // uuid, userID, identity, address, list account, type
    private Integer uuid;

    private BaseUser.Type userType; 

    private Long userId;

    private String identity;

    private String address;

    private List<BaseAccount> accounts;

    
    public static enum Type{
        SELF,
        BANKUSER
    }
}
