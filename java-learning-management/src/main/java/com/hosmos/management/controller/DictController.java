package com.hosmos.management.controller;

import com.hosmos.management.service.DictService;
import com.hosmos.management.model.Dict;
import com.hosmos.learning.common.utils.table.PageTableHandler;
import com.hosmos.learning.common.utils.table.PageTableRequest;
import com.hosmos.learning.common.utils.table.PageTableResponse;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典管理相关接口
 *
 * @author chenhuayang
 */
@Api(tags = "字典管理")
@RestController
@RequestMapping("/dicts")
public class DictController {
    @Autowired
    private DictService dictService;

    @RequiresPermissions("dict:add")
    @PostMapping
    @ApiOperation(value = "保存")
    public Dict save(@RequestBody Dict dict) {
        Dict d = dictService.getByTypeAndK(dict.getType(), dict.getK());
        if (d != null) {
            throw new IllegalArgumentException("类型和key已存在");
        }
        dictService.save(dict);
        return dict;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Dict get(@PathVariable Long id) {
        return dictService.getById(id);
    }

    @RequiresPermissions("dict:add")
    @PutMapping
    @ApiOperation(value = "修改")
    public Dict update(@RequestBody Dict dict) {
        dictService.update(dict);
        return dict;
    }

    @RequiresPermissions("dict:query")
    @GetMapping(params = {"start", "length"})
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return dictService.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {
            @Override
            public List<Dict> list(PageTableRequest request) {
                return dictService.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @RequiresPermissions("dict:del")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        dictService.delete(id);
    }

    @GetMapping(params = "type")
    public List<Dict> listByType(String type) {
        return dictService.listByType(type);
    }
}