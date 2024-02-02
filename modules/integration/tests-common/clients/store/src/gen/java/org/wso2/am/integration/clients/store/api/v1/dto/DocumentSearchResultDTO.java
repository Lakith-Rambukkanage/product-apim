/*
 * WSO2 API Manager - Developer Portal
 * This document specifies a **RESTful API** for WSO2 **API Manager** - **Developer Portal**. Please see [full OpenAPI Specification](https://raw.githubusercontent.com/wso2/carbon-apimgt/v6.7.206/components/apimgt/org.wso2.carbon.apimgt.rest.api.store.v1/src/main/resources/devportal-api.yaml) of the API which is written using [OAS 3.0](http://swagger.io/) specification.  # Authentication The Developer Portal REST API is protected using OAuth2 and access control is achieved through scopes. Before you start invoking the API, you need to obtain an access token with the required scopes. This guide will walk you through the steps that you will need to follow to obtain an access token. First you need to obtain the consumer key/secret key pair by calling the dynamic client registration (DCR) endpoint. You can add your preferred grant types in the payload. A Sample payload is shown below. ```   {   \"callbackUrl\":\"www.google.lk\",   \"clientName\":\"rest_api_devportal\",   \"owner\":\"admin\",   \"grantType\":\"client_credentials password refresh_token\",   \"saasApp\":true   } ``` Create a file (payload.json) with the above sample payload, and use the cURL shown below to invoke the DCR endpoint. Authorization header of this should contain the base64 encoded admin username and password. **Format of the request** ```   curl -X POST -H \"Authorization: Basic Base64(admin_username:admin_password)\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://<host>:<servlet_port>/client-registration/v0.17/register ``` **Sample request** ```   curl -X POST -H \"Authorization: Basic YWRtaW46YWRtaW4=\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://localhost:9443/client-registration/v0.17/register ``` Following is a sample response after invoking the above curl. ``` { \"clientId\": \"fOCi4vNJ59PpHucC2CAYfYuADdMa\", \"clientName\": \"rest_api_devportal\", \"callBackURL\": \"www.google.lk\", \"clientSecret\": \"a4FwHlq0iCIKVs2MPIIDnepZnYMa\", \"isSaasApplication\": true, \"appOwner\": \"admin\", \"jsonString\": \"{\\\"grant_types\\\":\\\"client_credentials password refresh_token\\\",\\\"redirect_uris\\\":\\\"www.google.lk\\\",\\\"client_name\\\":\\\"rest_api_devportal\\\"}\", \"jsonAppAttribute\": \"{}\", \"tokenType\": null } ``` Next you must use the above client id and secret to obtain the access token. We will be using the password grant type for this, you can use any grant type you desire. You also need to add the proper **scope** when getting the access token. All possible scopes for devportal REST API can be viewed in **OAuth2 Security** section of this document and scope for each resource is given in **authorization** section of resource documentation. Following is the format of the request if you are using the password grant type. ``` curl -k -d \"grant_type=password&username=<admin_username>&password=<admin_password>&scope=<scopes separated by space>\" \\ -H \"Authorization: Basic base64(cliet_id:client_secret)\" \\ https://<host>:<servlet_port>/oauth2/token ``` **Sample request** ``` curl https://localhost:9443/oauth2/token -k \\ -H \"Authorization: Basic Zk9DaTR2Tko1OVBwSHVjQzJDQVlmWXVBRGRNYTphNEZ3SGxxMGlDSUtWczJNUElJRG5lcFpuWU1h\" \\ -d \"grant_type=password&username=admin&password=admin&scope=apim:subscribe apim:api_key\" ``` Shown below is a sample response to the above request. ``` { \"access_token\": \"e79bda48-3406-3178-acce-f6e4dbdcbb12\", \"refresh_token\": \"a757795d-e69f-38b8-bd85-9aded677a97c\", \"scope\": \"apim:subscribe apim:api_key\", \"token_type\": \"Bearer\", \"expires_in\": 3600 } ``` Now you have a valid access token, which you can use to invoke an API. Navigate through the API descriptions to find the required API, obtain an access token as described above and invoke the API with the authentication header. If you use a different authentication mechanism, this process may change.  # Try out in Postman If you want to try-out the embedded postman collection with \"Run in Postman\" option, please follow the guidelines listed below. * All of the OAuth2 secured endpoints have been configured with an Authorization Bearer header with a parameterized access token. Before invoking any REST API resource make sure you run the `Register DCR Application` and `Generate Access Token` requests to fetch an access token with all required scopes. * Make sure you have an API Manager instance up and running. * Update the `basepath` parameter to match the hostname and port of the APIM instance.  [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/5bc0161b8aa7e701d7bf) 
 *
 * The version of the OpenAPI document: v4
 * Contact: architecture@wso2.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.wso2.am.integration.clients.store.api.v1.dto;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import org.wso2.am.integration.clients.store.api.v1.dto.DocumentSearchResultAllOfDTO;
import org.wso2.am.integration.clients.store.api.v1.dto.SearchResultDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
/**
* DocumentSearchResultDTO
*/

