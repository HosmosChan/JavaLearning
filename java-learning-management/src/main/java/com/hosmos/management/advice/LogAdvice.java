package com.hosmos.management.advice;


import com.hosmos.management.annotation.LogAnnotation;
import com.hosmos.management.dao.UserDao;
import com.hosmos.management.model.SysLogs;
import com.hosmos.management.service.SysLogService;
import com.hosmos.management.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 统一日志处理
 *
 * @author chenhuayang
 */
@Aspect
@Component
public class LogAdvice {
    @Autowired
    private SysLogService logService;
    @Autowired
    private UserDao userDao;

    @Around(value = "@annotation(com.hosmos.management.annotation.LogAnnotation)")
    public Object logSave(ProceedingJoinPoint joinPoint) throws Throwable {
        SysLogs sysLogs = new SysLogs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String module = null;
        LogAnnotation logAnnotation = methodSignature.getMethod().getDeclaredAnnotation(LogAnnotation.class);
        module = logAnnotation.module();
        if (StringUtils.isEmpty(module)) {
            ApiOperation apiOperation = methodSignature.getMethod().getDeclaredAnnotation(ApiOperation.class);
            if (apiOperation != null) {
                module = apiOperation.value();
            }
        }
        if (StringUtils.isEmpty(module)) {
            throw new RuntimeException("没有指定日志module");
        }
        sysLogs.setModule(module);
        try {
            Object object = joinPoint.proceed();
            sysLogs.setFlag(true);
            logService.save(sysLogs);
            if (module.contains("登陆")) {
                userDao.lastLogin(UserUtil.getCurrentUser().getUsername());
            }
            return object;
        } catch (Exception e) {
            sysLogs.setFlag(false);
            sysLogs.setRemark(e.getMessage());
            logService.save(sysLogs);
            throw e;
        }
    }
}