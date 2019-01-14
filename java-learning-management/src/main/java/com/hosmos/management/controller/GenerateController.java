package com.hosmos.management.controller;


import com.hosmos.management.annotation.LogAnnotation;
import com.hosmos.management.dto.BeanField;
import com.hosmos.management.dto.GenerateDetail;
import com.hosmos.management.dto.GenerateInput;
import com.hosmos.management.service.GenerateService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 代码生成接口
 *
 * @author chenhuayang
 */
@Api(tags = "代码生成")
@RestController
@RequestMapping("/generate")
public class GenerateController {
    @Autowired
    private GenerateService generateService;

    @ApiOperation("根据表名显示表信息")
    @GetMapping(params = {"tableName"})
    @RequiresPermissions("generate:edit")
    public GenerateDetail generateByTableName(String tableName) {
        GenerateDetail detail = new GenerateDetail();
        detail.setBeanName(generateService.upperFirstChar(tableName));
        List<BeanField> fields = generateService.listBeanField(tableName);
        detail.setFields(fields);
        return detail;
    }

    @LogAnnotation
    @ApiOperation("生成代码")
    @PostMapping
    @RequiresPermissions("generate:edit")
    public void save(@RequestBody GenerateInput input) {
        generateService.saveCode(input);
    }
}