package com.hosmos.management.controller;

import com.hosmos.management.service.SysLogService;
import com.hosmos.management.model.SysLogs;
import com.hosmos.learning.common.utils.table.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 日志相关接口
 */
@Api(tags = "日志")
@RestController
@RequestMapping("/logs")
public class SysLogsController {
    @Autowired
    private SysLogService sysLogsService;

    @GetMapping
    @RequiresPermissions(value = "sys:log:query")
    @ApiOperation(value = "日志列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return sysLogsService.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {
            @Override
            public List<SysLogs> list(PageTableRequest request) {
                return sysLogsService.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }
}