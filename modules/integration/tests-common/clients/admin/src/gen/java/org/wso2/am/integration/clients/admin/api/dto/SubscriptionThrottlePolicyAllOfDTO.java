/*
 * WSO2 API Manager - Admin
 * This document specifies a **RESTful API** for WSO2 **API Manager** - **Admin Portal**. Please see [full OpenAPI Specification](https://raw.githubusercontent.com/wso2/carbon-apimgt/v6.7.206/components/apimgt/org.wso2.carbon.apimgt.rest.api.admin.v1/src/main/resources/admin-api.yaml) of the API which is written using [OAS 3.0](http://swagger.io/) specification.  # Authentication Our REST APIs are protected using OAuth2 and access control is achieved through scopes. Before you start invoking the the API you need to obtain an access token with the required scopes. This guide will walk you through the steps that you will need to follow to obtain an access token. First you need to obtain the consumer key/secret key pair by calling the dynamic client registration (DCR) endpoint. You can add your preferred grant types in the payload. A sample payload is shown below. ```   {   \"callbackUrl\":\"www.google.lk\",   \"clientName\":\"rest_api_admin\",   \"owner\":\"admin\",   \"grantType\":\"client_credentials password refresh_token\",   \"saasApp\":true   } ``` Create a file (payload.json) with the above sample payload, and use the cURL shown bellow to invoke the DCR endpoint. Authorization header of this should contain the base64 encoded admin username and password. **Format of the request** ```   curl -X POST -H \"Authorization: Basic Base64(admin_username:admin_password)\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://<host>:<servlet_port>/client-registration/v0.17/register ``` **Sample request** ```   curl -X POST -H \"Authorization: Basic YWRtaW46YWRtaW4=\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://localhost:9443/client-registration/v0.17/register ``` Following is a sample response after invoking the above curl. ``` { \"clientId\": \"fOCi4vNJ59PpHucC2CAYfYuADdMa\", \"clientName\": \"rest_api_admin\", \"callBackURL\": \"www.google.lk\", \"clientSecret\": \"a4FwHlq0iCIKVs2MPIIDnepZnYMa\", \"isSaasApplication\": true, \"appOwner\": \"admin\", \"jsonString\": \"{\\\"grant_types\\\":\\\"client_credentials password refresh_token\\\",\\\"redirect_uris\\\":\\\"www.google.lk\\\",\\\"client_name\\\":\\\"rest_api_admin\\\"}\", \"jsonAppAttribute\": \"{}\", \"tokenType\": null } ``` Next you must use the above client id and secret to obtain the access token. We will be using the password grant type for this, you can use any grant type you desire. You also need to add the proper **scope** when getting the access token. All possible scopes for Admin REST API can be viewed in **OAuth2 Security** section of this document and scope for each resource is given in **authorizations** section of resource documentation. Following is the format of the request if you are using the password grant type. ``` curl -k -d \"grant_type=password&username=<admin_username>&password=<admin_passowrd>&scope=<scopes seperated by space>\" \\ -H \"Authorization: Basic base64(cliet_id:client_secret)\" \\ https://<host>:<gateway_port>/token ``` **Sample request** ``` curl https://localhost:8243/token -k \\ -H \"Authorization: Basic Zk9DaTR2Tko1OVBwSHVjQzJDQVlmWXVBRGRNYTphNEZ3SGxxMGlDSUtWczJNUElJRG5lcFpuWU1h\" \\ -d \"grant_type=password&username=admin&password=admin&scope=apim:admin apim:tier_view\" ``` Shown below is a sample response to the above request. ``` { \"access_token\": \"e79bda48-3406-3178-acce-f6e4dbdcbb12\", \"refresh_token\": \"a757795d-e69f-38b8-bd85-9aded677a97c\", \"scope\": \"apim:admin apim:tier_view\", \"token_type\": \"Bearer\", \"expires_in\": 3600 } ``` Now you have a valid access token, which you can use to invoke an API. Navigate through the API descriptions to find the required API, obtain an access token as described above and invoke the API with the authentication header. If you use a different authentication mechanism, this process may change.  # Try out in Postman If you want to try-out the embedded postman collection with \"Run in Postman\" option, please follow the guidelines listed below. * All of the OAuth2 secured endpoints have been configured with an Authorization Bearer header with a parameterized access token. Before invoking any REST API resource make sure you run the `Register DCR Application` and `Generate Access Token` requests to fetch an access token with all required scopes. * Make sure you have an API Manager instance up and running. * Update the `basepath` parameter to match the hostname and port of the APIM instance.  [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/f5ac2ca9fb22afef6ed6) 
 *
 * The version of the OpenAPI document: v5
 * Contact: architecture@wso2.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.wso2.am.integration.clients.admin.api.dto;

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
import java.util.ArrayList;
import java.util.List;
import org.wso2.am.integration.clients.admin.api.dto.CustomAttributeDTO;
import org.wso2.am.integration.clients.admin.api.dto.MonetizationInfoDTO;
import org.wso2.am.integration.clients.admin.api.dto.SubscriptionThrottlePolicyPermissionDTO;
import org.wso2.am.integration.clients.admin.api.dto.ThrottleLimitDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
/**
* SubscriptionThrottlePolicyAllOfDTO
*/

