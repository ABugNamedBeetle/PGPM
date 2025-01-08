package priv.pgpm.cfi.models.responses;

import org.springframework.data.jpa.repository.JpaRepository;

import priv.pgpm.cfi.models.entities.CMessage;

public interface CMessageRepository extends JpaRepository<CMessage, String>{

}
