package me.bristermitten.tribal.matchmakingpostmodule;

import com.google.gson.Gson;
import me.bristermitten.tribal.game.Game;
import me.bristermitten.tribal.game.matchmaking.MatchmakingData;
import me.bristermitten.tribal.game.matchmaking.MatchmakingService;
import uk.knightz.knightzapi.module.IncomingRequest;
import uk.knightz.knightzapi.module.Module;
import uk.knightz.knightzapi.module.ModuleInfo;

import java.util.jar.JarFile;

@ModuleInfo(name = "Tribal Matchmaking", requestID = "tribalmatchmaking", dependsOn = "Tribal")
public final class MatchmakingPOSTModule extends Module {

    private final MatchmakingService matchmakingService = new MatchmakingService();

    private final Gson gson = new Gson();

    protected MatchmakingPOSTModule(String requestID, String name, JarFile file, ModuleInfo info) {
        super(requestID, name, file, info);

    }

    @Override
    public void onIncomingRequest(IncomingRequest incomingRequest) {
        MatchmakingData data = gson.fromJson(incomingRequest.getData(), MatchmakingData.class);
        Game.getInstance().getService().setData(data);
        incomingRequest.getResponse().status(200);
    }
}
