package org.pegcode.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 节点类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeEntity {

    /**
     * 节点id
     */
    private String id;

    /**
     * 节点名
     */
    private String nodeName;

    /**
     * 父节点id
     */
    private String parentId;

    /**
     * 子节点集
     */
    private List<NodeEntity> childNodes;

}
