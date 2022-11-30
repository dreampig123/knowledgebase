package org.pegcode.common.utils;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.pegcode.common.entity.NodeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 递归读取树结构工具
 */
public class TreeUtil {


    static ArrayList<NodeEntity> totalList = new ArrayList();

    /**
     * 获取根节点
     */
    public static ArrayList<NodeEntity> getRootNodes() {
        ArrayList<NodeEntity> rootList = new ArrayList();
        for (NodeEntity nodeEntity : totalList) {
            if (StringUtils.isEmpty(nodeEntity.getParentId())) {
                rootList.add(nodeEntity);
            }
        }
        return rootList;
    }

    /**
     * 递归获取子节点
     * @param parentNode
     * @return
     */
    public static NodeEntity getChildNodes(NodeEntity parentNode) {
        ArrayList<NodeEntity> childNodes = new ArrayList<>();
        for (NodeEntity nodeEntity : totalList) {
            String parentId = nodeEntity.getParentId();
            if (parentId.equals(parentNode.getId())) {
                childNodes.add(getChildNodes(nodeEntity));
            }
        }
        parentNode.setChildNodes(childNodes);
        return parentNode;
    }

    /**
     * 构建树
     * @return
     */
    public static List<NodeEntity> buildTree(){
        ArrayList<NodeEntity> nodeEntities = new ArrayList<>();
        getRootNodes().stream().forEach(i->{
            NodeEntity childNodes = getChildNodes(i);
            nodeEntities.add(childNodes);
        });
        return nodeEntities;
    }

    public static void main(String[] args) {
        totalList.add(new NodeEntity("A","A节点","",null));
        totalList.add(new NodeEntity("B","B节点","",null));
        totalList.add(new NodeEntity("C","C节点","B",null));
        totalList.add(new NodeEntity("D","D节点","B",null));
        totalList.add(new NodeEntity("E","E节点","A",null));
        totalList.add(new NodeEntity("F","F节点","D",null));
        totalList.add(new NodeEntity("G","G节点","F",null));
        List<NodeEntity> nodeEntities = buildTree();
        System.out.println(JSONArray.toJSONString(nodeEntities));
    }
}
