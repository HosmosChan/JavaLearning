package com.hosmos.management.dao;

import com.hosmos.management.model.Mail;
import com.hosmos.management.model.MailTo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface MailDao {

	@Select("select * from t_mail t where t.id = #{id}")
	Mail getById(Long id);

//	@Delete("delete from t_mail where id = #{id}")
//	int delete(Long id);

//	@Update("update t_mail t set subject = #{subject}, content = #{content}, gmtTime = now() where t.id = #{id}")
//	int update(Mail mail);

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into t_mail(userId, subject, content, createTime, gmtTime) values(#{userId}, #{subject}, #{content}, now(), now())")
	int save(Mail mail);

	@Insert("insert into t_mail_to(mailId, toUser, status, createTime) values(#{mailId}, #{toUser}, #{status}, now())")
	int saveToUser(@Param("mailId") Long mailId, @Param("toUser") String toUser, @Param("status") int status);

	@Select("select t.* from t_mail_to t where t.mailId = #{mailId}")
	List<MailTo> getToUsers(Long mailId);

	int count(@Param("params") Map<String, Object> params);

	List<Mail> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                    @Param("limit") Integer limit);
}
