package com.example.amir.shetu.interfaces.ApiDataManager;

import com.example.amir.shetu.model.AgentComission;

import java.util.List;

public interface AgentComissionListListener {
    public void getAgentComissionList(List<AgentComission> agentComissionList, int endpage, String errorMessage);
}