public class SubscriptionThrottlePolicyAllOfDTO {
        public static final String SERIALIZED_NAME_DEFAULT_LIMIT = "defaultLimit";
        @SerializedName(SERIALIZED_NAME_DEFAULT_LIMIT)
            private ThrottleLimitDTO defaultLimit;

        public static final String SERIALIZED_NAME_MONETIZATION = "monetization";
        @SerializedName(SERIALIZED_NAME_MONETIZATION)
            private MonetizationInfoDTO monetization;

        public static final String SERIALIZED_NAME_RATE_LIMIT_COUNT = "rateLimitCount";
        @SerializedName(SERIALIZED_NAME_RATE_LIMIT_COUNT)
            private Integer rateLimitCount;

        public static final String SERIALIZED_NAME_RATE_LIMIT_TIME_UNIT = "rateLimitTimeUnit";
        @SerializedName(SERIALIZED_NAME_RATE_LIMIT_TIME_UNIT)
            private String rateLimitTimeUnit;

        public static final String SERIALIZED_NAME_CUSTOM_ATTRIBUTES = "customAttributes";
        @SerializedName(SERIALIZED_NAME_CUSTOM_ATTRIBUTES)
            private List<CustomAttributeDTO> customAttributes = null;

        public static final String SERIALIZED_NAME_STOP_ON_QUOTA_REACH = "stopOnQuotaReach";
        @SerializedName(SERIALIZED_NAME_STOP_ON_QUOTA_REACH)
            private Boolean stopOnQuotaReach = false;

        public static final String SERIALIZED_NAME_BILLING_PLAN = "billingPlan";
        @SerializedName(SERIALIZED_NAME_BILLING_PLAN)
            private String billingPlan;

        public static final String SERIALIZED_NAME_PERMISSIONS = "permissions";
        @SerializedName(SERIALIZED_NAME_PERMISSIONS)
            private SubscriptionThrottlePolicyPermissionDTO permissions;

        public static final String SERIALIZED_NAME_SUBSCRIBER_COUNT = "subscriberCount";
        @SerializedName(SERIALIZED_NAME_SUBSCRIBER_COUNT)
            private Integer subscriberCount;


        public SubscriptionThrottlePolicyAllOfDTO defaultLimit(ThrottleLimitDTO defaultLimit) {
        
        this.defaultLimit = defaultLimit;
        return this;
        }

    /**
        * Get defaultLimit
    * @return defaultLimit
    **/
      @ApiModelProperty(required = true, value = "")
    
    public ThrottleLimitDTO getDefaultLimit() {
        return defaultLimit;
    }


    public void setDefaultLimit(ThrottleLimitDTO defaultLimit) {
        this.defaultLimit = defaultLimit;
    }


        public SubscriptionThrottlePolicyAllOfDTO monetization(MonetizationInfoDTO monetization) {
        
        this.monetization = monetization;
        return this;
        }

