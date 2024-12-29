package priv.pgpm.cfi.models.entities.base;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseAccount {

    private Integer uuid;

    private Long accountNumber;

    private BaseUser user;

    private BaseAccount.Type accountType;

    


    public static enum Type{
        RESERVE,
        SUSPENSE
    }
}
