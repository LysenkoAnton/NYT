package com.example.myapplicationtablet;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
public class ResApi implements Serializable {


        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("copyright")
        @Expose
        private String copyright;
        @SerializedName("num_results")
        @Expose
        private Integer numResults;
        @SerializedName("results")
        @Expose
        private Article[] results = null;
        private final static long serialVersionUID = 5519556310714444796L;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public Integer getNumResults() {
            return numResults;
        }

        public void setNumResults(Integer numResults) {
            this.numResults = numResults;
        }

        public Article[] getResults() {
            return results;
        }

        public void setResults(Article[] results) {
            this.results = results;
        }

    }

