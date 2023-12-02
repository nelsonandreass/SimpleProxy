package com.proxy.proxy.repositories;

import java.util.LinkedHashMap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proxy.proxy.models.EntityACL;
import com.proxy.proxy.models.EntityClient;

public interface EntityACLRepository extends JpaRepository<EntityACL, String> {
	 @Query("SELECT ea FROM EntityACL ea WHERE ea.Sender = :sender and ea.Receiver = :receiver")
	 public EntityACL findByReceiver(@Param("sender") String sender, @Param("receiver") String receiver);
	 
	 @Query("SELECT ea FROM EntityACL ea WHERE ea.ServiceName = :serviceName and ea.Permission = 'Y'")
	 public EntityACL findByServiceName(@Param("serviceName")String serviceName);
	 
	 @Query("SELECT ea FROM EntityACL ea WHERE ea.ServiceName = :serviceName and ea.Sender = :sender and ea.Receiver = :receiver and ea.Permission = 'Y'")
	 public EntityACL findByPrivilege(@Param("serviceName")String serviceName , @Param("sender")String sender , @Param("receiver")String receiver);
	 
	 @Query("SELECT NEW MAP(ea.Sender as Sender, ea.Receiver as Receiver, iw.IPClient as IP) FROM EntityACL ea JOIN IPWhitelist iw "
	 		+ "on ea.Sender = iw.EntityName "
	 		+ "WHERE ea.ServiceName = :serviceName "
	 		+ "and ea.Sender = :sender "
	 		+ "and ea.Receiver = :receiver "
	 		+ "and ea.Permission = 'Y' "
	 		+ "and iw.IPClient = :ip")
	 public LinkedHashMap<String, Object> findByPrivilegeAndIp(@Param("serviceName")String serviceName , @Param("sender")String sender , @Param("receiver")String receiver, @Param("ip") String ip);
}
