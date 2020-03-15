package com.example.nasapicturesapp.data.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.io.Serializable

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("copyright", "date", "explanation", "hdurl", "media_type", "service_version", "title", "url")
class DataModel: Serializable {
    @JsonProperty("copyright")
    private var copyright: String? = null

    @JsonProperty("date")
    private var date: String? = null

    @JsonProperty("explanation")
    private var explanation: String? = null

    @JsonProperty("hdurl")
    private var hdurl: String? = null

    @JsonProperty("media_type")
    private var mediaType: String? = null

    @JsonProperty("service_version")
    private var serviceVersion: String? = null

    @JsonProperty("title")
    private var title: String? = null

    @JsonProperty("url")
    private var url: String? = null

    @JsonProperty("copyright")
    fun getCopyright(): String? {
        return copyright
    }

    @JsonProperty("copyright")
    fun setCopyright(copyright: String?) {
        this.copyright = copyright
    }

    @JsonProperty("date")
    fun getDate(): String? {
        return date
    }

    @JsonProperty("date")
    fun setDate(date: String?) {
        this.date = date
    }

    @JsonProperty("explanation")
    fun getExplanation(): String? {
        return explanation
    }

    @JsonProperty("explanation")
    fun setExplanation(explanation: String?) {
        this.explanation = explanation
    }

    @JsonProperty("hdurl")
    fun getHdurl(): String? {
        return hdurl
    }

    @JsonProperty("hdurl")
    fun setHdurl(hdurl: String?) {
        this.hdurl = hdurl
    }

    @JsonProperty("media_type")
    fun getMediaType(): String? {
        return mediaType
    }

    @JsonProperty("media_type")
    fun setMediaType(mediaType: String?) {
        this.mediaType = mediaType
    }

    @JsonProperty("service_version")
    fun getServiceVersion(): String? {
        return serviceVersion
    }

    @JsonProperty("service_version")
    fun setServiceVersion(serviceVersion: String?) {
        this.serviceVersion = serviceVersion
    }

    @JsonProperty("title")
    fun getTitle(): String? {
        return title
    }

    @JsonProperty("title")
    fun setTitle(title: String?) {
        this.title = title
    }

    @JsonProperty("url")
    fun getUrl(): String? {
        return url
    }

    @JsonProperty("url")
    fun setUrl(url: String?) {
        this.url = url
    }
}