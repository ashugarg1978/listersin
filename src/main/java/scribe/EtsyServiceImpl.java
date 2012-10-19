package org.scribe.builder.api;

import java.util.Map;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuth10aServiceImpl;
import org.scribe.utils.MapUtils;

/**
 * Scribe OAuth10aServiceImpl class rewritten to fit the Etsy API.
 */
public class EtsyServiceImpl extends OAuth10aServiceImpl {
	private OAuthConfig config;
	private DefaultApi10a api;
	
	/**
	 * {@inheritDoc}
	 */
	public EtsyServiceImpl(DefaultApi10a api, OAuthConfig config) {
		super(api, config);
		this.api = api;
		this.config = config;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Token getRequestToken(){
		config.log("obtaining request token from " + api.getRequestTokenEndpoint());
		
		String requestTokenEndpoint = api.getRequestTokenEndpoint();
		if(config.hasScope()){
			requestTokenEndpoint = requestTokenEndpoint + "?scope=" + config.getScope();
		}
		
	    OAuthRequest request = new OAuthRequest(api.getRequestTokenVerb(), requestTokenEndpoint);

	    config.log("setting oauth_callback to " + config.getCallback());
	    request.addOAuthParameter(OAuthConstants.CALLBACK, config.getCallback());
	    addOAuthParams(request, OAuthConstants.EMPTY_TOKEN);
	    appendSignature(request);

	    config.log("sending request...");
	    Response response = request.send();
	    String body = response.getBody();

	    config.log("response status code: " + response.getCode());
	    config.log("response body: " + body);
	    return api.getRequestTokenExtractor().extract(body);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Token getAccessToken(Token requestToken, Verifier verifier)
	  {
	    config.log("obtaining access token from " + api.getAccessTokenEndpoint());
	    
	    String accessTokenEndpoint = api.getAccessTokenEndpoint();
		if(config.hasScope()){
			accessTokenEndpoint = accessTokenEndpoint + "?scope=" + config.getScope();
		}
	    
	    OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(), accessTokenEndpoint);
	    request.addOAuthParameter(OAuthConstants.TOKEN, requestToken.getToken());
	    request.addOAuthParameter(OAuthConstants.VERIFIER, verifier.getValue());

	    config.log("setting token to: " + requestToken + " and verifier to: " + verifier);
	    addOAuthParams(request, requestToken);
	    appendSignature(request);
	    Response response = request.send();
	    return api.getAccessTokenExtractor().extract(response.getBody());
	  }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void signRequest(Token token, OAuthRequest request)
	  {
		if(token != null){
		    config.log("signing request: " + request.getCompleteUrl());
		    request.addOAuthParameter(OAuthConstants.TOKEN, token.getToken());
	
		    config.log("setting token to: " + token);
		    addOAuthParams(request, token);
		    appendSignature(request);
		}
	  }
	
	private void addOAuthParams(OAuthRequest request, Token token)
	  {
	    request.addOAuthParameter(OAuthConstants.TIMESTAMP, api.getTimestampService().getTimestampInSeconds());
	    request.addOAuthParameter(OAuthConstants.NONCE, api.getTimestampService().getNonce());
	    request.addOAuthParameter(OAuthConstants.CONSUMER_KEY, config.getApiKey());
	    request.addOAuthParameter(OAuthConstants.SIGN_METHOD, api.getSignatureService().getSignatureMethod());
	    request.addOAuthParameter(OAuthConstants.VERSION, getVersion());
	    request.addOAuthParameter(OAuthConstants.SIGNATURE, getSignature(request, token));

	    config.log("appended additional OAuth parameters: " + MapUtils.toString(request.getOauthParameters()));
	  }
	
	private String getSignature(OAuthRequest request, Token token)
	  {
	    config.log("generating signature...");
	    String baseString = api.getBaseStringExtractor().extract(request);
	    String signature = api.getSignatureService().getSignature(baseString, config.getApiSecret(), token.getSecret());

	    config.log("base string is: " + baseString);
	    config.log("signature is: " + signature);
	    return signature;
	  }
	
	private void appendSignature(OAuthRequest request)
	  {
	    switch (config.getSignatureType())
	    {
	      case Header:
	        config.log("using Http Header signature");

	        String oauthHeader = api.getHeaderExtractor().extract(request);
	        request.addHeader(OAuthConstants.HEADER, oauthHeader);
	        break;
	      case QueryString:
	        config.log("using Querystring signature");

	        for (Map.Entry<String, String> entry : request.getOauthParameters().entrySet())
	        {
	          request.addQuerystringParameter(entry.getKey(), entry.getValue());
	        }
	        break;
	    }
	  }

}