    /**
        * Get monetization
    * @return monetization
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(value = "")
    
    public MonetizationInfoDTO getMonetization() {
        return monetization;
    }


    public void setMonetization(MonetizationInfoDTO monetization) {
        this.monetization = monetization;
    }


        public SubscriptionThrottlePolicyAllOfDTO rateLimitCount(Integer rateLimitCount) {
        
        this.rateLimitCount = rateLimitCount;
        return this;
        }

    /**
        * Burst control request count
    * @return rateLimitCount
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "10", value = "Burst control request count")
    
    public Integer getRateLimitCount() {
        return rateLimitCount;
    }


    public void setRateLimitCount(Integer rateLimitCount) {
        this.rateLimitCount = rateLimitCount;
    }


        public SubscriptionThrottlePolicyAllOfDTO rateLimitTimeUnit(String rateLimitTimeUnit) {
        
        this.rateLimitTimeUnit = rateLimitTimeUnit;
        return this;
        }

    /**
        * Burst control time unit
    * @return rateLimitTimeUnit
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "min", value = "Burst control time unit")
    
    public String getRateLimitTimeUnit() {
        return rateLimitTimeUnit;
    }


    public void setRateLimitTimeUnit(String rateLimitTimeUnit) {
        this.rateLimitTimeUnit = rateLimitTimeUnit;
    }


        public SubscriptionThrottlePolicyAllOfDTO customAttributes(List<CustomAttributeDTO> customAttributes) {
        
        this.customAttributes = customAttributes;
        return this;
        }

    /**
        * Custom attributes added to the Subscription Throttling Policy 
    * @return customAttributes
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "[]", value = "Custom attributes added to the Subscription Throttling Policy ")
    
    public List<CustomAttributeDTO> getCustomAttributes() {
        return customAttributes;
    }


    public void setCustomAttributes(List<CustomAttributeDTO> customAttributes) {
        this.customAttributes = customAttributes;
    }


        public SubscriptionThrottlePolicyAllOfDTO stopOnQuotaReach(Boolean stopOnQuotaReach) {
        
        this.stopOnQuotaReach = stopOnQuotaReach;
        return this;
        }

    /**
        * This indicates the action to be taken when a user goes beyond the allocated quota. If checked, the user&#39;s requests will be dropped. If unchecked, the requests will be allowed to pass through. 
    * @return stopOnQuotaReach
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(value = "This indicates the action to be taken when a user goes beyond the allocated quota. If checked, the user's requests will be dropped. If unchecked, the requests will be allowed to pass through. ")
    
    public Boolean isStopOnQuotaReach() {
        return stopOnQuotaReach;
    }


    public void setStopOnQuotaReach(Boolean stopOnQuotaReach) {
        this.stopOnQuotaReach = stopOnQuotaReach;
    }


        public SubscriptionThrottlePolicyAllOfDTO billingPlan(String billingPlan) {
        
        this.billingPlan = billingPlan;
        return this;
        }

    /**
        * define whether this is Paid or a Free plan. Allowed values are FREE or COMMERCIAL. 
    * @return billingPlan
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "FREE", value = "define whether this is Paid or a Free plan. Allowed values are FREE or COMMERCIAL. ")
    
    public String getBillingPlan() {
        return billingPlan;
    }


    public void setBillingPlan(String billingPlan) {
        this.billingPlan = billingPlan;
    }


        public SubscriptionThrottlePolicyAllOfDTO permissions(SubscriptionThrottlePolicyPermissionDTO permissions) {
        
        this.permissions = permissions;
        return this;
        }

    /**
        * Get permissions
    * @return permissions
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(value = "")
    
    public SubscriptionThrottlePolicyPermissionDTO getPermissions() {
        return permissions;
    }


    public void setPermissions(SubscriptionThrottlePolicyPermissionDTO permissions) {
        this.permissions = permissions;
    }


        public SubscriptionThrottlePolicyAllOfDTO subscriberCount(Integer subscriberCount) {
        
        this.subscriberCount = subscriberCount;
        return this;
        }

    /**
        * Number of subscriptions allowed 
    * @return subscriberCount
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "10", value = "Number of subscriptions allowed ")
    
    public Integer getSubscriberCount() {
        return subscriberCount;
    }


    public void setSubscriberCount(Integer subscriberCount) {
        this.subscriberCount = subscriberCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
        return true;
        }
        if (o == null || getClass() != o.getClass()) {
        return false;
        }
            SubscriptionThrottlePolicyAllOfDTO subscriptionThrottlePolicyAllOf = (SubscriptionThrottlePolicyAllOfDTO) o;
            return Objects.equals(this.defaultLimit, subscriptionThrottlePolicyAllOf.defaultLimit) &&
            Objects.equals(this.monetization, subscriptionThrottlePolicyAllOf.monetization) &&
            Objects.equals(this.rateLimitCount, subscriptionThrottlePolicyAllOf.rateLimitCount) &&
            Objects.equals(this.rateLimitTimeUnit, subscriptionThrottlePolicyAllOf.rateLimitTimeUnit) &&
            Objects.equals(this.customAttributes, subscriptionThrottlePolicyAllOf.customAttributes) &&
            Objects.equals(this.stopOnQuotaReach, subscriptionThrottlePolicyAllOf.stopOnQuotaReach) &&
            Objects.equals(this.billingPlan, subscriptionThrottlePolicyAllOf.billingPlan) &&
            Objects.equals(this.permissions, subscriptionThrottlePolicyAllOf.permissions) &&
            Objects.equals(this.subscriberCount, subscriptionThrottlePolicyAllOf.subscriberCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(defaultLimit, monetization, rateLimitCount, rateLimitTimeUnit, customAttributes, stopOnQuotaReach, billingPlan, permissions, subscriberCount);
    }


@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append("class SubscriptionThrottlePolicyAllOfDTO {\n");
    sb.append("    defaultLimit: ").append(toIndentedString(defaultLimit)).append("\n");
    sb.append("    monetization: ").append(toIndentedString(monetization)).append("\n");
    sb.append("    rateLimitCount: ").append(toIndentedString(rateLimitCount)).append("\n");
    sb.append("    rateLimitTimeUnit: ").append(toIndentedString(rateLimitTimeUnit)).append("\n");
    sb.append("    customAttributes: ").append(toIndentedString(customAttributes)).append("\n");
    sb.append("    stopOnQuotaReach: ").append(toIndentedString(stopOnQuotaReach)).append("\n");
    sb.append("    billingPlan: ").append(toIndentedString(billingPlan)).append("\n");
    sb.append("    permissions: ").append(toIndentedString(permissions)).append("\n");
    sb.append("    subscriberCount: ").append(toIndentedString(subscriberCount)).append("\n");
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

