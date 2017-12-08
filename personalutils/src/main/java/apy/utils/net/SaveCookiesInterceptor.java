package apy.utils.net;

import java.io.IOException;
import java.util.HashSet;

import apy.utils.utils.ASPUtils;
import apy.utils.utils.Constants;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/11/30.
 */

public class SaveCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
            ASPUtils.putStringSet(Constants.PREF_COOKIES,cookies);
        }
        return originalResponse;
    }
}
