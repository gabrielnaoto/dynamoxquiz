package com.example.dynamoxquiz.services.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question {

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("statement")
    private String statement;

    @Expose
    @SerializedName("options")
    private List<String> options;

    public Question() {
    }

    public Question(String id, String statement, List<String> options) {
        this.id = id;
        this.statement = statement;
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
