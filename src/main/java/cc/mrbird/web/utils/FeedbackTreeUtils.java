package cc.mrbird.web.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedbackTreeUtils {
	
	public static <T> FeedbackTree<T> build(List<FeedbackTree<T>> nodes) {
		if (nodes == null) {
			return null;
		}
		List<FeedbackTree<T>> topNodes = new ArrayList<FeedbackTree<T>>();
		for (FeedbackTree<T> children : nodes) {
			String pid = children.getParentId();
			if (pid == null || "0".equals(pid)) {
				topNodes.add(children);
				continue;
			}
			for (FeedbackTree<T> parent : nodes) {
				String id = parent.getId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setChildren(true);
					continue;
				}
			}

		}

		FeedbackTree<T> root = new FeedbackTree<T>();
		root.setId("0");
		root.setParentId("");
		root.setHasParent(false);
		root.setChildren(true);
		root.setChecked(true);
		root.setChildren(topNodes);
		root.setText("根节点");
		Map<String, Object> state = new HashMap<>(16);
		state.put("opened", true);
		root.setState(state);
		return root;
	}

	public static <T> List<FeedbackTree<T>> buildList(List<FeedbackTree<T>> nodes, String idParam) {
		if (nodes == null) {
			return null;
		}
		List<FeedbackTree<T>> topNodes = new ArrayList<FeedbackTree<T>>();
		for (FeedbackTree<T> children : nodes) {
			String pid = children.getParentId();
			if (pid == null || idParam.equals(pid)) {
				topNodes.add(children);
				continue;
			}
			for (FeedbackTree<T> parent : nodes) {
				String id = parent.getId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setChildren(true);
					continue;
				}
			}
		}
		return topNodes;
	}
}