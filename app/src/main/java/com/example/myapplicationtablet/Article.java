package com.example.myapplicationtablet;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.List;

public class Article  implements Serializable {

        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("adx_keywords")
        @Expose
        private String adxKeywords;
        @SerializedName("subsection")
        @Expose
        private String subsection;
        @SerializedName("email_count")
        @Expose
        private Long emailCount;
        @SerializedName("count_type")
        @Expose
        private String countType;
        @SerializedName("column")
        @Expose
        private String column;
        @SerializedName("eta_id")
        @Expose
        private Long etaId;
        @SerializedName("section")
        @Expose
        private String section;
        @SerializedName("id")
        @Expose
        private Long id;
        @SerializedName("asset_id")
        @Expose
        private Long assetId;
        @SerializedName("nytdsection")
        @Expose
        private String nytdsection;
        @SerializedName("byline")
        @Expose
        private String byline;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("abstract")
        @Expose
        private String abstractStr;
        @SerializedName("published_date")
        @Expose
        private String publishedDate;
        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("updated")
        @Expose
        private String updated;
        // @SerializedName("des_facet")
        // @Expose
        // private List<String> desFacet = null;
        // @SerializedName("org_facet")
        //@Expose
        //private List<String> orgFacet = null;
        //@SerializedName("per_facet")
        //@Expose
        //private String perFacet = null;
        //@SerializedName("geo_facet")
        //@Expose
        //private String geoFacet;
        @SerializedName("media")
        @Expose
        private List<MediaObject> media = null;
        @SerializedName("uri")
        @Expose
        private String uri;
        private final static long serialVersionUID = -7675139725040282518L;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAdxKeywords() {
            return adxKeywords;
        }

        public void setAdxKeywords(String adxKeywords) {
            this.adxKeywords = adxKeywords;
        }

        public String getSubsection() {
            return subsection;
        }

        public void setSubsection(String subsection) {
            this.subsection = subsection;
        }

        public Long getEmailCount() {
            return emailCount;
        }

        public void setEmailCount(Long emailCount) {
            this.emailCount = emailCount;
        }

        public String getCountType() {
            return countType;
        }

        public void setCountType(String countType) {
            this.countType = countType;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public Long getEtaId() {
            return etaId;
        }

        public void setEtaId(Long etaId) {
            this.etaId = etaId;
        }

        public String getSection() {
            return section;
        }

        public void setSection(String section) {
            this.section = section;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getAssetId() {
            return assetId;
        }

        public void setAssetId(Long assetId) {
            this.assetId = assetId;
        }

        public String getNytdsection() {
            return nytdsection;
        }

        public void setNytdsection(String nytdsection) {
            this.nytdsection = nytdsection;
        }

        public String getByline() {
            return byline;
        }

        public void setByline(String byline) {
            this.byline = byline;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAbstractStr() {
            return abstractStr;
        }

        public void setAbstractStr(String abstractStr) {
            this.abstractStr = abstractStr;
        }

        public String getPublishedDate() {
            return publishedDate;
        }

        public void setPublishedDate(String publishedDate) {
            this.publishedDate = publishedDate;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public List<MediaObject> getMedia() {
            return media;
        }

        public void setMedia(List<MediaObject> media) {
            this.media = media;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }



    }


