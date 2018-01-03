package tradinggames.com.tradinggames.util;

/**
 * Created by toppeiradasgalaxias on 15/11/2017.
 */

public class Constantes {

    public static final String API_URL = "https://tradinggames-api.herokuapp.com";

    public static class Login {
        public static final String LOGIN_URL = Constantes.API_URL + "/user/login";
    }

    public static class Register {
        public static final String REGISTER_URL = Constantes.API_URL + "/user/register";
    }

    public static class Anuncios {
        public static final String REGISTER_URL = Constantes.API_URL + "/post/insert";
        public static final String SEARCH_URL = Constantes.API_URL + "/post/search";
        public static final String LOAD_FILE = Constantes.API_URL + "/post/loadFile";
        public static final String UPLOAD_FILE = Constantes.API_URL + "/post/upload";

    }

}
