/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2013-11-22 19:59:01 UTC)
 * on 2013-12-01 at 02:38:44 UTC 
 * Modify at your own risk.
 */

package com.appspot.dktttweb.tictactoe;

/**
 * Service definition for Tictactoe (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link TictactoeRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Tictactoe extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.16.0-rc of the tictactoe library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://dktttweb.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "tictactoe/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Tictactoe(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Tictactoe(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * An accessor for creating requests from the Board collection.
   *
   * <p>The typical use is:</p>
   * <pre>
   *   {@code Tictactoe tictactoe = new Tictactoe(...);}
   *   {@code Tictactoe.Board.List request = tictactoe.board().list(parameters ...)}
   * </pre>
   *
   * @return the resource collection
   */
  public Board board() {
    return new Board();
  }

  /**
   * The "board" collection of methods.
   */
  public class Board {

    /**
     * Create a request for the method "board.create".
     *
     * This request holds the parameters needed by the the tictactoe server.  After setting any optional
     * parameters, call the {@link Create#execute()} method to invoke the remote operation.
     *
     * @return the request
     */
    public Create create() throws java.io.IOException {
      Create result = new Create();
      initialize(result);
      return result;
    }

    public class Create extends TictactoeRequest<Void> {

      private static final String REST_PATH = "createBoard";

      /**
       * Create a request for the method "board.create".
       *
       * This request holds the parameters needed by the the tictactoe server.  After setting any
       * optional parameters, call the {@link Create#execute()} method to invoke the remote operation.
       * <p> {@link
       * Create#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must
       * be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @since 1.13
       */
      protected Create() {
        super(Tictactoe.this, "POST", REST_PATH, null, Void.class);
      }

      @Override
      public Create setAlt(java.lang.String alt) {
        return (Create) super.setAlt(alt);
      }

      @Override
      public Create setFields(java.lang.String fields) {
        return (Create) super.setFields(fields);
      }

      @Override
      public Create setKey(java.lang.String key) {
        return (Create) super.setKey(key);
      }

      @Override
      public Create setOauthToken(java.lang.String oauthToken) {
        return (Create) super.setOauthToken(oauthToken);
      }

      @Override
      public Create setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (Create) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public Create setQuotaUser(java.lang.String quotaUser) {
        return (Create) super.setQuotaUser(quotaUser);
      }

      @Override
      public Create setUserIp(java.lang.String userIp) {
        return (Create) super.setUserIp(userIp);
      }

      @Override
      public Create set(String parameterName, Object value) {
        return (Create) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "board.gamecreate".
     *
     * This request holds the parameters needed by the the tictactoe server.  After setting any optional
     * parameters, call the {@link Gamecreate#execute()} method to invoke the remote operation.
     *
     * @return the request
     */
    public Gamecreate gamecreate() throws java.io.IOException {
      Gamecreate result = new Gamecreate();
      initialize(result);
      return result;
    }

    public class Gamecreate extends TictactoeRequest<com.appspot.dktttweb.tictactoe.model.MyToken> {

      private static final String REST_PATH = "newGame";

      /**
       * Create a request for the method "board.gamecreate".
       *
       * This request holds the parameters needed by the the tictactoe server.  After setting any
       * optional parameters, call the {@link Gamecreate#execute()} method to invoke the remote
       * operation. <p> {@link
       * Gamecreate#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @since 1.13
       */
      protected Gamecreate() {
        super(Tictactoe.this, "POST", REST_PATH, null, com.appspot.dktttweb.tictactoe.model.MyToken.class);
      }

      @Override
      public Gamecreate setAlt(java.lang.String alt) {
        return (Gamecreate) super.setAlt(alt);
      }

      @Override
      public Gamecreate setFields(java.lang.String fields) {
        return (Gamecreate) super.setFields(fields);
      }

      @Override
      public Gamecreate setKey(java.lang.String key) {
        return (Gamecreate) super.setKey(key);
      }

      @Override
      public Gamecreate setOauthToken(java.lang.String oauthToken) {
        return (Gamecreate) super.setOauthToken(oauthToken);
      }

      @Override
      public Gamecreate setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (Gamecreate) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public Gamecreate setQuotaUser(java.lang.String quotaUser) {
        return (Gamecreate) super.setQuotaUser(quotaUser);
      }

      @Override
      public Gamecreate setUserIp(java.lang.String userIp) {
        return (Gamecreate) super.setUserIp(userIp);
      }

      @Override
      public Gamecreate set(String parameterName, Object value) {
        return (Gamecreate) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "board.getUserShips".
     *
     * This request holds the parameters needed by the the tictactoe server.  After setting any optional
     * parameters, call the {@link GetUserShips#execute()} method to invoke the remote operation.
     *
     * @return the request
     */
    public GetUserShips getUserShips() throws java.io.IOException {
      GetUserShips result = new GetUserShips();
      initialize(result);
      return result;
    }

    public class GetUserShips extends TictactoeRequest<Void> {

      private static final String REST_PATH = "void";

      /**
       * Create a request for the method "board.getUserShips".
       *
       * This request holds the parameters needed by the the tictactoe server.  After setting any
       * optional parameters, call the {@link GetUserShips#execute()} method to invoke the remote
       * operation. <p> {@link
       * GetUserShips#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @since 1.13
       */
      protected GetUserShips() {
        super(Tictactoe.this, "GET", REST_PATH, null, Void.class);
      }

      @Override
      public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
        return super.executeUsingHead();
      }

      @Override
      public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
        return super.buildHttpRequestUsingHead();
      }

      @Override
      public GetUserShips setAlt(java.lang.String alt) {
        return (GetUserShips) super.setAlt(alt);
      }

      @Override
      public GetUserShips setFields(java.lang.String fields) {
        return (GetUserShips) super.setFields(fields);
      }

      @Override
      public GetUserShips setKey(java.lang.String key) {
        return (GetUserShips) super.setKey(key);
      }

      @Override
      public GetUserShips setOauthToken(java.lang.String oauthToken) {
        return (GetUserShips) super.setOauthToken(oauthToken);
      }

      @Override
      public GetUserShips setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (GetUserShips) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public GetUserShips setQuotaUser(java.lang.String quotaUser) {
        return (GetUserShips) super.setQuotaUser(quotaUser);
      }

      @Override
      public GetUserShips setUserIp(java.lang.String userIp) {
        return (GetUserShips) super.setUserIp(userIp);
      }

      @Override
      public GetUserShips set(String parameterName, Object value) {
        return (GetUserShips) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "board.getchannel".
     *
     * This request holds the parameters needed by the the tictactoe server.  After setting any optional
     * parameters, call the {@link Getchannel#execute()} method to invoke the remote operation.
     *
     * @return the request
     */
    public Getchannel getchannel() throws java.io.IOException {
      Getchannel result = new Getchannel();
      initialize(result);
      return result;
    }

    public class Getchannel extends TictactoeRequest<com.appspot.dktttweb.tictactoe.model.MyToken> {

      private static final String REST_PATH = "mytoken";

      /**
       * Create a request for the method "board.getchannel".
       *
       * This request holds the parameters needed by the the tictactoe server.  After setting any
       * optional parameters, call the {@link Getchannel#execute()} method to invoke the remote
       * operation. <p> {@link
       * Getchannel#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @since 1.13
       */
      protected Getchannel() {
        super(Tictactoe.this, "GET", REST_PATH, null, com.appspot.dktttweb.tictactoe.model.MyToken.class);
      }

      @Override
      public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
        return super.executeUsingHead();
      }

      @Override
      public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
        return super.buildHttpRequestUsingHead();
      }

      @Override
      public Getchannel setAlt(java.lang.String alt) {
        return (Getchannel) super.setAlt(alt);
      }

      @Override
      public Getchannel setFields(java.lang.String fields) {
        return (Getchannel) super.setFields(fields);
      }

      @Override
      public Getchannel setKey(java.lang.String key) {
        return (Getchannel) super.setKey(key);
      }

      @Override
      public Getchannel setOauthToken(java.lang.String oauthToken) {
        return (Getchannel) super.setOauthToken(oauthToken);
      }

      @Override
      public Getchannel setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (Getchannel) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public Getchannel setQuotaUser(java.lang.String quotaUser) {
        return (Getchannel) super.setQuotaUser(quotaUser);
      }

      @Override
      public Getchannel setUserIp(java.lang.String userIp) {
        return (Getchannel) super.setUserIp(userIp);
      }

      @Override
      public Getchannel set(String parameterName, Object value) {
        return (Getchannel) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "board.gethit".
     *
     * This request holds the parameters needed by the the tictactoe server.  After setting any optional
     * parameters, call the {@link Gethit#execute()} method to invoke the remote operation.
     *
     * @param content the {@link com.appspot.dktttweb.tictactoe.model.ComputerBoard}
     * @return the request
     */
    public Gethit gethit(com.appspot.dktttweb.tictactoe.model.ComputerBoard content) throws java.io.IOException {
      Gethit result = new Gethit(content);
      initialize(result);
      return result;
    }

    public class Gethit extends TictactoeRequest<com.appspot.dktttweb.tictactoe.model.ComputerBoard> {

      private static final String REST_PATH = "computerboard";

      /**
       * Create a request for the method "board.gethit".
       *
       * This request holds the parameters needed by the the tictactoe server.  After setting any
       * optional parameters, call the {@link Gethit#execute()} method to invoke the remote operation.
       * <p> {@link
       * Gethit#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must
       * be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.appspot.dktttweb.tictactoe.model.ComputerBoard}
       * @since 1.13
       */
      protected Gethit(com.appspot.dktttweb.tictactoe.model.ComputerBoard content) {
        super(Tictactoe.this, "POST", REST_PATH, content, com.appspot.dktttweb.tictactoe.model.ComputerBoard.class);
      }

      @Override
      public Gethit setAlt(java.lang.String alt) {
        return (Gethit) super.setAlt(alt);
      }

      @Override
      public Gethit setFields(java.lang.String fields) {
        return (Gethit) super.setFields(fields);
      }

      @Override
      public Gethit setKey(java.lang.String key) {
        return (Gethit) super.setKey(key);
      }

      @Override
      public Gethit setOauthToken(java.lang.String oauthToken) {
        return (Gethit) super.setOauthToken(oauthToken);
      }

      @Override
      public Gethit setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (Gethit) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public Gethit setQuotaUser(java.lang.String quotaUser) {
        return (Gethit) super.setQuotaUser(quotaUser);
      }

      @Override
      public Gethit setUserIp(java.lang.String userIp) {
        return (Gethit) super.setUserIp(userIp);
      }

      @Override
      public Gethit set(String parameterName, Object value) {
        return (Gethit) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "board.getmove".
     *
     * This request holds the parameters needed by the the tictactoe server.  After setting any optional
     * parameters, call the {@link Getmove#execute()} method to invoke the remote operation.
     *
     * @param content the {@link com.appspot.dktttweb.tictactoe.model.Board}
     * @return the request
     */
    public Getmove getmove(com.appspot.dktttweb.tictactoe.model.Board content) throws java.io.IOException {
      Getmove result = new Getmove(content);
      initialize(result);
      return result;
    }

    public class Getmove extends TictactoeRequest<com.appspot.dktttweb.tictactoe.model.Board> {

      private static final String REST_PATH = "board";

      /**
       * Create a request for the method "board.getmove".
       *
       * This request holds the parameters needed by the the tictactoe server.  After setting any
       * optional parameters, call the {@link Getmove#execute()} method to invoke the remote operation.
       * <p> {@link
       * Getmove#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must
       * be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.appspot.dktttweb.tictactoe.model.Board}
       * @since 1.13
       */
      protected Getmove(com.appspot.dktttweb.tictactoe.model.Board content) {
        super(Tictactoe.this, "POST", REST_PATH, content, com.appspot.dktttweb.tictactoe.model.Board.class);
      }

      @Override
      public Getmove setAlt(java.lang.String alt) {
        return (Getmove) super.setAlt(alt);
      }

      @Override
      public Getmove setFields(java.lang.String fields) {
        return (Getmove) super.setFields(fields);
      }

      @Override
      public Getmove setKey(java.lang.String key) {
        return (Getmove) super.setKey(key);
      }

      @Override
      public Getmove setOauthToken(java.lang.String oauthToken) {
        return (Getmove) super.setOauthToken(oauthToken);
      }

      @Override
      public Getmove setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (Getmove) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public Getmove setQuotaUser(java.lang.String quotaUser) {
        return (Getmove) super.setQuotaUser(quotaUser);
      }

      @Override
      public Getmove setUserIp(java.lang.String userIp) {
        return (Getmove) super.setUserIp(userIp);
      }

      @Override
      public Getmove set(String parameterName, Object value) {
        return (Getmove) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "board.getusermove".
     *
     * This request holds the parameters needed by the the tictactoe server.  After setting any optional
     * parameters, call the {@link Getusermove#execute()} method to invoke the remote operation.
     *
     * @param content the {@link com.appspot.dktttweb.tictactoe.model.MyToken}
     * @return the request
     */
    public Getusermove getusermove(com.appspot.dktttweb.tictactoe.model.MyToken content) throws java.io.IOException {
      Getusermove result = new Getusermove(content);
      initialize(result);
      return result;
    }

    public class Getusermove extends TictactoeRequest<com.appspot.dktttweb.tictactoe.model.MyToken> {

      private static final String REST_PATH = "mytoken";

      /**
       * Create a request for the method "board.getusermove".
       *
       * This request holds the parameters needed by the the tictactoe server.  After setting any
       * optional parameters, call the {@link Getusermove#execute()} method to invoke the remote
       * operation. <p> {@link
       * Getusermove#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.appspot.dktttweb.tictactoe.model.MyToken}
       * @since 1.13
       */
      protected Getusermove(com.appspot.dktttweb.tictactoe.model.MyToken content) {
        super(Tictactoe.this, "POST", REST_PATH, content, com.appspot.dktttweb.tictactoe.model.MyToken.class);
      }

      @Override
      public Getusermove setAlt(java.lang.String alt) {
        return (Getusermove) super.setAlt(alt);
      }

      @Override
      public Getusermove setFields(java.lang.String fields) {
        return (Getusermove) super.setFields(fields);
      }

      @Override
      public Getusermove setKey(java.lang.String key) {
        return (Getusermove) super.setKey(key);
      }

      @Override
      public Getusermove setOauthToken(java.lang.String oauthToken) {
        return (Getusermove) super.setOauthToken(oauthToken);
      }

      @Override
      public Getusermove setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (Getusermove) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public Getusermove setQuotaUser(java.lang.String quotaUser) {
        return (Getusermove) super.setQuotaUser(quotaUser);
      }

      @Override
      public Getusermove setUserIp(java.lang.String userIp) {
        return (Getusermove) super.setUserIp(userIp);
      }

      @Override
      public Getusermove set(String parameterName, Object value) {
        return (Getusermove) super.set(parameterName, value);
      }
    }

  }

  /**
   * An accessor for creating requests from the Scores collection.
   *
   * <p>The typical use is:</p>
   * <pre>
   *   {@code Tictactoe tictactoe = new Tictactoe(...);}
   *   {@code Tictactoe.Scores.List request = tictactoe.scores().list(parameters ...)}
   * </pre>
   *
   * @return the resource collection
   */
  public Scores scores() {
    return new Scores();
  }

  /**
   * The "scores" collection of methods.
   */
  public class Scores {

    /**
     * Create a request for the method "scores.insert".
     *
     * This request holds the parameters needed by the the tictactoe server.  After setting any optional
     * parameters, call the {@link Insert#execute()} method to invoke the remote operation.
     *
     * @param content the {@link com.appspot.dktttweb.tictactoe.model.Score}
     * @return the request
     */
    public Insert insert(com.appspot.dktttweb.tictactoe.model.Score content) throws java.io.IOException {
      Insert result = new Insert(content);
      initialize(result);
      return result;
    }

    public class Insert extends TictactoeRequest<com.appspot.dktttweb.tictactoe.model.Score> {

      private static final String REST_PATH = "score";

      /**
       * Create a request for the method "scores.insert".
       *
       * This request holds the parameters needed by the the tictactoe server.  After setting any
       * optional parameters, call the {@link Insert#execute()} method to invoke the remote operation.
       * <p> {@link
       * Insert#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must
       * be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.appspot.dktttweb.tictactoe.model.Score}
       * @since 1.13
       */
      protected Insert(com.appspot.dktttweb.tictactoe.model.Score content) {
        super(Tictactoe.this, "POST", REST_PATH, content, com.appspot.dktttweb.tictactoe.model.Score.class);
      }

      @Override
      public Insert setAlt(java.lang.String alt) {
        return (Insert) super.setAlt(alt);
      }

      @Override
      public Insert setFields(java.lang.String fields) {
        return (Insert) super.setFields(fields);
      }

      @Override
      public Insert setKey(java.lang.String key) {
        return (Insert) super.setKey(key);
      }

      @Override
      public Insert setOauthToken(java.lang.String oauthToken) {
        return (Insert) super.setOauthToken(oauthToken);
      }

      @Override
      public Insert setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (Insert) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public Insert setQuotaUser(java.lang.String quotaUser) {
        return (Insert) super.setQuotaUser(quotaUser);
      }

      @Override
      public Insert setUserIp(java.lang.String userIp) {
        return (Insert) super.setUserIp(userIp);
      }

      @Override
      public Insert set(String parameterName, Object value) {
        return (Insert) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "scores.list".
     *
     * This request holds the parameters needed by the the tictactoe server.  After setting any optional
     * parameters, call the {@link List#execute()} method to invoke the remote operation.
     *
     * @return the request
     */
    public List list() throws java.io.IOException {
      List result = new List();
      initialize(result);
      return result;
    }

    public class List extends TictactoeRequest<com.appspot.dktttweb.tictactoe.model.ScoreCollection> {

      private static final String REST_PATH = "score";

      /**
       * Create a request for the method "scores.list".
       *
       * This request holds the parameters needed by the the tictactoe server.  After setting any
       * optional parameters, call the {@link List#execute()} method to invoke the remote operation. <p>
       * {@link List#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @since 1.13
       */
      protected List() {
        super(Tictactoe.this, "GET", REST_PATH, null, com.appspot.dktttweb.tictactoe.model.ScoreCollection.class);
      }

      @Override
      public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
        return super.executeUsingHead();
      }

      @Override
      public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
        return super.buildHttpRequestUsingHead();
      }

      @Override
      public List setAlt(java.lang.String alt) {
        return (List) super.setAlt(alt);
      }

      @Override
      public List setFields(java.lang.String fields) {
        return (List) super.setFields(fields);
      }

      @Override
      public List setKey(java.lang.String key) {
        return (List) super.setKey(key);
      }

      @Override
      public List setOauthToken(java.lang.String oauthToken) {
        return (List) super.setOauthToken(oauthToken);
      }

      @Override
      public List setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (List) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public List setQuotaUser(java.lang.String quotaUser) {
        return (List) super.setQuotaUser(quotaUser);
      }

      @Override
      public List setUserIp(java.lang.String userIp) {
        return (List) super.setUserIp(userIp);
      }

      @com.google.api.client.util.Key
      private java.lang.String limit;

      /**

       */
      public java.lang.String getLimit() {
        return limit;
      }

      public List setLimit(java.lang.String limit) {
        this.limit = limit;
        return this;
      }

      @com.google.api.client.util.Key
      private java.lang.String order;

      /**

       */
      public java.lang.String getOrder() {
        return order;
      }

      public List setOrder(java.lang.String order) {
        this.order = order;
        return this;
      }

      @Override
      public List set(String parameterName, Object value) {
        return (List) super.set(parameterName, value);
      }
    }

  }

  /**
   * Builder for {@link Tictactoe}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Tictactoe}. */
    @Override
    public Tictactoe build() {
      return new Tictactoe(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link TictactoeRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setTictactoeRequestInitializer(
        TictactoeRequestInitializer tictactoeRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(tictactoeRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
