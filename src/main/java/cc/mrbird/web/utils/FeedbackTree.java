package cc.mrbird.web.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FeedbackTree<T> {
	/**
	 * 节点ID
	 */
	private String id;
	/**
	 * 图标
	 */
	private String toUsername;//给谁评论的
	/**
	 * url
	 */
	private String fromUsername;//评论人

	private Date createDate;

	private String toUserImg;

	private String  fromUserImg;



	private String  feedbackTitle;
	private String  suggestInfo;
	private String  type;
	private String  suggestionUserName;


	/**
	 * 显示节点文本
	 */
	private String text;
	/**
	 * 节点状态，open closed
	 */
	private Map<String, Object> state;
	/**
	 * 节点是否被选中 true false
	 */
	private boolean checked = false;
	/**
	 * 节点属性
	 */
	private Map<String, Object> attributes;

	/**
	 * 节点的子节点
	 */
	private List<FeedbackTree<T>> children = new ArrayList<FeedbackTree<T>>();

	/**
	 * 父ID
	 */
	private String parentId;
	/**
	 * 是否有父节点
	 */
	private boolean hasParent = false;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getToUserImg() {
		return toUserImg;
	}

	public void setToUserImg(String toUserImg) {
		this.toUserImg = toUserImg;
	}

	public String getFromUserImg() {
		return fromUserImg;
	}

	public void setFromUserImg(String fromUserImg) {
		this.fromUserImg = fromUserImg;
	}

	public String getFeedbackTitle() {
		return feedbackTitle;
	}

	public void setFeedbackTitle(String feedbackTitle) {
		this.feedbackTitle = feedbackTitle;
	}

	public String getSuggestInfo() {
		return suggestInfo;
	}

	public void setSuggestInfo(String suggestInfo) {
		this.suggestInfo = suggestInfo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSuggestionUserName() {
		return suggestionUserName;
	}

	public void setSuggestionUserName(String suggestionUserName) {
		this.suggestionUserName = suggestionUserName;
	}

	/**
	 * 是否有子节点
	 */




	private boolean hasChildren = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Map<String, Object> getState() {
		return state;
	}

	public void setState(Map<String, Object> state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<FeedbackTree<T>> getChildren() {
		return children;
	}

	public void setChildren(List<FeedbackTree<T>> children) {
		this.children = children;
	}

	public boolean isHasParent() {
		return hasParent;
	}

	public void setHasParent(boolean isParent) {
		this.hasParent = isParent;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setChildren(boolean isChildren) {
		this.hasChildren = isChildren;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public String getToUsername() {
		return toUsername;
	}

	public void setToUsername(String toUsername) {
		this.toUsername = toUsername;
	}

	public String getFromUsername() {
		return fromUsername;
	}

	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}

	public FeedbackTree(String id, String text, Map<String, Object> state, boolean checked, Map<String, Object> attributes,
						List<FeedbackTree<T>> children, String toUsername, String fromUsername, boolean isParent, boolean isChildren, String parentID,String toUserImg,
						String fromUserImg,
						String  feedbackTitle,
						String  suggestInfo,
						String  type,
						String  suggestionUserName,
						Date createDate) {





		super();
		this.id = id;
		this.text = text;
		this.toUsername = toUsername;
		this.fromUsername = fromUsername;
		this.state = state;
		this.checked = checked;
		this.attributes = attributes;
		this.children = children;
		this.hasParent = isParent;
		this.hasChildren = isChildren;
		this.parentId = parentID;
		this.toUserImg=toUserImg;
		this.fromUserImg=fromUserImg;
		this.createDate=createDate;
		this.feedbackTitle=feedbackTitle;
		this.suggestInfo=suggestInfo;
		this.type=type;
		this.suggestionUserName=suggestionUserName;
	}

	public FeedbackTree() {
		super();
	}

	@Override
	public String toString() {

		return JSON.toJSONString(this);
	}

}