package com.example.chainedException;

/*
    created by Atiye Mousavi
    Date: 3/18/2022
    Time: 10:58 AM
*/


import com.example.chainedException.exception.GirlFriendOfManagerUpsetException;
import com.example.chainedException.exception.ManagerUpsetException;
import com.example.chainedException.exception.NoLeaveGrantedException;
import com.example.chainedException.exception.TeamLeadUpsetException;

public class LogWithoutChain {
    public static void main(String[] args) throws NoLeaveGrantedException {
        getLeave();
    }
    private static void getLeave() throws NoLeaveGrantedException {
        try {
            howIsTeamLead();
        } catch (TeamLeadUpsetException e) {
            throw new NoLeaveGrantedException("Leave not sanctioned.");
        }
    }
    private static void howIsTeamLead() throws TeamLeadUpsetException {
        try {
            howIsManager();
        } catch (ManagerUpsetException e) {
            throw new TeamLeadUpsetException("Team lead is not in good mood");
        }
    }
    private static void howIsManager() throws ManagerUpsetException {
        try {
            howIsGirlFriendOfManager();
        } catch (GirlFriendOfManagerUpsetException e) {
            throw new ManagerUpsetException("Manager is in bad mood");
        }
    }
    private static void howIsGirlFriendOfManager() throws GirlFriendOfManagerUpsetException {
        throw new GirlFriendOfManagerUpsetException("Girl friend of manager is in bad mood");
    }
}
