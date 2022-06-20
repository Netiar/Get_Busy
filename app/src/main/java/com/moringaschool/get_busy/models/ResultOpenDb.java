
package com.moringaschool.get_busy.models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultOpenDb implements Serializable {

    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("results")
    @Expose
    private List<Result__1> results = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResultOpenDb() {

    }

    /**
     * 
     * @param results
     * @param responseCode
     */
    public ResultOpenDb(Integer responseCode, List<Result__1> results) {
        super();
        this.responseCode = responseCode;
        this.results = results;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public List<Result__1> getResults() {
        return results;
    }

    public void setResults(List<Result__1> results) {
        this.results = results;
    }

}
