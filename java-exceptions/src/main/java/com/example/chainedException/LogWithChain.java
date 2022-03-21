package com.example.chainedException;

/*
    created by Atiye Mousavi
    Date: 3/18/2022
    Time: 10:13 AM
*/


import com.example.chainedException.exception.GirlFriendOfManagerUpsetException;
import com.example.chainedException.exception.ManagerUpsetException;
import com.example.chainedException.exception.NoLeaveGrantedException;
import com.example.chainedException.exception.TeamLeadUpsetException;

public class LogWithChain {

    public static void main(String[] args) throws NoLeaveGrantedException {
        getLeave();
    }

    private static void getLeave() throws NoLeaveGrantedException {
        try {
            howIsTeamLead();
        } catch (TeamLeadUpsetException e) {
            throw new NoLeaveGrantedException("Leave not sanctioned.",e);
        }
    }

    private static void howIsTeamLead() throws TeamLeadUpsetException {
        try {
            howIsManger();
        } catch (ManagerUpsetException e) {
            throw new TeamLeadUpsetException("Team lead is not in ", e);
        }
    }

    private static void howIsManger() throws ManagerUpsetException {
        try {

            howIsGirlFriendOfManager();
        } catch (GirlFriendOfManagerUpsetException e) {
            throw new ManagerUpsetException("Manager is in bad mod", e);
        }
    }

    private static void howIsGirlFriendOfManager() throws GirlFriendOfManagerUpsetException {
        throw new GirlFriendOfManagerUpsetException("Girl friend of manager is in bad mod");
    }
}