public class DocumentSearchResultDTO extends SearchResultDTO {
            /**
* Gets or Sets docType
*/
    @JsonAdapter(DocTypeEnum.Adapter.class)
public enum DocTypeEnum {
        HOWTO("HOWTO"),
        
        SAMPLES("SAMPLES"),
        
        PUBLIC_FORUM("PUBLIC_FORUM"),
        
        SUPPORT_FORUM("SUPPORT_FORUM"),
        
        API_MESSAGE_FORMAT("API_MESSAGE_FORMAT"),
        
        SWAGGER_DOC("SWAGGER_DOC"),
        
        OTHER("OTHER");

private String value;

DocTypeEnum(String value) {
this.value = value;
}

public String getValue() {
return value;
}

@Override
public String toString() {
return String.valueOf(value);
}

public static DocTypeEnum fromValue(String value) {
    for (DocTypeEnum b : DocTypeEnum.values()) {
    if (b.name().equals(value)) {
        return b;
    }
}
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
}

    public static class Adapter extends TypeAdapter<DocTypeEnum> {
    @Override
    public void write(final JsonWriter jsonWriter, final DocTypeEnum enumeration) throws IOException {
    jsonWriter.value(enumeration.getValue());
    }

    @Override
    public DocTypeEnum read(final JsonReader jsonReader) throws IOException {
    String value =  jsonReader.nextString();
    return DocTypeEnum.fromValue(value);
    }
    }
}

        public static final String SERIALIZED_NAME_DOC_TYPE = "docType";
        @SerializedName(SERIALIZED_NAME_DOC_TYPE)
            private DocTypeEnum docType;

        public static final String SERIALIZED_NAME_SUMMARY = "summary";
        @SerializedName(SERIALIZED_NAME_SUMMARY)
            private String summary;

            /**
* Gets or Sets sourceType
*/
    @JsonAdapter(SourceTypeEnum.Adapter.class)
public enum SourceTypeEnum {
        INLINE("INLINE"),
        
        URL("URL"),
        
        FILE("FILE"),
        
        MARKDOWN("MARKDOWN");

private String value;

SourceTypeEnum(String value) {
this.value = value;
}

public String getValue() {
return value;
}

@Override
public String toString() {
return String.valueOf(value);
}

public static SourceTypeEnum fromValue(String value) {
    for (SourceTypeEnum b : SourceTypeEnum.values()) {
    if (b.name().equals(value)) {
        return b;
    }
}
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
}

    public static class Adapter extends TypeAdapter<SourceTypeEnum> {
    @Override
    public void write(final JsonWriter jsonWriter, final SourceTypeEnum enumeration) throws IOException {
    jsonWriter.value(enumeration.getValue());
    }

    @Override
    public SourceTypeEnum read(final JsonReader jsonReader) throws IOException {
    String value =  jsonReader.nextString();
    return SourceTypeEnum.fromValue(value);
    }
    }
}

        public static final String SERIALIZED_NAME_SOURCE_TYPE = "sourceType";
        @SerializedName(SERIALIZED_NAME_SOURCE_TYPE)
            private SourceTypeEnum sourceType;

        public static final String SERIALIZED_NAME_SOURCE_URL = "sourceUrl";
        @SerializedName(SERIALIZED_NAME_SOURCE_URL)
            private String sourceUrl;

