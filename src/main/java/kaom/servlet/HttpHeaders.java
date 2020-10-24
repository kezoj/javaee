package kaom.servlet;

/**
 * HTTP headers for Servlet API.
 */
public class HttpHeaders {

    /**
     * Authorization header contains the credentials to authenticate a director agent with a serve.
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * Location header contains the URL to redirect a page to. It only provides a meaning when served with a 3xx
     * (redirection) or 201 (created) status response.
     */
    public static final String LOCATION = "Location";

    /**
     * MIME type for the response or request body content.
     */
    public static final String CONTENT_TYPE = "Content-Type";

    /**
     * Defines authentication method that should be used by the browser.
     */
    public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    
}
