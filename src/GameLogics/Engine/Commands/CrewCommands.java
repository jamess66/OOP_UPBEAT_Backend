package GameLogics.Engine.Commands;

import AST.Node.Exec;
import ConstructionParser.ConsParser;
import ConstructionParser.Parser;
import ConstructionParser.Tokenizer.ConsTokenizer;
import GameLogics.Engine.GameCommands;
import GameLogics.Engine.PlayerInstance;
import GameLogics.Player.Player;
import GameLogics.Region.Region;
import GameLogics.Region.Territory;
import GameLogics.Utility;

import java.io.IOException;
import java.util.*;

import static ConstructionParser.Tokenizer.ReadConstructionPlan.forTestingConstructionPlan;
public class CrewCommands implements PlayerInstance {
    private final Player player;
    private final Territory territory;
    private String constructionPlan;//asdasdasdadasdasdas

    private List<Exec> parsedExec;

    public Map<String, Long> boundVar;

    public CrewCommands(Player player, Territory territory){
        this.player = player;
        this.territory = territory;
        constructionPlan = "done";
        Parser parser = new ConsParser(new ConsTokenizer(constructionPlan));
        parsedExec = parser.Parse();
        boundVar = new IdentifierHashMap();
    }

    @Override
    public void actionExecute(){
        Parser parser = new ConsParser(new ConsTokenizer(constructionPlan));
        parsedExec = parser.Parse();
        boolean isExecutable = true;
        for(Exec exec : parsedExec){
            if(isExecutable){
                isExecutable = exec.execute(this);
            }
        }
    }

    @Override
    public void newConstructionPlan(){
        try {
            constructionPlan = forTestingConstructionPlan();
        } catch (IOException e) {
            constructionPlan = "";
        }
    }

    @Override
    public void newConstructionPlan(String string){
            constructionPlan = string;
    }

    @Override
    public boolean attack(Utility.Direction dir, long v) {
        return GameCommands.shoot(territory, player, dir, v);
    }

    @Override
    public boolean collect(long v) {
        return GameCommands.collect(player, v);
    }

    @Override
    public boolean invest(long eval) {
        return GameCommands.invest(territory, player, eval);
    }

    @Override
    public boolean relocate() {
        return GameCommands.relocate(player);
    }

    @Override
    public long nearby(Utility.Direction dir) {
        return InformationExpression.nearby(territory, player, dir);
    }

    @Override
    public boolean move(Utility.Direction dir) {
        return GameCommands.move(territory, player, dir);
    }

    @Override
    public long opponent() {
        return InformationExpression.opponent(territory, player);
    }

    @Override
    public long getCurrentRow() {
        return player.getCurrentRegion().getRegionX();
    }

    @Override
    public long getCurrentCol() {
        return player.getCurrentRegion().getRegionY();
    }

    @Override
    public long getCityCenterRow() {
        return player.getCityCenter().getRegionX();
    }

    @Override
    public long getCityCenterCol() {
        return player.getCityCenter().getRegionY();
    }

    @Override
    public long getBudget() {
        return player.getBudget();
    }

    @Override
    public long getRandomVal() {
        Random random = new Random();
        return random.nextLong(0,999) ;
    }

    @Override
    public Territory getTerritory() {
        return territory;
    }

    @Override
    public Region getCurrentRegion() {
        return player.getCurrentRegion();
    }

    @Override
    public Region getCityCenter() {
        return player.getCityCenter();
    }

    @Override
    public Map<String, Long> getIdentifiers() {
        return boundVar;
    }

}
