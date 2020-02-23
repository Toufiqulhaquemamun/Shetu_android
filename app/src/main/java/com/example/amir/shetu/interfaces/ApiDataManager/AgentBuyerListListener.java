package com.example.amir.shetu.interfaces.ApiDataManager;
import com.example.amir.shetu.model.AgentBuyerList;

import java.util.List;


public interface AgentBuyerListListener {
    public void getAgentBuyerList(List<AgentBuyerList.Datum> agentbuyList,List<AgentBuyerList> agentbuyerList, int endpage, String errorMessage);
}
