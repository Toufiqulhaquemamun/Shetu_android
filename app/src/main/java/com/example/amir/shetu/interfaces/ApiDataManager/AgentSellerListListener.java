package com.example.amir.shetu.interfaces.ApiDataManager;

import com.example.amir.shetu.model.AgentSellerList;

import java.util.List;

public interface AgentSellerListListener {

    public void getAgentSellerList(List<AgentSellerList.Datum> agentsellList,List<AgentSellerList> agentsellerList,int endpage, String errorMessage);
}
