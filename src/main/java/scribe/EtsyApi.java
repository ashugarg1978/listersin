package org.scribe.builder.api;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class EtsyApi extends DefaultApi10a 
{
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAccessTokenEndpoint(){
		return "http://openapi.etsy.com/v2/oauth/access_token";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRequestTokenEndpoint(){
		return "http://openapi.etsy.com/v2/oauth/request_token";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAuthorizationUrl(Token requestToken){
		return "http://www.etsy.com/oauth/signin?oauth_token=" + requestToken.getToken();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Verb getAccessTokenVerb(){
		return Verb.POST;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Verb getRequestTokenVerb(){
		return Verb.POST;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public OAuthService createService(OAuthConfig config){
		return new EtsyServiceImpl(this, config);
	}
}
