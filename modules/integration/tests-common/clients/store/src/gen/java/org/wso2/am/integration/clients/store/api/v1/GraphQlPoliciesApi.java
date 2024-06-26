/*
 * WSO2 API Manager - Developer Portal
 * This document specifies a **RESTful API** for WSO2 **API Manager** - **Developer Portal**. Please see [full OpenAPI Specification](https://raw.githubusercontent.com/wso2/carbon-apimgt/v6.7.206/components/apimgt/org.wso2.carbon.apimgt.rest.api.store.v1/src/main/resources/devportal-api.yaml) of the API which is written using [OAS 3.0](http://swagger.io/) specification.  # Authentication The Developer Portal REST API is protected using OAuth2 and access control is achieved through scopes. Before you start invoking the API, you need to obtain an access token with the required scopes. This guide will walk you through the steps that you will need to follow to obtain an access token. First you need to obtain the consumer key/secret key pair by calling the dynamic client registration (DCR) endpoint. You can add your preferred grant types in the payload. A Sample payload is shown below. ```   {   \"callbackUrl\":\"www.google.lk\",   \"clientName\":\"rest_api_devportal\",   \"owner\":\"admin\",   \"grantType\":\"client_credentials password refresh_token\",   \"saasApp\":true   } ``` Create a file (payload.json) with the above sample payload, and use the cURL shown below to invoke the DCR endpoint. Authorization header of this should contain the base64 encoded admin username and password. **Format of the request** ```   curl -X POST -H \"Authorization: Basic Base64(admin_username:admin_password)\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://<host>:<servlet_port>/client-registration/v0.17/register ``` **Sample request** ```   curl -X POST -H \"Authorization: Basic YWRtaW46YWRtaW4=\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://localhost:9443/client-registration/v0.17/register ``` Following is a sample response after invoking the above curl. ``` { \"clientId\": \"fOCi4vNJ59PpHucC2CAYfYuADdMa\", \"clientName\": \"rest_api_devportal\", \"callBackURL\": \"www.google.lk\", \"clientSecret\": \"a4FwHlq0iCIKVs2MPIIDnepZnYMa\", \"isSaasApplication\": true, \"appOwner\": \"admin\", \"jsonString\": \"{\\\"grant_types\\\":\\\"client_credentials password refresh_token\\\",\\\"redirect_uris\\\":\\\"www.google.lk\\\",\\\"client_name\\\":\\\"rest_api_devportal\\\"}\", \"jsonAppAttribute\": \"{}\", \"tokenType\": null } ``` Next you must use the above client id and secret to obtain the access token. We will be using the password grant type for this, you can use any grant type you desire. You also need to add the proper **scope** when getting the access token. All possible scopes for devportal REST API can be viewed in **OAuth2 Security** section of this document and scope for each resource is given in **authorization** section of resource documentation. Following is the format of the request if you are using the password grant type. ``` curl -k -d \"grant_type=password&username=<admin_username>&password=<admin_password>&scope=<scopes separated by space>\" \\ -H \"Authorization: Basic base64(cliet_id:client_secret)\" \\ https://<host>:<servlet_port>/oauth2/token ``` **Sample request** ``` curl https://localhost:9443/oauth2/token -k \\ -H \"Authorization: Basic Zk9DaTR2Tko1OVBwSHVjQzJDQVlmWXVBRGRNYTphNEZ3SGxxMGlDSUtWczJNUElJRG5lcFpuWU1h\" \\ -d \"grant_type=password&username=admin&password=admin&scope=apim:subscribe apim:api_key\" ``` Shown below is a sample response to the above request. ``` { \"access_token\": \"e79bda48-3406-3178-acce-f6e4dbdcbb12\", \"refresh_token\": \"a757795d-e69f-38b8-bd85-9aded677a97c\", \"scope\": \"apim:subscribe apim:api_key\", \"token_type\": \"Bearer\", \"expires_in\": 3600 } ``` Now you have a valid access token, which you can use to invoke an API. Navigate through the API descriptions to find the required API, obtain an access token as described above and invoke the API with the authentication header. If you use a different authentication mechanism, this process may change.  # Try out in Postman If you want to try-out the embedded postman collection with \"Run in Postman\" option, please follow the guidelines listed below. * All of the OAuth2 secured endpoints have been configured with an Authorization Bearer header with a parameterized access token. Before invoking any REST API resource make sure you run the `Register DCR Application` and `Generate Access Token` requests to fetch an access token with all required scopes. * Make sure you have an API Manager instance up and running. * Update the `basepath` parameter to match the hostname and port of the APIM instance.  [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/5bc0161b8aa7e701d7bf) 
 *
 * The version of the OpenAPI document: v3
 * Contact: architecture@wso2.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.wso2.am.integration.clients.store.api.v1;

import org.wso2.am.integration.clients.store.api.ApiCallback;
import org.wso2.am.integration.clients.store.api.ApiClient;
import org.wso2.am.integration.clients.store.api.ApiException;
import org.wso2.am.integration.clients.store.api.ApiResponse;
import org.wso2.am.integration.clients.store.api.Configuration;
import org.wso2.am.integration.clients.store.api.Pair;
import org.wso2.am.integration.clients.store.api.ProgressRequestBody;
import org.wso2.am.integration.clients.store.api.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import org.wso2.am.integration.clients.store.api.v1.dto.GraphQLQueryComplexityInfoDTO;
import org.wso2.am.integration.clients.store.api.v1.dto.GraphQLSchemaTypeListDTO;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphQlPoliciesApi {
    private ApiClient localVarApiClient;

    public GraphQlPoliciesApi() {
        this(Configuration.getDefaultApiClient());
    }

    public GraphQlPoliciesApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for apisApiIdGraphqlPoliciesComplexityGet
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Requested complexity details returned.  </td><td>  * Content-Type - The content of the body.  <br>  </td></tr>
        <tr><td> 404 </td><td> Not Found. Requested API does not contain any complexity details.  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call apisApiIdGraphqlPoliciesComplexityGetCall(String apiId, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/apis/{apiId}/graphql-policies/complexity"
            .replaceAll("\\{" + "apiId" + "\\}", localVarApiClient.escapeString(apiId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "OAuth2Security" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call apisApiIdGraphqlPoliciesComplexityGetValidateBeforeCall(String apiId, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'apiId' is set
        if (apiId == null) {
            throw new ApiException("Missing the required parameter 'apiId' when calling apisApiIdGraphqlPoliciesComplexityGet(Async)");
        }
        

        okhttp3.Call localVarCall = apisApiIdGraphqlPoliciesComplexityGetCall(apiId, _callback);
        return localVarCall;

    }

    /**
     * Get the Complexity Related Details of an API
     * This operation can be used to retrieve complexity related details belonging to an API by providing the API id. 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @return GraphQLQueryComplexityInfoDTO
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Requested complexity details returned.  </td><td>  * Content-Type - The content of the body.  <br>  </td></tr>
        <tr><td> 404 </td><td> Not Found. Requested API does not contain any complexity details.  </td><td>  -  </td></tr>
     </table>
     */
    public GraphQLQueryComplexityInfoDTO apisApiIdGraphqlPoliciesComplexityGet(String apiId) throws ApiException {
        ApiResponse<GraphQLQueryComplexityInfoDTO> localVarResp = apisApiIdGraphqlPoliciesComplexityGetWithHttpInfo(apiId);
        return localVarResp.getData();
    }

    /**
     * Get the Complexity Related Details of an API
     * This operation can be used to retrieve complexity related details belonging to an API by providing the API id. 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @return ApiResponse&lt;GraphQLQueryComplexityInfoDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Requested complexity details returned.  </td><td>  * Content-Type - The content of the body.  <br>  </td></tr>
        <tr><td> 404 </td><td> Not Found. Requested API does not contain any complexity details.  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<GraphQLQueryComplexityInfoDTO> apisApiIdGraphqlPoliciesComplexityGetWithHttpInfo(String apiId) throws ApiException {
        okhttp3.Call localVarCall = apisApiIdGraphqlPoliciesComplexityGetValidateBeforeCall(apiId, null);
        Type localVarReturnType = new TypeToken<GraphQLQueryComplexityInfoDTO>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get the Complexity Related Details of an API (asynchronously)
     * This operation can be used to retrieve complexity related details belonging to an API by providing the API id. 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Requested complexity details returned.  </td><td>  * Content-Type - The content of the body.  <br>  </td></tr>
        <tr><td> 404 </td><td> Not Found. Requested API does not contain any complexity details.  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call apisApiIdGraphqlPoliciesComplexityGetAsync(String apiId, final ApiCallback<GraphQLQueryComplexityInfoDTO> _callback) throws ApiException {

        okhttp3.Call localVarCall = apisApiIdGraphqlPoliciesComplexityGetValidateBeforeCall(apiId, _callback);
        Type localVarReturnType = new TypeToken<GraphQLQueryComplexityInfoDTO>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for apisApiIdGraphqlPoliciesComplexityTypesGet
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Types and fields returned successfully.  </td><td>  * Content-Type - The content of the body.  <br>  </td></tr>
        <tr><td> 404 </td><td> Not Found. Retrieving types and fields failed.  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call apisApiIdGraphqlPoliciesComplexityTypesGetCall(String apiId, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/apis/{apiId}/graphql-policies/complexity/types"
            .replaceAll("\\{" + "apiId" + "\\}", localVarApiClient.escapeString(apiId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "OAuth2Security" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call apisApiIdGraphqlPoliciesComplexityTypesGetValidateBeforeCall(String apiId, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'apiId' is set
        if (apiId == null) {
            throw new ApiException("Missing the required parameter 'apiId' when calling apisApiIdGraphqlPoliciesComplexityTypesGet(Async)");
        }
        

        okhttp3.Call localVarCall = apisApiIdGraphqlPoliciesComplexityTypesGetCall(apiId, _callback);
        return localVarCall;

    }

    /**
     * Retrieve Types and Fields of a GraphQL Schema
     * This operation can be used to retrieve all types and fields of the GraphQL Schema by providing the API id. 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @return GraphQLSchemaTypeListDTO
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Types and fields returned successfully.  </td><td>  * Content-Type - The content of the body.  <br>  </td></tr>
        <tr><td> 404 </td><td> Not Found. Retrieving types and fields failed.  </td><td>  -  </td></tr>
     </table>
     */
    public GraphQLSchemaTypeListDTO apisApiIdGraphqlPoliciesComplexityTypesGet(String apiId) throws ApiException {
        ApiResponse<GraphQLSchemaTypeListDTO> localVarResp = apisApiIdGraphqlPoliciesComplexityTypesGetWithHttpInfo(apiId);
        return localVarResp.getData();
    }

    /**
     * Retrieve Types and Fields of a GraphQL Schema
     * This operation can be used to retrieve all types and fields of the GraphQL Schema by providing the API id. 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @return ApiResponse&lt;GraphQLSchemaTypeListDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Types and fields returned successfully.  </td><td>  * Content-Type - The content of the body.  <br>  </td></tr>
        <tr><td> 404 </td><td> Not Found. Retrieving types and fields failed.  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<GraphQLSchemaTypeListDTO> apisApiIdGraphqlPoliciesComplexityTypesGetWithHttpInfo(String apiId) throws ApiException {
        okhttp3.Call localVarCall = apisApiIdGraphqlPoliciesComplexityTypesGetValidateBeforeCall(apiId, null);
        Type localVarReturnType = new TypeToken<GraphQLSchemaTypeListDTO>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve Types and Fields of a GraphQL Schema (asynchronously)
     * This operation can be used to retrieve all types and fields of the GraphQL Schema by providing the API id. 
     * @param apiId **API ID** consisting of the **UUID** of the API.  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Types and fields returned successfully.  </td><td>  * Content-Type - The content of the body.  <br>  </td></tr>
        <tr><td> 404 </td><td> Not Found. Retrieving types and fields failed.  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call apisApiIdGraphqlPoliciesComplexityTypesGetAsync(String apiId, final ApiCallback<GraphQLSchemaTypeListDTO> _callback) throws ApiException {

        okhttp3.Call localVarCall = apisApiIdGraphqlPoliciesComplexityTypesGetValidateBeforeCall(apiId, _callback);
        Type localVarReturnType = new TypeToken<GraphQLSchemaTypeListDTO>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
