package com.hosmos.management.service.impl;

import com.hosmos.management.dao.MailDao;
import com.hosmos.management.model.Mail;
import com.hosmos.management.service.MailService;
import com.hosmos.management.service.SendMailService;
import com.hosmos.management.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Code is far away from bug with the animal protected
 * 　┏┓　　  ┏┓
 * ┏┻┻━━━┻┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┣┛
 * 　　┗┳┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @author chenhuayang
 * @description
 * @date 2019年04月19日
 */
@Service
public class MailServiceImpl implements MailService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private SendMailService sendMailService;
    @Autowired
    private MailDao mailDao;

    @Override
    @Transactional
    public void save(Mail mail, List<String> toUsers) {
        mail.setUserId(UserUtil.getCurrentUser().getId());
        mailDao.save(mail);

        toUsers.forEach(u -> {
            int status = 1;
            try {
                sendMailService.sendMail(u, mail.getSubject(), mail.getContent());
            } catch (Exception e) {
                log.error("发送邮件失败", e);
                status = 0;
            }

            mailDao.saveToUser(mail.getId(), u, status);
        });

    }
}