        public static final String SERIALIZED_NAME_OTHER_TYPE_NAME = "otherTypeName";
        @SerializedName(SERIALIZED_NAME_OTHER_TYPE_NAME)
            private String otherTypeName;

            /**
* Gets or Sets visibility
*/
    @JsonAdapter(VisibilityEnum.Adapter.class)
public enum VisibilityEnum {
        OWNER_ONLY("OWNER_ONLY"),
        
        PRIVATE("PRIVATE"),
        
        API_LEVEL("API_LEVEL");

private String value;

VisibilityEnum(String value) {
this.value = value;
}

public String getValue() {
return value;
}

@Override
public String toString() {
return String.valueOf(value);
}

public static VisibilityEnum fromValue(String value) {
    for (VisibilityEnum b : VisibilityEnum.values()) {
    if (b.name().equals(value)) {
        return b;
    }
}
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
}

    public static class Adapter extends TypeAdapter<VisibilityEnum> {
    @Override
    public void write(final JsonWriter jsonWriter, final VisibilityEnum enumeration) throws IOException {
    jsonWriter.value(enumeration.getValue());
    }

    @Override
    public VisibilityEnum read(final JsonReader jsonReader) throws IOException {
    String value =  jsonReader.nextString();
    return VisibilityEnum.fromValue(value);
    }
    }
}

        public static final String SERIALIZED_NAME_VISIBILITY = "visibility";
        @SerializedName(SERIALIZED_NAME_VISIBILITY)
            private VisibilityEnum visibility;

        public static final String SERIALIZED_NAME_API_NAME = "apiName";
        @SerializedName(SERIALIZED_NAME_API_NAME)
            private String apiName;

        public static final String SERIALIZED_NAME_API_VERSION = "apiVersion";
        @SerializedName(SERIALIZED_NAME_API_VERSION)
            private String apiVersion;

        public static final String SERIALIZED_NAME_API_PROVIDER = "apiProvider";
        @SerializedName(SERIALIZED_NAME_API_PROVIDER)
            private String apiProvider;

        public static final String SERIALIZED_NAME_API_U_U_I_D = "apiUUID";
        @SerializedName(SERIALIZED_NAME_API_U_U_I_D)
            private String apiUUID;


        public DocumentSearchResultDTO docType(DocTypeEnum docType) {
        
        this.docType = docType;
        return this;
        }

