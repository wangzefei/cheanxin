package cheanxin.global;

import cheanxin.domain.OAuth2Client;

/**
 * Created by 273cn on 16/12/24.
 */
public class Constants {
    // string array separator
    public static final String STRING_ARRAY_SEPARATOR = ",";

    // department's max level
    public static final long DEPT_MAX_LEVEL = 5;

    // password secret
    public static final String PASSWORD_SECRET = "7EsF+0BCtNRW1hLtf39QLDQq5G+4Eh1/euW4azW7Qux";

    // environment constants
    public static final String DEV = "dev";
    public static final String QA = "qa";
    public static final String SIM = "sim";
    public static final String PROD = "prod";

    // query constants
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "10";

    // security constants
    public static final String REALM = "cheanxin";
    public static final String RESOURCE_ID = "cheanxin_rest_resource";

    // authenticated clients
    public static final OAuth2Client[] O_AUTH2_CLIENTS = {
        new OAuth2Client(
                "cheanxin_client",
                "ampnvaUQrmQj7r9a6f94ltjCuzqY7jcvX",
                new String[]{"password", "refresh_token"},
                new String[]{"CLIENT"},
                new String[]{"read", "write"},
                3600,
                86400 * 30)
    };
}
