package example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import javax.websocket.Session;

import static com.hangole.game.common.Player.getPlayerEqualSession;

/**
 * Created by KwonJH on 2017-04-10.
 */
public class JsonTest {

    @Test
    public static String getPositionAsJSON(Session session){
        JSONObject message = new JSONObject();
        JSONArray array = new JSONArray();
        message.put("type","Position");
        message.put("x",getPlayerEqualSession(session).getPositionX());
        message.put("y",getPlayerEqualSession(session).getPositionY());

        return message.toString();
    }
}