    /**
        * Get docType
    * @return docType
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "HOWTO", value = "")
    
    public DocTypeEnum getDocType() {
        return docType;
    }


    public void setDocType(DocTypeEnum docType) {
        this.docType = docType;
    }


        public DocumentSearchResultDTO summary(String summary) {
        
        this.summary = summary;
        return this;
        }

    /**
        * Get summary
    * @return summary
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "Summary of Calculator Documentation", value = "")
    
    public String getSummary() {
        return summary;
    }


    public void setSummary(String summary) {
        this.summary = summary;
    }


        public DocumentSearchResultDTO sourceType(SourceTypeEnum sourceType) {
        
        this.sourceType = sourceType;
        return this;
        }

    /**
        * Get sourceType
    * @return sourceType
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "INLINE", value = "")
    
    public SourceTypeEnum getSourceType() {
        return sourceType;
    }


    public void setSourceType(SourceTypeEnum sourceType) {
        this.sourceType = sourceType;
    }


        public DocumentSearchResultDTO sourceUrl(String sourceUrl) {
        
        this.sourceUrl = sourceUrl;
        return this;
        }

    /**
        * Get sourceUrl
    * @return sourceUrl
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(value = "")
    
    public String getSourceUrl() {
        return sourceUrl;
    }


    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }


        public DocumentSearchResultDTO otherTypeName(String otherTypeName) {
        
        this.otherTypeName = otherTypeName;
        return this;
        }

    /**
        * Get otherTypeName
    * @return otherTypeName
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(value = "")
    
    public String getOtherTypeName() {
        return otherTypeName;
    }


    public void setOtherTypeName(String otherTypeName) {
        this.otherTypeName = otherTypeName;
    }


        public DocumentSearchResultDTO visibility(VisibilityEnum visibility) {
        
        this.visibility = visibility;
        return this;
        }

    /**
        * Get visibility
    * @return visibility
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "API_LEVEL", value = "")
    
    public VisibilityEnum getVisibility() {
        return visibility;
    }


    public void setVisibility(VisibilityEnum visibility) {
        this.visibility = visibility;
    }


        public DocumentSearchResultDTO apiName(String apiName) {
        
        this.apiName = apiName;
        return this;
        }

    /**
        * The name of the associated API
    * @return apiName
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "TestAPI", value = "The name of the associated API")
    
    public String getApiName() {
        return apiName;
    }


    public void setApiName(String apiName) {
        this.apiName = apiName;
    }


        public DocumentSearchResultDTO apiVersion(String apiVersion) {
        
        this.apiVersion = apiVersion;
        return this;
        }

    /**
        * The version of the associated API
    * @return apiVersion
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "1.0.0", value = "The version of the associated API")
    
    public String getApiVersion() {
        return apiVersion;
    }


    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }


        public DocumentSearchResultDTO apiProvider(String apiProvider) {
        
        this.apiProvider = apiProvider;
        return this;
        }

    /**
        * Get apiProvider
    * @return apiProvider
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "admin", value = "")
    
    public String getApiProvider() {
        return apiProvider;
    }


    public void setApiProvider(String apiProvider) {
        this.apiProvider = apiProvider;
    }


        public DocumentSearchResultDTO apiUUID(String apiUUID) {
        
        this.apiUUID = apiUUID;
        return this;
        }

    /**
        * Get apiUUID
    * @return apiUUID
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(value = "")
    
    public String getApiUUID() {
        return apiUUID;
    }


    public void setApiUUID(String apiUUID) {
        this.apiUUID = apiUUID;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
        return true;
        }
        if (o == null || getClass() != o.getClass()) {
        return false;
        }
            DocumentSearchResultDTO documentSearchResult = (DocumentSearchResultDTO) o;
            return Objects.equals(this.docType, documentSearchResult.docType) &&
            Objects.equals(this.summary, documentSearchResult.summary) &&
            Objects.equals(this.sourceType, documentSearchResult.sourceType) &&
            Objects.equals(this.sourceUrl, documentSearchResult.sourceUrl) &&
            Objects.equals(this.otherTypeName, documentSearchResult.otherTypeName) &&
            Objects.equals(this.visibility, documentSearchResult.visibility) &&
            Objects.equals(this.apiName, documentSearchResult.apiName) &&
            Objects.equals(this.apiVersion, documentSearchResult.apiVersion) &&
            Objects.equals(this.apiProvider, documentSearchResult.apiProvider) &&
            Objects.equals(this.apiUUID, documentSearchResult.apiUUID) &&
            super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(docType, summary, sourceType, sourceUrl, otherTypeName, visibility, apiName, apiVersion, apiProvider, apiUUID, super.hashCode());
    }


@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append("class DocumentSearchResultDTO {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    docType: ").append(toIndentedString(docType)).append("\n");
    sb.append("    summary: ").append(toIndentedString(summary)).append("\n");
    sb.append("    sourceType: ").append(toIndentedString(sourceType)).append("\n");
    sb.append("    sourceUrl: ").append(toIndentedString(sourceUrl)).append("\n");
    sb.append("    otherTypeName: ").append(toIndentedString(otherTypeName)).append("\n");
    sb.append("    visibility: ").append(toIndentedString(visibility)).append("\n");
    sb.append("    apiName: ").append(toIndentedString(apiName)).append("\n");
    sb.append("    apiVersion: ").append(toIndentedString(apiVersion)).append("\n");
    sb.append("    apiProvider: ").append(toIndentedString(apiProvider)).append("\n");
    sb.append("    apiUUID: ").append(toIndentedString(apiUUID)).append("\n");
sb.append("}");
return sb.toString();
}

/**
* Convert the given object to string with each line indented by 4 spaces
* (except the first line).
*/
private String toIndentedString(Object o) {
if (o == null) {
return "null";
}
return o.toString().replace("\n", "\n    ");
}

}

