package org.pegcode.web.controller;


import io.swagger.annotations.ApiOperation;
import org.pegcode.common.entity.response.ResponseEntity;
import org.pegcode.core.service.TbOnlineDocumentService;
import org.pegcode.core.vo.request.DocumentRequest;
import org.pegcode.core.vo.request.OnlineDocumentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 在线编辑文档 前端控制器
 * </p>
 *
 * @author dreampig123
 * @since 2023-01-16
 */
@RestController
@RequestMapping("/doc")
public class TbOnlineDocumentController {

    @Autowired
    private TbOnlineDocumentService tbOnlineDocumentService;

    @Deprecated
    @ApiOperation(value = "新增在线编辑文档")
    @ExceptionHandler(RuntimeException.class)
    @PostMapping(value = "/addDoc")
    public ResponseEntity addOnlineDoc(@RequestBody OnlineDocumentRequest request) {
        return ResponseEntity.success(tbOnlineDocumentService.addOnlineDoc(request));
    }

    @Transactional
    @ApiOperation(value = "新增在线文档")
    @PostMapping(value = "/add")
    public ResponseEntity addDoc(@RequestBody DocumentRequest request) {
        return ResponseEntity.success(tbOnlineDocumentService.addDocMain(request));
    }


}

