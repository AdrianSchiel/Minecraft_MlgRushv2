package net.royalbyte.mlgrush.v2.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueneHandler {

    private List<String> list;
    private Map<String, String> challenges;

    public QueneHandler() {
        this.list = new ArrayList<>();
        this.challenges = new HashMap<>();
    }

    public Map<String, String> getChallenges() {
        return challenges;
    }

    public List<String> getList() {
        return list;
    }
}
