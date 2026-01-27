package ecommerce.core.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecommerce.core.user.entity.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long>{

}
