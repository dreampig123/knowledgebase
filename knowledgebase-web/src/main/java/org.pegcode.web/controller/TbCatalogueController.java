package org.pegcode.web.controller;


import io.swagger.annotations.ApiOperation;
import org.pegcode.common.entity.response.ResponseEntity;
import org.pegcode.core.service.TbCatalogueService;
import org.pegcode.core.vo.request.CatalogueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 目录表 前端控制器
 * </p>
 *
 * @author dreampig123
 * @since 2023-01-16
 */
@RestController
@RequestMapping("/catalogue")
public class TbCatalogueController {
    @Autowired
    private TbCatalogueService catalogueService;

    @ApiOperation(value = "新增目录")
    @ExceptionHandler(RuntimeException.class)
    @PostMapping(value = "/add")
    public ResponseEntity addCatalogue(@RequestBody @Valid CatalogueRequest request) {
        return ResponseEntity.success(catalogueService.addCatalogue(request));
    }
}

