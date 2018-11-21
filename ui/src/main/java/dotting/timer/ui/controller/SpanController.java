/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */
package dotting.timer.ui.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import dotting.timer.ui.db.ConnectionPool;
import dotting.timer.ui.po.Span;
import dotting.timer.ui.po.SpanTree;
import dotting.timer.ui.resp.DataResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author sunqinwen
 * @version \: SpanController.java,v 0.1 2018-11-16 19:04
 */
@RestController
@RequestMapping("/span")
public class SpanController {

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public DataResult userWatchReport() {
        return DataResult.success("rua");
    }

    @RequestMapping(value = "/spans", method = RequestMethod.GET)
    public DataResult spans(@RequestParam(value = "traceId") int traceId) {

        String sql = String.format("SELECT * FROM t_span_node WHERE trace_id = %s ORDER BY start ASC", traceId);
        List<Span> result = ConnectionPool.connectionPool.getResults(sql);
        Map<Long, List<Span>> treeMap = Maps.newTreeMap();
        SpanTree root = new SpanTree();
        if (result != null) {
            result.forEach(r -> {
                if (r.getParent_id() == 0) {
                    root.setNode(r);
                }
                treeMap.computeIfAbsent(r.getParent_id(), k -> Lists.newArrayList());
                treeMap.get(r.getParent_id()).add(r);
            });
            makeTree(root, treeMap);
        }
        return DataResult.success(root);
    }

    private void makeTree(SpanTree currentSpan, Map<Long, List<Span>> treeMap) {
        List<Span> nowChild = treeMap.get(currentSpan.getNode().getSpan_id());
        if (nowChild != null && nowChild.size() > 0) {
            nowChild.forEach(nc -> makeTree(currentSpan.setChild(nc), treeMap));
        }
    }

}
