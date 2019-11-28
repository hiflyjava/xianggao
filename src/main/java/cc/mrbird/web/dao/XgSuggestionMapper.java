package cc.mrbird.web.dao;


import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.XgSuggestion;

public interface XgSuggestionMapper extends MyMapper<XgSuggestion> {

    int updateSuggestion(XgSuggestion xgSuggestion);

}